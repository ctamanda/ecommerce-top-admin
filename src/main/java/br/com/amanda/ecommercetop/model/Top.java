package br.com.amanda.ecommercetop.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// Produto concreto que agrega as escolhas do top (modelo, tamanho, cor, etc.).
@Entity
@Table(name = "tops")
public class Top extends Produto {

    // Codigo interno/sku para busca rapida.
    private String codigo;

    // Associacao N:1 com Modelo.
    @ManyToOne(optional = false)
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    // Associacao N:1 com Tamanho.
    @ManyToOne(optional = false)
    @JoinColumn(name = "tamanho_id")
    private Tamanho tamanho;

    // Associacao N:1 com Cor.
    @ManyToOne(optional = false)
    @JoinColumn(name = "cor_id")
    private Cor cor;

    // Associacao N:1 com TipoSustentacao.
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_sustentacao_id")
    private TipoSustentacao tipoSustentacao;

    // Associacao N:1 com Marca.
    @ManyToOne(optional = false)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    // Agregacao N:N com Material.
    @ManyToMany
    @JoinTable(
        name = "top_material",
        joinColumns = @JoinColumn(name = "top_id"),
        inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private Set<Material> materiais = new HashSet<>();

    // Composicao 1:1 com FichaTecnica (cascata e orphanRemoval).
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ficha_tecnica_id", unique = true)
    private FichaTecnica fichaTecnica;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Modelo getModelo() { return modelo; }
    public void setModelo(Modelo modelo) { this.modelo = modelo; }

    public Tamanho getTamanho() { return tamanho; }
    public void setTamanho(Tamanho tamanho) { this.tamanho = tamanho; }

    public Cor getCor() { return cor; }
    public void setCor(Cor cor) { this.cor = cor; }

    public TipoSustentacao getTipoSustentacao() { return tipoSustentacao; }
    public void setTipoSustentacao(TipoSustentacao tipoSustentacao) { this.tipoSustentacao = tipoSustentacao; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    public Set<Material> getMateriais() { return materiais; }
    public void setMateriais(Set<Material> materiais) { this.materiais = materiais; }

    public FichaTecnica getFichaTecnica() { return fichaTecnica; }
    public void setFichaTecnica(FichaTecnica fichaTecnica) { this.fichaTecnica = fichaTecnica; }
}
