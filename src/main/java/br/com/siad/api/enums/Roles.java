package br.com.siad.api.enums;

public enum Roles {

    ADMIN(1L),
    PARCEIRO(2L),
    SUPORTE(3L),
    CONTADOR(4l),
    CLIENTE(5L);

    long roleId;

    Roles(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

}
