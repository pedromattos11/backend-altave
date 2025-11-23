package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.HardSkill;
import br.com.altave.backend_altave.dto.HardSkillDTO;
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

    // Endpoint legado (evite usar - causa N+1 queries)
    @GetMapping
    public List<HardSkill> list() {
        return service.findAll();
    }
    
    /**
     * Endpoint otimizado para dashboard que retorna DTOs.
     * Usa uma única query ao invés de centenas. 
     * USE ESTE ao invés do endpoint GET /api/hardskill para melhor performance.
     */
    @GetMapping("/light")
    public List<HardSkillDTO> listLight() {
        return service.findAllLight();
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
    
    /**
     * Marca uma hard skill como destacada (top 3).
     */
    @PatchMapping("/{id}/highlight")
    public ResponseEntity<?> markAsHighlighted(@PathVariable Integer id) {
        try {
            HardSkill updated = service.markAsHighlighted(id);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao destacar skill: " + e.getMessage());
        }
    }
    
    /**
     * Remove a marcação de destaque de uma hard skill.
     */
    @PatchMapping("/{id}/unhighlight")
    public ResponseEntity<?> unmarkAsHighlighted(@PathVariable Integer id) {
        try {
            HardSkill updated = service.unmarkAsHighlighted(id);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao remover destaque: " + e.getMessage());
        }
    }
}
