package br.com.academico.endereco;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/enderecos")
@Tag(name = "Endereços")
public class EnderecoResource {
    
    private Endereco endereco;
    
    private IEnderecoService enderecoService;

    @Inject
    public EnderecoResource(@Named("enderecoservicedefault") IEnderecoService enderecoService){
        this.enderecoService = enderecoService;
    }

    //////////////////
    //Operações CRUD//
    //////////////////

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Endereços",
        description = "Recupera uma lista completa de endereços com todos os dados"
	) 
    public Response recuperar() {
        List<Endereco> listaEnderecos = new ArrayList<Endereco>();
        try {
            listaEnderecos = enderecoService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(listaEnderecos, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Endereço",
        description = "Recupera apenas um endereço a partir de seu id"
    )
    public Response recuperar(@PathParam("id") Long id) {
        Endereco endereco;
        try {
            endereco = enderecoService.recuperar(id);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response.ok(endereco, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Endereço",
        description = "Cria um endereço completo"
    )
    public Response criar(@Valid Endereco endereco){
        Long id;
        try {
            id = enderecoService.criar(endereco);
            endereco.setId(id);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
            .status(Response.Status.CREATED)
            .entity(endereco)
            .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Endereço",
        description = "Cria um endereço completo"
    )
    public Response atualizar(@PathParam("id") Long id, Endereco endereco){
        try {
            endereco = enderecoService.atualizar(id, endereco);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
            .status(Response.Status.NO_CONTENT)
            .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
        summary = "Deletar Endereço",
        description = "Deleta apenas um endereço a partir de seu id"
    )
    public Response deletar(@PathParam("id") Long id){
        try {
            enderecoService.deletar(id);
        } catch (Exception e) {
           return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
            .status(Response.Status.NO_CONTENT)
            .build();
    }

    //////////////////////
    //Operações Não CRUD//
    //////////////////////

    @PUT
    @Path("{id}/status")
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(
        summary = "Atualizar Status do Endereço",
        description = "Ativa ou desativa o status do endereço",
        requestBody = @RequestBody(
            description = "Status do endereço", 
			required = true,
            content = @Content(
                schema = @Schema(implementation = StatusEndereco.class)
            )
        )
    )
    public Response mudarStatus(@PathParam("id") Long id, String status){
        try {
            endereco = enderecoService.mudarStatus(id, StatusEndereco.fromString(status));
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
            .status(Response.Status.OK)
            .entity(endereco)
            .build();
    }

}
