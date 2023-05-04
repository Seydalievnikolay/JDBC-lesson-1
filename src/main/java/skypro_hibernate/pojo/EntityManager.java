package skypro_hibernate.pojo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManager {
    public static javax.persistence.EntityManager createEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("myPersistenceUnit");
        return entityManagerFactory.createEntityManager();
    }


}
