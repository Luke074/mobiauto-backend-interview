package com.mobiauto.lucas.domain.Usuarios;

import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuarios implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private CargoUsuario cargo;
    private Long loja_id;

    public Usuarios(UsuariosRequest req) {
        this.nome = req.nome();
        this.login = req.login();
        this.email = req.email();
        this.senha = req.senha();
        this.cargo = req.cargo();
        this.loja_id = req.loja_id();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == CargoUsuario.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        else if (this.cargo == CargoUsuario.PROPRIETARIO)
            return List.of(new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        else if (this.cargo == CargoUsuario.GERENTE)
            return List.of(new SimpleGrantedAuthority("ROLE_GERENTE"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_ASSISTENTE"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
