package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.TamanhoRequestDTO;
import br.com.amanda.ecommercetop.dto.TamanhoResponseDTO;
import br.com.amanda.ecommercetop.model.Tamanho;

// Converte entre DTOs de Tamanho e a entidade.
public class TamanhoMapper {
    // Mapeia o request para entidade.
    public static Tamanho toEntity(TamanhoRequestDTO dto) {
        Tamanho t = new Tamanho();
        t.setSigla(dto.sigla());
        t.setDescricao(dto.descricao());
        return t;
    }
    // Mapeia a entidade para o response.
    public static TamanhoResponseDTO toResponseDTO(Tamanho t) {
        return new TamanhoResponseDTO(t.getId(), t.getSigla(), t.getDescricao());
    }
}