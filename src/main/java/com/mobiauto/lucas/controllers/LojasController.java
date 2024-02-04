package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mobiauto.lucas.domain.Admin.Admin;
import com.mobiauto.lucas.domain.Lojas.Lojas;
import com.mobiauto.lucas.domain.Lojas.LojasRepository;
import com.mobiauto.lucas.domain.Lojas.LojasRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/lojas")
public class LojasController {

    @Autowired
    private LojasRepository lojaRespository;

    @GetMapping
    public ResponseEntity<Object> getAllLojas() {
        List<Lojas> lojas = lojaRespository.findAll();

        if (lojas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum administrador encontrado.");
        }

        List<Map<String, String>> lojasReturn = new ArrayList<>();
        for (Lojas loja : lojas) {
            Map<String, String> lojaInfo = new HashMap<>();
            lojaInfo.put("nome", loja.getNome());
            lojaInfo.put("cnpj", loja.getCnpj());
            lojasReturn.add(lojaInfo);
        }

        return ResponseEntity.status(HttpStatus.OK).body(lojasReturn);
    }

    @PostMapping
    public ResponseEntity<String> createdLoja(@RequestBody @Valid LojasRequest data) {
        try {
            Lojas newLoja = new Lojas(data);
            lojaRespository.save(newLoja);
            return ResponseEntity.status(HttpStatus.CREATED).body("Loja cadastrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar loja: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateLoja(@RequestBody @Valid LojasRequest data) {
        Optional<Lojas> optionalLoja = lojaRespository.findById(data.id());
        if (optionalLoja.isPresent()) {
            try {
                Lojas loja = lojaRespository.getReferenceById(data.id());
                loja.setNome(data.nome());
                loja.setCnpj(data.cnpj());

                return ResponseEntity.status(HttpStatus.OK).body("Loja atualizado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao cadastrar loja: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLoja(@PathVariable Long id) {
        Optional<Lojas> optionalLoja = lojaRespository.findById(id);

        if (optionalLoja.isPresent()) {
            try {
                Lojas loja = lojaRespository.getReferenceById(id);
                lojaRespository.delete(loja);
                return ResponseEntity.status(HttpStatus.OK).body("Loja excluída com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao cadastrar loja: " + e.getMessage());

            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }
    }

}
