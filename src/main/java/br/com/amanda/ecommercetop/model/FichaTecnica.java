package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

// Dados tecnicos do top (composicao 1:1 em Top).
@Entity
public class FichaTecnica extends DefaultEntity {

    // Peso aproximado do produto.
    private double peso;
    // Nivel/descricao de elasticidade.
    private String elasticidade;
    // Tipo de costura usada.
    private String costura;

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getElasticidade() { return elasticidade; }
    public void setElasticidade(String elasticidade) { this.elasticidade = elasticidade; }

    public String getCostura() { return costura; }
    public void setCostura(String costura) { this.costura = costura; }
}
