package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.CertificacaoRepository;
import br.com.altave.backend_altave.model.Certificacao;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CertificacaoService {

    private final CertificacaoRepository repo;

    public CertificacaoService(CertificacaoRepository repo) {
        this.repo = repo;
    }

    public List<Certificacao> findAll() {
        return repo.findAll();
    }

    public Optional<Certificacao> findById(Integer id) {
        return repo.findById(id);
    }

    public Certificacao save(Certificacao obj) {
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
