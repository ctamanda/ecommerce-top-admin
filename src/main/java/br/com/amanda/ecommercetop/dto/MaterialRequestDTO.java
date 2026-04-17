package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.*;

// Dados de entrada para criar/atualizar Material.
public record MaterialRequestDTO(
    @NotBlank @Size(min = 3, max = 60) String nome,
    @NotBlank @Size(min = 3, max = 120) String composicao
) { }