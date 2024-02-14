package com.mobiauto.lucas.domain.Revendas;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RevendasRequest(
        Long id,
        @NotBlank(message = "Nome social é obrigatório.") String nome_social,
        @NotBlank(message = "CNPJ é obrigatório") @CNPJ(message = "CNPJ não é válido.") String cnpj,
        @NotNull(message = "O id da oportunidade é obrigatório") Long oportunidade_id) {
}
