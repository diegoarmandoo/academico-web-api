package br.com.academico.endereco;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import jakarta.persistence.EntityManager;

@Service @Named("enderecorepositoryJPA")
public class EnderecoRepositoryJPA implements IEnderecoRepository {

    private EntityManager em;

    @Inject
    public EnderecoRepositoryJPA(EntityManager entityManager) {
        this.em = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Endereco> findAll() {
        return this.em.createQuery("from enderecos").getResultList();
    }

    @Override
    public Optional<Endereco> getById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Endereco save(Endereco endereco) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Endereco update(Endereco endereco) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }
    
}
