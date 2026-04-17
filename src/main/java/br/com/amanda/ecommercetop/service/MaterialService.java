package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Material;

// Contrato de operacoes de negocio para Material.
public interface MaterialService {
    List<Material> findAll();
    Material findById(Long id);
    List<Material> findByNome(String nome);
    Material create(Material material);
    void update(Long id, Material material);
    void delete(Long id);
}