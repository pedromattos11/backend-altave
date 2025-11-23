package br.com.altave.backend_altave.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filtro de Rate Limiting para prevenir abuso da API.
 * 
 * Implementa limite de requisições por IP usando algoritmo Token Bucket.
 * 
 * Limites configurados:
 * - 100 requisições por minuto por IP
 * - 1000 requisições por hora por IP
 * 
 * Headers retornados:
 * - X-RateLimit-Limit: Número máximo de requisições permitidas
 * - X-RateLimit-Remaining: Número de requisições restantes
 * - X-RateLimit-Reset: Tempo até o reset do limite (em segundos)
 */
@Component
public class RateLimitFilter implements Filter {

    // Armazena um bucket por IP
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    // Limite: 100 requisições por minuto
    private static final int REQUESTS_PER_MINUTE = 100;
    
    // Limite: 1000 requisições por hora (backup)
    private static final int REQUESTS_PER_HOUR = 1000;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Obter IP do cliente (considera proxies)
        String clientIp = getClientIP(httpRequest);
        
        // Obter ou criar bucket para este IP
        Bucket bucket = resolveBucket(clientIp);
        
        // Tentar consumir 1 token
        if (bucket.tryConsume(1)) {
            // Adicionar headers informativos
            long availableTokens = bucket.getAvailableTokens();
            httpResponse.setHeader("X-RateLimit-Limit", String.valueOf(REQUESTS_PER_MINUTE));
            httpResponse.setHeader("X-RateLimit-Remaining", String.valueOf(availableTokens));
            
            // Requisição permitida, continuar a cadeia
            chain.doFilter(request, response);
        } else {
            // Rate limit excedido
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpResponse.setHeader("X-RateLimit-Limit", String.valueOf(REQUESTS_PER_MINUTE));
            httpResponse.setHeader("X-RateLimit-Remaining", "0");
            httpResponse.setHeader("X-RateLimit-Reset", "60"); // Reset em 60 segundos
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                "{\"error\": \"Too many requests\", " +
                "\"message\": \"Rate limit exceeded. Maximum " + REQUESTS_PER_MINUTE + " requests per minute allowed.\", " +
                "\"retryAfter\": 60}"
            );
        }
    }

    /**
     * Cria ou recupera um bucket para o IP específico
     */
    private Bucket resolveBucket(String clientIp) {
        return cache.computeIfAbsent(clientIp, k -> createNewBucket());
    }

    /**
     * Cria um novo bucket com os limites configurados
     */
    private Bucket createNewBucket() {
        // Limite 1: 100 requisições por minuto (refill de 100 tokens a cada 1 minuto)
        Bandwidth minuteLimit = Bandwidth.classic(
            REQUESTS_PER_MINUTE,
            Refill.intervally(REQUESTS_PER_MINUTE, Duration.ofMinutes(1))
        );
        
        // Limite 2: 1000 requisições por hora (backup, mais generoso)
        Bandwidth hourLimit = Bandwidth.classic(
            REQUESTS_PER_HOUR,
            Refill.intervally(REQUESTS_PER_HOUR, Duration.ofHours(1))
        );
        
        return Bucket.builder()
            .addLimit(minuteLimit)
            .addLimit(hourLimit)
            .build();
    }

    /**
     * Obtém o IP real do cliente, considerando proxies e load balancers
     */
    private String getClientIP(HttpServletRequest request) {
        // Tentar obter IP real através de headers de proxy
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null && !xfHeader.isEmpty()) {
            // X-Forwarded-For pode conter múltiplos IPs, pegar o primeiro (cliente real)
            return xfHeader.split(",")[0].trim();
        }
        
        // Headers alternativos
        String cfConnectingIp = request.getHeader("CF-Connecting-IP"); // Cloudflare
        if (cfConnectingIp != null && !cfConnectingIp.isEmpty()) {
            return cfConnectingIp;
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        
        // Fallback para IP direto
        return request.getRemoteAddr();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização não necessária
    }

    @Override
    public void destroy() {
        // Limpar cache ao destruir filtro
        cache.clear();
    }
}
