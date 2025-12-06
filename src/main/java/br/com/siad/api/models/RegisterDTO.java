package br.com.siad.api.models;

public record RegisterDTO (String name, String email, String password,UserRoles role) {}
