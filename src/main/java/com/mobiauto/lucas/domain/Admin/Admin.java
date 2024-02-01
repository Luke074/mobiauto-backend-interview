package com.mobiauto.lucas.domain.Admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "admin")
@Entity(name = "admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "E-mail é obrigatório")
    @Email
    private String email;
    @NotNull
    @Size(min = 6)
    private String senha;

    public Admin(AdminRequest req) {
        this.nome = req.nome();
        this.email = req.email();
        this.senha = req.senha();
    }
}
