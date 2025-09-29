    package br.com.altave.backend_altave.model;

    import jakarta.persistence.*;
    import java.util.*;
    import java.time.*;

    @Entity
    @Table(name = "competencia")
    public class Competencia {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(length = 60)
private String nomeCompetencia;

@Column(length = 60)
private String tipoHabilidade;

// getters/setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getNomeCompetencia() { return nomeCompetencia; }
public void setNomeCompetencia(String nomeCompetencia) { this.nomeCompetencia = nomeCompetencia; }
public String getTipoHabilidade() { return tipoHabilidade; }
public void setTipoHabilidade(String tipoHabilidade) { this.tipoHabilidade = tipoHabilidade; }

    }
