package br.com.altave.backend_altave.dto;

import br.com.altave.backend_altave.model.Usuario;

public class LoginResponse {
    private Long id;
    private String nomeCompleto;
    private String email;
    private String role;

    public LoginResponse() {}

    public LoginResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
