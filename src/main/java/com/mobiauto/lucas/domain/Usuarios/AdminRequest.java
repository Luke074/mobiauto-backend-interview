package com.mobiauto.lucas.domain.Usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public interface AdminRequest {
    interface PrimeiraEtapa {
    }

    @NotBlank(message = "Nome é obrigatório", groups = PrimeiraEtapa.class)
    String getNome();

    @NotBlank(message = "Login é obrigatório", groups = PrimeiraEtapa.class)
    String getLogin();

    @NotBlank(message = "E-mail é obrigatório", groups = PrimeiraEtapa.class)
    @Email(message = "E-mail não é válido.", groups = PrimeiraEtapa.class)
    String getEmail();

    @NotBlank(message = "Senha é obrigatória", groups = PrimeiraEtapa.class)
    @Size(min = 6, max = 32, message = "A senha deve ter entre 6 e 32 caracteres", groups = PrimeiraEtapa.class)
    String getSenha();

    CargoUsuario getCargo();

    Long getLoja_id();
}
