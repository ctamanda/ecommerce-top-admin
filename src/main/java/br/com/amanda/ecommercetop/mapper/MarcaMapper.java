package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.MarcaRequestDTO;
import br.com.amanda.ecommercetop.dto.MarcaResponseDTO;
import br.com.amanda.ecommercetop.model.Marca;

// Converte entre DTOs de Marca e a entidade.
public class MarcaMapper {
    // Mapeia o request para entidade.
    public static Marca toEntity(MarcaRequestDTO dto) {
        Marca m = new Marca();
        m.setNome(dto.nome());
        return m;
    }
    // Mapeia a entidade para o response.
    public static MarcaResponseDTO toResponseDTO(Marca m) {
        return new MarcaResponseDTO(m.getId(), m.getNome());
    }
}
