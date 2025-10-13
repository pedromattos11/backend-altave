package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.model.Comentario;
import br.com.altave.backend_altave.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;

    public ComentarioService(ComentarioRepository repository) {
        this.repository = repository;
    }

    public List<Comentario> findAll() {
        return repository.findAll();
    }

    public Optional<Comentario> findById(Integer id) {
        return repository.findById(id);
    }

    public Comentario save(Comentario comentario) {
        // Garantir que a data seja definida
        if (comentario.getDataComentario() == null) {
            comentario.setDataComentario(LocalDate.now());
        }
        return repository.save(comentario);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    // Buscar comentários recebidos por um colaborador
    public List<Comentario> findComentariosByDestino(Integer idColaboradorDestino) {
        return repository.findByIdColaboradorDestinoOrderByDataComentarioDesc(idColaboradorDestino);
    }

    // Buscar comentários enviados por um colaborador
    public List<Comentario> findComentariosByOrigem(Integer idColaboradorOrigem) {
        return repository.findByIdColaboradorOrigemOrderByDataComentarioDesc(idColaboradorOrigem);
    }

    // Buscar comentários com dados dos colaboradores
    public List<Comentario> findComentariosComColaboradorByDestino(Integer idColaboradorDestino) {
        return repository.findComentariosComColaboradorByDestino(idColaboradorDestino);
    }

    // Criar um novo comentário
    public Comentario criarComentario(Integer idColaboradorOrigem, Integer idColaboradorDestino, String textoComentario) {
        Comentario comentario = new Comentario(idColaboradorOrigem, idColaboradorDestino, textoComentario);
        return save(comentario);
    }
}
