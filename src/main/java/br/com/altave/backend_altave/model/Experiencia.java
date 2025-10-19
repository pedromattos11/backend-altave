    package br.com.altave.backend_altave.model;

    import jakarta.persistence.*;
    import java.util.*;
    import java.time.*;

    @Entity
    @Table(name = "experiencia")
    public class Experiencia {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(length = 60)
private String cargo;

@Column(length = 60)
private String empresa;

private LocalDate dataInicio;
private LocalDate dataFim;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "colaborador_id")
private Colaborador colaborador;

// getters/setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getCargo() { return cargo; }
public void setCargo(String cargo) { this.cargo = cargo; }
public String getEmpresa() { return empresa; }
public void setEmpresa(String empresa) { this.empresa = empresa; }
public LocalDate getDataInicio() { return dataInicio; }
public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
public LocalDate getDataFim() { return dataFim; }
public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
public Colaborador getColaborador() { return colaborador; }
public void setColaborador(Colaborador colaborador) { this.colaborador = colaborador; }

    }
