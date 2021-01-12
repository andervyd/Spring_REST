package by.andervyd.dao;

import by.andervyd.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();

/* v1
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> allEmployee = query.getResultList();
*/

        // v2
        List<Employee> allEmployee = session.createQuery(
                "FROM Employee", Employee.class).getResultList();

        return allEmployee;
    }

    @Override
    public void savingEmployeeData(Employee employee) {

        Session session = sessionFactory.getCurrentSession();

/*
        if(employee.getId() == 0) {
            session.save(employee);
        } else session.update(employee);
*/

        session.saveOrUpdate(employee);

    }

    @Override
    public Employee getEmployee(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery(
                "DELETE FROM Employee WHERE id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
