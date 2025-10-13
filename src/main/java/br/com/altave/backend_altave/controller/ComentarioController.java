package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.Comentario;
import br.com.altave.backend_altave.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {

    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Comentario> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comentario> create(@RequestBody Comentario comentario) {
        try {
            Comentario novoComentario = service.save(comentario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoComentario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> update(@PathVariable Integer id, @RequestBody Comentario comentario) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comentario.setIdComentario(id);
        return ResponseEntity.ok(service.save(comentario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar comentários recebidos por um colaborador
    @GetMapping("/colaborador/{idColaborador}")
    public List<Comentario> getComentariosByColaborador(@PathVariable Integer idColaborador) {
        return service.findComentariosByDestino(idColaborador);
    }

    // Buscar comentários enviados por um colaborador
    @GetMapping("/enviados/{idColaborador}")
    public List<Comentario> getComentariosEnviadosByColaborador(@PathVariable Integer idColaborador) {
        return service.findComentariosByOrigem(idColaborador);
    }

    // Criar um comentário rápido (endpoint simplificado)
    @PostMapping("/criar")
    public ResponseEntity<Comentario> criarComentario(@RequestBody Map<String, Object> request) {
        try {
            Integer idColaboradorOrigem = (Integer) request.get("idColaboradorOrigem");
            Integer idColaboradorDestino = (Integer) request.get("idColaboradorDestino");
            String textoComentario = (String) request.get("textoComentario");

            if (idColaboradorOrigem == null || idColaboradorDestino == null || textoComentario == null || textoComentario.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Comentario comentario = service.criarComentario(idColaboradorOrigem, idColaboradorDestino, textoComentario.trim());
            return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
