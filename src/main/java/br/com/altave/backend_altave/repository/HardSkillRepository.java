package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.HardSkill;
import br.com.altave.backend_altave.dto.HardSkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HardSkillRepository extends JpaRepository<HardSkill, Integer> {
    
    /**
     * Query otimizada que retorna DTO com apenas dados necessários.
     * Evita N+1 query problem ao não carregar o objeto Colaborador completo.
     * Uma única query com JOIN para buscar todas as hardskills e IDs dos colaboradores.
     */
    @Query("""
        SELECT new br.com.altave.backend_altave.dto.HardSkillDTO(
            h.id,
            h.nomeCompetencia,
            h.colaborador.id
        )
        FROM HardSkill h
        ORDER BY h.nomeCompetencia ASC
        """)
    List<HardSkillDTO> findAllLight();
}
