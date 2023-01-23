package br.com.academico.endereco;

import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import br.com.academico.exception.AcademicoExceptionMapper;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
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

    private IEnderecoService enderecoServiceMocked;
    
    @Override
	protected Application configure() {
        enderecoServiceMocked = mock(IEnderecoService.class);
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig()
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .register(AcademicoExceptionMapper.class)
            .register(new EnderecoResource(enderecoServiceMocked));
	}

    @Test
    public void teste_recuperar_lista_enderecos(){
        //GIVEN - Dada alguma(s) pré-condição (arrange)
        List<Endereco> listEnderecoEsperada;
        listEnderecoEsperada = new ArrayList<Endereco>();
        listEnderecoEsperada.add(new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A"));
        listEnderecoEsperada.add(new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A"));

        given(enderecoServiceMocked.listar()).willReturn(listEnderecoEsperada);

        //WHEN - Quando ocorrer uma ação (act)
        Response response = target("/enderecos").request().get();
        List<Endereco> listEnderecoResposta = response.readEntity(new GenericType<List<Endereco>>() {});

        //THEN - Verifique a saída (assert)
        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
            .isEqualTo(Status.OK.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(listEnderecoResposta)
            .withFailMessage("As Listas devem ter o mesmo tamanho")
            .hasSameSizeAs(listEnderecoEsperada);
    }

    @Test
    public void teste_recuperar_endereco_por_id() {
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 1L;
        Endereco enderecoEsperado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEsperado.setId(idEndereco);
       
        given(enderecoServiceMocked.recuperar(idEndereco)).willReturn(enderecoEsperado);

        //WHEN - Quando ocorrer uma ação (act)

        Response response = target("/enderecos/{id}")
            .resolveTemplate("id", idEndereco)
            .request().get();

        Endereco enderecoResposta = response.readEntity(new GenericType<Endereco>() {});
 
        //THEN - Verifique a saída (assert)
        
        assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
             .isEqualTo(Status.OK.getStatusCode());  
 
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
             .isEqualTo(MediaType.APPLICATION_JSON);
 
        assertThat(enderecoResposta)
             .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Endereço")
             .isInstanceOf(Endereco.class);
    }

    @Test
    public void teste_criar_endereco() {
       //GIVEN - Dada alguma(s) pré-condição (arrange)

       Long idEndereco = 1L;
       Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
       enderecoEnviado.setId(idEndereco);
       
       given(enderecoServiceMocked.criar(enderecoEnviado)).willReturn(idEndereco);

       //WHEN - Quando ocorrer uma ação (act)

       Response response = target("/enderecos").request().post(Entity.json(enderecoEnviado));

       Endereco enderecoSalvo = response.readEntity(new GenericType<Endereco>() {});

       //THEN - Verifique a saída (assert)
       
       assertThat(response.getStatus())
           .withFailMessage("O codigo de status HTTP da resposta deve ser 201")
           .isEqualTo(Status.CREATED.getStatusCode());  

       assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
           .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
           .isEqualTo(MediaType.APPLICATION_JSON);

       assertThat(enderecoSalvo)
           .withFailMessage("O endereço salvo não pode ser nullo")
           .isNotNull();   

       assertThat(enderecoSalvo)
           .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Endereço")
           .isInstanceOf(Endereco.class);
    }

    @Test
    public void teste_atualizar_endereco_por_id() {
       //GIVEN - Dada alguma(s) pré-condição (arrange)

       Long idEndereco = 1L;
       Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
       enderecoEnviado.setId(idEndereco);
        
       given(enderecoServiceMocked.atualizar(idEndereco, enderecoEnviado)).willReturn(enderecoEnviado);

       //WHEN - Quando ocorrer uma ação (act)

       Response response = target("/enderecos/{id}")
           .resolveTemplate("id", idEndereco)
           .request()
           .put(Entity.json(enderecoEnviado));

       //THEN - Verifique a saída (assert)
       
       assertThat(response.getStatus())
           .withFailMessage("codigo de status HTTP da resposta deve ser 204")
           .isEqualTo(Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void teste_deletar_endereco_por_id() {
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 1L;
        
        given(enderecoServiceMocked.deletar(idEndereco)).willReturn(idEndereco);

        //WHEN - Quando ocorrer uma ação (act)

        Response response = target("/enderecos/{id}")
            .resolveTemplate("id", idEndereco)
            .request()
            .delete();

        //THEN - Verifique a saída (assert)
        
        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void teste_atlerar_status_endereco_por_id() {
      //GIVEN - Dada alguma(s) pré-condição (arrange)

      Long idEndereco = 1L;
      Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
      enderecoEnviado.setId(idEndereco);
      enderecoEnviado.setStatus(StatusEndereco.ATIVO);

      given(enderecoServiceMocked.mudarStatus(idEndereco, enderecoEnviado.getStatus())).willReturn(enderecoEnviado);

      //WHEN - Quando ocorrer uma ação (act)

      Response response = target("/enderecos/{id}/status")
          .resolveTemplate("id", idEndereco)
          .request()
          .put(Entity.entity(enderecoEnviado.getStatus().toString(), MediaType.TEXT_PLAIN));

      Endereco enderecoComStatusAlterado = response.readEntity(new GenericType<Endereco>() {});

      //THEN - Verifique a saída (assert)
      
      assertThat(response.getStatus())
          .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
          .isEqualTo(Status.OK.getStatusCode());

      assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
          .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
          .isEqualTo(MediaType.APPLICATION_JSON);    

      assertThat(enderecoComStatusAlterado)
          .withFailMessage("O endereço com status alterado não pode ser nullo")
          .isNotNull();   

      assertThat(enderecoComStatusAlterado)
          .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Endereço")
          .isInstanceOf(Endereco.class);
    }

    @Test
    public void teste_criar_endereco_sem_rua(){
       //GIVEN - Dada alguma(s) pré-condição (arrange)

       Long idEndereco = 1L;
       Endereco enderecoEnviado = new Endereco(49000000L, null, "Bairro A", "Cidade A", "Estado A");
       enderecoEnviado.setId(idEndereco);
        
       given(enderecoServiceMocked.criar(enderecoEnviado)).willReturn(idEndereco);

       //WHEN - Quando ocorrer uma ação (act)

       Response response = target("/enderecos").request().post(Entity.json(enderecoEnviado));

       String msg = response.readEntity(String.class); 

       //THEN - Verifique a saída (assert)
       
       assertThat(response.getStatus())
           .withFailMessage("O codigo de status HTTP da resposta deve ser 422")
           .isEqualTo(422);  

       assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
           .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser texto plano")
           .isEqualTo(MediaType.TEXT_PLAIN);

       assertThat(msg)
           .withFailMessage("O conteúdo da resposta deve conter uma mensagem de validação pré-definida")
           .contains("O atributo rua não pode ser nulo nem vazio.");
    }

    @Test
    public void teste_criar_endereco_rua_tamanho_invalido() {
         //GIVEN - Dada alguma(s) pré-condição (arrange)

         Long idEndereco = 1L;
         Endereco enderecoEnviado = new Endereco(49000000L, "Rua", "Bairro A", "Cidade A", "Estado A");
         enderecoEnviado.setId(idEndereco);
          
         given(enderecoServiceMocked.criar(enderecoEnviado)).willReturn(idEndereco);
  
         //WHEN - Quando ocorrer uma ação (act)
  
         Response response = target("/enderecos").request().post(Entity.json(enderecoEnviado));
  
         String msg = response.readEntity(String.class); 
 
         //THEN - Verifique a saída (assert)
         
         assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 422")
             .isEqualTo(422);  
 
         assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser texto plano")
             .isEqualTo(MediaType.TEXT_PLAIN);
 
         assertThat(msg)
             .withFailMessage("O conteúdo da resposta deve conter uma mensagem de validação pré-definida")
             .contains("O atributo rua deve conter no mínimo 5 e no máximo 50 caracteres."); 
    }

    @Test
    public void teste_criar_endereco_com_cep_invalido() {
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 1L;
        Endereco enderecoEnviado = new Endereco(8975L, "Rua", "Bairro A", "Cidade A", "Estado A");
        enderecoEnviado.setId(idEndereco);
          
        given(enderecoServiceMocked.criar(enderecoEnviado)).willReturn(idEndereco);
  
        //WHEN - Quando ocorrer uma ação (act)
  
        Response response = target("/enderecos").request().post(Entity.json(enderecoEnviado));
  
        String msg = response.readEntity(String.class); 
 
        //THEN - Verifique a saída (assert)
         
        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 422")
            .isEqualTo(422);  
 
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser texto plano")
            .isEqualTo(MediaType.TEXT_PLAIN);
 
        assertThat(msg)
            .withFailMessage("O conteúdo da resposta deve conter uma mensagem de validação pré-definida")
            .contains("O atributo CEP deve ser inteiro e ter no mínimo 8 algarismos.");
    }

}
