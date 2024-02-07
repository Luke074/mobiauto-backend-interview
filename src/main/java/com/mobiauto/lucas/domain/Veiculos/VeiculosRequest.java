package com.mobiauto.lucas.domain.Veiculos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeiculosRequest(
                Long id,
                @NotBlank(message = "Nome é obrigatório") String nome,
                @NotNull(message = "Valor do veiculo não pode ser nulo ou zerado") Float valor,
                @NotBlank(message = "Marca é obrigatório") String marca,
                @NotBlank(message = "Modelo é obrigatório") String modelo,
                @NotNull(message = "Ano do modelo não pode ser nulo") @Min(value = 1980) Integer ano_modelo,
                @NotBlank(message = "Versão é obrigatório") String versao) {
}
