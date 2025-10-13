package br.com.altave.backend_altave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/sql-fix")
@CrossOrigin("*")
public class SqlFixController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/soft-skills")
    @Transactional
    public Map<String, Object> fixSoftSkillsDirectSQL() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // Step 1: Clear existing associations
            int deletedAssociations = jdbcTemplate.update("DELETE FROM colaborador_softskill");
            
            // Step 2: Create soft skills (ignore if exist)
            String[] softSkills = {
                "Comunicação", "Liderança", "Trabalho em equipe", "Criatividade",
                "Resolução de problemas", "Adaptabilidade", "Gestão do tempo",
                "Pensamento crítico", "Inteligência emocional", "Negociação",
                "Empatia", "Proatividade", "Organização", "Flexibilidade",
                "Iniciativa", "Colaboração", "Resiliência", "Inovação",
                "Networking", "Orientação para resultados", "Aprendizado contínuo",
                "Multitarefa", "Persuasão", "Coaching", "Mentoria"
            };
            
            int createdSkills = 0;
            for (String skill : softSkills) {
                try {
                    jdbcTemplate.update("INSERT IGNORE INTO soft_skill (nome_competencia) VALUES (?)", skill);
                    createdSkills++;
                } catch (Exception e) {
                    // Ignore if already exists
                }
            }
            
            // Step 3: Associate skills by profile
            Map<Integer, String[]> profileSkills = new HashMap<>();
            profileSkills.put(1, new String[]{"Resolução de problemas", "Pensamento crítico", "Organização", "Proatividade", "Aprendizado contínuo", "Trabalho em equipe"});
            profileSkills.put(2, new String[]{"Criatividade", "Comunicação", "Inovação", "Flexibilidade", "Colaboração", "Adaptabilidade"});
            profileSkills.put(3, new String[]{"Adaptabilidade", "Multitarefa", "Resolução de problemas", "Comunicação", "Organização", "Aprendizado contínuo"});
            profileSkills.put(4, new String[]{"Liderança", "Gestão do tempo", "Resiliência", "Proatividade", "Orientação para resultados", "Resolução de problemas"});
            profileSkills.put(5, new String[]{"Pensamento crítico", "Resolução de problemas", "Aprendizado contínuo", "Comunicação", "Organização", "Inovação"});
            profileSkills.put(6, new String[]{"Inovação", "Adaptabilidade", "Criatividade", "Trabalho em equipe", "Flexibilidade", "Aprendizado contínuo"});
            
            int totalAssociations = 0;
            for (Map.Entry<Integer, String[]> entry : profileSkills.entrySet()) {
                Integer profile = entry.getKey();
                String[] skills = entry.getValue();
                
                for (String skillName : skills) {
                    String sql = "INSERT INTO colaborador_softskill (colaborador_id, softskill_id) " +
                               "SELECT c.id, s.id FROM colaborador c CROSS JOIN soft_skill s " +
                               "WHERE c.perfil = ? AND s.nome_competencia = ?";
                    int inserted = jdbcTemplate.update(sql, profile, skillName);
                    totalAssociations += inserted;
                }
            }
            
            // Step 4: Special case for Pedro Mattos
            String[] pedroSkills = {"Liderança", "Comunicação", "Inteligência emocional", "Negociação", "Coaching"};
            for (String skillName : pedroSkills) {
                String sql = "INSERT IGNORE INTO colaborador_softskill (colaborador_id, softskill_id) " +
                           "SELECT c.id, s.id FROM colaborador c CROSS JOIN soft_skill s " +
                           "WHERE c.email = 'pedro.mattos@email.com' AND s.nome_competencia = ?";
                jdbcTemplate.update(sql, skillName);
            }
            
            // Step 5: Get final counts
            Integer totalColaboradores = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM colaborador", Integer.class);
            Integer totalSoftSkills = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM soft_skill", Integer.class);
            Integer finalAssociations = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM colaborador_softskill", Integer.class);
            
            result.put("success", true);
            result.put("message", "Soft skills fixed successfully via direct SQL!");
            result.put("deletedAssociations", deletedAssociations);
            result.put("totalSoftSkills", totalSoftSkills);
            result.put("createdAssociations", totalAssociations);
            result.put("finalAssociations", finalAssociations);
            result.put("totalColaboradores", totalColaboradores);
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Error: " + e.getMessage());
            result.put("stackTrace", Arrays.toString(e.getStackTrace()));
        }
        
        return result;
    }

    @GetMapping("/verify")
    public Map<String, Object> verifySoftSkills() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String sql = "SELECT c.nome, c.perfil, COUNT(cs.softskill_id) as soft_skills_count " +
                        "FROM colaborador c " +
                        "LEFT JOIN colaborador_softskill cs ON c.id = cs.colaborador_id " +
                        "GROUP BY c.id, c.nome, c.perfil " +
                        "ORDER BY c.perfil, c.nome";
            
            List<Map<String, Object>> verification = jdbcTemplate.queryForList(sql);
            
            result.put("success", true);
            result.put("colaboradores", verification);
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
}
