package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.CorRequestDTO;
import br.com.amanda.ecommercetop.dto.CorResponseDTO;
import br.com.amanda.ecommercetop.model.Cor;

// Converte entre DTOs de Cor e a entidade persistida.
public class CorMapper {
    // Mapeia o request para entidade.
    public static Cor toEntity(CorRequestDTO dto) {
        Cor c = new Cor();
        c.setNome(dto.nome());
        c.setHex(dto.hex());
        return c;
    }
    // Mapeia a entidade para o response.
    public static CorResponseDTO toResponseDTO(Cor c) {
        return new CorResponseDTO(c.getId(), c.getNome(), c.getHex());
    }
}