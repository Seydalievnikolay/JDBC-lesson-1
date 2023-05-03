package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoHibernateImpl implements EmployeeDAO{
    private  final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private  final SessionFactory sessionFactory = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Employee create(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return employee;
    }

    @Override
    public Employee getById(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = null;
        try {
            session.beginTransaction();
            Query<Employee> query = session.createQuery(
                            "FROM Employee WHERE id = :fId", Employee.class)
                    .setParameter("fId", id);
            session.getTransaction().commit();
            employee = query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<>();
        try {
            session.beginTransaction();
            employees = session.createQuery("FROM Employee ", Employee.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return employees;
    }

    @Override
    public Employee updateByEmployee(Employee employee, int id) {
        Session session = sessionFactory.openSession();
        Employee employeeResult = null;
        try {
            session.beginTransaction();
            var query = session.createQuery(
                            "UPDATE Employee SET firstName = :fFirstName, lastName = :fLastName," +
                                    " gender = :fGender, age = :fAge WHERE id = :fId", Employee.class)
                    .setParameter("fFirstName", employee.getFirstName())
                    .setParameter("fLastName", employee.getLastName())
                    .setParameter("fGender", employee.getGender())
                    .setParameter("fAge", employee.getAge())
                    .uniqueResult();
            session.getTransaction().commit();
            employeeResult = query;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return employeeResult;
    }

    @Override
    public void deleteByEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery(
                            "DELETE Employee WHERE id = :fId")
                    .setParameter("fId", employee.getId())
                    .executeUpdate();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
