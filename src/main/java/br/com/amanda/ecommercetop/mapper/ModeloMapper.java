package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.ModeloRequestDTO;
import br.com.amanda.ecommercetop.dto.ModeloResponseDTO;
import br.com.amanda.ecommercetop.model.Modelo;

public class ModeloMapper {
    public static Modelo toEntity(ModeloRequestDTO dto) {
        Modelo m = new Modelo();
        m.setNome(dto.nome());
        m.setDescricao(dto.descricao());
        m.setCategoria(dto.categoria());
        return m;
    }
    public static ModeloResponseDTO toResponseDTO(Modelo m) {
        return new ModeloResponseDTO(m.getId(), m.getNome(), m.getDescricao(), m.getCategoria());
    }
}