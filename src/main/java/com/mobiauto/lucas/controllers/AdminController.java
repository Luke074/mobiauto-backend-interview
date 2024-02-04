package com.mobiauto.lucas.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Admin.Admin;
import com.mobiauto.lucas.domain.Admin.AdminRepository;
import com.mobiauto.lucas.domain.Admin.AdminRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public ResponseEntity<Object> getAllAdmin() {
        List<Admin> administrators = adminRepository.findAll();

        if (administrators.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum administrador encontrado.");
        }

        List<Map<String, String>> adminReturn = new ArrayList<>();
        for (Admin admin : administrators) {
            Map<String, String> adminInfo = new HashMap<>();
            adminInfo.put("nome", admin.getNome());
            adminInfo.put("email", admin.getEmail());
            adminReturn.add(adminInfo);
        }

        return ResponseEntity.status(HttpStatus.OK).body(adminReturn);
    }

    @PostMapping
    public ResponseEntity<String> createdAdmin(@RequestBody @Valid AdminRequest data) {
        try {
            Admin newAdmin = new Admin(data);
            adminRepository.save(newAdmin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar administrador: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateAdmin(@RequestBody @Valid AdminRequest data) {
        Optional<Admin> optionalAdmin = adminRepository.findById(data.id());

        if (optionalAdmin.isPresent()) {
            try {
                Admin admin = adminRepository.getReferenceById(data.id());
                admin.setNome(data.nome());
                admin.setEmail(data.email());
                admin.setSenha(data.senha());

                return ResponseEntity.status(HttpStatus.OK).body("Admin atualizado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao cadastrar administrador: " + e.getMessage());
            }

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isPresent()) {
            try {
                Admin admin = adminRepository.getReferenceById(id);

                adminRepository.delete(admin);

                return ResponseEntity.status(HttpStatus.OK).body("Admin excluído com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao cadastrar administrador: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }
    }

}
