package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

@Entity
public class Modelo extends DefaultEntity {

    private String nome;
    private String descricao;
    private CategoriaModelo categoria;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public CategoriaModelo getCategoria() { return categoria; }
    public void setCategoria(CategoriaModelo categoria) { this.categoria = categoria; }
}