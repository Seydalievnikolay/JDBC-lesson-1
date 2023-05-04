package skypro_hibernate.dao;

import skypro_hibernate.pojo.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO{


    @Override
    public Employee create(Employee employee) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(employee);
        transaction.commit();
        return  employee;

    }

    @Override
    public Employee getById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.detach(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        return entityManager.createQuery("FROM Employee ").getResultList();
    }

    @Override
    public Employee updateByEmployee(Employee employee, int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        employee.setId(id);
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return employee;
    }


    @Override
    public void deleteByEmployee(Employee employee) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Employee.class, employee.getId()));
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
