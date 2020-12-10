package com.helman.Controller;

import com.helman.Dao.Employeedao;
import com.helman.Entity.Employee;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Act", urlPatterns = {"/Act"})
public class EmployeeCon extends HttpServlet {
    Employeedao dao = new Employeedao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = dao.findall();
        req.setAttribute("employees", employeeList);
        req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
    }
}
