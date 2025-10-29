package br.com.altave.backend_altave.service;

import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${EMAIL_API_KEY:}")
    private String apiKey;
    
    @Value("${EMAIL_FROM:notificacoes@altave.com.br}")
    private String emailFrom;

    private static final String RESEND_API_URL = "https://api.resend.com/emails";

    public void enviarNotificacaoPerfilDesatualizado(String destinatario, String nomeColaborador) {
        try {
            System.out.println("📧 Iniciando envio de email...");
            System.out.println("API Key configurada: " + (apiKey != null && !apiKey.isEmpty() ? "SIM" : "NÃO"));
            System.out.println("Email FROM: " + emailFrom);
            System.out.println("Email TO: " + destinatario);
            
            // Validar email
            if (destinatario == null || destinatario.trim().isEmpty()) {
                System.err.println("❌ Email destinatário não informado!");
                return;
            }
            
            // Limpar email (remover espaços extras)
            destinatario = destinatario.trim();
            
            // Se não tem API key configurada, usa fallback para log
            if (apiKey == null || apiKey.isEmpty()) {
                System.out.println("⚠️ EMAIL_API_KEY não configurada. Email seria enviado para: " + destinatario);
                System.out.println("Assunto: Lembrete: Atualize seu perfil profissional - Altave");
                System.out.println("Para: " + nomeColaborador);
                return;
            }

            JSONObject payload = new JSONObject();
            payload.put("from", emailFrom);
            payload.put("to", new String[]{destinatario}); // Resend aceita array
            payload.put("subject", "Lembrete: Atualize seu perfil profissional - Altave");
            payload.put("text", 
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

            System.out.println("Payload: " + payload.toString(2));

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(
                payload.toString(),
                MediaType.parse("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                .url(RESEND_API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body() != null ? response.body().string() : "";
                
                if (response.isSuccessful()) {
                    System.out.println("✅ Email enviado com sucesso para: " + destinatario);
                    System.out.println("Resposta: " + responseBody);
                } else {
                    System.err.println("❌ Erro ao enviar email: " + response.code());
                    System.err.println("Resposta: " + responseBody);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar email para " + destinatario + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}

