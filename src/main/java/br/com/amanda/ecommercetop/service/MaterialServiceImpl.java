package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Material;
import br.com.amanda.ecommercetop.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// Implementacao do service de Material com operacoes transacionais.
@ApplicationScoped
public class MaterialServiceImpl implements MaterialService {

    // Acesso ao repositorio Panache.
    @Inject
    MaterialRepository repository;

    // Consultas simples delegadas ao repositorio.
    public List<Material> findAll() { return repository.findAll().list(); }
    public Material findById(Long id) { return repository.findById(id); }
    public List<Material> findByNome(String nome) { return repository.findByNome(nome).list(); }

    // Cria e persiste um novo material.
    @Transactional
    public Material create(Material material) {
        repository.persist(material);
        return material;
    }

    // Atualiza campos editaveis.
    @Transactional
    public void update(Long id, Material material) {
        Material m = findById(id);
        m.setNome(material.getNome());
        m.setComposicao(material.getComposicao());
    }

    // Remove o material pelo id.
    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}