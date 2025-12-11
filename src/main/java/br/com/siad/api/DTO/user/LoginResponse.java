package br.com.siad.api.DTO.user;

public record LoginResponse(UserResponse user, String token, Long expiresIn) {}
