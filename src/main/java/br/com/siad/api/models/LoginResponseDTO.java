package br.com.siad.api.models;

public record LoginResponseDTO(String nome, String email, UserRoles role, String token) {}
