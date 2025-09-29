package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.ExperienciaRepository;
import br.com.altave.backend_altave.model.Experiencia;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExperienciaService {

    private final ExperienciaRepository repo;

    public ExperienciaService(ExperienciaRepository repo) {
        this.repo = repo;
    }

    public List<Experiencia> findAll() {
        return repo.findAll();
    }

    public Optional<Experiencia> findById(Integer id) {
        return repo.findById(id);
    }

    public Experiencia save(Experiencia obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
