package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Material;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

// Repositorio para consultas de Material usando Panache.
@ApplicationScoped
public class MaterialRepository implements PanacheRepository<Material> {
    // Busca por nome com LIKE case-insensitive.
    public PanacheQuery<Material> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}