package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Top;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

// Repositorio para consultas de Top usando Panache.
@ApplicationScoped
public class TopRepository implements PanacheRepository<Top> {
    // Busca por codigo com LIKE case-insensitive.
    public PanacheQuery<Top> findByCodigo(String codigo) {
        return find("UPPER(codigo) LIKE UPPER(?1)", "%" + codigo + "%");
    }
}
