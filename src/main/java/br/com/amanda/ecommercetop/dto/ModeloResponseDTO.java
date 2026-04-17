package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.CategoriaModelo;

// Dados de saida do Modelo para respostas da API.
public record ModeloResponseDTO(Long id, String nome, String descricao, CategoriaModelo categoria) { }