package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorBasicDTO {
    private Integer id;
    private String nome;
    private String email;
    private String apresentacao;
    private Integer perfil;
    private String cargoNome;
}