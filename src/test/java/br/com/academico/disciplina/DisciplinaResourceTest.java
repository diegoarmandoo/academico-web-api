package br.com.academico.disciplina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import br.com.academico.exception.AcademicoExceptionMapper;

import javax.json.Json;
import javax.ws.rs.core.Application;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DisciplinaResourceTest extends JerseyTest {
    
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(DisciplinaResource.class)
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .register(AcademicoExceptionMapper.class);
    }

    @Test
    public void teste_criar_disciplina_com_nome_invalido() {
        String disciplinaJSON = Json.createObjectBuilder()
            .add("nome", "Programação II")
            .add("cargaHoraria", 203)
            .build()
            .toString();

        Response response = target("/disciplinas").request().post(Entity.json(disciplinaJSON));
        String msg = response.readEntity(String.class);
        
        assertEquals("O codigo de status HTTP da resposta deve ser 422: ", 422, response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser texto plano: ", MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve conter uma mensagem de validação pré-definida: ", msg.contains("O atributo nome da disciplina é inválido."));
    }

}
