package dao;

import pojo.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CityDaoImpl implements CityDao {

    @Override
    public City create(City city) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(city);
        transaction.commit();
        return  city;
    }

    @Override
    public City getById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class, id);
        entityManager.detach(city);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return city;
    }

    @Override
    public List<City> getAllCity() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        return entityManager.createQuery("FROM City ").getResultList();
    }

    @Override
    public City updateCity(City city) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        city.setCity_id();
        entityManager.merge(city);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return city;
    }

    @Override
    public void deleteCity(City city) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(City.class, city.getCity_id()));
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
