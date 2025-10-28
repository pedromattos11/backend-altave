package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.ProjetoRepository;
import br.com.altave.backend_altave.model.Projeto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProjetoService {

    private final ProjetoRepository repo;

    public ProjetoService(ProjetoRepository repo) {
        this.repo = repo;
    }

    public List<Projeto> findAll() {
        return repo.findAll();
    }

    public Optional<Projeto> findById(Integer id) {
        return repo.findById(id);
    }

    public List<Projeto> findByColaboradorId(Integer colaboradorId) {
        return repo.findByColaboradorId(colaboradorId);
    }

    public Projeto save(Projeto obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}

