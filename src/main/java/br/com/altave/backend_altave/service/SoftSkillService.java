package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.model.Colaborador;
import br.com.altave.backend_altave.repository.ColaboradorRepository;
import br.com.altave.backend_altave.repository.SoftSkillRepository;
import br.com.altave.backend_altave.model.SoftSkill;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SoftSkillService {

    private final SoftSkillRepository repo;
    private final ColaboradorRepository colaboradorRepository;

    public SoftSkillService(SoftSkillRepository repo, ColaboradorRepository colaboradorRepository) {
        this.repo = repo;
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<SoftSkill> findAll() {
        return repo.findAll();
    }

    public Optional<SoftSkill> findById(Integer id) {
        return repo.findById(id);
    }

    public SoftSkill save(SoftSkill obj) {
        if (obj.getColaborador() != null && obj.getColaborador().getId() != null) {
            Colaborador colaborador = colaboradorRepository.findById(obj.getColaborador().getId())
                    .orElseThrow(() -> new NoSuchElementException("Colaborador não encontrado"));
            obj.setColaborador(colaborador);
        }
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
