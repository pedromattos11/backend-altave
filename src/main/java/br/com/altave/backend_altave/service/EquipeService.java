package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.model.Colaborador;
import br.com.altave.backend_altave.repository.ColaboradorRepository;
import br.com.altave.backend_altave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EquipeService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Retorna "Minha Equipe" baseado na hierarquia do colaborador.
     * 
     * DIRETOR: Vê todos os 14 supervisores + todos os 79 colaboradores
     * SUPERVISOR: Vê seu diretor + seus N colaboradores + outros supervisores do mesmo diretor
     * COLABORADOR: Vê seu supervisor + colegas da mesma equipe
     */
    public Map<String, Object> getMinhaEquipe(Integer colaboradorId) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            Optional<Colaborador> colaboradorOpt = colaboradorRepository.findByIdWithRelations(colaboradorId);
            
            if (colaboradorOpt.isEmpty()) {
                result.put("success", false);
                result.put("error", "Colaborador não encontrado");
                return result;
            }
            
            Colaborador colaborador = colaboradorOpt.get();
            boolean isDiretor = colaboradorRepository.isDiretor(colaboradorId);
            boolean isSupervisor = colaboradorRepository.isSupervisor(colaboradorId);
            
            result.put("colaboradorId", colaboradorId);
            result.put("nome", colaborador.getNome());
            result.put("isDiretor", isDiretor);
            result.put("isSupervisor", isSupervisor);
            
            if (isDiretor) {
                // DIRETOR: Vê todos os supervisores e colaboradores
                List<Colaborador> supervisores = colaboradorRepository.findSupervisoresByDiretorId(colaboradorId);
                List<Colaborador> colaboradores = colaboradorRepository.findColaboradoresByDiretorId(colaboradorId);
                
                result.put("supervisores", simplifyColaboradores(supervisores));
                result.put("colaboradores", simplifyColaboradores(colaboradores));
                result.put("totalSupervisores", supervisores.size());
                result.put("totalColaboradores", colaboradores.size());
                
            } else if (isSupervisor) {
                // SUPERVISOR: Vê seu diretor, seus colaboradores e outros supervisores
                Colaborador diretor = colaborador.getDiretor();
                List<Colaborador> minhEquipe = colaboradorRepository.findBySupervisorId(colaboradorId);
                List<Colaborador> outrosSupervisores = colaboradorRepository.findSupervisoresByDiretorId(diretor.getId())
                    .stream()
                    .filter(s -> !s.getId().equals(colaboradorId))
                    .collect(Collectors.toList());
                
                result.put("diretor", simplifyColaborador(diretor));
                result.put("minhaEquipe", simplifyColaboradores(minhEquipe));
                result.put("outrosSupervisores", simplifyColaboradores(outrosSupervisores));
                result.put("totalMinhaEquipe", minhEquipe.size());
                
            } else {
                // COLABORADOR: Vê seu supervisor e colegas de equipe
                Colaborador supervisor = colaborador.getSupervisor();
                List<Colaborador> colegas = colaboradorRepository.findColegasDeEquipe(supervisor.getId(), colaboradorId);
                
                result.put("supervisor", simplifyColaborador(supervisor));
                result.put("colegas", simplifyColaboradores(colegas));
                result.put("totalColegas", colegas.size());
            }
            
            result.put("success", true);
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Simplifica um colaborador para evitar loop infinito de serialização
     */
    private Map<String, Object> simplifyColaborador(Colaborador c) {
        if (c == null) return null;
        
        Map<String, Object> simple = new HashMap<>();
        simple.put("id", c.getId());
        simple.put("nome", c.getNome());
        simple.put("email", c.getEmail());
        
        // Adicionado para buscar o ID do usuário real, que é o "perfil" para o frontend
        usuarioRepository.findByEmail(c.getEmail()).ifPresent(usuario -> {
            simple.put("perfil", usuario.getId());
        });
        
        if (c.getCargo() != null) {
            simple.put("cargo", c.getCargo().getNomeCargo());
        }
        
        return simple;
    }
    
    /**
     * Simplifica uma lista de colaboradores
     */
    private List<Map<String, Object>> simplifyColaboradores(List<Colaborador> colaboradores) {
        return colaboradores.stream()
            .map(this::simplifyColaborador)
            .collect(Collectors.toList());
    }
    
    /**
     * Retorna estatísticas da hierarquia organizacional
     */
    public Map<String, Object> getHierarchyStats() {
        Map<String, Object> stats = new HashMap<>();
        
        try {
            long totalDiretores = colaboradorRepository.count() - 
                colaboradorRepository.findAll().stream()
                    .filter(c -> c.getDiretor() != null || c.getSupervisor() != null)
                    .count();
            
            long totalSupervisores = colaboradorRepository.findAll().stream()
                .filter(c -> c.getDiretor() != null && c.getSupervisor() == null)
                .count();
            
            long totalColaboradores = colaboradorRepository.findAll().stream()
                .filter(c -> c.getSupervisor() != null)
                .count();
            
            stats.put("totalDiretores", totalDiretores);
            stats.put("totalSupervisores", totalSupervisores);
            stats.put("totalColaboradores", totalColaboradores);
            stats.put("success", true);
            
        } catch (Exception e) {
            stats.put("success", false);
            stats.put("error", e.getMessage());
        }
        
        return stats;
    }
}
