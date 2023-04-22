import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import pojo.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {



    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "nimbus2001";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final Connection connection =
                DriverManager.getConnection(url, user, password);
        try (
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee WHERE  id = (?) ")){
            statement.setInt(1,3);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int cityId = resultSet.getInt("city_id");

                System.out.println("Имя: " + firstName);
                System.out.println("Фамилия: " + lastName);
                System.out.println("Пол: " + gender);
                System.out.println("Город: " + cityId);
            }

            System.out.println();

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        EmployeeDAO employeeDao = new EmployeeDaoImpl(connection);
        Employee employee1 = new Employee(7, "Ivan", "Fedorov", "M", 34,"Omsk" );
        employeeDao.create(employee1);
        List<Employee> employees = new ArrayList<>(employeeDao.getAllEmployees());
        for(Employee employee:employees){
            System.out.println(employee);
        }
        employeeDao.updateById(5, employee1);
        employeeDao.deleteById(7);
        
    }


}
