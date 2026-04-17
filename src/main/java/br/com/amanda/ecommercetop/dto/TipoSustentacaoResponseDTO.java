package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.NivelSustentacao;

// Dados de saida do TipoSustentacao para respostas da API.
public record TipoSustentacaoResponseDTO(Long id, String descricao, NivelSustentacao nivel) { }