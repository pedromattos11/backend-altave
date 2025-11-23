package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO otimizado para listagem de colaboradores.
 * Contém apenas campos essenciais, evitando queries N+1.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorListDTO {
    private Integer id;
    private String nome;
    private String email;
    private String cargoNome;
    private String profilePicturePath;
    private Integer totalHardSkills;
    private Integer totalSoftSkills;
    private Integer totalCertificacoes;
}
