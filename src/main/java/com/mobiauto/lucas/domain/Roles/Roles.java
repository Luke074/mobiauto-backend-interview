package com.mobiauto.lucas.domain.Roles;

public enum Roles {
    ADMIN("admin"),
    PROPRIETARIO("proprietario"),
    GERENTE("gerente"),
    ASSISTENTE("assistente");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
