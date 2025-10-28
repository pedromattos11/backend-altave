package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia, Integer> {
    List<Competencia> findByTipoHabilidade(String tipo);
}
