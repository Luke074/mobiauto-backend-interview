package com.mobiauto.lucas.domain.Oportunidades;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public record OportunidadesRequest(
                Long id,
                @NotNull Long cliente_id,
                @NotNull StatusOportunidade status_oportunidade,
                @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_aplicacao,
                @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_conclusao,
                Long revenda_id) {
}
