package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.SoftSkillRepository;
import br.com.altave.backend_altave.model.SoftSkill;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SoftSkillService {

    private final SoftSkillRepository repo;

    public SoftSkillService(SoftSkillRepository repo) {
        this.repo = repo;
    }

    public List<SoftSkill> findAll() {
        return repo.findAll();
    }

    public Optional<SoftSkill> findById(Integer id) {
        return repo.findById(id);
    }

    public SoftSkill save(SoftSkill obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
