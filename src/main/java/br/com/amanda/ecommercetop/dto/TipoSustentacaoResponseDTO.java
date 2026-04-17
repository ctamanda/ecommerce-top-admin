package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.NivelSustentacao;

public record TipoSustentacaoResponseDTO(Long id, String descricao, NivelSustentacao nivel) { }