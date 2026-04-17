package br.com.amanda.ecommercetop.resource;

import java.util.List;

import br.com.amanda.ecommercetop.dto.TopRequestDTO;
import br.com.amanda.ecommercetop.dto.TopResponseDTO;
import br.com.amanda.ecommercetop.mapper.TopMapper;
import br.com.amanda.ecommercetop.model.Top;
import br.com.amanda.ecommercetop.service.TopService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Endpoints REST para CRUD de Top.
@Path("/tops")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopResource {

    // Injeta o service com as regras de negocio.
    @Inject
    TopService service;

    // Lista todos os tops cadastrados.
    @GET
    public Response buscarTodos() {
        List<TopResponseDTO> lista = service.findAll()
            .stream().map(TopMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Filtra tops por codigo (LIKE).
    @GET
    @Path("/find/{codigo}")
    public Response buscarPorCodigo(@PathParam("codigo") String codigo) {
        List<TopResponseDTO> lista = service.findByCodigo(codigo)
            .stream().map(TopMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Busca um top por id.
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Top t = service.findById(id);
        if (t == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(TopMapper.toResponseDTO(t)).build();
    }

    // Cria um novo top com validacao do DTO.
    @POST
    public Response incluir(@Valid TopRequestDTO dto) {
        Top t = service.create(dto);
        return Response.status(Response.Status.CREATED).entity(TopMapper.toResponseDTO(t)).build();
    }

    // Atualiza um top existente.
    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid TopRequestDTO dto) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.update(id, dto);
        return Response.noContent().build();
    }

    // Remove um top existente.
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(id);
        return Response.noContent().build();
    }
}
