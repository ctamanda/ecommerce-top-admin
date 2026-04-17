package br.com.amanda.ecommercetop.resource;

import java.util.List;

import br.com.amanda.ecommercetop.dto.CorRequestDTO;
import br.com.amanda.ecommercetop.dto.CorResponseDTO;
import br.com.amanda.ecommercetop.mapper.CorMapper;
import br.com.amanda.ecommercetop.model.Cor;
import br.com.amanda.ecommercetop.service.CorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/cores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CorResource {

    @Inject
    CorService service;

    @GET
    public Response buscarTodos() {
        List<CorResponseDTO> lista = service.findAll()
            .stream().map(CorMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/find/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        List<CorResponseDTO> lista = service.findByNome(nome)
            .stream().map(CorMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Cor c = service.findById(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(CorMapper.toResponseDTO(c)).build();
    }

    @POST
    public Response incluir(@Valid CorRequestDTO dto) {
        Cor c = service.create(CorMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).entity(CorMapper.toResponseDTO(c)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid CorRequestDTO dto) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.update(id, CorMapper.toEntity(dto));
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(id);
        return Response.noContent().build();
    }
}