package dao;

import pojo.Employee;

import java.util.List;


public interface EmployeeDAO {
    Employee create (Employee employee);
    Employee getById (int id);
    List<Employee> getAllEmployees();

    Employee updateByEmployee(Employee employee, int id);

    void deleteByEmployee (Employee employee);

}
