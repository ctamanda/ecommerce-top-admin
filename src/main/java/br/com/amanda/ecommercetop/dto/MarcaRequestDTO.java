package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Dados de entrada para criar/atualizar Marca.
public record MarcaRequestDTO(
    @NotBlank @Size(min = 3, max = 60) String nome
) { }
