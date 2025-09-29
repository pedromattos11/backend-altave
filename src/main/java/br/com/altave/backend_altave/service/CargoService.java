package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.CargoRepository;
import br.com.altave.backend_altave.model.Cargo;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CargoService {

    private final CargoRepository repo;

    public CargoService(CargoRepository repo) {
        this.repo = repo;
    }

    public List<Cargo> findAll() {
        return repo.findAll();
    }

    public Optional<Cargo> findById(Integer id) {
        return repo.findById(id);
    }

    public Cargo save(Cargo obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
