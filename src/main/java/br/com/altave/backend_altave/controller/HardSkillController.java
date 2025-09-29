package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.HardSkill;
import br.com.altave.backend_altave.service.HardSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/hardskill")
public class HardSkillController {

    private final HardSkillService service;

    public HardSkillController(HardSkillService service) {
        this.service = service;
    }

    @GetMapping
    public List<HardSkill> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HardSkill> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HardSkill create(@RequestBody HardSkill obj) {
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
