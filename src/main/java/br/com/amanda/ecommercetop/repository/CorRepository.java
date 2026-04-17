package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Cor;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CorRepository implements PanacheRepository<Cor> {
    public PanacheQuery<Cor> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}