package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.model.Colaborador;
import br.com.altave.backend_altave.repository.UsuarioRepository;
import br.com.altave.backend_altave.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final ColaboradorService colaboradorService;

    public UsuarioService(UsuarioRepository repo, ColaboradorService colaboradorService) {
        this.repo = repo;
        this.colaboradorService = colaboradorService;
    }

    public List<Usuario> findAll() {
        return repo.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return repo.findById(id);
    }

    public Usuario save(Usuario obj) {
        Usuario novoUsuario = repo.save(obj);

        Colaborador novoColaborador = new Colaborador();
        novoColaborador.setNome(novoUsuario.getNomeCompleto());
        novoColaborador.setEmail(novoUsuario.getEmail());
        // The CPF from frontend is a masked string. The backend expects a Long.
        // This logic might need to be more robust depending on the exact format.
        if (novoUsuario.getCpf() != null) {
            try {
                long cpfAsLong = Long.parseLong(novoUsuario.getCpf().replaceAll("[^0-9]", ""));
                novoColaborador.setCpf(cpfAsLong);
            } catch (NumberFormatException e) {
                // Handle case where CPF is not a valid number, maybe log it or set it to null
                novoColaborador.setCpf(null);
            }
        }

        novoColaborador.setApresentacao("Bem-vindo(a) ao seu perfil! Use o botão editar para customizar sua apresentação.");

        colaboradorService.save(novoColaborador);

        return novoUsuario;
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<Usuario> login(String email, String senha) {
        return repo.findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha));
    }
}
