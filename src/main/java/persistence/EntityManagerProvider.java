package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import java.util.HashMap;

public class EntityManagerProvider {
    private static EntityManagerProvider provider = null;

    private EntityManager em=null;

    private  EntityManagerProvider() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projet_Session_PU");

        this.em=emf.createEntityManager();
    }

    public static EntityManagerProvider getInstance() {
        if(EntityManagerProvider.provider == null) {
            EntityManagerProvider.provider = new EntityManagerProvider();
        }
        return EntityManagerProvider.provider;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }
}
