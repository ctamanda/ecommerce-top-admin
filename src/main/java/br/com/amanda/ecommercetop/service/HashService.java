package br.com.amanda.ecommercetop.service;

import org.mindrot.jbcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashService {

    public String bcrypt(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Senha nao pode ser nula");
        }
        return BCrypt.hashpw(valor, BCrypt.gensalt());
    }

    public boolean verificarBcrypt(String valor, String hash) {
        if (valor == null || hash == null) {
            throw new IllegalArgumentException("Valor e hash nao podem ser nulos");
        }
        return BCrypt.checkpw(valor, hash);
    }
}