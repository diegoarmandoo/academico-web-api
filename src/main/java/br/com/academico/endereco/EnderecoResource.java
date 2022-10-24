package br.com.academico.endereco;

import java.util.ArrayList;
import java.util.List;

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

@Path("/enderecos")
public class EnderecoResource {
    
    private Endereco endereco;

    //////////////////
    //Operações CRUD//
    //////////////////

    @GET
    @Produces(MediaType.APPLICATION_JSON)   
    public Response recuperar() {
        List<Endereco> listaEnderecos = new ArrayList<Endereco>();
        listaEnderecos.add(new Endereco(49000, "Rua A", "Centro", "Aracaju", "Sergipe"));
		listaEnderecos.add(new Endereco(49700, "Rua C", "Atalaia", "Aracaju", "Sergipe"));
        return Response.ok(listaEnderecos, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") int id) {
        endereco = new Endereco(43000, "Rua B", "Centro", "Tobias Barreto", "Sergipe");
        endereco.setId(id);
        return Response.ok(endereco, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(Endereco endereco){
        endereco.setId(200);
        return Response
            .status(Response.Status.CREATED)
            .entity(endereco)
            .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Endereco endereco){
        return Response
            .status(Response.Status.NO_CONTENT)
            .build();
    }

    @DELETE
    @Path("{id}")
    public Response deletar(@PathParam("id") int id){
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
    public Response mudarStatus(@PathParam("id") int id, String status){
        endereco = new Endereco();
        endereco.setId(id);
        endereco.setStatus(StatusEndereco.fromString(status));
        return Response
            .status(Response.Status.OK)
            .entity(endereco)
            .build();
    }

}
