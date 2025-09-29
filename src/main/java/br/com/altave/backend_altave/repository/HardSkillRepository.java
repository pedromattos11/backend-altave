package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.HardSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardSkillRepository extends JpaRepository<HardSkill, Integer> {
}
