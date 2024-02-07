package com.mobiauto.lucas.domain.Revendas;

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

@Table(name = "revendas")
@Entity(name = "revendas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Revendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long veiculo_id;
    private String nome_social;
    private String cnpj;

    public Revendas(RevendasRequest req) {
        this.nome_social = req.nome_social();
        this.cnpj = req.cnpj();
        this.veiculo_id = req.veiculo_id();
    }
}
