package br.com.altave.backend_altave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "colaborador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"competencias", "softSkills", "hardSkills", "experiencias", "certificacoes", "projetos", "supervisor", "diretor", "subordinados"})
@ToString(exclude = {"competencias", "softSkills", "hardSkills", "experiencias", "certificacoes", "projetos", "supervisor", "diretor", "subordinados"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    
    // Hierarquia organizacional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "supervisor", "diretor", "subordinados"})
    private Colaborador supervisor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diretor_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "supervisor", "diretor", "subordinados"})
    private Colaborador diretor;
    
    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "supervisor", "diretor", "subordinados"})
    private Set<Colaborador> subordinados = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "colaborador_competencia",
       joinColumns = @JoinColumn(name = "colaborador_id"),
       inverseJoinColumns = @JoinColumn(name = "competencia_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Competencia> competencias = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "colaborador_softskill",
       joinColumns = @JoinColumn(name = "colaborador_id"),
       inverseJoinColumns = @JoinColumn(name = "softskill_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<SoftSkill> softSkills = new HashSet<>();

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"colaborador", "hibernateLazyInitializer", "handler"})
    private Set<HardSkill> hardSkills = new HashSet<>();

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"colaborador", "hibernateLazyInitializer", "handler"})
    private Set<Experiencia> experiencias = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "colaborador_certificacao",
       joinColumns = @JoinColumn(name = "colaborador_id"),
       inverseJoinColumns = @JoinColumn(name = "certificacao_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Certificacao> certificacoes = new HashSet<>();

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"colaborador", "hibernateLazyInitializer", "handler"})
    private Set<Projeto> projetos = new HashSet<>();
}