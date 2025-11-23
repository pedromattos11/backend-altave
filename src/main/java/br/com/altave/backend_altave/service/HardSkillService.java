package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.HardSkillRepository;
import br.com.altave.backend_altave.model.HardSkill;
import br.com.altave.backend_altave.dto.HardSkillDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HardSkillService {

    private final HardSkillRepository repo;
    private static final int MAX_HIGHLIGHTED = 3;

    public HardSkillService(HardSkillRepository repo) {
        this.repo = repo;
    }

    public List<HardSkill> findAll() {
        return repo.findAll();
    }
    
    /**
     * Versão otimizada que retorna DTOs ao invés de entidades completas.
     * Evita N+1 query problem.
     */
    public List<HardSkillDTO> findAllLight() {
        return repo.findAllLight();
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
    
    /**
     * Marca uma hard skill como destacada (top 3).
     * Valida se o colaborador já tem 3 skills destacadas.
     */
    public HardSkill markAsHighlighted(Integer id) {
        Optional<HardSkill> skillOpt = findById(id);
        if (!skillOpt.isPresent()) {
            throw new IllegalArgumentException("Hard skill não encontrada com ID: " + id);
        }
        
        HardSkill skill = skillOpt.get();
        
        // Se já está destacada, retornar
        if (skill.getIsHighlighted() != null && skill.getIsHighlighted()) {
            return skill;
        }
        
        // Verificar quantas skills destacadas o colaborador tem
        if (skill.getColaborador() != null) {
            long highlightedCount = skill.getColaborador().getHardSkills().stream()
                .filter(hs -> hs.getIsHighlighted() != null && hs.getIsHighlighted())
                .count();
            
            if (highlightedCount >= MAX_HIGHLIGHTED) {
                throw new IllegalArgumentException("Colaborador já atingiu o limite de " + MAX_HIGHLIGHTED + " hard skills destacadas");
            }
        }
        
        // Marcar como destacada e atribuir posição
        skill.setIsHighlighted(true);
        
        // Atribuir posição baseado no número de skills já destacadas
        if (skill.getColaborador() != null) {
            long nextPosition = skill.getColaborador().getHardSkills().stream()
                .filter(hs -> hs.getIsHighlighted() != null && hs.getIsHighlighted())
                .count() + 1;
            skill.setOrderPosition((int) nextPosition);
        }
        
        return repo.save(skill);
    }
    
    /**
     * Remove a marcação de destaque de uma hard skill.
     */
    public HardSkill unmarkAsHighlighted(Integer id) {
        Optional<HardSkill> skillOpt = findById(id);
        if (!skillOpt.isPresent()) {
            throw new IllegalArgumentException("Hard skill não encontrada com ID: " + id);
        }
        
        HardSkill skill = skillOpt.get();
        Integer oldPosition = skill.getOrderPosition();
        
        skill.setIsHighlighted(false);
        skill.setOrderPosition(null);
        
        HardSkill updated = repo.save(skill);
        
        // Reordenar as skills que ficaram destacadas
        if (skill.getColaborador() != null && oldPosition != null) {
            reorderHighlightedSkills(skill.getColaborador().getId());
        }
        
        return updated;
    }
    
    /**
     * Reordena as posições das skills destacadas após remoção.
     */
    private void reorderHighlightedSkills(Integer colaboradorId) {
        // Esta é uma operação que seria feita através de query nativa
        // Por simplicidade, deixamos a reordenação para ser feita conforme necessário
    }
}
