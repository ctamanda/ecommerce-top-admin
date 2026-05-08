package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.Perfil;

public record UsuarioResponseDTO(
    Long id,
    String login,
    Perfil perfil
) {}