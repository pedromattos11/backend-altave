package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO otimizado para listar HardSkills sem lazy loading do colaborador.
 * Evita problema N+1 query retornando apenas o ID do colaborador.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HardSkillDTO {
    private Integer id;
    private String nomeCompetencia;
    private Integer colaboradorId;
    private Boolean isHighlighted;
    private Integer orderPosition;
}
