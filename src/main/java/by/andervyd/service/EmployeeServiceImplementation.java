package by.andervyd.service;

import by.andervyd.dao.EmployeeDAO;
import by.andervyd.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void savingEmployeeData(Employee employee) {

        employeeDAO.savingEmployeeData(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(Long id) {

        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {

        employeeDAO.deleteEmployee(id);
    }
}
