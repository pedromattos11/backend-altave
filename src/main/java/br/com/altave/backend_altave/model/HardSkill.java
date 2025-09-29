package br.com.altave.backend_altave.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "hard_skill")
public class HardSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60)
    private String nomeCompetencia;

    @ManyToOne
    @JoinColumn(name = "competencia_id")
    private Competencia competencia;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    @JsonBackReference
    private Colaborador colaborador;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomeCompetencia() { return nomeCompetencia; }
    public void setNomeCompetencia(String nomeCompetencia) { this.nomeCompetencia = nomeCompetencia; }
    public Competencia getCompetencia() { return competencia; }
    public void setCompetencia(Competencia competencia) { this.competencia = competencia; }
    public Colaborador getColaborador() { return colaborador; }
    public void setColaborador(Colaborador colaborador) { this.colaborador = colaborador; }

}
