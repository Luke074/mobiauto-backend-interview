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

}
