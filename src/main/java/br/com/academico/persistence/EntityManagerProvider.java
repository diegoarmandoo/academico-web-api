package br.com.academico.persistence;

import java.io.Closeable;
import java.io.IOException;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;
import org.jvnet.hk2.annotations.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Service
public class EntityManagerProvider implements Factory<EntityManager> {
    
    private final EntityManagerFactory entityManagerFactory;

    @Inject
    public EntityManagerProvider(EntityManagerFactory entityManagerFactor) {
        this.entityManagerFactory = entityManagerFactor;
    }

    @Override
    public EntityManager provide() {
        final EntityManager em = entityManagerFactory.createEntityManager();
        return em;
    }
    
    @Override
    public void dispose(EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

}
