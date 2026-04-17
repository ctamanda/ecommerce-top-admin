package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// Dados de entrada para criar/atualizar FichaTecnica.
public record FichaTecnicaRequestDTO(
    @NotNull @Positive Double peso,
    @NotBlank @Size(min = 3, max = 80) String elasticidade,
    @NotBlank @Size(min = 3, max = 80) String costura
) { }
