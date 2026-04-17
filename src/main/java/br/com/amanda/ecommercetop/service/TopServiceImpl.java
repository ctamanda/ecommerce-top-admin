package br.com.amanda.ecommercetop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.amanda.ecommercetop.dto.FichaTecnicaRequestDTO;
import br.com.amanda.ecommercetop.dto.TopRequestDTO;
import br.com.amanda.ecommercetop.model.Cor;
import br.com.amanda.ecommercetop.model.FichaTecnica;
import br.com.amanda.ecommercetop.model.Marca;
import br.com.amanda.ecommercetop.model.Material;
import br.com.amanda.ecommercetop.model.Modelo;
import br.com.amanda.ecommercetop.model.Tamanho;
import br.com.amanda.ecommercetop.model.TipoSustentacao;
import br.com.amanda.ecommercetop.model.Top;
import br.com.amanda.ecommercetop.repository.CorRepository;
import br.com.amanda.ecommercetop.repository.MarcaRepository;
import br.com.amanda.ecommercetop.repository.MaterialRepository;
import br.com.amanda.ecommercetop.repository.ModeloRepository;
import br.com.amanda.ecommercetop.repository.TamanhoRepository;
import br.com.amanda.ecommercetop.repository.TipoSustentacaoRepository;
import br.com.amanda.ecommercetop.repository.TopRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

// Implementacao do service de Top com montagem das associacoes.
@ApplicationScoped
public class TopServiceImpl implements TopService {

    // Repositorio principal de Top.
    @Inject
    TopRepository repository;

    // Repositorios das entidades relacionadas para validar ids.
    @Inject
    ModeloRepository modeloRepository;

    @Inject
    TamanhoRepository tamanhoRepository;

    @Inject
    CorRepository corRepository;

    @Inject
    TipoSustentacaoRepository tipoSustentacaoRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    MaterialRepository materialRepository;

    // Consultas simples delegadas ao repositorio.
    public List<Top> findAll() { return repository.findAll().list(); }
    public Top findById(Long id) { return repository.findById(id); }
    public List<Top> findByCodigo(String codigo) { return repository.findByCodigo(codigo).list(); }

    // Cria o Top e monta todas as associacoes a partir do DTO.
    @Transactional
    public Top create(TopRequestDTO dto) {
        Top t = new Top();
        applyRequest(t, dto);
        repository.persist(t);
        return t;
    }

    // Atualiza o Top existente reaplicando o DTO.
    @Transactional
    public void update(Long id, TopRequestDTO dto) {
        Top t = findById(id);
        applyRequest(t, dto);
    }

    // Remove o Top pelo id.
    @Transactional
    public void delete(Long id) { repository.deleteById(id); }

    // Aplica os dados do DTO e resolve todas as dependencias.
    private void applyRequest(Top t, TopRequestDTO dto) {
        t.setNome(dto.nome());
        t.setDescricao(dto.descricao());
        t.setPreco(dto.preco());
        t.setCodigo(dto.codigo());

        t.setModelo(getEntity(modeloRepository.findById(dto.modeloId()), "Modelo"));
        t.setTamanho(getEntity(tamanhoRepository.findById(dto.tamanhoId()), "Tamanho"));
        t.setCor(getEntity(corRepository.findById(dto.corId()), "Cor"));
        t.setTipoSustentacao(getEntity(tipoSustentacaoRepository.findById(dto.tipoSustentacaoId()), "TipoSustentacao"));
        t.setMarca(getEntity(marcaRepository.findById(dto.marcaId()), "Marca"));

        Set<Material> materiais = new HashSet<>();
        for (Long id : dto.materialIds()) {
            materiais.add(getEntity(materialRepository.findById(id), "Material"));
        }
        t.setMateriais(materiais);

        t.setFichaTecnica(applyFichaTecnica(t.getFichaTecnica(), dto.fichaTecnica()));
    }

    // Cria ou atualiza a ficha tecnica conforme o DTO.
    private FichaTecnica applyFichaTecnica(FichaTecnica fichaTecnica, FichaTecnicaRequestDTO dto) {
        FichaTecnica f = fichaTecnica == null ? new FichaTecnica() : fichaTecnica;
        f.setPeso(dto.peso());
        f.setElasticidade(dto.elasticidade());
        f.setCostura(dto.costura());
        return f;
    }

    // Valida existencia da entidade relacionada ou lança 404.
    private <T> T getEntity(T entity, String name) {
        if (entity == null) {
            throw new WebApplicationException(name + " nao encontrado", Response.Status.NOT_FOUND);
        }
        return entity;
    }
}
