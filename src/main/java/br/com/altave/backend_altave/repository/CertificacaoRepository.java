package br.com.altave.backend_altave.repository;

import br.com.altave.backend_altave.model.Certificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificacaoRepository extends JpaRepository<Certificacao, Integer> {
}
