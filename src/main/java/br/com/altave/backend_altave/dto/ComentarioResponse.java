package br.com.altave.backend_altave.dto;

import br.com.altave.backend_altave.model.Comentario;
import java.time.LocalDate;

public class ComentarioResponse {
    private Integer idComentario;
    private Integer idColaboradorOrigem;
    private String nomeColaboradorOrigem;
    private Integer idColaboradorDestino;
    private String nomeColaboradorDestino;
    private String textoComentario;
    private LocalDate dataComentario;

    public ComentarioResponse() {}

    public ComentarioResponse(Comentario comentario, String nomeColaboradorOrigem, String nomeColaboradorDestino) {
        this.idComentario = comentario.getIdComentario();
        this.idColaboradorOrigem = comentario.getIdColaboradorOrigem();
        this.nomeColaboradorOrigem = nomeColaboradorOrigem;
        this.idColaboradorDestino = comentario.getIdColaboradorDestino();
        this.nomeColaboradorDestino = nomeColaboradorDestino;
        this.textoComentario = comentario.getTextoComentario();
        this.dataComentario = comentario.getDataComentario();
    }

    // Getters e Setters
    public Integer getIdComentario() { return idComentario; }
    public void setIdComentario(Integer idComentario) { this.idComentario = idComentario; }

    public Integer getIdColaboradorOrigem() { return idColaboradorOrigem; }
    public void setIdColaboradorOrigem(Integer idColaboradorOrigem) { this.idColaboradorOrigem = idColaboradorOrigem; }

    public String getNomeColaboradorOrigem() { return nomeColaboradorOrigem; }
    public void setNomeColaboradorOrigem(String nomeColaboradorOrigem) { this.nomeColaboradorOrigem = nomeColaboradorOrigem; }

    public Integer getIdColaboradorDestino() { return idColaboradorDestino; }
    public void setIdColaboradorDestino(Integer idColaboradorDestino) { this.idColaboradorDestino = idColaboradorDestino; }

    public String getNomeColaboradorDestino() { return nomeColaboradorDestino; }
    public void setNomeColaboradorDestino(String nomeColaboradorDestino) { this.nomeColaboradorDestino = nomeColaboradorDestino; }

    public String getTextoComentario() { return textoComentario; }
    public void setTextoComentario(String textoComentario) { this.textoComentario = textoComentario; }

    public LocalDate getDataComentario() { return dataComentario; }
    public void setDataComentario(LocalDate dataComentario) { this.dataComentario = dataComentario; }
}
