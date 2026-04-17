package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.*;

public record CorRequestDTO(
    @NotBlank @Size(min = 3, max = 50) String nome,
    @NotBlank @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Hex inválido") String hex
) { }