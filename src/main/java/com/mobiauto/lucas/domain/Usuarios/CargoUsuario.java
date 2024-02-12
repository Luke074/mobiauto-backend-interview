package com.mobiauto.lucas.domain.Usuarios;

public enum CargoUsuario {
    ADMIN("admin"),
    PROPRIETARIO("proprietario"),
    GERENTE("gerente"),
    ASSISTENTE("assistente");

    private String cargo;

    CargoUsuario(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}