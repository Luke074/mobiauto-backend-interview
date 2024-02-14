package com.mobiauto.lucas.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Usuarios.AdminRequest;
import com.mobiauto.lucas.domain.Usuarios.CargoUsuario;
import com.mobiauto.lucas.domain.Usuarios.Usuarios;
import com.mobiauto.lucas.domain.Usuarios.UsuariosRepository;
import com.mobiauto.lucas.domain.Usuarios.UsuariosRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuariosRepository adminRepository;

    @GetMapping
    public ResponseEntity<Object> getAllAdmin() {
        List<Usuarios> administrators = adminRepository.findAll();
        if (administrators.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum administrador encontrado.");
        }

        List<Map<String, String>> adminReturn = new ArrayList<>();
        for (Usuarios admin : administrators) {
            if (admin.getCargo() == CargoUsuario.ADMIN) {
                Map<String, String> adminInfo = new HashMap<>();
                adminInfo.put("nome", admin.getNome());
                adminInfo.put("login", admin.getLogin());
                adminInfo.put("email", admin.getEmail());
                adminReturn.add(adminInfo);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(adminReturn);
    }

    @PostMapping
    public ResponseEntity<String> createdAdmin(@RequestBody @Validated(AdminRequest.class) UsuariosRequest data) {
        try {
            Usuarios admin = new Usuarios(data);

            String encripty = new BCryptPasswordEncoder().encode(data.senha());

            admin.setNome(data.nome());
            admin.setEmail(data.email());
            admin.setLogin(data.login());
            admin.setSenha(encripty);
            admin.setCargo(CargoUsuario.ADMIN);

            adminRepository.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar administrador: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateAdmin(@RequestBody @Validated(AdminRequest.class) UsuariosRequest data) {
        Optional<Usuarios> optionalAdmin = adminRepository.findById(data.id());

        if (optionalAdmin.isPresent()) {
            try {
                Usuarios admin = adminRepository.getReferenceById(data.id());

                if (admin.getCargo() != CargoUsuario.ADMIN) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Este usuario não é admin!");
                }
                String nomeAtualizado = data.nome() != null ? data.nome() : admin.getNome();
                admin.setNome(nomeAtualizado);
                String loginAtualizado = data.login() != null ? data.login()
                        : admin.getLogin();
                admin.setLogin(loginAtualizado);
                String emailAtualizado = data.email() != null ? data.email() : admin.getEmail();
                admin.setEmail(emailAtualizado);
                String senhaAtualizada = data.senha() != null ? data.senha() : admin.getSenha();
                admin.setSenha(senhaAtualizada);
                adminRepository.save(admin);

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
        Optional<Usuarios> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isPresent()) {
            try {
                Usuarios admin = adminRepository.getReferenceById(id);
                if (admin.getCargo() != CargoUsuario.ADMIN) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Este usuario não é admin!");
                }

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
