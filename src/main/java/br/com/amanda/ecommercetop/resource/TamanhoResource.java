package br.com.amanda.ecommercetop.resource;

import java.util.List;

import br.com.amanda.ecommercetop.dto.TamanhoRequestDTO;
import br.com.amanda.ecommercetop.dto.TamanhoResponseDTO;
import br.com.amanda.ecommercetop.mapper.TamanhoMapper;
import br.com.amanda.ecommercetop.model.Tamanho;
import br.com.amanda.ecommercetop.service.TamanhoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

// Endpoints REST para CRUD de Tamanho.
@Path("/tamanhos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TamanhoResource {

    // Injeta o service com as regras de negocio.
    @Inject
    TamanhoService service;

    // Lista todos os tamanhos cadastrados.
    @GET
    public Response buscarTodos() {
        List<TamanhoResponseDTO> lista = service.findAll()
            .stream().map(TamanhoMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Filtra tamanhos por descricao (LIKE).
    @GET
    @Path("/find/{descricao}")
    public Response buscarPorDescricao(@PathParam("descricao") String descricao) {
        List<TamanhoResponseDTO> lista = service.findByDescricao(descricao)
            .stream().map(TamanhoMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Busca um tamanho por id.
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Tamanho t = service.findById(id);
        if (t == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(TamanhoMapper.toResponseDTO(t)).build();
    }

    // Cria um novo tamanho com validacao do DTO.
    @POST
    public Response incluir(@Valid TamanhoRequestDTO dto) {
        Tamanho t = service.create(TamanhoMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).entity(TamanhoMapper.toResponseDTO(t)).build();
    }

    // Atualiza um tamanho existente.
    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid TamanhoRequestDTO dto) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.update(id, TamanhoMapper.toEntity(dto));
        return Response.noContent().build();
    }

    // Remove um tamanho existente.
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(id);
        return Response.noContent().build();
    }
}