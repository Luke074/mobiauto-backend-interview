package com.mobiauto.lucas.domain.Usuarios;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@GroupSequence({ UsuariosRequest.class, AdminRequest.class })
public record UsuariosRequest(
                Long id,
                @NotBlank(message = "Nome é obrigatório") String nome,
                @NotBlank(message = "Login é obrigatório") String login,
                @NotBlank(message = "E-mail é obrigatório") @Email(message = "E-mail não é válido.") String email,
                @NotBlank(message = "Senha é obrigatória") @Size(min = 6, max = 32) String senha,
                @NotNull(message = "Cargo não pode ser nulo") CargoUsuario cargo,
                Long loja_id) {
}
