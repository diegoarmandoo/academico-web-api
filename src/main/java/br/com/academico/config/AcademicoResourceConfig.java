package br.com.academico.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.academico.disciplina.DisciplinaResource;
import br.com.academico.endereco.EnderecoResource;
import br.com.academico.sala.SalaResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Acadêmico Web API",
        version = "1.0",
        description = "WEB API utilizando o estilo arquitetural REST"
	),
    servers = {
        @Server(
            description = "Desenvolvimento",
            url = "/academico-web-api"
        )
    }
)
public class AcademicoResourceConfig extends ResourceConfig {
    
    public AcademicoResourceConfig() {
        registrarEndPoints();
        configurarSwagger();
    }

    private void registrarEndPoints(){
        System.out.println("[Configurando as classes resources/endpoints da aplicação]");
        register(EnderecoResource.class);
        register(DisciplinaResource.class);
        register(SalaResource.class);
    }

    private void configurarSwagger(){
        System.out.println("[Configurando o Swagger | OPEN API]");
        OpenApiResource openApiResource = new OpenApiResource();
        register(openApiResource);
    }

}
