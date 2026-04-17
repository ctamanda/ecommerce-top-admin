package br.com.amanda.ecommercetop.resource;

import java.util.List;

import br.com.amanda.ecommercetop.dto.ModeloRequestDTO;
import br.com.amanda.ecommercetop.dto.ModeloResponseDTO;
import br.com.amanda.ecommercetop.mapper.ModeloMapper;
import br.com.amanda.ecommercetop.model.Modelo;
import br.com.amanda.ecommercetop.service.ModeloService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

// Endpoints REST para CRUD de Modelo.
@Path("/modelos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModeloResource {

    // Injeta o service com as regras de negocio.
    @Inject
    ModeloService service;

    // Lista todos os modelos cadastrados.
    @GET
    public Response buscarTodos() {
        List<ModeloResponseDTO> lista = service.findAll()
            .stream().map(ModeloMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Filtra modelos por nome (LIKE).
    @GET
    @Path("/find/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        List<ModeloResponseDTO> lista = service.findByNome(nome)
            .stream().map(ModeloMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Busca um modelo por id.
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Modelo m = service.findById(id);
        if (m == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(ModeloMapper.toResponseDTO(m)).build();
    }

    // Cria um novo modelo com validacao do DTO.
    @POST
    public Response incluir(@Valid ModeloRequestDTO dto) {
        Modelo m = service.create(ModeloMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).entity(ModeloMapper.toResponseDTO(m)).build();
    }

    // Atualiza um modelo existente.
    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid ModeloRequestDTO dto) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.update(id, ModeloMapper.toEntity(dto));
        return Response.noContent().build();
    }

    // Remove um modelo existente.
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(id);
        return Response.noContent().build();
    }
}