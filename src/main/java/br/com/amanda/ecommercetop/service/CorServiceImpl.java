package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Cor;
import br.com.amanda.ecommercetop.repository.CorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CorServiceImpl implements CorService {

    @Inject
    CorRepository repository;

    public List<Cor> findAll() { return repository.findAll().list(); }
    public Cor findById(Long id) { return repository.findById(id); }
    public List<Cor> findByNome(String nome) { return repository.findByNome(nome).list(); }

    @Transactional
    public Cor create(Cor cor) {
        repository.persist(cor);
        return cor;
    }

    @Transactional
    public void update(Long id, Cor cor) {
        Cor c = findById(id);
        c.setNome(cor.getNome());
        c.setHex(cor.getHex());
    }

    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}