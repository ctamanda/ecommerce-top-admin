package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.CategoriaModelo;
import jakarta.validation.constraints.*;

public record ModeloRequestDTO(
    @NotBlank @Size(min = 3, max = 60) String nome,
    @NotBlank @Size(min = 5, max = 150) String descricao,
    @NotNull CategoriaModelo categoria
) { }