package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Material;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaterialRepository implements PanacheRepository<Material> {
    public PanacheQuery<Material> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}