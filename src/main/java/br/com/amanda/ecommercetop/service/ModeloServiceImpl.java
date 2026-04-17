package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Modelo;
import br.com.amanda.ecommercetop.repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// Implementacao do service de Modelo com operacoes transacionais.
@ApplicationScoped
public class ModeloServiceImpl implements ModeloService {

    // Acesso ao repositorio Panache.
    @Inject
    ModeloRepository repository;

    // Consultas simples delegadas ao repositorio.
    public List<Modelo> findAll() { return repository.findAll().list(); }
    public Modelo findById(Long id) { return repository.findById(id); }
    public List<Modelo> findByNome(String nome) { return repository.findByNome(nome).list(); }

    // Cria e persiste um novo modelo.
    @Transactional
    public Modelo create(Modelo modelo) {
        repository.persist(modelo);
        return modelo;
    }

    // Atualiza campos editaveis.
    @Transactional
    public void update(Long id, Modelo modelo) {
        Modelo m = findById(id);
        m.setNome(modelo.getNome());
        m.setDescricao(modelo.getDescricao());
        m.setCategoria(modelo.getCategoria());
    }

    // Remove o modelo pelo id.
    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}