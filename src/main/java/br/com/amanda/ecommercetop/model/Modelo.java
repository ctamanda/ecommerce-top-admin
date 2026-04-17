package br.com.amanda.ecommercetop.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

// Modelo do top (categoria e descricao de referencia).
@Entity
public class Modelo extends DefaultEntity {

    // Nome comercial do modelo.
    private String nome;
    // Descricao detalhada do modelo.
    private String descricao;

    // Categoria do modelo persistida como string.
    @Enumerated(EnumType.STRING)
    private CategoriaModelo categoria;

    // Lista de tops associados a este modelo.
    @OneToMany(mappedBy = "modelo")
    private List<Top> tops = new ArrayList<>();

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public CategoriaModelo getCategoria() { return categoria; }
    public void setCategoria(CategoriaModelo categoria) { this.categoria = categoria; }

    public List<Top> getTops() { return tops; }
    public void setTops(List<Top> tops) { this.tops = tops; }
}