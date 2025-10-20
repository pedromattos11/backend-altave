package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.dto.ExperienciaDTO;
import br.com.altave.backend_altave.model.Experiencia;
import br.com.altave.backend_altave.service.ExperienciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/experiencia")
public class ExperienciaController {

    private final ExperienciaService service;

    public ExperienciaController(ExperienciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExperienciaDTO> list() {
        return service.findAll().stream()
                .map(ExperienciaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaDTO> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ExperienciaDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExperienciaDTO create(@RequestBody Experiencia obj) {
        Experiencia saved = service.save(obj);
        return new ExperienciaDTO(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
