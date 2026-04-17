package br.com.amanda.ecommercetop.resource;

import java.util.List;

import br.com.amanda.ecommercetop.dto.MaterialRequestDTO;
import br.com.amanda.ecommercetop.dto.MaterialResponseDTO;
import br.com.amanda.ecommercetop.mapper.MaterialMapper;
import br.com.amanda.ecommercetop.model.Material;
import br.com.amanda.ecommercetop.service.MaterialService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/materiais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaterialResource {

    @Inject
    MaterialService service;

    @GET
    public Response buscarTodos() {
        List<MaterialResponseDTO> lista = service.findAll()
            .stream().map(MaterialMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/find/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        List<MaterialResponseDTO> lista = service.findByNome(nome)
            .stream().map(MaterialMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Material m = service.findById(id);
        if (m == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(MaterialMapper.toResponseDTO(m)).build();
    }

    @POST
    public Response incluir(@Valid MaterialRequestDTO dto) {
        Material m = service.create(MaterialMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).entity(MaterialMapper.toResponseDTO(m)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid MaterialRequestDTO dto) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.update(id, MaterialMapper.toEntity(dto));
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