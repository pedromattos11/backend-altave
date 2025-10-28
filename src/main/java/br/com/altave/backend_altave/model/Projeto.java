package br.com.altave.backend_altave.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_projeto", length = 100)
    private String nomeProjeto;

    @Column(length = 500)
    private String descricao;

    @Column(length = 200)
    private String tecnologias;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(length = 500)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "certificacoes", "experiencias", "projetos", "hardSkills", "softSkills", "competencias"}, allowSetters = true)
    private Colaborador colaborador;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomeProjeto() { return nomeProjeto; }
    public void setNomeProjeto(String nomeProjeto) { this.nomeProjeto = nomeProjeto; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTecnologias() { return tecnologias; }
    public void setTecnologias(String tecnologias) { this.tecnologias = tecnologias; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public Colaborador getColaborador() { return colaborador; }
    public void setColaborador(Colaborador colaborador) { this.colaborador = colaborador; }
}

