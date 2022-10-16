package br.com.academico.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import br.com.academico.endereco.EnderecoResource;

@ApplicationPath("/")
public class AcademicoResourceConfig extends ResourceConfig {
    
    public AcademicoResourceConfig() {
        System.out.println("[Configurando as classes resources/endpoints da aplicação]");
        register(EnderecoResource.class);
    }

}
