package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Tamanho;
import br.com.amanda.ecommercetop.repository.TamanhoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// Implementacao do service de Tamanho com operacoes transacionais.
@ApplicationScoped
public class TamanhoServiceImpl implements TamanhoService {

    // Acesso ao repositorio Panache.
    @Inject
    TamanhoRepository repository;

    // Consultas simples delegadas ao repositorio.
    public List<Tamanho> findAll() { return repository.findAll().list(); }
    public Tamanho findById(Long id) { return repository.findById(id); }
    public List<Tamanho> findByDescricao(String descricao) { return repository.findByDescricao(descricao).list(); }

    // Cria e persiste um novo tamanho.
    @Transactional
    public Tamanho create(Tamanho tamanho) {
        repository.persist(tamanho);
        return tamanho;
    }

    // Atualiza campos editaveis.
    @Transactional
    public void update(Long id, Tamanho tamanho) {
        Tamanho t = findById(id);
        t.setSigla(tamanho.getSigla());
        t.setDescricao(tamanho.getDescricao());
    }

    // Remove o tamanho pelo id.
    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}