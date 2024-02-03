package com.mobiauto.lucas.domain.Lojas;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LojasRequest(
                Long id,
                @NotBlank(message = "Nome é obrigatório") String nome,
                @NotBlank(message = "CNPJ é obrigatório") @Email(message = "CNPJ não é válido.") String cnpj) {
}
