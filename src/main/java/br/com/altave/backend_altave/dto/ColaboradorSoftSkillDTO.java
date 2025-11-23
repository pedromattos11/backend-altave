package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorSoftSkillDTO {
    private Integer colaboradorId;
    private String nomeCompetencia;
}
