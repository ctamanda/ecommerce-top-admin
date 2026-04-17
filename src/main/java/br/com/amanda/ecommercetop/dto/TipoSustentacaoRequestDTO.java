package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.NivelSustentacao;
import jakarta.validation.constraints.*;

// Dados de entrada para criar/atualizar TipoSustentacao.
public record TipoSustentacaoRequestDTO(
    @NotBlank String descricao,
    @NotNull NivelSustentacao nivel
) { }