package br.com.academico.disciplina;

import java.util.ArrayList;
import java.util.List;

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

import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/disciplinas")
@Tag(name = "Disciplinas")
public class DisciplinaResource {
    
    private Disciplina disciplina;

    //////////////////
    //Operações CRUD//
	//////////////////

    @GET
    @Produces(MediaType.APPLICATION_JSON)   
    public Response recuperar() {
        List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
        listaDisciplinas.add(new Disciplina("Programação III", 240));
        listaDisciplinas.add(new Disciplina("Programação II", 200));
        listaDisciplinas.add(new Disciplina("Banco de Dados", 120));
        return Response.ok(listaDisciplinas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") int id) {
        disciplina = new Disciplina("Engenharia de Software", 200);
        disciplina.setId(id);
        return Response.ok(disciplina, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(@Valid Disciplina disciplina){
        disciplina.setId(200);
        return Response
            .status(Response.Status.CREATED)
            .entity(disciplina)
            .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Disciplina disciplina){
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
