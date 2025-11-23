package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * Endpoint otimizado para estatísticas do dashboard.
     * Retorna contagens diretas sem carregar objetos completos.
     * 
     * GET /api/dashboard/stats
     * 
     * Response:
     * {
     *   "totalColaboradores": 116,
     *   "competenciasMapeadas": 90,
     *   "hardSkillsMapeadas": 812,
     *   "softSkillsCatalogo": 90,
     *   "colaboradoresComSkills": 116,
     *   "desatualizados": 0,
     *   "success": true
     * }
     */
    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        return dashboardService.getDashboardStats();
    }
}
