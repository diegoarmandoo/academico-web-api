package br.com.academico.endereco;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IEnderecoService {
    
    public List<Endereco> listar();
    public Endereco recuperar(Long id);
	public Long criar(Endereco endereco);
	public Endereco atualizar(Long id, Endereco endereco);
	public Long deletar(Long id);
	public Endereco mudarStatus(Long id, StatusEndereco status);

}
