package br.com.amanda.ecommercetop.service;

import java.util.List;
import br.com.amanda.ecommercetop.model.Material;
import br.com.amanda.ecommercetop.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MaterialServiceImpl implements MaterialService {

    @Inject
    MaterialRepository repository;

    public List<Material> findAll() { return repository.findAll().list(); }
    public Material findById(Long id) { return repository.findById(id); }
    public List<Material> findByNome(String nome) { return repository.findByNome(nome).list(); }

    @Transactional
    public Material create(Material material) {
        repository.persist(material);
        return material;
    }

    @Transactional
    public void update(Long id, Material material) {
        Material m = findById(id);
        m.setNome(material.getNome());
        m.setComposicao(material.getComposicao());
    }

    @Transactional
    public void delete(Long id) { repository.deleteById(id); }
}