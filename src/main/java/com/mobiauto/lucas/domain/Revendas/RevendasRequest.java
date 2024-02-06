package com.mobiauto.lucas.domain.Revendas;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RevendasRequest(
                Long id,
                @NotNull(message = "cliente_id não pode ser nulo") Long cliente_id,
                @NotBlank(message = "Nome social é obrigatório.") String nome_social,

                @NotBlank(message = "CNPJ é obrigatório") @CNPJ(message = "CNPJ não é válido.") String cnpj,

                Long veiculo_id,
                Long oportunidade_id) {
}
