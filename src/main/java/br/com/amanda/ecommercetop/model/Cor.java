package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

// Representa uma cor disponivel para o top.
@Entity
public class Cor extends DefaultEntity {

    // Nome da cor exibido ao usuario.
    private String nome;
    // Codigo hex para exibicao no front.
    private String hex;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getHex() { return hex; }
    public void setHex(String hex) { this.hex = hex; }
}