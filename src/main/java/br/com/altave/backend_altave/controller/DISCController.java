package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.dto.DISCQuestionarioDTO;
import br.com.altave.backend_altave.dto.PerfilDISCDTO;
import br.com.altave.backend_altave.service.DISCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disc")
@RequiredArgsConstructor

public class DISCController {

    private final DISCService discService;

    @PostMapping("/calcular")
    public ResponseEntity<?> calcularPerfil(@RequestBody DISCQuestionarioDTO questionarioDTO) {
        try {
            PerfilDISCDTO perfil = discService.calcularEArmazenarPerfil(questionarioDTO);
            return ResponseEntity.ok(perfil);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPerfilPorUsuario(@PathVariable Long usuarioId) {
        try {
            PerfilDISCDTO perfil = discService.buscarPerfilPorUsuario(usuarioId);
            return ResponseEntity.ok(perfil);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}/possui-perfil")
    public ResponseEntity<Boolean> verificarPerfil(@PathVariable Long usuarioId) {
        boolean possuiPerfil = discService.usuarioPossuiPerfil(usuarioId);
        return ResponseEntity.ok(possuiPerfil);
    }
}
