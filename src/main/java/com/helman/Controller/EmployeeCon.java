package com.helman.Controller;

import com.helman.Dao.Employeedao;
import com.helman.Entity.Employee;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeAct", urlPatterns = {"/EmployeeAct"})
public class EmployeeCon extends HttpServlet {
    Employeedao employeedao = new Employeedao();
    Employee employee = new Employee();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");
        if(crud.equals("read")) {
            List<Employee> employeeList = new ArrayList<>();
            String empid = req.getParameter("empnum");
            if (empid == null || empid.isEmpty())
                employeeList = employeedao.findall();
            else
                employeeList.add(employeedao.findbyid(Long.parseLong(empid)));
            req.setAttribute("employees", employeeList);
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
        if(crud.equals("create")){
            employee.setEmployeeNumber(Long.parseLong(req.getParameter("empnum")));
            employee.setLastName(req.getParameter("lname"));
            employee.setFirstName(req.getParameter("fname"));
            employee.setExtension(req.getParameter("exten"));
            employee.setEmail(req.getParameter("email"));
            employee.setOfficeCode(req.getParameter("offcode"));
            employee.setReportsTo(Long.parseLong(req.getParameter("repto")));
            employee.setJobTitle(req.getParameter("jobtit"));
            employeedao.insert(employee);
            req.setAttribute("message", "Add was success.");
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
        if (crud.equals("update")){
            employee.setEmployeeNumber(Long.parseLong(req.getParameter("empnum")));
            employee.setLastName(req.getParameter("lname"));
            employee.setFirstName(req.getParameter("fname"));
            employee.setExtension(req.getParameter("exten"));
            employee.setEmail(req.getParameter("email"));
            employee.setOfficeCode(req.getParameter("offcode"));
            employee.setReportsTo(Long.parseLong(req.getParameter("repto")));
            employee.setJobTitle(req.getParameter("jobtit"));
            employeedao.update(employee);
            req.setAttribute("emp", employee);
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");
        if (crud.equals("delete")) {
            employeedao.delete(Long.parseLong(req.getParameter("empnumber")));
            req.setAttribute("message", "Employee is deleted.");
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
        if (crud.equals("edit")){
            Employee employee = employeedao.findbyid(Long.parseLong(req.getParameter("empnumber")));
            req.setAttribute("empobjt",employee);
            req.getRequestDispatcher("/EmployeeEdit.jsp").forward(req,resp);
        }
    }
}
