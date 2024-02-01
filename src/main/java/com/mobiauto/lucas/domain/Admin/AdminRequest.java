package com.mobiauto.lucas.domain.Admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdminRequest(

        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "E-mail é obrigatório") @Email(message = "E-mail não é válido.") String email,
        @NotNull @Size(min = 6) String senha) {
}
