package br.com.academico.endereco;

import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.TestProperties;

import javax.json.Json;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class EnderecoResourceTest extends JerseyTest {
    
    @Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(EnderecoResource.class);
	}

    @Test
    public void teste_recuperar_lista_enderecos(){

        //Construindo a requisição para teste
        Response response = target("/enderecos").request().get();

        //Recupera o corpo(body) da resposta
        List<Endereco> listEndereco = response.readEntity(new GenericType<List<Endereco>>() {});

        //Verificar os dados da resposta
        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma lista: ", listEndereco instanceof List<?> );
    }

    @Test
    public void teste_recuperar_endereco_por_id() {
        Response response = target("/enderecos/123").request().get();
        Endereco endereco = response.readEntity(new GenericType<Endereco>() {});
        
        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Endereço: ", endereco instanceof Endereco);
    }

    @Test
    public void teste_criar_endereco() {
        String enderecoJSON = Json.createObjectBuilder()
            .add("CEP", 49000)
            .add("bairro", "Centro")
            .add("cidade", "Aracaju")
            .add("estado", "Sergipe")
            .add("rua", "Rua da Feira")
            .build()
            .toString();

        Response response = target("/enderecos").request().post(Entity.json(enderecoJSON));
        Endereco endereco = response.readEntity(new GenericType<Endereco>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 201: ", Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Endereço: ", endereco instanceof Endereco);
    }

    @Test
    public void teste_atualizar_endereco_por_id() {
        String enderecoJSON = Json.createObjectBuilder()
            .add("CEP", 49000)
            .add("bairro", "Centro")
            .add("cidade", "Aracaju")
            .add("estado", "Sergipe")
            .add("rua", "Rua da Feira")
            .build()
            .toString();

        Response response = target("/enderecos/123").request().put(Entity.json(enderecoJSON));
        Endereco endereco = response.readEntity(new GenericType<Endereco>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_deletar_endereco_por_id() {
        Response response = target("/enderecos/123").request().delete();
        
        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_atlerar_status_endereco_por_id() {
        String status = "ATIVO";

        Response response = target("/enderecos/100/status").request().put(Entity.entity(status, MediaType.TEXT_PLAIN));
        Endereco endereco = response.readEntity(new GenericType<Endereco>() {});
        
        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Endereço: ", endereco instanceof Endereco);
    }

}
