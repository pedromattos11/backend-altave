package br.com.altave.backend_altave.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
