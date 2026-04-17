package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.MaterialRequestDTO;
import br.com.amanda.ecommercetop.dto.MaterialResponseDTO;
import br.com.amanda.ecommercetop.model.Material;

// Converte entre DTOs de Material e a entidade.
public class MaterialMapper {
    // Mapeia o request para entidade.
    public static Material toEntity(MaterialRequestDTO dto) {
        Material m = new Material();
        m.setNome(dto.nome());
        m.setComposicao(dto.composicao());
        return m;
    }
    // Mapeia a entidade para o response.
    public static MaterialResponseDTO toResponseDTO(Material m) {
        return new MaterialResponseDTO(m.getId(), m.getNome(), m.getComposicao());
    }
}