package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    Optional<Colaborador> findByEmail(String email);
    
    // Busca colaborador com relações carregadas (evita LazyInitializationException)
    @Query("SELECT c FROM Colaborador c LEFT JOIN FETCH c.diretor LEFT JOIN FETCH c.supervisor LEFT JOIN FETCH c.cargo WHERE c.id = :id")
    Optional<Colaborador> findByIdWithRelations(@Param("id") Integer id);
    
    // Queries otimizadas para dashboard - não carrega objetos completos
    @Query("SELECT COUNT(DISTINCT c.id) FROM Colaborador c")
    long countTotalColaboradores();
    
    @Query("SELECT COUNT(DISTINCT c.id) FROM Colaborador c JOIN c.softSkills cs")
    long countColaboradoresComSoftSkills();
    
    @Query("SELECT COUNT(DISTINCT cs) FROM Colaborador c JOIN c.softSkills cs")
    long countTotalSoftSkillsMapeadas();
    
    @Query("SELECT COUNT(DISTINCT h) FROM HardSkill h")
    long countTotalHardSkillsMapeadas();
    
    // Query otimizada para listagem - retorna apenas dados essenciais sem eager loading
@Query("""
            SELECT new br.com.altave.backend_altave.dto.ColaboradorListDTO(
            c.id, 
            c.nome, 
            c.email, 
            COALESCE(ca.nomeCargo, 'Não definido'), 
            c.profilePicturePath,
            CAST((SELECT COUNT(DISTINCT h.id) FROM HardSkill h WHERE h.colaborador.id = c.id) AS int),
            CAST((SELECT COUNT(DISTINCT cs.id) FROM Colaborador co JOIN co.softSkills cs WHERE co.id = c.id) AS int),
            CAST((SELECT COUNT(DISTINCT cert.id) FROM Colaborador co JOIN co.certificacoes cert WHERE co.id = c.id) AS int)
        )
        FROM Colaborador c
        LEFT JOIN c.cargo ca
        ORDER BY c.nome ASC
        """)
    List<br.com.altave.backend_altave.dto.ColaboradorListDTO> findAllOptimized();
    
    // Query otimizada para findAll - carrega cargo com JOIN FETCH para evitar N+1
    @Query("SELECT DISTINCT c FROM Colaborador c LEFT JOIN FETCH c.cargo ORDER BY c.nome ASC")
    List<Colaborador> findAllWithCargo();
    
    // Queries para hierarquia organizacional
    
    // Busca colaboradores diretamente subordinados a um supervisor
    @Query("SELECT c FROM Colaborador c WHERE c.supervisor.id = :supervisorId")
    List<Colaborador> findBySupervisorId(@Param("supervisorId") Integer supervisorId);
    
    // Busca todos os supervisores de um diretor
    @Query("SELECT c FROM Colaborador c WHERE c.diretor.id = :diretorId AND c.supervisor IS NULL")
    List<Colaborador> findSupervisoresByDiretorId(@Param("diretorId") Integer diretorId);
    
    // Busca todos os colaboradores (não-supervisores) de um diretor
    @Query("SELECT c FROM Colaborador c WHERE c.diretor.id = :diretorId AND c.supervisor IS NOT NULL")
    List<Colaborador> findColaboradoresByDiretorId(@Param("diretorId") Integer diretorId);
    
    // Busca colegas de equipe (mesmo supervisor)
    @Query("SELECT c FROM Colaborador c WHERE c.supervisor.id = :supervisorId AND c.id <> :colaboradorId")
    List<Colaborador> findColegasDeEquipe(@Param("supervisorId") Integer supervisorId, @Param("colaboradorId") Integer colaboradorId);
    
    // Verifica se é diretor (não tem diretor_id e não tem supervisor_id)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Colaborador c WHERE c.id = :colaboradorId AND c.diretor IS NULL AND c.supervisor IS NULL")
    boolean isDiretor(@Param("colaboradorId") Integer colaboradorId);
    
    // Verifica se é supervisor (tem diretor_id mas não tem supervisor_id)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Colaborador c WHERE c.id = :colaboradorId AND c.diretor IS NOT NULL AND c.supervisor IS NULL")
    boolean isSupervisor(@Param("colaboradorId") Integer colaboradorId);
}
