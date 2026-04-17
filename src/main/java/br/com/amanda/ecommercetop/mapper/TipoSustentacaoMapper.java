package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.TipoSustentacaoRequestDTO;
import br.com.amanda.ecommercetop.dto.TipoSustentacaoResponseDTO;
import br.com.amanda.ecommercetop.model.TipoSustentacao;

// Converte entre DTOs de TipoSustentacao e a entidade.
public class TipoSustentacaoMapper {
    // Mapeia o request para entidade.
    public static TipoSustentacao toEntity(TipoSustentacaoRequestDTO dto) {
        TipoSustentacao t = new TipoSustentacao();
        t.setDescricao(dto.descricao());
        t.setNivel(dto.nivel());
        return t;
    }
    // Mapeia a entidade para o response.
    public static TipoSustentacaoResponseDTO toResponseDTO(TipoSustentacao t) {
        return new TipoSustentacaoResponseDTO(t.getId(), t.getDescricao(), t.getNivel());
    }
}