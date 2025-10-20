package br.com.altave.backend_altave.dto;

import java.time.LocalDate;

public class ComentarioBasicDTO {
    private Integer idComentario;
    private Integer idColaboradorOrigem;
    private String nomeColaboradorOrigem;
    private Integer idColaboradorDestino;
    private String nomeColaboradorDestino;
    private String textoComentario;
    private LocalDate dataComentario;

    // Construtores
    public ComentarioBasicDTO() {}

    public ComentarioBasicDTO(Integer idComentario, Integer idColaboradorOrigem, String nomeColaboradorOrigem,
                             Integer idColaboradorDestino, String nomeColaboradorDestino, 
                             String textoComentario, LocalDate dataComentario) {
        this.idComentario = idComentario;
        this.idColaboradorOrigem = idColaboradorOrigem;
        this.nomeColaboradorOrigem = nomeColaboradorOrigem;
        this.idColaboradorDestino = idColaboradorDestino;
        this.nomeColaboradorDestino = nomeColaboradorDestino;
        this.textoComentario = textoComentario;
        this.dataComentario = dataComentario;
    }

    // Getters and Setters
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