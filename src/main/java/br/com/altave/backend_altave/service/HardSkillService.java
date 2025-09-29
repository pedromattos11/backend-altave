package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.HardSkillRepository;
import br.com.altave.backend_altave.model.HardSkill;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HardSkillService {

    private final HardSkillRepository repo;

    public HardSkillService(HardSkillRepository repo) {
        this.repo = repo;
    }

    public List<HardSkill> findAll() {
        return repo.findAll();
    }

    public Optional<HardSkill> findById(Integer id) {
        return repo.findById(id);
    }

    public HardSkill save(HardSkill obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
