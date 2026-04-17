package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.dto.TopRequestDTO;
import br.com.amanda.ecommercetop.model.Top;

// Contrato de operacoes de negocio para Top.
public interface TopService {
    List<Top> findAll();
    Top findById(Long id);
    List<Top> findByCodigo(String codigo);
    Top create(TopRequestDTO dto);
    void update(Long id, TopRequestDTO dto);
    void delete(Long id);
}
