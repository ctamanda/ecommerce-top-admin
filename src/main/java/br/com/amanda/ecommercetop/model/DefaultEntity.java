package br.com.amanda.ecommercetop.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

// Base comum para entidades com id e data de cadastro automatica.
@MappedSuperclass
public class DefaultEntity {

    // Identificador tecnico gerado pelo banco.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Momento em que o registro foi criado.
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    // Preenche dataCadastro automaticamente antes do insert.
    @PrePersist
    private void preencherDataCadastro() {
        setDataCadastro(LocalDateTime.now());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}