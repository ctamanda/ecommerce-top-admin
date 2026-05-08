package br.com.amanda.ecommercetop.dto;

import br.com.amanda.ecommercetop.model.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
    @NotBlank @Size(min = 3, max = 30) String login,
    @NotBlank @Size(min = 6, max = 60) String senha,
    @NotNull Perfil perfil
) {}