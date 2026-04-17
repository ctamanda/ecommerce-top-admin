package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.TamanhoRequestDTO;
import br.com.amanda.ecommercetop.dto.TamanhoResponseDTO;
import br.com.amanda.ecommercetop.model.Tamanho;

public class TamanhoMapper {
    public static Tamanho toEntity(TamanhoRequestDTO dto) {
        Tamanho t = new Tamanho();
        t.setSigla(dto.sigla());
        t.setDescricao(dto.descricao());
        return t;
    }
    public static TamanhoResponseDTO toResponseDTO(Tamanho t) {
        return new TamanhoResponseDTO(t.getId(), t.getSigla(), t.getDescricao());
    }
}