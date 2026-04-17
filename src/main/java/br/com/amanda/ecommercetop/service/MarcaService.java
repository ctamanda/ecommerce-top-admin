package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Marca;

// Contrato de operacoes de negocio para Marca.
public interface MarcaService {
    List<Marca> findAll();
    Marca findById(Long id);
    List<Marca> findByNome(String nome);
    Marca create(Marca marca);
    void update(Long id, Marca marca);
    void delete(Long id);
}
