package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Marca;
import br.com.amanda.ecommercetop.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// Implementacao do service de Marca com operacoes transacionais.
@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    // Acesso ao repositorio Panache.
    @Inject
    MarcaRepository repository;

    // Consultas simples delegadas ao repositorio.
    public List<Marca> findAll() { return repository.findAll().list(); }
    public Marca findById(Long id) { return repository.findById(id); }
    public List<Marca> findByNome(String nome) { return repository.findByNome(nome).list(); }

    // Cria e persiste uma nova marca.
    @Transactional
    public Marca create(Marca marca) {
        repository.persist(marca);
        return marca;
    }

    // Atualiza campos editaveis.
    @Transactional
    public void update(Long id, Marca marca) {
        Marca m = findById(id);
        m.setNome(marca.getNome());
    }

    // Remove a marca pelo id.
    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}
