package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

@Entity
public class Material extends DefaultEntity {

    private String nome;
    private String composicao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getComposicao() { return composicao; }
    public void setComposicao(String composicao) { this.composicao = composicao; }
}