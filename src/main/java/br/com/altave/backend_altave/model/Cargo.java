    package br.com.altave.backend_altave.model;

    import jakarta.persistence.*;
    import java.util.*;
    import java.time.*;

    @Entity
    @Table(name = "cargo")
    public class Cargo {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(length = 60, nullable = false)
private String nomeCargo;

// getters/setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getNomeCargo() { return nomeCargo; }
public void setNomeCargo(String nomeCargo) { this.nomeCargo = nomeCargo; }

    }
