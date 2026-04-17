package br.com.amanda.ecommercetop.repository;

import br.com.amanda.ecommercetop.model.Modelo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModeloRepository implements PanacheRepository<Modelo> {
    public PanacheQuery<Modelo> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}