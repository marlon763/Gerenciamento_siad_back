package br.com.siad.api.models;

public enum UserRoles {

    ADMIN("admin"),
    CLIENTE("cliente"),
    CONTADOR("contador"),
    SUPORTE("suporte"),
    PARCEIRO("parceiro");

    private String roles;

    UserRoles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

}