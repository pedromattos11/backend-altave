package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioBasicDTO {
    private Integer idComentario;
    private Integer idColaboradorOrigem;
    private String nomeColaboradorOrigem;
    private Integer idColaboradorDestino;
    private String nomeColaboradorDestino;
    private String textoComentario;
    private LocalDate dataComentario;
}