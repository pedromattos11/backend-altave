package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.Experiencia;
import br.com.altave.backend_altave.service.ExperienciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/experiencia")
public class ExperienciaController {

    private final ExperienciaService service;

    public ExperienciaController(ExperienciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Experiencia> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Experiencia create(@RequestBody Experiencia obj) {
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
