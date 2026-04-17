package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.MaterialRequestDTO;
import br.com.amanda.ecommercetop.dto.MaterialResponseDTO;
import br.com.amanda.ecommercetop.model.Material;

public class MaterialMapper {
    public static Material toEntity(MaterialRequestDTO dto) {
        Material m = new Material();
        m.setNome(dto.nome());
        m.setComposicao(dto.composicao());
        return m;
    }
    public static MaterialResponseDTO toResponseDTO(Material m) {
        return new MaterialResponseDTO(m.getId(), m.getNome(), m.getComposicao());
    }
}