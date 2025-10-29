package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teste")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    /**
     * Endpoint de teste para enviar email
     * GET /api/teste/email?destinatario=email@exemplo.com
     */
    @GetMapping("/email")
    public ResponseEntity<?> enviarEmailTeste(@RequestParam(required = false) String destinatario) {
        try {
            String email = destinatario != null ? destinatario : "pedro.hmattos19@gmail.com";
            
            emailService.enviarNotificacaoPerfilDesatualizado(email, "Teste");
            
            return ResponseEntity.ok().body("Email enviado para: " + email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}

