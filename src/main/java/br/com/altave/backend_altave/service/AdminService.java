package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.model.Colaborador;
import br.com.altave.backend_altave.model.SoftSkill;
import br.com.altave.backend_altave.repository.ColaboradorRepository;
import br.com.altave.backend_altave.repository.SoftSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AdminService {

    @Autowired
    private SoftSkillRepository softSkillRepo;

    @Autowired
    private ColaboradorRepository colaboradorRepo;

    public Map<String, Object> debugSoftSkills() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Colaborador> colaboradores = colaboradorRepo.findAll();
            List<SoftSkill> allSoftSkills = softSkillRepo.findAll();

            result.put("success", true);
            result.put("totalColaboradores", colaboradores.size());
            result.put("totalSoftSkills", allSoftSkills.size());

            Map<Integer, Object> colaboradorDetails = new HashMap<>();
            for (Colaborador c : colaboradores) {
                Map<String, Object> details = new HashMap<>();
                details.put("nome", c.getNome());
                details.put("perfil", c.getPerfil());
                details.put("softSkillsCount", c.getSoftSkills() != null ? c.getSoftSkills().size() : 0);
                colaboradorDetails.put(c.getId(), details);
            }
            result.put("colaboradores", colaboradorDetails);
            result.put("softSkillNames", allSoftSkills.stream().map(SoftSkill::getNomeCompetencia).toArray());

        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("stackTrace", e.getStackTrace());
        }
        return result;
    }

    @Transactional
    public Map<String, Object> fixSoftSkills() {
        Map<String, Object> result = new HashMap<>();
        try {
            // Step 1: Get all colaboradores
            List<Colaborador> colaboradores = colaboradorRepo.findAll();

            // Step 2: Clear existing associations (but keep soft skills in database)
            for (Colaborador c : colaboradores) {
                if (c.getSoftSkills() != null) {
                    c.getSoftSkills().clear();
                }
            }
            colaboradorRepo.saveAll(colaboradores);

            // Step 3: Ensure we have soft skills in database
            List<SoftSkill> existingSkills = softSkillRepo.findAll();
            if (existingSkills.isEmpty()) {
                // Create new soft skills
                List<String> softSkillNames = Arrays.asList(
                        "Comunicação", "Liderança", "Trabalho em equipe", "Criatividade",
                        "Resolução de problemas", "Adaptabilidade", "Gestão do tempo",
                        "Pensamento crítico", "Inteligência emocional", "Negociação",
                        "Empatia", "Proatividade", "Organização", "Flexibilidade",
                        "Iniciativa", "Colaboração", "Resiliência", "Inovação",
                        "Networking", "Orientação para resultados", "Aprendizado contínuo",
                        "Multitarefa", "Persuasão", "Coaching", "Mentoria"
                );

                List<SoftSkill> softSkills = new ArrayList<>();
                for (String name : softSkillNames) {
                    SoftSkill newSkill = new SoftSkill();
                    newSkill.setNomeCompetencia(name);
                    softSkills.add(newSkill);
                }
                softSkillRepo.saveAll(softSkills);
            }

            // 4. Associate soft skills by profile
            Map<Integer, List<String>> profileSkills = new HashMap<>();
            profileSkills.put(1, Arrays.asList("Resolução de problemas", "Pensamento crítico", "Organização", "Proatividade", "Aprendizado contínuo", "Trabalho em equipe"));
            profileSkills.put(2, Arrays.asList("Criatividade", "Comunicação", "Inovação", "Flexibilidade", "Colaboração", "Adaptabilidade"));
            profileSkills.put(3, Arrays.asList("Adaptabilidade", "Multitarefa", "Resolução de problemas", "Comunicação", "Organização", "Aprendizado contínuo"));
            profileSkills.put(4, Arrays.asList("Liderança", "Gestão do tempo", "Resiliência", "Proatividade", "Orientação para resultados", "Resolução de problemas"));
            profileSkills.put(5, Arrays.asList("Pensamento crítico", "Resolução de problemas", "Aprendizado contínuo", "Comunicação", "Organização", "Inovação"));
            profileSkills.put(6, Arrays.asList("Inovação", "Adaptabilidade", "Criatividade", "Trabalho em equipe", "Flexibilidade", "Aprendizado contínuo"));

            int updatedColaboradores = 0;
            for (Colaborador colaborador : colaboradores) {
                if (colaborador.getPerfil() != null && profileSkills.containsKey(colaborador.getPerfil())) {
                    Set<SoftSkill> colaboradorSoftSkills = new HashSet<>();
                    for (String skillName : profileSkills.get(colaborador.getPerfil())) {
                        Optional<SoftSkill> skill = softSkillRepo.findByNomeCompetencia(skillName);
                        skill.ifPresent(colaboradorSoftSkills::add);
                    }
                    colaborador.setSoftSkills(colaboradorSoftSkills);
                    updatedColaboradores++;
                }

                // Special case for Pedro Mattos
                if ("pedro.mattos@email.com".equals(colaborador.getEmail())) {
                    Set<SoftSkill> extraSkills = new HashSet<>(colaborador.getSoftSkills());
                    List<String> extraSkillNames = Arrays.asList("Liderança", "Comunicação", "Inteligência emocional", "Negociação", "Coaching");
                    for (String skillName : extraSkillNames) {
                        Optional<SoftSkill> skill = softSkillRepo.findByNomeCompetencia(skillName);
                        skill.ifPresent(extraSkills::add);
                    }
                    colaborador.setSoftSkills(extraSkills);
                }
            }

            colaboradorRepo.saveAll(colaboradores);

            result.put("success", true);
            result.put("message", "Soft skills fixed successfully!");
            result.put("totalSoftSkills", softSkillRepo.findAll().size());
            result.put("colaboradoresUpdated", updatedColaboradores);

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Error: " + e.getMessage());
        }

        return result;
    }
}
