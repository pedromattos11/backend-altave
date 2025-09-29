package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.Cargo;
import br.com.altave.backend_altave.service.CargoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    private final CargoService service;

    public CargoController(CargoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cargo> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cargo create(@RequestBody Cargo obj) {
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
