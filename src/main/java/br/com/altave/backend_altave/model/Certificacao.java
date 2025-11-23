package br.com.altave.backend_altave.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "certificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60)
    private String nomeCertificacao;

    @Column(length = 60)
    private String instituicao;
}
