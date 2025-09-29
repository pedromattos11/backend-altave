    package br.com.altave.backend_altave.model;

    import jakarta.persistence.*;
    import java.util.*;
    import java.time.*;

    @Entity
    @Table(name = "soft_skill")
    public class SoftSkill {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(length = 60)
private String nomeCompetencia;

// getters/setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getNomeCompetencia() { return nomeCompetencia; }
public void setNomeCompetencia(String nomeCompetencia) { this.nomeCompetencia = nomeCompetencia; }

    }
