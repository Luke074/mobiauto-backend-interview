package com.mobiauto.lucas.domain.Veiculos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VeiculosRequest(
        Long id,
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "E-mail é obrigatório") @Email(message = "E-mail não é válido.") String valor,
        @NotNull @Size(min = 6) String marca,
        @NotNull @Size(min = 6) String modelo,
        @NotNull @Size(min = 6) String ano_modelo,
        @NotNull String versao) {
}
