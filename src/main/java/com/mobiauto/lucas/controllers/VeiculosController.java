package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculosRepository veiculosRepository;

    @GetMapping
    public ResponseEntity<Object> getAllVeiculos() {
        List<Veiculos> veiculos = veiculosRepository.findAll();
        if (veiculos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum veículo encontrado.");
        }

        List<Map<String, String>> veiculosReturn = new ArrayList<>();
        for (Veiculos veiculo : veiculos) {
            Map<String, String> veiculoInfo = new HashMap<>();
            veiculoInfo.put("nome", veiculo.getNome());
            veiculoInfo.put("valor", String.valueOf(veiculo.getValor()));
            veiculoInfo.put("marca", veiculo.getMarca());
            veiculoInfo.put("ano_modelo", String.valueOf(veiculo.getAno_modelo()));
            veiculoInfo.put("versao", veiculo.getVersao());
            veiculosReturn.add(veiculoInfo);
        }

        return ResponseEntity.status(HttpStatus.OK).body(veiculosReturn);
    }

    @PostMapping
    public ResponseEntity<String> createVeiculos(@RequestBody @Valid VeiculosRequest data) {
        try {
            Veiculos veiculos = new Veiculos(data);

            veiculos.setNome(data.nome());
            veiculos.setValor(data.valor());
            veiculos.setMarca(data.marca());
            veiculos.setAno_modelo(data.ano_modelo());
            veiculos.setVersao(data.versao());
            veiculosRepository.save(veiculos);

            veiculosRepository.save(veiculos);
            return ResponseEntity.status(HttpStatus.CREATED).body("Veiculo cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar administrador: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVeiculos(@RequestBody @Valid VeiculosRequest data) {
        Optional<Veiculos> optionVeiculos = veiculosRepository.findById(data.id());

        if (optionVeiculos.isPresent()) {
            try {
                Veiculos veiculo = veiculosRepository.getReferenceById(data.id());
                veiculo.setNome(data.nome());
                veiculo.setValor(data.valor());
                veiculo.setMarca(data.marca());
                veiculo.setAno_modelo(data.ano_modelo());
                veiculo.setVersao(data.versao());
                veiculosRepository.save(veiculo);
                return ResponseEntity.status(HttpStatus.OK).body("Veiculo atualizado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao cadastrar veiculo: " + e.getMessage());
            }

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVeiculos(@PathVariable Long id) {
        Optional<Veiculos> optionVeiculos = veiculosRepository.findById(id);
        if (optionVeiculos.isPresent()) {
            try {
                Veiculos veiculo = veiculosRepository.getReferenceById(id);
                veiculosRepository.delete(veiculo);
                return ResponseEntity.status(HttpStatus.OK).body("Veiculo deletado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao cadastrar veiculo: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }
    }
}
