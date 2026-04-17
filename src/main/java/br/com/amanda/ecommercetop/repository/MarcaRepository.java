package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

// Repositorio para consultas de Marca usando Panache.
@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    // Busca por nome com LIKE case-insensitive.
    public PanacheQuery<Marca> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}
