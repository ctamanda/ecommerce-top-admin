package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.TipoSustentacao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoSustentacaoRepository implements PanacheRepository<TipoSustentacao> {
    public PanacheQuery<TipoSustentacao> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1)", "%" + descricao + "%");
    }
}