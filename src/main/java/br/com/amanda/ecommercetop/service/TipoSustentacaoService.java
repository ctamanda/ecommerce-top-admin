package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.TipoSustentacao;

public interface TipoSustentacaoService {
    List<TipoSustentacao> findAll();
    TipoSustentacao findById(Long id);
    List<TipoSustentacao> findByDescricao(String descricao);
    TipoSustentacao create(TipoSustentacao tipo);
    void update(Long id, TipoSustentacao tipo);
    void delete(Long id);
}