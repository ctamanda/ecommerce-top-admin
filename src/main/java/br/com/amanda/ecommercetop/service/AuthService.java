package br.com.amanda.ecommercetop.service;

import br.com.amanda.ecommercetop.dto.AuthRequestDTO;
import br.com.amanda.ecommercetop.dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO dto);
}