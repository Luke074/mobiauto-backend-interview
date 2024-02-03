package com.mobiauto.lucas.domain.Veiculos;

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

@Table(name = "veiculos")
@Entity(name = "veiculos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Veiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String valor;
    private String marca;
    private String modelo;
    private String ano_modelo;
    private String versao;

    public Veiculos(VeiculosRequest req) {
        this.nome = req.nome();
        this.valor = req.valor();
        this.marca = req.marca();
        this.modelo = req.modelo();
        this.ano_modelo = req.ano_modelo();
        this.versao = req.versao();
    }
}
