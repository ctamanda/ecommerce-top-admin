package br.com.amanda.ecommercetop.dto;

import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// Dados de entrada para criar/atualizar Top e suas associacoes.
public record TopRequestDTO(
    @NotBlank @Size(min = 3, max = 60) String nome,
    @NotBlank @Size(min = 5, max = 150) String descricao,
    @NotNull @Positive Double preco,
    @NotBlank @Size(min = 3, max = 30) String codigo,
    @NotNull Long modeloId,
    @NotNull Long tamanhoId,
    @NotNull Long corId,
    @NotNull Long tipoSustentacaoId,
    @NotNull Long marcaId,
    @NotNull @Size(min = 1) Set<Long> materialIds,
    @NotNull @Valid FichaTecnicaRequestDTO fichaTecnica
) { }
