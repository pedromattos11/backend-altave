package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.SoftSkill;
import br.com.altave.backend_altave.dto.ColaboradorSoftSkillDTO;
import br.com.altave.backend_altave.dto.SoftSkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkill, Integer> {
    Optional<SoftSkill> findByNomeCompetencia(String nomeCompetencia);
    
    /**
     * Query otimizada que retorna DTO com apenas dados necessários.
     * SoftSkills não têm relacionamento direto com colaborador (ManyToMany via tabela associativa),
     * então apenas retorna id e nome da competência.
     */
    @Query("""
        SELECT new br.com.altave.backend_altave.dto.SoftSkillDTO(
            s.id,
            s.nomeCompetencia
        )
        FROM SoftSkill s
        ORDER BY s.nomeCompetencia ASC
        """)
    List<SoftSkillDTO> findAllLight();

    @Query("""
        SELECT new br.com.altave.backend_altave.dto.ColaboradorSoftSkillDTO(c.id, s.nomeCompetencia)
        FROM Colaborador c JOIN c.softSkills s
        """)
    List<ColaboradorSoftSkillDTO> findAllColaboradorSoftSkillMap();
}
