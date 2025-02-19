package com.abhishek.kumar.employee_management_system.service;

import com.abhishek.kumar.employee_management_system.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeService {
    private static final SessionFactory factory;

    static {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
    }

    public static void saveEmployee(Employee employee) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
    }

    public static List<Employee> getAllEmployees() {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();
        transaction.commit();
        return employees;
    }

    public Employee getEmployee(int employeeId) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, employeeId);
        transaction.commit();
        return employee;
    }

    public static void updateEmployee(int id, String name, String department, double salary) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        if(employee != null) {
            employee.setName(name);
            employee.setDepartment(department);
            employee.setSalary(salary);
            session.merge(employee);
        }
        transaction.commit();
    }

    public static void deleteEmployee(int employeeId) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, employeeId);
        session.remove(employee);
        transaction.commit();
    }

}
