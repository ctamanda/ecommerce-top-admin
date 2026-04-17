package br.com.amanda.ecommercetop.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@MappedSuperclass
public class DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @PrePersist
    private void preencherDataCadastro() {
        setDataCadastro(LocalDateTime.now());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}