package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.SoftSkill;
import br.com.altave.backend_altave.dto.ColaboradorSoftSkillDTO;
import br.com.altave.backend_altave.dto.SoftSkillDTO;
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

    // Endpoint legado (evite usar para listagem completa)
    @GetMapping
    public List<SoftSkill> list() {
        return service.findAll();
    }
    
    /**
     * Endpoint otimizado para dashboard que retorna DTOs.
     * USE ESTE ao invés do endpoint GET /api/softskill para melhor performance.
     */
    @GetMapping("/light")
    public List<SoftSkillDTO> listLight() {
        return service.findAllLight();
    }

    @GetMapping("/colaborador-map")
    public List<ColaboradorSoftSkillDTO> listColaboradorMap() {
        return service.findAllColaboradorSoftSkillMap();
    }

    /**
     * Retorna soft skills disponíveis (pré-definidas)
     */
    @GetMapping("/available")
    public List<SoftSkill> listAvailable() {
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
