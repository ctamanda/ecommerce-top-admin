package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

// Material que compoe o top.
@Entity
public class Material extends DefaultEntity {

    // Nome do material.
    private String nome;
    // Descricao da composicao do material.
    private String composicao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getComposicao() { return composicao; }
    public void setComposicao(String composicao) { this.composicao = composicao; }
}