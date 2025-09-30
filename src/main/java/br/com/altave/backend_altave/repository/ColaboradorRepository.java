package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    Optional<Colaborador> findByEmail(String email);
}
