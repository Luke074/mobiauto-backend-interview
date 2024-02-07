package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Oportunidades.Oportunidades;
import com.mobiauto.lucas.domain.Oportunidades.OportunidadesRepository;
import com.mobiauto.lucas.domain.Revendas.Revendas;
import com.mobiauto.lucas.domain.Revendas.RevendasRepository;
import com.mobiauto.lucas.domain.Revendas.RevendasRequest;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/revendas")
public class RevendasController {

    @Autowired
    private RevendasRepository revendaRepository;

    @Autowired
    private OportunidadesRepository oportunidadeRepository;

    @GetMapping
    public ResponseEntity<Object> getAllOportunidades() {
        List<Revendas> revendas = revendaRepository.findAll();
        if (revendas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma revenda encontrada.");
        }
        List<Map<String, String>> revendaReturn = new ArrayList<>();
        for (Revendas revenda : revendas) {
            Map<String, String> revendaInfo = new HashMap<>();
            revendaInfo.put("nome_social", revenda.getNome_social());
            revendaInfo.put("cnpj", revenda.getCnpj().replaceAll(
                    "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5"));

            revendaReturn.add(revendaInfo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(revendaReturn);
    }

    @PostMapping
    public ResponseEntity<String> createdRevenda(@RequestBody @Valid RevendasRequest data) {
        try {
            Revendas newRevenda = new Revendas();

            newRevenda.setNome_social(data.nome_social());
            newRevenda.setCnpj(data.cnpj().replaceAll("\\D", ""));
            newRevenda.setVeiculo_id(data.veiculo_id());

            Optional<Oportunidades> optionOportunidades = oportunidadeRepository.findById(data.oportunidade_id());

            if (optionOportunidades.isPresent()) {

                Revendas revenda = revendaRepository.save(newRevenda);
                Long revenda_id = revenda.getId();

                Oportunidades oportunidade = oportunidadeRepository.getReferenceById(data.id());

                Date dataAtual = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date data_conclusao = formatter.parse(formatter.format(dataAtual));

                oportunidade.setCliente_id(oportunidade.getCliente_id());
                oportunidade.setStatus_oportunidade("concluido");
                oportunidade.setData_aplicacao(oportunidade.getData_aplicacao());
                oportunidade.setData_conclusao(data_conclusao);
                oportunidade.setRevenda_id(revenda_id);
                oportunidadeRepository.save(oportunidade);

            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro no servidor, aguarde alguns instantes.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Revenda cadastrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar oportunidade: " + e.getMessage());
        }
    }

}
