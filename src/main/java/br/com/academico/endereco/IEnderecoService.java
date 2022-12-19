package br.com.academico.endereco;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IEnderecoService {
    
    public List<Endereco> listar();
    public Endereco recuperar(int id);
	public int criar(Endereco endereco);
	public Endereco atualizar(int id, Endereco endereco);
	public int deletar(int id);
	public Endereco mudarStatus(int id, StatusEndereco status);

}
