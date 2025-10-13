package br.com.altave.backend_altave.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Integer idComentario;

    @Column(name = "id_colaborador_origem", nullable = false)
    private Integer idColaboradorOrigem;

    @Column(name = "id_colaborador_destino", nullable = false)
    private Integer idColaboradorDestino;

    @Column(name = "texto_comentario", length = 500, nullable = false)
    private String textoComentario;

    @Column(name = "data_comentario", nullable = false)
    private LocalDate dataComentario;

    // Relacionamentos opcionais para buscar dados dos colaboradores
    @ManyToOne
    @JoinColumn(name = "id_colaborador_destino", insertable = false, updatable = false)
    private Colaborador colaboradorDestino;

    // Construtores
    public Comentario() {}

    public Comentario(Integer idColaboradorOrigem, Integer idColaboradorDestino, String textoComentario) {
        this.idColaboradorOrigem = idColaboradorOrigem;
        this.idColaboradorDestino = idColaboradorDestino;
        this.textoComentario = textoComentario;
        this.dataComentario = LocalDate.now();
    }

    // Getters e Setters
    public Integer getIdComentario() { return idComentario; }
    public void setIdComentario(Integer idComentario) { this.idComentario = idComentario; }

    public Integer getIdColaboradorOrigem() { return idColaboradorOrigem; }
    public void setIdColaboradorOrigem(Integer idColaboradorOrigem) { this.idColaboradorOrigem = idColaboradorOrigem; }

    public Integer getIdColaboradorDestino() { return idColaboradorDestino; }
    public void setIdColaboradorDestino(Integer idColaboradorDestino) { this.idColaboradorDestino = idColaboradorDestino; }

    public String getTextoComentario() { return textoComentario; }
    public void setTextoComentario(String textoComentario) { this.textoComentario = textoComentario; }

    public LocalDate getDataComentario() { return dataComentario; }
    public void setDataComentario(LocalDate dataComentario) { this.dataComentario = dataComentario; }

    public Colaborador getColaboradorDestino() { return colaboradorDestino; }
    public void setColaboradorDestino(Colaborador colaboradorDestino) { this.colaboradorDestino = colaboradorDestino; }
}
