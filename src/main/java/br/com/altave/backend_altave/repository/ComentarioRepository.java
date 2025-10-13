package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    
    // Buscar comentários de um colaborador específico (comentários recebidos)
    List<Comentario> findByIdColaboradorDestinoOrderByDataComentarioDesc(Integer idColaboradorDestino);
    
    // Buscar comentários feitos por um colaborador específico (comentários enviados)
    List<Comentario> findByIdColaboradorOrigemOrderByDataComentarioDesc(Integer idColaboradorOrigem);
    
    // Buscar comentários com dados do colaborador de origem
    @Query("SELECT c FROM Comentario c LEFT JOIN FETCH c.colaboradorDestino WHERE c.idColaboradorDestino = :idColaboradorDestino ORDER BY c.dataComentario DESC")
    List<Comentario> findComentariosComColaboradorByDestino(@Param("idColaboradorDestino") Integer idColaboradorDestino);
}
