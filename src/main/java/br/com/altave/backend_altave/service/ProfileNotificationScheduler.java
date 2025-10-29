package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.model.Colaborador;
import br.com.altave.backend_altave.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ProfileNotificationScheduler {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private EmailService emailService;

    /**
     * MODO TESTE: Executa a cada 2 minutos
     * Verifica colaboradores com perfil desatualizado há mais de 2 minutos
     * 
     * Para voltar ao modo produção, altere para:
     * @Scheduled(cron = "0 0 8 * * *") // Todos os dias às 8:00
     * E mude: agora.minus(2, ChronoUnit.MINUTES) para agora.minus(6, ChronoUnit.MONTHS)
     */
    @Scheduled(cron = "0 */2 * * * *") // A cada 2 minutos (APENAS PARA TESTE)
    public void verificarPerfisDesatualizados() {
        System.out.println("Iniciando verificação de perfis desatualizados...");
        
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime doisMinutosAtras = agora.minus(2, ChronoUnit.MINUTES);
        
        // Buscar todos os colaboradores
        List<Colaborador> colaboradores = colaboradorRepository.findAll();
        
        int notificados = 0;
        
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getUltimaAtualizacao() == null || 
                colaborador.getUltimaAtualizacao().isBefore(doisMinutosAtras)) {
                
                System.out.println("Perfil desatualizado encontrado: " + colaborador.getNome() + 
                                 " (Última atualização: " + colaborador.getUltimaAtualizacao() + ")");
                
                // Enviar notificação por email
                emailService.enviarNotificacaoPerfilDesatualizado(
                    colaborador.getEmail(), 
                    colaborador.getNome()
                );
                
                notificados++;
            }
        }
        
        System.out.println("Verificação concluída. Total de notificações enviadas: " + notificados);
    }
}

