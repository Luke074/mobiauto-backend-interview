package com.mobiauto.lucas.domain.Lojas;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;

public record LojasRequest(
                Long id,
                @NotBlank(message = "Nome é obrigatório") String nome,
                @NotBlank(message = "CNPJ é obrigatório") @CNPJ(message = "CNPJ não é válido.") String cnpj) {
}
