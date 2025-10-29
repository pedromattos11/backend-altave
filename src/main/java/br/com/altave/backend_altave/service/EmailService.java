package br.com.altave.backend_altave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarNotificacaoPerfilDesatualizado(String destinatario, String nomeColaborador) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@altave.com.br"); // Este é o email configurado no spring.mail.username
            message.setTo(destinatario);
            message.setSubject("Lembrete: Atualize seu perfil profissional - Altave");
            message.setText(
                "Olá " + nomeColaborador + ",\n\n" +
                "Este é um lembrete automático de que seu perfil profissional no sistema Altave não foi atualizado há mais de 6 meses.\n\n" +
                "É importante manter seu perfil atualizado para que você possa:\n" +
                "- Compartilhar suas habilidades e experiências mais recentes\n" +
                "- Facilitar o trabalho da equipe de RH\n" +
                "- Garantir que as oportunidades sejam alinhadas ao seu perfil atual\n\n" +
                "Por favor, acesse o sistema e atualize suas informações:\n" +
                "Experiências profissionais, habilidades técnicas, soft skills, certificações e projetos.\n\n" +
                "Caso você tenha dúvidas ou precise de ajuda, entre em contato com o RH.\n\n" +
                "Atenciosamente,\n" +
                "Equipe Altave"
            );
            
            mailSender.send(message);
            System.out.println("Email de notificação enviado para: " + destinatario);
        } catch (Exception e) {
            System.err.println("Erro ao enviar email para " + destinatario + ": " + e.getMessage());
        }
    }
}

