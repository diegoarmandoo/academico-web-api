package br.com.academico.endereco;

import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IEnderecoRepository {
    
    public List<Endereco> findAll();
	public Optional <Endereco> getById(Long id);
	public Endereco save(Endereco endereco);
	public Endereco update(Endereco endereco);
	public void delete(Long id);

}
