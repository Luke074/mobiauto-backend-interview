package com.mobiauto.lucas.domain.Oportunidades;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@GroupSequence({ OportunidadesRequest.class, OportunidadeConcluida.class })
public record OportunidadesRequest(
                Long id,
                @NotNull(message = "A oportunidade precisa de um usuario alocado!") Long usuario_id,
                StatusOportunidade status_oportunidade,
                @NotNull(message = "Data de aplicação não pode ser nula") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_aplicacao,
                @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_conclusao,
                Long revenda_id,
                @NotNull(message = "O id do veiculo não pode ser nulo") Long veiculo_id,
                String nome_cliente,
                @Email(message = "E-mail é invalido!") String email_cliente,
                @Size(min = 12, max = 13) String telefone_cliente) {
}
