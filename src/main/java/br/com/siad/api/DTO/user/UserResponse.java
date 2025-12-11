package br.com.siad.api.DTO.user;

import br.com.siad.api.enums.Roles;

import java.util.List;

public record UserResponse(String id , String name, String email, List<Roles> roles) {}
