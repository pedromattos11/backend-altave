    package br.com.altave.backend_altave.model;

    import jakarta.persistence.*;
    import java.util.*;
    import java.time.*;

    @Entity
    @Table(name = "usuarios")
    public class Usuario {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "nome_completo")
private String nomeCompleto;

@Column(name = "data_nascimento")
private LocalDate dataNascimento;

@Column(unique = true)
private String cpf;

@Column(unique = true)
private String email;

private String senha;

// Getters and Setters
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getNomeCompleto() { return nomeCompleto; }
public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
public LocalDate getDataNascimento() { return dataNascimento; }
public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
public String getCpf() { return cpf; }
public void setCpf(String cpf) { this.cpf = cpf; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getSenha() { return senha; }
public void setSenha(String senha) { this.senha = senha; }

    }
