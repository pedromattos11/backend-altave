package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkill, Integer> {
    Optional<SoftSkill> findByNomeCompetencia(String nomeCompetencia);
}
