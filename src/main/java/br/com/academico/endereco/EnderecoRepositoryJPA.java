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
        em.getTransaction().begin();
        Endereco endereco = em.find(Endereco.class, id);
        em.getTransaction().commit();
        return endereco != null ? Optional.of(endereco) : Optional.empty();
    }

    @Override
    public Endereco save(Endereco endereco) {
        em.getTransaction().begin();
        em.persist(endereco);
        em.getTransaction().commit();
        return endereco;
    }

    @Override
    public Endereco update(Endereco endereco) {
        em.getTransaction().begin();
		endereco = em.merge(endereco);
	    em.getTransaction().commit();
        return endereco;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
		em.remove(em.find(Endereco.class, id));
		em.getTransaction().commit();  
    }
    
}
