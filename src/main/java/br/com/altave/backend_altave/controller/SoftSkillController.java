package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.SoftSkill;
import br.com.altave.backend_altave.service.SoftSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/softskill")
public class SoftSkillController {

    private final SoftSkillService service;

    public SoftSkillController(SoftSkillService service) {
        this.service = service;
    }

    @GetMapping
    public List<SoftSkill> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoftSkill> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SoftSkill create(@RequestBody SoftSkill obj) {
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
