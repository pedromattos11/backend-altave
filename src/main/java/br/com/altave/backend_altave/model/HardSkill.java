package br.com.altave.backend_altave.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hard_skill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"colaborador"})
@ToString(exclude = {"colaborador"})
public class HardSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_competencia", length = 60)
    private String nomeCompetencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id")
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "hardSkills", "softSkills", "competencias", "experiencias", "certificacoes", "projetos"})
    private Colaborador colaborador;

    @Column(name = "is_highlighted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isHighlighted = false;

    @Column(name = "order_position")
    private Integer orderPosition;
}
