package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.Competencia;
import br.com.altave.backend_altave.service.CompetenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/competencia")

public class CompetenciaController {

    private final CompetenciaService service;

    public CompetenciaController(CompetenciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Competencia> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competencia> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Competencia create(@RequestBody Competencia obj) {
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
