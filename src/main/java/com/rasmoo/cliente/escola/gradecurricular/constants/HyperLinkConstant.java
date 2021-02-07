package com.rasmoo.cliente.escola.gradecurricular.constants;

public enum HyperLinkConstant {

    LISTAR("GET_ALL"),
    CONSULTAR("GET"),
    ATUALIZAR("UPDATE"),
    EXCLUIR("DELETE");

    private final String descricao;

    HyperLinkConstant(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
