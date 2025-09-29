package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.CompetenciaRepository;
import br.com.altave.backend_altave.model.Competencia;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetenciaService {

    private final CompetenciaRepository repo;

    public CompetenciaService(CompetenciaRepository repo) {
        this.repo = repo;
    }

    public List<Competencia> findAll() {
        return repo.findAll();
    }

    public Optional<Competencia> findById(Integer id) {
        return repo.findById(id);
    }

    public Competencia save(Competencia obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
