package br.com.altave.backend_altave.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_disc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDISC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    @Column(name = "pontuacao_d", nullable = false)
    private Integer pontuacaoD;

    @Column(name = "pontuacao_i", nullable = false)
    private Integer pontuacaoI;

    @Column(name = "pontuacao_s", nullable = false)
    private Integer pontuacaoS;

    @Column(name = "pontuacao_c", nullable = false)
    private Integer pontuacaoC;

    @Column(name = "tipo_dominante", length = 1, nullable = false)
    private String tipoDominante;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
}
