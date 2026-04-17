package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Modelo;

// Contrato de operacoes de negocio para Modelo.
public interface ModeloService {
    List<Modelo> findAll();
    Modelo findById(Long id);
    List<Modelo> findByNome(String nome);
    Modelo create(Modelo modelo);
    void update(Long id, Modelo modelo);
    void delete(Long id);
}