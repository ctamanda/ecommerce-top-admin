package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Tamanho;

// Contrato de operacoes de negocio para Tamanho.
public interface TamanhoService {
    List<Tamanho> findAll();
    Tamanho findById(Long id);
    List<Tamanho> findByDescricao(String descricao);
    Tamanho create(Tamanho tamanho);
    void update(Long id, Tamanho tamanho);
    void delete(Long id);
}