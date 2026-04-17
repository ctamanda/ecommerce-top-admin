package br.com.amanda.ecommercetop.resource;

import java.util.List;

import br.com.amanda.ecommercetop.dto.MarcaRequestDTO;
import br.com.amanda.ecommercetop.dto.MarcaResponseDTO;
import br.com.amanda.ecommercetop.mapper.MarcaMapper;
import br.com.amanda.ecommercetop.model.Marca;
import br.com.amanda.ecommercetop.service.MarcaService;
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

// Endpoints REST para CRUD de Marca.
@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    // Injeta o service com as regras de negocio.
    @Inject
    MarcaService service;

    // Lista todas as marcas cadastradas.
    @GET
    public Response buscarTodos() {
        List<MarcaResponseDTO> lista = service.findAll()
            .stream().map(MarcaMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Filtra marcas por nome (LIKE).
    @GET
    @Path("/find/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        List<MarcaResponseDTO> lista = service.findByNome(nome)
            .stream().map(MarcaMapper::toResponseDTO).toList();
        return Response.ok(lista).build();
    }

    // Busca uma marca por id.
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Marca m = service.findById(id);
        if (m == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(MarcaMapper.toResponseDTO(m)).build();
    }

    // Cria uma nova marca com validacao do DTO.
    @POST
    public Response incluir(@Valid MarcaRequestDTO dto) {
        Marca m = service.create(MarcaMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).entity(MarcaMapper.toResponseDTO(m)).build();
    }

    // Atualiza uma marca existente.
    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid MarcaRequestDTO dto) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.update(id, MarcaMapper.toEntity(dto));
        return Response.noContent().build();
    }

    // Remove uma marca existente.
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(id);
        return Response.noContent().build();
    }
}
