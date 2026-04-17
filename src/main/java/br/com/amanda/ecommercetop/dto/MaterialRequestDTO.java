package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.*;

public record MaterialRequestDTO(
    @NotBlank @Size(min = 3, max = 60) String nome,
    @NotBlank @Size(min = 3, max = 120) String composicao
) { }