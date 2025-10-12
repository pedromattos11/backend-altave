package br.com.altave.backend_altave.model;

import jakarta.persistence.*;

@Entity
@Table(name = "soft_skill")
public class SoftSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_competencia")
    private String nomeCompetencia;

    public SoftSkill() {
    }

    public SoftSkill(String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompetencia() {
        return nomeCompetencia;
    }

    public void setNomeCompetencia(String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia;
    }
}
