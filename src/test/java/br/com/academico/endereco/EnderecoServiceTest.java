package br.com.academico.endereco;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EnderecoServiceTest {
    
    private EnderecoService enderecoService;

    @Before
    public void init(){
        enderecoService = new EnderecoService();
    }

    @Test
    public void teste_recuperar_lista_enderecos(){
        List<Endereco> listaEnderecos = enderecoService.listar();
        assertTrue("O retorno do método listar deve ser uma lista de endereços: ", listaEnderecos instanceof List<?>);
    }

    @Test
    public void teste_recuperar_endereco_por_id(){
        Endereco endereco = enderecoService.recuperar(10L);
        assertTrue("O retorno do método recuperar deve ser um objeto Endereco: ", endereco instanceof Endereco);
    }    

    @Test
    public void teste_criar_endereco() {
        Endereco endereco = new Endereco(33333L, "Rua H", "Centro", "Tobias Barreto", "Sergipe");
        Long idEndereco = enderecoService.criar(endereco);
        assertTrue("O retorno do método criar deve ser um ID de um Endereco criado: ", idEndereco == (Long)idEndereco);
    }

    @Test
    public void teste_atualizar_endereco_por_id(){
        Endereco endereco = new Endereco(44444L, "Rua N", "Centro", "Tobias Barreto", "Sergipe");
        Endereco enderecoAtualizado = enderecoService.atualizar(12L, endereco);
        assertTrue("O retorno do método atualizar deve ser um objeto Endereco: ", enderecoAtualizado instanceof Endereco);
    }

    @Test
    public void teste_deletar_endereco_por_id(){
        Long idEnderecoDeletado = enderecoService.deletar(587L);
        assertTrue("O retorno do método deletar deve ser um ID do Endereco deletado: ", idEnderecoDeletado == (Long)idEnderecoDeletado);
    }

    
    @Test
    public void teste_atlerar_status_endereco_por_id(){
        Endereco enderecoAtualizado = enderecoService.mudarStatus(12L, StatusEndereco.DESATIVO);
        assertTrue("O retorno do método mudar status deve ser um objeto Endereco: ", enderecoAtualizado instanceof Endereco);
    }

    @Test
    public void teste_recuperar_endereco_por_id_inexistente(){
        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
            Endereco endereco = enderecoService.recuperar(999L);
        });     
        String mensagemEsperada = "O endereço não existe na base de dados.";
        String MensagemLancada = exception.getMessage();
        assertTrue(MensagemLancada.contains(mensagemEsperada));
    }

    @Test
    public void teste_atualizar_endereco_por_id_inexistente(){
        Endereco endereco = new Endereco(44444L, "Rua N", "Centro", "Tobias Barreto", "Sergipe");
        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
            Endereco enderecoAtualizado = enderecoService.atualizar(999L, endereco);
        });     
        String MensagemEsperada = "O endereço não existe na base de dados.";
        String MensagemLancada = exception.getMessage();
        assertTrue(MensagemLancada.contains(MensagemEsperada));
    }  

    @Test
    public void teste_criar_endereco_cep_invalido(){
        Endereco endereco = new Endereco(88888L, "Rua H", "Centro", "Tobias Barreto", "Sergipe");
        Exception exception = assertThrows(CEPEnderecoInvalidoException.class, () -> {
            Long idEndereco = enderecoService.criar(endereco);
        });     
        String MensagemEsperada = "O CEP do endereço é inválido.";
        String MensagemLancada = exception.getMessage();
        assertTrue(MensagemLancada.contains(MensagemEsperada));
    } 

}
