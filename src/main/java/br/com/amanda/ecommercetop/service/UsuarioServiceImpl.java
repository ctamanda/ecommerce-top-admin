package br.com.amanda.ecommercetop.service;

import java.util.List;

import br.com.amanda.ecommercetop.model.Usuario;
import br.com.amanda.ecommercetop.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Usuario findByLogin(String login) {
        return repository.findByLogin(login)
            .orElseThrow(() -> new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND));
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        if (repository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new WebApplicationException("Login ja existe", Status.BAD_REQUEST);
        }

        usuario.setSenhaHash(hashService.bcrypt(usuario.getSenhaHash()));
        repository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public void update(Long id, Usuario usuario) {
        Usuario u = findById(id);

        if (!u.getLogin().equals(usuario.getLogin()) &&
            repository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new WebApplicationException("Login ja existe", Status.BAD_REQUEST);
        }

        u.setLogin(usuario.getLogin());
        u.setPerfil(usuario.getPerfil());

        if (usuario.getSenhaHash() != null && !usuario.getSenhaHash().isEmpty()) {
            u.setSenhaHash(hashService.bcrypt(usuario.getSenhaHash()));
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario u = findById(id);
        repository.delete(u);
    }
}