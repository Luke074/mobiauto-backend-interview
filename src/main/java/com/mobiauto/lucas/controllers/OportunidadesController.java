package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Oportunidades.Oportunidades;
import com.mobiauto.lucas.domain.Oportunidades.OportunidadesRepository;
import com.mobiauto.lucas.domain.Oportunidades.OportunidadesRequest;
import com.mobiauto.lucas.domain.Oportunidades.StatusOportunidade;
import com.mobiauto.lucas.domain.Veiculos.Veiculos;
import com.mobiauto.lucas.domain.Veiculos.VeiculosRepository;
import com.mobiauto.lucas.domain.Veiculos.VeiculosRequest;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadesController {

    @Autowired
    private OportunidadesRepository oportunidadesRepository;

    @GetMapping
    public ResponseEntity<Object> getAllOportunidades() {
        List<Oportunidades> oportunidades = oportunidadesRepository.findAll();
        if (oportunidades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma oportunidade encontrada.");
        }
        List<Map<String, String>> oportunidadeReturn = new ArrayList<>();
        for (Oportunidades oportunidade : oportunidades) {
            Map<String, String> oportunidadeInfo = new HashMap<>();
            oportunidadeInfo.put("oportunidade_id", String.valueOf(oportunidade.getId()));
            oportunidadeInfo.put("data_aplicacao", String.valueOf(oportunidade.getData_aplicacao()));
            oportunidadeInfo.put("data_conclusao", String.valueOf(oportunidade.getData_conclusao()));
            oportunidadeInfo.put("status_oportunidade", String.valueOf(oportunidade.getStatus_oportunidade()));

            oportunidadeReturn.add(oportunidadeInfo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(oportunidadeReturn);
    }

    @PostMapping
    public ResponseEntity<String> createdOportunidade(@RequestBody @Valid OportunidadesRequest data) {
        try {
            Oportunidades newOportunidade = new Oportunidades();

            newOportunidade.setStatus_oportunidade(StatusOportunidade.NOVO);
            newOportunidade.setData_aplicacao(data.data_aplicacao());
            newOportunidade.setCliente_id(data.cliente_id());

            oportunidadesRepository.save(newOportunidade);
            return ResponseEntity.status(HttpStatus.CREATED).body("Oportunidade cadastrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar oportunidade: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateOportunidade(@RequestBody @Valid OportunidadesRequest data) {
        try {

            Oportunidades oportunidade = oportunidadesRepository.getReferenceById(data.id());

            oportunidade.setStatus_oportunidade(data.status_oportunidade());
            oportunidade.setData_conclusao(data.data_conclusao());
            oportunidade.setRevenda_id(data.revenda_id());

            return ResponseEntity.status(HttpStatus.OK).body("Oportunidade atualizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar oportunidade: " + e.getMessage());

        }
    }
}
