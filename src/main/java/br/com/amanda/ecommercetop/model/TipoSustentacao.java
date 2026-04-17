package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;

@Entity
public class TipoSustentacao extends DefaultEntity {

    private String descricao;
    private NivelSustentacao nivel;

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public NivelSustentacao getNivel() { return nivel; }
    public void setNivel(NivelSustentacao nivel) { this.nivel = nivel; }
}