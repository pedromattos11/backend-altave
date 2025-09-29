package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.UsuarioRepository;
import br.com.altave.backend_altave.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> findAll() {
        return repo.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return repo.findById(id);
    }

    public Usuario save(Usuario obj) {
        return repo.save(obj);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
