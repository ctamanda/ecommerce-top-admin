package br.com.amanda.ecommercetop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

// Define o tipo de sustentacao e seu nivel.
@Entity
public class TipoSustentacao extends DefaultEntity {

    // Descricao do tipo de sustentacao.
    private String descricao;

    // Nivel de sustentacao persistido como string.
    @Enumerated(EnumType.STRING)
    private NivelSustentacao nivel;

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public NivelSustentacao getNivel() { return nivel; }
    public void setNivel(NivelSustentacao nivel) { this.nivel = nivel; }
}