package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO otimizado para listar SoftSkills.
 * SoftSkills não têm relacionamento direto com colaborador (ManyToMany),
 * então só retorna id e nome.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftSkillDTO {
    private Integer id;
    private String nomeCompetencia;
}
