package br.com.altave.backend_altave.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 60, nullable = false, unique = true)
    private String email;

    @Column(precision = 10, scale = 0, unique = true)
    private Long cpf;

    @Column(length = 2000)
    private String apresentacao;

    @Column(length = 255)
    private String profilePicturePath;

    private Integer perfil;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "colaborador_competencia",
       joinColumns = @JoinColumn(name = "colaborador_id"),
       inverseJoinColumns = @JoinColumn(name = "competencia_id"))
    private Set<Competencia> competencias = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "colaborador_softskill",
       joinColumns = @JoinColumn(name = "colaborador_id"),
       inverseJoinColumns = @JoinColumn(name = "softskill_id"))
    private Set<SoftSkill> softSkills = new HashSet<>();

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<HardSkill> hardSkills = new HashSet<>();

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("colaborador-experiencias")
    private Set<Experiencia> experiencias = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "colaborador_certificacao",
       joinColumns = @JoinColumn(name = "colaborador_id"),
       inverseJoinColumns = @JoinColumn(name = "certificacao_id"))
    private Set<Certificacao> certificacoes = new HashSet<>();

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("colaborador-projetos")
    private Set<Projeto> projetos = new HashSet<>();

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getCpf() { return cpf; }
    public void setCpf(Long cpf) { this.cpf = cpf; }
    public String getApresentacao() { return apresentacao; }
    public void setApresentacao(String apresentacao) { this.apresentacao = apresentacao; }
    public String getProfilePicturePath() { return profilePicturePath; }
    public void setProfilePicturePath(String profilePicturePath) { this.profilePicturePath = profilePicturePath; }
    public Integer getPerfil() { return perfil; }
    public void setPerfil(Integer perfil) { this.perfil = perfil; }
    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
    public Set<Competencia> getCompetencias() { return competencias; }
    public void setCompetencias(Set<Competencia> competencias) { this.competencias = competencias; }
    public Set<SoftSkill> getSoftSkills() { return softSkills; }
    public void setSoftSkills(Set<SoftSkill> softSkills) { this.softSkills = softSkills; }
    public Set<HardSkill> getHardSkills() { return hardSkills; }
    public void setHardSkills(Set<HardSkill> hardSkills) { this.hardSkills = hardSkills; }
    public Set<Experiencia> getExperiencias() { return experiencias; }
    public void setExperiencias(Set<Experiencia> experiencias) { this.experiencias = experiencias; }
    public Set<Certificacao> getCertificacoes() { return certificacoes; }
    public void setCertificacoes(Set<Certificacao> certificacoes) { this.certificacoes = certificacoes; }
    public Set<Projeto> getProjetos() { return projetos; }
    public void setProjetos(Set<Projeto> projetos) { this.projetos = projetos; }
}