package br.com.amanda.ecommercetop.dto;

import java.util.Set;

// Dados de saida do Top para respostas da API.
public record TopResponseDTO(
    Long id,
    String nome,
    String descricao,
    double preco,
    String codigo,
    Long modeloId,
    Long tamanhoId,
    Long corId,
    Long tipoSustentacaoId,
    Long marcaId,
    Set<Long> materialIds,
    FichaTecnicaResponseDTO fichaTecnica
) { }
