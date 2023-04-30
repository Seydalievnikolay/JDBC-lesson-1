import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import pojo.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static pojo.EntityManager.createEntityManager;

public class Application {
    public static Connection getConnection() throws SQLException {
        final String user = "postgres";
        final String password = "nimbus2001";
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        EntityManager entityManager = createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        Employee employee = new Employee(8, "Stepan", "Stepanov", "M", 45, 3);
        employeeDAO.create(employee);

        entityManager.getTransaction().commit();
        entityManager.close();



    }


}
