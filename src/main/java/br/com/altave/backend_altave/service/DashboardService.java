package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.ColaboradorRepository;
import br.com.altave.backend_altave.repository.SoftSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    
    @Autowired
    private SoftSkillRepository softSkillRepository;

    /**
     * Retorna estatísticas do dashboard de forma otimizada.
     * Usa COUNT queries diretas sem carregar objetos completos.
     * Cache por 60 segundos para reduzir carga no banco.
     */
    @Cacheable(value = "dashboardStats", unless = "#result['success'] == false")
    @Transactional(readOnly = true)
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        try {
            // Total de colaboradores na organização
            long totalColaboradores = colaboradorRepository.countTotalColaboradores();
            
            // Total de competências únicas mapeadas (soft skills)
            long totalCompetenciasMapeadas = colaboradorRepository.countTotalSoftSkillsMapeadas();
            
            // Total de hard skills mapeadas
            long totalHardSkillsMapeadas = colaboradorRepository.countTotalHardSkillsMapeadas();
            
            // Total de soft skills disponíveis no catálogo
            long totalSoftSkillsCatalogo = softSkillRepository.count();
            
            // Colaboradores que possuem ao menos uma soft skill
            long colaboradoresComSkills = colaboradorRepository.countColaboradoresComSoftSkills();
            
            // Calcular desatualizados (colaboradores sem soft skills)
            long colaboradoresDesatualizados = totalColaboradores - colaboradoresComSkills;
            
            stats.put("totalColaboradores", totalColaboradores);
            stats.put("competenciasMapeadas", totalCompetenciasMapeadas);
            stats.put("hardSkillsMapeadas", totalHardSkillsMapeadas);
            stats.put("softSkillsCatalogo", totalSoftSkillsCatalogo);
            stats.put("colaboradoresComSkills", colaboradoresComSkills);
            stats.put("desatualizados", colaboradoresDesatualizados);
            stats.put("success", true);
            
        } catch (Exception e) {
            stats.put("success", false);
            stats.put("error", e.getMessage());
        }
        
        return stats;
    }
}
