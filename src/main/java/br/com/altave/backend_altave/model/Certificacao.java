    package br.com.altave.backend_altave.model;

    import jakarta.persistence.*;
    import java.util.*;
    import java.time.*;

    @Entity
    @Table(name = "certificacao")
    public class Certificacao {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(length = 60)
private String nomeCertificacao;

@Column(length = 60)
private String instituicao;

// getters/setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getNomeCertificacao() { return nomeCertificacao; }
public void setNomeCertificacao(String nomeCertificacao) { this.nomeCertificacao = nomeCertificacao; }
public String getInstituicao() { return instituicao; }
public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    }
