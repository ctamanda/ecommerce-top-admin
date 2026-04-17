package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

@Entity
public class Cor extends DefaultEntity {

    private String nome;
    private String hex;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getHex() { return hex; }
    public void setHex(String hex) { this.hex = hex; }
}