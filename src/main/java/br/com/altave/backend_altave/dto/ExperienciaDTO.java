package br.com.altave.backend_altave.dto;

import br.com.altave.backend_altave.model.Experiencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaDTO {

    private Integer id;
    private String cargo;
    private String empresa;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private ColaboradorBasicoDTO colaborador;

    public ExperienciaDTO(Experiencia experiencia) {
        this.id = experiencia.getId();
        this.cargo = experiencia.getCargo();
        this.empresa = experiencia.getEmpresa();
        this.dataInicio = experiencia.getDataInicio();
        this.dataFim = experiencia.getDataFim();

        if (experiencia.getColaborador() != null) {
            this.colaborador = new ColaboradorBasicoDTO(
                    experiencia.getColaborador().getId(),
                    experiencia.getColaborador().getNome(),
                    experiencia.getColaborador().getEmail()
            );
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColaboradorBasicoDTO {
        private Integer id;
        private String nome;
        private String email;
    }
}