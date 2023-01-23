package br.com.academico.persistence;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

import static org.hibernate.cfg.AvailableSettings.DATASOURCE;
import static org.hibernate.cfg.AvailableSettings.IMPLICIT_NAMING_STRATEGY;
import static org.hibernate.cfg.AvailableSettings.PHYSICAL_NAMING_STRATEGY;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.glassfish.hk2.api.Factory;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.jvnet.hk2.annotations.Service;

import jakarta.persistence.EntityManagerFactory;

@Service
public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {

    private final DataSource dataSource;

    @Inject
    EntityManagerFactoryProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Singleton
    @Override
    public EntityManagerFactory provide() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(DATASOURCE, dataSource);
        properties.put(PHYSICAL_NAMING_STRATEGY, new PhysicalNamingStrategyStandardImpl());
        properties.put(IMPLICIT_NAMING_STRATEGY, new ImplicitNamingStrategyComponentPathImpl());

        return createEntityManagerFactory("academico-pu-ds", properties);
    }

    @Override
    public void dispose(EntityManagerFactory instance) {
        if (instance != null && instance.isOpen()){
            instance.close();
        }
    }

}
