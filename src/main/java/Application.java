import dao.*;
import pojo.City;
import pojo.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Application {
    public static Connection getConnection() throws SQLException {
        final String user = "postgres";
        final String password = "nimbus2001";
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        return DriverManager.getConnection(url, user, password);
    }



    public static void main(String[] args) {
        CityDao cityDao = new CityDaoImpl();
        EmployeeDAO employeeDAO = new EmployeeDaoHibernateImpl();
        City city = new City(8,"Rio_de_Janeiro");
        cityDao.create(city);
        Employee employee = new Employee(9,"Sergey", "Sergeev", "M", 30, city, city);

        Employee employee2 = new Employee(10,"Anna", "Sergeeva", "W", 22, city, city);

        city.setEmployees(List.of(employee,employee2));
        City updateCity  = cityDao.updateCity(city);
        System.out.println("Сотрудники добавлены " + employeeDAO.getAllEmployees().contains(updateCity.getEmployees()));
        cityDao.getById(updateCity.getCity_id());




    }


}
