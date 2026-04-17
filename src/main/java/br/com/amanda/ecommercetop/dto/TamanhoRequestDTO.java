package br.com.amanda.ecommercetop.dto;

import jakarta.validation.constraints.*;

public record TamanhoRequestDTO(
    @NotBlank @Size(min = 1, max = 5) String sigla,
    @NotBlank @Size(min = 3, max = 60) String descricao
) { }