package com.mobiauto.lucas.domain.Oportunidades;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public interface OportunidadeConcluida {
    interface PrimeiraEtapa {
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date getData_conclusao();

    @NotBlank(message = "Nome do cliente é obrigatório", groups = PrimeiraEtapa.class)
    String getNome_cliente();

    @NotBlank(message = "Email do cliente é obrigatório", groups = PrimeiraEtapa.class)
    @Email(message = "E-mail é invalido!", groups = PrimeiraEtapa.class)
    String getEmail_cliente();

    @NotBlank(message = "Telefone do cliente é obrigatório", groups = PrimeiraEtapa.class)
    @Size(min = 12, max = 13)
    String getTelefone_cliente();
}
