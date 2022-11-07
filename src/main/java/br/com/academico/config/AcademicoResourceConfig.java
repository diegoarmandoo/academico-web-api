package br.com.academico.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.academico.disciplina.DisciplinaResource;
import br.com.academico.endereco.EnderecoResource;
import br.com.academico.sala.SalaResource;

@ApplicationPath("/")
public class AcademicoResourceConfig extends ResourceConfig {
    
    public AcademicoResourceConfig() {
        System.out.println("[Configurando as classes resources/endpoints da aplicação]");
        register(EnderecoResource.class);
        register(DisciplinaResource.class);
        register(SalaResource.class);
    }

}
