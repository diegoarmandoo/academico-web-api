package br.com.academico.endereco;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EnderecoRepositoryTest {

    private EntityManagerFactory emf;
    
    private EntityManager em;

    private IEnderecoRepository enderecoRepositoryJPA;

    @Before
    public void init(){
        emf = Persistence.createEntityManagerFactory("academico-pu");
        em = emf.createEntityManager();
        enderecoRepositoryJPA = new EnderecoRepositoryJPA(em);
    }

    @Test
    public void teste_findAll_enderecos(){
        
        //WHEN - Quando ocorrer uma ação (act)

        List<Endereco> listaEnderecosResposta = enderecoRepositoryJPA.findAll();

        //THEN - Verifique a saída (assert)

        assertThat(listaEnderecosResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços não nulla")
            .isNotNull();
        
        assertThat(listaEnderecosResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços")
            .isInstanceOf(List.class);

        assertThat(listaEnderecosResposta.size())
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços com a quantidade de endereços no banco de dados")
            .isEqualTo(listaEnderecosResposta.size()); 
        
    }

    @Test
    public void teste_getById_enderecos(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Endereco enderecoEsperado = new Endereco(55000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoRepositoryJPA.save(enderecoEsperado);
        
        //WHEN - Quando ocorrer uma ação (act)

        Optional<Endereco> enderecoRecuperado = enderecoRepositoryJPA.getById(enderecoEsperado.getId());

        //THEN - Verifique a saída (assert)

        assertThat(enderecoRecuperado.get())
            .withFailMessage("O retorno do método getById não pode ser nullo")
            .isNotNull();

        assertThat(enderecoRecuperado.get())
            .withFailMessage("O retorno do método getById deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);

        assertThat(enderecoRecuperado.get().getId())
            .withFailMessage("O endereço recuperado deve ter o mesmo ID do endereço recuperado")
            .isEqualTo(enderecoEsperado.getId());    
        
    }

    @Test
    public void teste_save_enderecos(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Endereco enderecoEnviado = new Endereco(55000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        
        //WHEN - Quando ocorrer uma ação (act)
        Endereco enderecoSalvo = enderecoRepositoryJPA.save(enderecoEnviado);
        
        //THEN - Verifique a saída (assert)
        assertThat(enderecoSalvo)
            .withFailMessage("O retorno do método save não pode ser nullo")
            .isNotNull();

        assertThat(enderecoSalvo)
            .withFailMessage("O retorno do método save deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);
            
        assertThat(enderecoSalvo.getCEP())
            .withFailMessage("O CEP do endereço deve ser igual ao informado")
            .isEqualTo(55000000L);
            
        assertThat(enderecoSalvo.getRua())
            .withFailMessage("A Rua do endereço deve ser igual a informada")
            .isEqualTo("Rua A");
            
        assertThat(enderecoSalvo.getBairro())
            .withFailMessage("O Bairro do endereço deve ser igual ao informado")
            .isEqualTo("Bairro A");
            
        assertThat(enderecoSalvo.getCidade())
            .withFailMessage("A Cidade do endereço deve ser igual a informada")
            .isEqualTo("Cidade A");
            
        assertThat(enderecoSalvo.getEstado())
            .withFailMessage("O Estado do endereço deve ser igual ao informado")
            .isEqualTo("Estado A");    
        
    }

    @Test
    public void teste_update_enderecos(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Endereco enderecoEnviado = new Endereco(55000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoRepositoryJPA.save(enderecoEnviado);
        
        //WHEN - Quando ocorrer uma ação (act)
        enderecoEnviado.setCEP(99999999L);
        enderecoEnviado.setCidade("Aracaju");
        enderecoEnviado.setEstado("Sergipe");
        Endereco enderecoAtualizado = enderecoRepositoryJPA.update(enderecoEnviado);

        //THEN - Verifique a saída (assert)
        assertThat(enderecoAtualizado)
            .withFailMessage("O retorno do método update não pode ser nullo")
            .isNotNull();
            
        assertThat(enderecoAtualizado.getCEP())
            .withFailMessage("O CEP atualizado do endereço deve ser igual ao informado")
            .isEqualTo(99999999L);
            
        assertThat(enderecoAtualizado.getCidade())
            .withFailMessage("A Cidade atualizada do endereço deve ser igual a informada")
            .isEqualTo("Aracaju");
            
        assertThat(enderecoAtualizado.getEstado())
            .withFailMessage("O Estado atualizado do endereço deve ser igual ao informado")
            .isEqualTo("Sergipe");    
    
    }

    @Test
    public void teste_delete_enderecos(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Endereco enderecoEnviado = new Endereco(55000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
        enderecoRepositoryJPA.save(enderecoEnviado);
        
        //WHEN - Quando ocorrer uma ação (act)
        
        enderecoRepositoryJPA.delete(enderecoEnviado.getId());

        //THEN - Verifique a saída (assert)

        Optional<Endereco> enderecoDeletado = enderecoRepositoryJPA.getById(enderecoEnviado.getId());

        assertThat(enderecoDeletado.isEmpty())
            .withFailMessage("O retorno do método delete deve ser nullo")
            .isEqualTo(true);     
        
    }
}
