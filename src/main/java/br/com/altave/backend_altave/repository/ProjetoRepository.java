package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    List<Projeto> findByColaboradorId(Integer colaboradorId);
}

