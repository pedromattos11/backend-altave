package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/equipe")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    /**
     * Endpoint para "Minha Equipe" - retorna colaboradores visíveis baseado na hierarquia.
     * 
     * GET /api/equipe/minha/{colaboradorId}
     * 
     * Se DIRETOR, retorna:
     * {
     *   "isDiretor": true,
     *   "supervisores": [...],
     *   "colaboradores": [...],
     *   "totalSupervisores": 2,
     *   "totalColaboradores": 12
     * }
     * 
     * Se SUPERVISOR, retorna:
     * {
     *   "isSupervisor": true,
     *   "diretor": {...},
     *   "minhaEquipe": [...],
     *   "outrosSupervisores": [...],
     *   "totalMinhaEquipe": 6
     * }
     * 
     * Se COLABORADOR, retorna:
     * {
     *   "supervisor": {...},
     *   "colegas": [...],
     *   "totalColegas": 5
     * }
     */
    @GetMapping("/minha/{colaboradorId}")
    public Map<String, Object> getMinhaEquipe(@PathVariable Integer colaboradorId) {
        return equipeService.getMinhaEquipe(colaboradorId);
    }

    /**
     * Endpoint para estatísticas da hierarquia organizacional.
     * 
     * GET /api/equipe/hierarchy-stats
     * 
     * Response:
     * {
     *   "totalDiretores": 7,
     *   "totalSupervisores": 14,
     *   "totalColaboradores": 79,
     *   "success": true
     * }
     */
    @GetMapping("/hierarchy-stats")
    public Map<String, Object> getHierarchyStats() {
        return equipeService.getHierarchyStats();
    }
}
