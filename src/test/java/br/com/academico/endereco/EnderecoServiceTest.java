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
        Endereco endereco = enderecoService.recuperar(10);
        assertTrue("O retorno do método recuperar deve ser um objeto Endereco: ", endereco instanceof Endereco);
    }    

    @Test
    public void teste_criar_endereco() {
        Endereco endereco = new Endereco(33333, "Rua H", "Centro", "Tobias Barreto", "Sergipe");
        int idEndereco = enderecoService.criar(endereco);
        assertTrue("O retorno do método criar deve ser um ID de um Endereco criado: ", idEndereco == (int)idEndereco);
    }

    @Test
    public void teste_atualizar_endereco_por_id(){
        Endereco endereco = new Endereco(44444, "Rua N", "Centro", "Tobias Barreto", "Sergipe");
        Endereco enderecoAtualizado = enderecoService.atualizar(12, endereco);
        assertTrue("O retorno do método atualizar deve ser um objeto Endereco: ", enderecoAtualizado instanceof Endereco);
    }

    @Test
    public void teste_deletar_endereco_por_id(){
        int idEnderecoDeletado = enderecoService.deletar(587);
        assertTrue("O retorno do método deletar deve ser um ID do Endereco deletado: ", idEnderecoDeletado == (int)idEnderecoDeletado);
    }

    
    @Test
    public void teste_atlerar_status_endereco_por_id(){
        Endereco enderecoAtualizado = enderecoService.mudarStatus(12, StatusEndereco.DESATIVO);
        assertTrue("O retorno do método mudar status deve ser um objeto Endereco: ", enderecoAtualizado instanceof Endereco);
    }

    @Test
    public void teste_recuperar_endereco_por_id_inexistente(){
        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
            Endereco endereco = enderecoService.recuperar(999);
        });     
        String mensagemEsperada = "O endereço não existe na base de dados.";
        String MensagemLancada = exception.getMessage();
        assertTrue(MensagemLancada.contains(mensagemEsperada));
    }

    @Test
    public void teste_atualizar_endereco_por_id_inexistente(){
        Endereco endereco = new Endereco(44444, "Rua N", "Centro", "Tobias Barreto", "Sergipe");
        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
            Endereco enderecoAtualizado = enderecoService.atualizar(999, endereco);
        });     
        String MensagemEsperada = "O endereço não existe na base de dados.";
        String MensagemLancada = exception.getMessage();
        assertTrue(MensagemLancada.contains(MensagemEsperada));
    }  

    @Test
    public void teste_criar_endereco_cep_invalido(){
        Endereco endereco = new Endereco(88888, "Rua H", "Centro", "Tobias Barreto", "Sergipe");
        Exception exception = assertThrows(CEPEnderecoInvalidoException.class, () -> {
            int idEndereco = enderecoService.criar(endereco);
        });     
        String MensagemEsperada = "O CEP do endereço é inválido.";
        String MensagemLancada = exception.getMessage();
        assertTrue(MensagemLancada.contains(MensagemEsperada));
    } 

}
