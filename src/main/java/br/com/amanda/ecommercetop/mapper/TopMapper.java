package br.com.amanda.ecommercetop.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.amanda.ecommercetop.dto.FichaTecnicaResponseDTO;
import br.com.amanda.ecommercetop.dto.TopResponseDTO;
import br.com.amanda.ecommercetop.model.FichaTecnica;
import br.com.amanda.ecommercetop.model.Top;

// Converte entidade Top para o DTO de resposta.
public class TopMapper {
    // Monta o DTO com ids das associacoes para simplificar o payload.
    public static TopResponseDTO toResponseDTO(Top t) {
        return new TopResponseDTO(
            t.getId(),
            t.getNome(),
            t.getDescricao(),
            t.getPreco(),
            t.getCodigo(),
            t.getModelo().getId(),
            t.getTamanho().getId(),
            t.getCor().getId(),
            t.getTipoSustentacao().getId(),
            t.getMarca().getId(),
            toMaterialIds(t),
            toFichaTecnicaDTO(t.getFichaTecnica())
        );
    }

    // Extrai os ids dos materiais associados.
    private static Set<Long> toMaterialIds(Top t) {
        return t.getMateriais().stream().map(m -> m.getId()).collect(Collectors.toSet());
    }

    // Converte a ficha tecnica, evitando NPE quando nao existe.
    private static FichaTecnicaResponseDTO toFichaTecnicaDTO(FichaTecnica f) {
        if (f == null) return null;
        return new FichaTecnicaResponseDTO(f.getId(), f.getPeso(), f.getElasticidade(), f.getCostura());
    }
}
