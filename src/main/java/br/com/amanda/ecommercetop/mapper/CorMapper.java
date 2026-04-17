package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.CorRequestDTO;
import br.com.amanda.ecommercetop.dto.CorResponseDTO;
import br.com.amanda.ecommercetop.model.Cor;

public class CorMapper {
    public static Cor toEntity(CorRequestDTO dto) {
        Cor c = new Cor();
        c.setNome(dto.nome());
        c.setHex(dto.hex());
        return c;
    }
    public static CorResponseDTO toResponseDTO(Cor c) {
        return new CorResponseDTO(c.getId(), c.getNome(), c.getHex());
    }
}