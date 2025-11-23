package br.com.altave.backend_altave.dto;

import br.com.altave.backend_altave.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String nomeCompleto;
    private String email;
    private String role;

    public LoginResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
    }
}
