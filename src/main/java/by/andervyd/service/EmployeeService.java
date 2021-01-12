package by.andervyd.service;

import by.andervyd.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void savingEmployeeData(Employee employee);

    public Employee getEmployee(Long id);

    public void deleteEmployee(Long id);
}
