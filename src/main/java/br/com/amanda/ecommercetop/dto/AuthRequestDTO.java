package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
    @NotBlank String login,
    @NotBlank String senha
) {}