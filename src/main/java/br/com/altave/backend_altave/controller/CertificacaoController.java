package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.Certificacao;
import br.com.altave.backend_altave.service.CertificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/certificacao")
public class CertificacaoController {

    private final CertificacaoService service;

    public CertificacaoController(CertificacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Certificacao> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificacao> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Certificacao create(@RequestBody Certificacao obj) {
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
