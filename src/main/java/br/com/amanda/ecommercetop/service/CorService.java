package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Cor;

public interface CorService {
    List<Cor> findAll();
    Cor findById(Long id);
    List<Cor> findByNome(String nome);
    Cor create(Cor cor);
    void update(Long id, Cor cor);
    void delete(Long id);
}