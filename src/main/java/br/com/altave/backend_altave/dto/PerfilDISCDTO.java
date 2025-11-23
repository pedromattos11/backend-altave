package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDISCDTO {
    private Long id;
    private Long usuarioId;
    private Integer pontuacaoD;
    private Integer pontuacaoI;
    private Integer pontuacaoS;
    private Integer pontuacaoC;
    private String tipoDominante;
    private LocalDateTime dataCriacao;
}
