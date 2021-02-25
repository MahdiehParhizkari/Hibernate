package com.helman.Controller;

import com.helman.Dao.Employeedao;
import com.helman.Dao.JRsqlFunction;
import com.helman.Entity.Employee;
import com.helman.General.Logback;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeAct", urlPatterns = {"/EmployeeAct"})
public class EmployeeCon extends HttpServlet {
    Employeedao employeedao = new Employeedao();
    List<Employee> employeeList = new ArrayList<>();
    Employee employee = new Employee();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeeList.clear();
        String action = req.getParameter("crud");

        try {
            if (action.equals("read")) {
                Logback.logger.trace("{}.{}|read: Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                String empid = req.getParameter("empnum");
                if (empid == null || empid.isEmpty())
                    employeeList = employeedao.findall();
                else
                    employeeList.add(employeedao.findbyid(Long.parseLong(empid)));

                req.setAttribute("employees", employeeList);
                req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
                Logback.logger.trace("{}.{}|read: Exit from if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
            if (action.equals("create")) {
                Logback.logger.trace("{}.{}|create:Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
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
                Logback.logger.trace("{}.{}|create:Exit from if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
            if (action.equals("update")) {
                Logback.logger.trace("{}.{}|update:Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                employee = employeedao.findbyid(Long.parseLong(req.getParameter("empnum")));
                employee.setLastName(req.getParameter("lname"));
                employee.setFirstName(req.getParameter("fname"));
                employee.setExtension(req.getParameter("exten"));
                employee.setEmail(req.getParameter("email"));
                employee.setOfficeCode(req.getParameter("offcode"));
                employee.setReportsTo(Long.parseLong(req.getParameter("repto")));
                employee.setJobTitle(req.getParameter("jobtit"));
                employeedao.update(employee);
                req.setAttribute("message", "Updated.");
                req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
                Logback.logger.trace("{}.{}|update: Exit from if!",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
/*        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeeList.clear();
        String action = req.getParameter("crud");

        try {
            if (action.equals("delete")) {
                Logback.logger.trace("{}.{}|delete: Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                employeedao.delete(Long.parseLong(req.getParameter("empnumber")));
                req.setAttribute("message", "Employee is deleted.");
                req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
                Logback.logger.trace("{}.{}|delete:Exit from if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
            if (action.equals("edit")) {
                Logback.logger.trace("{}.{}|edit: Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                Employee employee = employeedao.findbyid(Long.parseLong(req.getParameter("empnumber")));
                req.setAttribute("empobjt", employee);
                Logback.logger.trace("{}.{}|edit: Exit to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                req.getRequestDispatcher("/EmployeeEdit.jsp").forward(req, resp);
            }
            if (action.equals("mngrof")) {
                Logback.logger.trace("{}.{}|mngrof: Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                employeeList = employeedao.besonderSelect(Long.parseLong(req.getParameter("managerof")));
                req.setAttribute("employees", employeeList);
                Logback.logger.trace("{}.{}|mngrof: Exit from if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
            }
            if (action.equals("rpto")) {
                Logback.logger.trace("{}.{}|rpto: Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                Employee employee = employeedao.findbyid(Long.parseLong(req.getParameter("reportto")));
                employeeList.add(employee);
                req.setAttribute("employees", employeeList);
                Logback.logger.trace("{}.{}|rpto: Exit from if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
            }
            if (action.equals("report")) {
                Logback.logger.trace("{}.{}|report: Enter to if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                String path = req.getSession().getServletContext().getRealPath("/WEB-INF/Report/Employee.jrxml");
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("empnum", Long.parseLong(req.getParameter("empNumber")));
                try {
                    JRsqlFunction.viewReport(path, parameters);
                } catch (JRException | SQLException e) {
                    e.printStackTrace();
                }
                Logback.logger.trace("{}.{}|report:Exit from if!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
            }
        }catch (Exception e){
            Logback.logger.error("{},{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}