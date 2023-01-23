package br.com.academico.endereco;

import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class EnderecoServiceTest {
    
    private IEnderecoRepository enderecoRepositoryMocked;

    private EnderecoService enderecoService;

    @Before
    public void init(){
        enderecoRepositoryMocked = mock(IEnderecoRepository.class);
        enderecoService = new EnderecoService(enderecoRepositoryMocked);
    }

    @Test
    public void teste_recuperar_lista_enderecos(){
        
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        List<Endereco> listEnderecoEsperada;
        listEnderecoEsperada = new ArrayList<Endereco>();
        listEnderecoEsperada.add(new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A"));
        listEnderecoEsperada.add(new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A"));

        given(enderecoRepositoryMocked.findAll()).willReturn(listEnderecoEsperada);

        //WHEN - Quando ocorrer uma ação (act)

        List<Endereco> listEnderecoResposta = enderecoService.listar();

        //THEN - Verifique a saída (assert)
        
        assertThat(listEnderecoResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços")
            .isInstanceOf(List.class);

        assertThat(listEnderecoResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços não nulla")
            .isNotNull();

        assertThat(listEnderecoResposta.size())
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços com dois endereços")
            .isEqualTo(2);

    }

    @Test
    public void teste_recuperar_endereco_por_id(){
       
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 1L;
        Endereco enderecoEsperado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEsperado.setId(idEndereco);
        
        given(enderecoRepositoryMocked.getById(idEndereco)).willReturn(Optional.of(enderecoEsperado));
 
        //WHEN - Quando ocorrer uma ação (act)
 
        Endereco enderecoResposta = enderecoService.recuperar(idEndereco);
  
        //THEN - Verifique a saída (assert)
         
        assertThat(enderecoResposta)
            .withFailMessage("O retorno do método recuperar não pode ser nullo")
            .isNotNull();     
  
        assertThat(enderecoResposta)
            .withFailMessage("O retorno do método recuperar deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);

    }    

    @Test
    public void teste_criar_endereco() {
       
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 10L;
        Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEnviado.setId(idEndereco);
         
        given(enderecoRepositoryMocked.save(enderecoEnviado)).willReturn(enderecoEnviado);
 
        //WHEN - Quando ocorrer uma ação (act)
 
        Long idEnderecoSalvo = enderecoService.criar(enderecoEnviado);
         
        //THEN - Verifique a saída (assert)

        assertThat(idEnderecoSalvo)
            .withFailMessage("O retorno do método criar não pode ser um ID nullo")
            .isNotNull();   
         
        assertThat(idEnderecoSalvo)
            .withFailMessage("O retorno do método criar deve ser um ID de um Endereco criado")
            .isInstanceOf(Long.class);

    }

    @Test
    public void teste_atualizar_endereco_por_id(){
       
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 10L;
        Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEnviado.setId(idEndereco);
 
        given(enderecoRepositoryMocked.getById(enderecoEnviado.getId()))
            .willReturn(Optional.of(enderecoEnviado));
 
        given(enderecoRepositoryMocked.update(enderecoEnviado))
            .willAnswer(invocation -> invocation.getArgument(0));
 
        //WHEN - Quando ocorrer uma ação (act)
 
        Endereco enderecoAtualizado = enderecoService.atualizar(enderecoEnviado.getId(), enderecoEnviado);
 
        //THEN - Verifique a saída (assert)
 
        assertThat(enderecoAtualizado)
            .withFailMessage("O retorno do método atualizar não pode ser um Endereço nullo")
            .isNotNull();   
          
        assertThat(enderecoAtualizado)
            .withFailMessage("O retorno do método atualizar deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);

    }

    @Test
    public void teste_deletar_endereco_por_id(){
       
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 10L;
        Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEnviado.setId(idEndereco);

        given(enderecoRepositoryMocked.getById(idEndereco))
            .willReturn(Optional.of(enderecoEnviado));
        
        willDoNothing().given(enderecoRepositoryMocked).delete(idEndereco);

        //WHEN - Quando ocorrer uma ação (act)

        Long idEnderecoDeletado = enderecoService.deletar(idEndereco);

        //THEN - Verifique a saída (assert)

        assertThat(idEnderecoDeletado)
            .withFailMessage("O retorno do método deletar não pode ser um ID nullo")
            .isNotNull();   
            
        assertThat(idEnderecoDeletado)
            .withFailMessage("O retorno do método deletar deve ser um ID de um Endereco deletado")
            .isInstanceOf(Long.class);

    }

    @Test
    public void teste_atlerar_status_endereco_por_id(){
    
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 10L;
        Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEnviado.setId(idEndereco);
        enderecoEnviado.setStatus(StatusEndereco.ATIVO);
 
        given(enderecoRepositoryMocked.getById(enderecoEnviado.getId()))
            .willReturn(Optional.of(enderecoEnviado));
 
        given(enderecoRepositoryMocked.update(enderecoEnviado))
            .willAnswer(invocation -> invocation.getArgument(0));
 
        //WHEN - Quando ocorrer uma ação (act)
 
        Endereco enderecoComStatusAlterado = enderecoService.mudarStatus(enderecoEnviado.getId(), enderecoEnviado.getStatus());
 
        //THEN - Verifique a saída (assert)
         
        assertThat(enderecoComStatusAlterado)
            .withFailMessage("O retorno do método mudar status não pode ser nullo")
            .isNotNull();     
 
        assertThat(enderecoComStatusAlterado)
            .withFailMessage("O retorno do método mudar status deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);

    }

    @Test
    public void teste_recuperar_endereco_por_id_inexistente(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 10L;
        Endereco enderecoEsperado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEsperado.setId(idEndereco);
 
        given(enderecoRepositoryMocked.getById(enderecoEsperado.getId()))
            .willReturn(Optional.empty());
 
        //WHEN - Quando ocorrer uma ação (act)
 
        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
            enderecoService.recuperar(enderecoEsperado.getId());
        });
 
        //THEN - Verifique a saída (assert)
 
        assertThat(exception.getMessage())
            .withFailMessage("Menssagem de execeção deve ser lançada e ser compatível com a esperada")
            .contains("O endereço não existe na base de dados.");

    }

    @Test
    public void teste_atualizar_endereco_por_id_inexistente(){
        
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idEndereco = 10L;
        Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoEnviado.setId(idEndereco);
  
        given(enderecoRepositoryMocked.getById(enderecoEnviado.getId()))
            .willReturn(Optional.empty());
 
        //WHEN - Quando ocorrer uma ação (act)
 
        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
            enderecoService.atualizar(enderecoEnviado.getId(), enderecoEnviado);
        });
  
        //THEN - Verifique a saída (assert)
 
        assertThat(exception.getMessage())
            .withFailMessage("Mensagem de execeção deve ser lançada e ser compatível com a esperada")
            .contains("O endereço não existe na base de dados.");

    }  


}
