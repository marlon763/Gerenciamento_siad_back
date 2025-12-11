package br.com.siad.api.enums;

import lombok.Getter;

public enum FilialTypes {

    SUPORTE("suporte"),
    DESENVOLVIMENTO("desenvolvimento"),
    PARCEIRO("parceiro");

    @Getter
    private String descricao;

    FilialTypes(String descricao) {this.descricao = descricao;}

}
