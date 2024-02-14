package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Lojas.LojasRepository;
import com.mobiauto.lucas.domain.Usuarios.CargoUsuario;
import com.mobiauto.lucas.domain.Usuarios.Usuarios;
import com.mobiauto.lucas.domain.Usuarios.UsuariosRepository;
import com.mobiauto.lucas.domain.Usuarios.UsuariosRequest;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private LojasRepository lojasRepository;

    @GetMapping
    public ResponseEntity<Object> getAllUsuarios() {
        List<Usuarios> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado.");
        }

        List<Map<String, String>> usuarioReturn = new ArrayList<>();
        for (Usuarios usuario : usuarios) {
            Map<String, String> usuarioInfo = new HashMap<>();
            usuarioInfo.put("nome", usuario.getNome());
            usuarioInfo.put("email", usuario.getEmail());
            usuarioInfo.put("email", String.valueOf(usuario.getCargo()));
            usuarioReturn.add(usuarioInfo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioReturn);
    }

    @PostMapping
    public ResponseEntity<String> createdUsuario(@RequestBody @Valid UsuariosRequest data) {
        try {
            Usuarios usuario = new Usuarios(data);

            String encripty = new BCryptPasswordEncoder().encode(data.senha());

            usuario.setNome(data.nome());
            usuario.setEmail(data.email());
            usuario.setSenha(encripty);
            usuario.setCargo(data.cargo());
            usuario.setLoja_id(data.loja_id());

            usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario " + data.nome() + " cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUsuario(@RequestBody @Valid UsuariosRequest data) {
        Optional<Usuarios> optionUsuario = usuarioRepository.findById(data.id());

        if (optionUsuario.isPresent()) {
            try {
                Usuarios usuario = usuarioRepository.getReferenceById(data.id());
                String nomeAtualizado = data.nome() != null ? data.nome() : usuario.getNome();
                usuario.setNome(nomeAtualizado);
                String emailAtualizado = data.email() != null ? data.email() : usuario.getEmail();
                usuario.setEmail(emailAtualizado);
                String senhaAtualizado = data.senha() != null ? data.senha() : usuario.getSenha();
                usuario.setSenha(senhaAtualizado);
                CargoUsuario cargoAtualizado = data.cargo() != null ? data.cargo() : usuario.getCargo();
                usuario.setCargo(cargoAtualizado);

                Long lojaIdAtualizado = data.loja_id() != null ? data.loja_id() : usuario.getLoja_id();
                usuario.setLoja_id(lojaIdAtualizado);

                usuarioRepository.save(usuario);
                return ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao atualizar usuário: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        try {
            Usuarios usuario = usuarioRepository.getReferenceById(id);
            usuarioRepository.delete(usuario);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario excluído com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
