package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

// Entidade abstrata para atributos comuns de produtos.
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produto extends DefaultEntity {

    // Nome exibido ao usuario.
    private String nome;
    // Descricao comercial do produto.
    private String descricao;
    // Preco de venda.
    private double preco;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}
