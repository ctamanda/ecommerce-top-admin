package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.TipoSustentacao;
import br.com.amanda.ecommercetop.repository.TipoSustentacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TipoSustentacaoServiceImpl implements TipoSustentacaoService {

    @Inject
    TipoSustentacaoRepository repository;

    public List<TipoSustentacao> findAll() { return repository.findAll().list(); }
    public TipoSustentacao findById(Long id) { return repository.findById(id); }
    public List<TipoSustentacao> findByDescricao(String descricao) { return repository.findByDescricao(descricao).list(); }

    @Transactional
    public TipoSustentacao create(TipoSustentacao tipo) {
        repository.persist(tipo);
        return tipo;
    }

    @Transactional
    public void update(Long id, TipoSustentacao tipo) {
        TipoSustentacao t = findById(id);
        t.setDescricao(tipo.getDescricao());
        t.setNivel(tipo.getNivel());
    }

    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}