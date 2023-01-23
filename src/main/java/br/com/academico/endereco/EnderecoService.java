package br.com.academico.endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service @Named("enderecoservicedefault")
public class EnderecoService implements IEnderecoService {

    private IEnderecoRepository enderecoRepository;

    @Inject
    public EnderecoService(@Named("enderecorepositoryJPA") IEnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listar(){
        return enderecoRepository.findAll();
    }

    public Endereco recuperar(Long id) {
        return enderecoRepository.getById(id)
			.orElseThrow(() -> new EnderecoNaoExisteException());
    }

    public Long criar(Endereco endereco) {
        enderecoRepository.save(endereco);
		return endereco.getId();
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        return enderecoRepository.getById(id).map(e -> {
            e.setCEP(endereco.getCEP());
            e.setRua(endereco.getRua());
            e.setBairro(endereco.getBairro());
            e.setCidade(endereco.getCidade());
            e.setEstado(endereco.getEstado());
            enderecoRepository.update(e);
            return e;
        }).orElseThrow(() -> new EnderecoNaoExisteException());
    }

    public Long deletar(Long id) {
        Optional<Endereco> endereco = enderecoRepository.getById(id);
        if (endereco.isPresent()) {
            enderecoRepository.delete(endereco.get().getId());
            return endereco.get().getId();
        }
        else{
            throw new EnderecoNaoExisteException();
        }
    }

    public Endereco mudarStatus(Long id, StatusEndereco status) {
        return enderecoRepository.getById(id).map(e -> {
            e.setStatus(status);
            enderecoRepository.update(e);
            return e;
        }).orElseThrow(() -> new EnderecoNaoExisteException());
    }

}
