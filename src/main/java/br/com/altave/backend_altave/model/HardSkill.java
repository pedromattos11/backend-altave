package br.com.altave.backend_altave.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "hard_skill")
public class HardSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_competencia", length = 60)
    private String nomeCompetencia;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    @JsonBackReference
    private Colaborador colaborador;

    // Constructors
    public HardSkill() {}

    public HardSkill(String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia;
    }

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomeCompetencia() { return nomeCompetencia; }
    public void setNomeCompetencia(String nomeCompetencia) { this.nomeCompetencia = nomeCompetencia; }
    public Colaborador getColaborador() { return colaborador; }
    public void setColaborador(Colaborador colaborador) { this.colaborador = colaborador; }

}
