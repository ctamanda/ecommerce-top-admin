package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Tamanho;
import br.com.amanda.ecommercetop.repository.TamanhoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TamanhoServiceImpl implements TamanhoService {

    @Inject
    TamanhoRepository repository;

    public List<Tamanho> findAll() { return repository.findAll().list(); }
    public Tamanho findById(Long id) { return repository.findById(id); }
    public List<Tamanho> findByDescricao(String descricao) { return repository.findByDescricao(descricao).list(); }

    @Transactional
    public Tamanho create(Tamanho tamanho) {
        repository.persist(tamanho);
        return tamanho;
    }

    @Transactional
    public void update(Long id, Tamanho tamanho) {
        Tamanho t = findById(id);
        t.setSigla(tamanho.getSigla());
        t.setDescricao(tamanho.getDescricao());
    }

    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}