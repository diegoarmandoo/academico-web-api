package br.com.academico.sala;

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

import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/salas")
@Tag(name = "Salas")
public class SalaResource {
    
    private Sala sala;

    //////////////////
	//Operações CRUD//
	//////////////////

    @GET
    @Produces(MediaType.APPLICATION_JSON)   
    public Response recuperar() {
        List<Sala> listaSalas = new ArrayList<Sala>();
        listaSalas.add(new Sala(02,35,true,true,false));
        listaSalas.add(new Sala(03,20,true,true,true));
        return Response.ok(listaSalas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") int id) {
        sala = new Sala(05,100,false,true,true);
        sala.setId(id);
        return Response.ok(sala, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(Sala sala){
        sala.setId(200);
        return Response
            .status(Response.Status.CREATED)
            .entity(sala)
            .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Sala sala){
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

}
