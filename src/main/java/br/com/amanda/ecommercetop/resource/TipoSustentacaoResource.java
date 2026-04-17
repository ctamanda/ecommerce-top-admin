package br.com.amanda.ecommercetop.resource;

import java.util.List;

import br.com.amanda.ecommercetop.dto.TipoSustentacaoRequestDTO;
import br.com.amanda.ecommercetop.dto.TipoSustentacaoResponseDTO;
import br.com.amanda.ecommercetop.mapper.TipoSustentacaoMapper;
import br.com.amanda.ecommercetop.model.TipoSustentacao;
import br.com.amanda.ecommercetop.service.TipoSustentacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

// Endpoints REST para CRUD de TipoSustentacao.
@Path("/tiposustentacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoSustentacaoResource {

    // Injeta o service com as regras de negocio.
    @Inject
    TipoSustentacaoService service;

    // Lista todos os tipos cadastrados.
    @GET
    public Response buscarTodos() {
        List<TipoSustentacaoResponseDTO> lista = service.findAll()
            .stream().map(TipoSustentacaoMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Filtra tipos por descricao (LIKE).
    @GET
    @Path("/find/{descricao}")
    public Response buscarPorDescricao(@PathParam("descricao") String descricao) {
        List<TipoSustentacaoResponseDTO> lista = service.findByDescricao(descricao)
            .stream().map(TipoSustentacaoMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Busca um tipo por id.
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        TipoSustentacao t = service.findById(id);
        if (t == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(TipoSustentacaoMapper.toResponseDTO(t)).build();
    }

    // Cria um novo tipo com validacao do DTO.
    @POST
    public Response incluir(@Valid TipoSustentacaoRequestDTO dto) {
        TipoSustentacao t = service.create(TipoSustentacaoMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).entity(TipoSustentacaoMapper.toResponseDTO(t)).build();
    }

    // Atualiza um tipo existente.
    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid TipoSustentacaoRequestDTO dto) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.update(id, TipoSustentacaoMapper.toEntity(dto));
        return Response.noContent().build();
    }

    // Remove um tipo existente.
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(id);
        return Response.noContent().build();
    }
}