package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.NivelSustentacao;
import jakarta.validation.constraints.*;

public record TipoSustentacaoRequestDTO(
    @NotBlank String descricao,
    @NotNull NivelSustentacao nivel
) { }