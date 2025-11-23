package br.com.altave.backend_altave.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Filtro para adicionar headers de segurança em todas as respostas HTTP.
 * 
 * Headers implementados:
 * - X-Content-Type-Options: Previne MIME type sniffing
 * - X-XSS-Protection: Ativa proteção XSS no browser
 * - X-Frame-Options: Previne clickjacking
 * - Strict-Transport-Security: Força uso de HTTPS
 * - Referrer-Policy: Controla informações de referrer
 * - Permissions-Policy: Controla acesso a APIs do browser
 */
@Component
public class SecurityHeadersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Previne MIME type sniffing
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        
        // Ativa proteção XSS no browser (legacy, mas ainda útil para browsers antigos)
        httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
        
        // Previne que a página seja exibida em iframes (clickjacking protection)
        httpResponse.setHeader("X-Frame-Options", "DENY");
        
        // Força uso de HTTPS por 1 ano (apenas em produção)
        // httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        
        // Controla quanto de informação de referrer é enviada
        httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
        
        // Desabilita features desnecessárias do browser
        httpResponse.setHeader("Permissions-Policy", "camera=(), microphone=(), geolocation=()");
        
        // Cache-Control para APIs (não cachear por padrão)
        httpResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");
        httpResponse.setHeader("Pragma", "no-cache");
        
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização não necessária
    }

    @Override
    public void destroy() {
        // Limpeza não necessária
    }
}
