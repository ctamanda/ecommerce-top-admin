package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.*;

// Dados de entrada para criar/atualizar Cor, com validacoes de formato.
public record CorRequestDTO(
    @NotBlank @Size(min = 3, max = 50) String nome,
    @NotBlank @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Hex inválido") String hex
) { }