package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Tamanho;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

// Repositorio para consultas de Tamanho usando Panache.
@ApplicationScoped
public class TamanhoRepository implements PanacheRepository<Tamanho> {
    // Busca por descricao com LIKE case-insensitive.
    public PanacheQuery<Tamanho> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1)", "%" + descricao + "%");
    }
}