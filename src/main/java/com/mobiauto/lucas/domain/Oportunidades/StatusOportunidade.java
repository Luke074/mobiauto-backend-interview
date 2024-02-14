package com.mobiauto.lucas.domain.Oportunidades;

public enum StatusOportunidade {
    NOVO("novo"),
    EM_ATENDIMENTO("em_atendimento"),
    CONCLUIDO("concluido");

    private String status;

    StatusOportunidade(String status) {
        this.status = status;
    }

    public String getStatusOportunidade() {
        return status;
    }
}
