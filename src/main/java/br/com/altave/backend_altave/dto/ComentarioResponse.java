package br.com.altave.backend_altave.dto;

import br.com.altave.backend_altave.model.Comentario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioResponse {
    private Integer idComentario;
    private Integer idColaboradorOrigem;
    private String nomeColaboradorOrigem;
    private Integer idColaboradorDestino;
    private String nomeColaboradorDestino;
    private String textoComentario;
    private LocalDate dataComentario;

    public ComentarioResponse(Comentario comentario, String nomeColaboradorOrigem, String nomeColaboradorDestino) {
        this.idComentario = comentario.getIdComentario();
        this.idColaboradorOrigem = comentario.getIdColaboradorOrigem();
        this.nomeColaboradorOrigem = nomeColaboradorOrigem;
        this.idColaboradorDestino = comentario.getIdColaboradorDestino();
        this.nomeColaboradorDestino = nomeColaboradorDestino;
        this.textoComentario = comentario.getTextoComentario();
        this.dataComentario = comentario.getDataComentario();
    }
}
