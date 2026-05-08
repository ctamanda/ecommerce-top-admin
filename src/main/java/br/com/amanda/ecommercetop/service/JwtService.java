package br.com.amanda.ecommercetop.service;

import java.util.Set;

import br.com.amanda.ecommercetop.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtService {

    private static final long EXPIRACAO_SEGUNDOS = 24 * 3600L;

    public String gerarToken(Usuario usuario) {
        return Jwt.issuer("ecommerce-top-admin")
                .upn(usuario.getLogin())
                .groups(Set.of(usuario.getPerfil().name()))
                .expiresIn(EXPIRACAO_SEGUNDOS)
                .sign();
    }
}