package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Tamanho;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TamanhoRepository implements PanacheRepository<Tamanho> {
    public PanacheQuery<Tamanho> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1)", "%" + descricao + "%");
    }
}