package dao;
import pojo.Employee;

import java.util.List;


public interface EmployeeDAO {
    void create (Employee employee);
    Employee getById (int id);
    List<Employee> getAllEmployees();

    void updateByEmployee(Employee employee, int id);

    void deleteByEmployee (Employee employee);

}
