package com.mobiauto.lucas.domain.Usuarios;

import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mobiauto.lucas.domain.Roles.Roles;

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
    private String email;
    private String senha;
    private CargoUsuario cargo;
    private Long loja_id;
    private Roles role;

    public Usuarios(UsuariosRequest req) {
        this.nome = req.nome();
        this.email = req.email();
        this.senha = req.senha();
        this.cargo = req.cargo();
        this.loja_id = req.loja_id();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // if (this.role == Roles.ADMIN)
        // return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new
        // SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        if (this.role == Roles.PROPRIETARIO)
            return List.of(new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        else if (this.role == Roles.GERENTE)
            return List.of(new SimpleGrantedAuthority("ROLE_GERENTE"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_ASSISTENTE"));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return senha;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
