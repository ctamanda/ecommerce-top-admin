package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.CategoriaModelo;

public record ModeloResponseDTO(Long id, String nome, String descricao, CategoriaModelo categoria) { }