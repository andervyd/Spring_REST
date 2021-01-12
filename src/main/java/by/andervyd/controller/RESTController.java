package by.andervyd.controller;

import by.andervyd.entity.Employee;
import by.andervyd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {

        List<Employee> allEmployee = employeeService.getAllEmployees();

        return allEmployee;
    }

    @GetMapping("employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {

        Employee employee = employeeService.getEmployee(id);

        return  employee;
    }
}
