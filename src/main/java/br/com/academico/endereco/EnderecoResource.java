package br.com.academico.endereco;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/enderecos")
public class EnderecoResource {
    
    private Endereco endereco;

    @GET
    @Produces(MediaType.APPLICATION_JSON)   
    public Response recuperar() {
        endereco = new Endereco(49000, "Rua A", "Centro", "Aracaju", "Sergipe");
        return Response.ok(endereco, MediaType.APPLICATION_JSON).build();
    }

}
