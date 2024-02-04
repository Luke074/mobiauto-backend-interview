package com.mobiauto.lucas.domain.Oportunidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "oportunidades")
@Entity(name = "oportunidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Oportunidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cliente_id;
    private Long revenda_id;
    private Date data_aplicacao;
    private Date data_conclusao;
    private StatusOportunidade status_oportunidade;

    public Oportunidades(OportunidadesRequest req) {
        this.cliente_id = req.cliente_id();
        this.revenda_id = req.revenda_id();
        this.data_aplicacao = req.data_aplicacao();
        this.data_conclusao = req.data_conclusao();
        this.status_oportunidade = req.status_oportunidade();
    }
}
