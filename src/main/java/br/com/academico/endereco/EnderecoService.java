package br.com.academico.endereco;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service @Named("enderecoservicedefault")
public class EnderecoService implements IEnderecoService {
    
    public List<Endereco> listar(){
        List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		listaEnderecos.add(new Endereco(55555L, "Rua da feira", "Centro", "Aracaju", "Sergipe"));
		listaEnderecos.add(new Endereco(66666L, "Rua da igreja", "Atalaia", "Aracaju", "Sergipe"));
        return listaEnderecos;
    }

    public Endereco recuperar(Long id) {
        Endereco endereco;
        if (id != 999){
            endereco = new Endereco(88888L, "Rua B", "Centro", "Tobias Barreto", "Sergipe");
            endereco.setId(id);
        }
        else {
            throw new EnderecoNaoExisteException();
        }
        return endereco;
    }

    public Long criar(Endereco endereco) {
        if (endereco.getCEP() != 88888) {
            endereco.setId(200L);
        }
        else {
            throw new CEPEnderecoInvalidoException();
        }
        return endereco.getId();
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        if (id != 999) {
            endereco.setId(id);
            endereco.setRua("Rua Nova");
        }
        else {
            throw new EnderecoNaoExisteException();            
        }
        return endereco;
    }

    public Long deletar(Long id) {
        return id;
    }

    public Endereco mudarStatus(Long id, StatusEndereco status) {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setStatus(status);
        return endereco;
    }

}
