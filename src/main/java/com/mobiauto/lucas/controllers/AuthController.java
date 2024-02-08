package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Roles.AuthenticationDTO;
import com.mobiauto.lucas.domain.Usuarios.Usuarios;
import com.mobiauto.lucas.domain.Usuarios.UsuariosRepository;
import com.mobiauto.lucas.domain.Usuarios.UsuariosRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuariosRepository usuarioRepository;

    @PostMapping("/usuario/login")
    public ResponseEntity<String> loginUsuarios(@RequestBody @Valid AuthenticationDTO data) {
        var emailPassoword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(emailPassoword);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario logado com sucesso.");
    }

    @PostMapping("/usuario/register")
    public ResponseEntity<String> registerUsuario(@RequestBody @Valid UsuariosRequest data) {
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
                    .body("Usuario " + data.nome() + " cadastrado com o cargo" + data.cargo() + " com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

}
