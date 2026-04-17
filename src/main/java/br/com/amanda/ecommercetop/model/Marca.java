package br.com.amanda.ecommercetop.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

// Marca proprietaria do top.
@Entity
public class Marca extends DefaultEntity {

    // Nome da marca.
    private String nome;

    // Tops associados a esta marca.
    @OneToMany(mappedBy = "marca")
    private List<Top> tops = new ArrayList<>();

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Top> getTops() { return tops; }
    public void setTops(List<Top> tops) { this.tops = tops; }
}
