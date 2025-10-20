package br.com.altave.backend_altave.dto;

public class ColaboradorBasicDTO {
    private Integer id;
    private String nome;
    private String email;
    private String apresentacao;
    private Integer perfil;
    private String cargoNome;

    // Construtores
    public ColaboradorBasicDTO() {}

    public ColaboradorBasicDTO(Integer id, String nome, String email, String apresentacao, Integer perfil, String cargoNome) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.apresentacao = apresentacao;
        this.perfil = perfil;
        this.cargoNome = cargoNome;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getApresentacao() { return apresentacao; }
    public void setApresentacao(String apresentacao) { this.apresentacao = apresentacao; }

    public Integer getPerfil() { return perfil; }
    public void setPerfil(Integer perfil) { this.perfil = perfil; }

    public String getCargoNome() { return cargoNome; }
    public void setCargoNome(String cargoNome) { this.cargoNome = cargoNome; }
}