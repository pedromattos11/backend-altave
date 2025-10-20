package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.ColaboradorRepository;
import br.com.altave.backend_altave.model.Colaborador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repo;

    public ColaboradorService(ColaboradorRepository repo) {
        this.repo = repo;
    }

    public List<Colaborador> findAll() {
        return repo.findAll();
    }

    public Optional<Colaborador> findById(Integer id) {
        return repo.findById(id);
    }

    public Colaborador save(Colaborador obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public Optional<Colaborador> update(Integer id, Colaborador newData) {
        return repo.findById(id)
                .map(existingColaborador -> {
                    existingColaborador.setNome(newData.getNome());
                    existingColaborador.setApresentacao(newData.getApresentacao());
                    return repo.save(existingColaborador);
                });
    }

    public Optional<Colaborador> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
