package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.TipoSustentacao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

// Repositorio para consultas de TipoSustentacao usando Panache.
@ApplicationScoped
public class TipoSustentacaoRepository implements PanacheRepository<TipoSustentacao> {
    // Busca por descricao com LIKE case-insensitive.
    public PanacheQuery<TipoSustentacao> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1)", "%" + descricao + "%");
    }
}