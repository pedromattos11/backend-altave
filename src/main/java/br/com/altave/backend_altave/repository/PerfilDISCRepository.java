package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.PerfilDISC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilDISCRepository extends JpaRepository<PerfilDISC, Long> {
    Optional<PerfilDISC> findByUsuario_Id(Long usuarioId);
    boolean existsByUsuario_Id(Long usuarioId);
    List<PerfilDISC> findAllByUsuario_IdIn(List<Long> usuarioIds);
}
