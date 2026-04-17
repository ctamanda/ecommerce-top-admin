package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Modelo;
import br.com.amanda.ecommercetop.repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService {

    @Inject
    ModeloRepository repository;

    public List<Modelo> findAll() { return repository.findAll().list(); }
    public Modelo findById(Long id) { return repository.findById(id); }
    public List<Modelo> findByNome(String nome) { return repository.findByNome(nome).list(); }

    @Transactional
    public Modelo create(Modelo modelo) {
        repository.persist(modelo);
        return modelo;
    }

    @Transactional
    public void update(Long id, Modelo modelo) {
        Modelo m = findById(id);
        m.setNome(modelo.getNome());
        m.setDescricao(modelo.getDescricao());
        m.setCategoria(modelo.getCategoria());
    }

    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}