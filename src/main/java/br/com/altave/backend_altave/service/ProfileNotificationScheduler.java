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
     * MODO PRODUÇÃO: Executa todos os dias às 8:00
     * Verifica colaboradores com perfil desatualizado há mais de 6 meses
     */
    @Scheduled(cron = "0 0 8 * * *") // Todos os dias às 8:00
    public void verificarPerfisDesatualizados() {
        System.out.println("Iniciando verificação de perfis desatualizados...");
        
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime seisMesesAtras = agora.minus(6, ChronoUnit.MONTHS);
        
        // Buscar todos os colaboradores
        List<Colaborador> colaboradores = colaboradorRepository.findAll();
        
        int notificados = 0;
        
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getUltimaAtualizacao() == null || 
                colaborador.getUltimaAtualizacao().isBefore(seisMesesAtras)) {
                
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

