package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Cor;
import br.com.amanda.ecommercetop.repository.CorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// Implementacao do service de Cor com operacoes transacionais.
@ApplicationScoped
public class CorServiceImpl implements CorService {

    // Acesso ao repositorio Panache.
    @Inject
    CorRepository repository;

    // Consultas simples delegadas ao repositorio.
    public List<Cor> findAll() { return repository.findAll().list(); }
    public Cor findById(Long id) { return repository.findById(id); }
    public List<Cor> findByNome(String nome) { return repository.findByNome(nome).list(); }

    // Cria e persiste uma nova cor.
    @Transactional
    public Cor create(Cor cor) {
        repository.persist(cor);
        return cor;
    }

    // Atualiza campos editaveis.
    @Transactional
    public void update(Long id, Cor cor) {
        Cor c = findById(id);
        c.setNome(cor.getNome());
        c.setHex(cor.getHex());
    }

    // Remove a cor pelo id.
    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}