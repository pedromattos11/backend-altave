package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.Projeto;
import br.com.altave.backend_altave.service.ProjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/projeto")
@CrossOrigin(origins = "*")
public class ProjetoController {

    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Projeto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/colaborador/{colaboradorId}")
    public List<Projeto> getByColaborador(@PathVariable Integer colaboradorId) {
        return service.findByColaboradorId(colaboradorId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Projeto projeto) {
        try {
            // Garantir que o colaborador está configurado
            if (projeto.getColaborador() == null || projeto.getColaborador().getId() == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Colaborador ID é obrigatório"));
            }
            
            Projeto saved = service.save(projeto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace(); // Log para debug
            return ResponseEntity.status(500).body(Map.of(
                "error", "Erro ao salvar projeto: " + e.getMessage(),
                "type", e.getClass().getSimpleName()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

