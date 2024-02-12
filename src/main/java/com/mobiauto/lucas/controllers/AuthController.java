package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Response.ResponseDTO;
import com.mobiauto.lucas.domain.Roles.AuthenticationUsuarioDTO;
import com.mobiauto.lucas.domain.Usuarios.Usuarios;
import com.mobiauto.lucas.security.TokenService;

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
    private TokenService tokenService;

    @PostMapping("/usuario/login")
    public ResponseEntity<String> loginUsuarios(@RequestBody @Valid AuthenticationUsuarioDTO data) {
        var emailSenha = new UsernamePasswordAuthenticationToken(data.email(),
                data.senha());
        var auth = this.authenticationManager.authenticate(emailSenha);
        System.err.println(auth);

        var token = tokenService.gerarToken((Usuarios) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK)
                .body("Usuario logado com sucesso, token de acesso: /n" + new ResponseDTO(token));
    }

}
