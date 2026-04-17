package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

@Entity
public class Tamanho extends DefaultEntity {

    private String sigla;
    private String descricao;

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}