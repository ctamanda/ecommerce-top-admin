package br.com.amanda.ecommercetop.dto;

public record AuthResponseDTO(
    String token,
    String type
) {}