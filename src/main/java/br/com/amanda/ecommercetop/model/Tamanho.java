package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

// Representa o tamanho do top (sigla e descricao).
@Entity
public class Tamanho extends DefaultEntity {

    // Sigla curta do tamanho (ex.: P, M, G).
    private String sigla;
    // Descricao legivel do tamanho.
    private String descricao;

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}