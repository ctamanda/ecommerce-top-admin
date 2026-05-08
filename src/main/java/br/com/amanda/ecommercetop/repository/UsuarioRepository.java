package br.com.amanda.ecommercetop.repository;

import java.util.Optional;

import br.com.amanda.ecommercetop.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Optional<Usuario> findByLogin(String login) {
        return find("login", login).firstResultOptional();
    }
}