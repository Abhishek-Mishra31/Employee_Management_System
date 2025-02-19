package com.abhishek.kumar.employee_management_system;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.abhishek.kumar.employee_management_system.model.Employee;
import com.abhishek.kumar.employee_management_system.service.EmployeeService;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "employee data", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
        System.out.println("Hello Servlet");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Employee> employees = EmployeeService.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/listEmployees.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if(action.equals("addEmployee")) {
            String name = req.getParameter("name");
            String department = req.getParameter("department");
            double salary = Double.parseDouble(req.getParameter("salary"));
            Employee employee = new Employee(name, department, salary);
            EmployeeService.saveEmployee(employee);
            resp.getWriter().write("Employee added");
        }
       else if (action.equals("updateEmployee")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String department = req.getParameter("department");
            double salary = Double.parseDouble(req.getParameter("salary"));
            EmployeeService.updateEmployee(id, name, department, salary);
            resp.getWriter().println("Employee updated");
        } else if (action.equals("deleteEmployee")) {
            int id = Integer.parseInt(req.getParameter("employeeId"));
            EmployeeService.deleteEmployee(id);
            resp.getWriter().println("Employee deleted");
        }
    }



    public void destroy() {

    }
}