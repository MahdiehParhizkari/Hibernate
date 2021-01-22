package com.helman.Controller;

import com.helman.Dao.Employeedao;
import com.helman.Entity.Employee;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "EmployeeAct", urlPatterns = {"/EmployeeAct"})
public class EmployeeCon extends HttpServlet {
    Employeedao employeedao = new Employeedao();
    List<Employee> employeeList = new ArrayList<>();
    Employee employee = new Employee();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeeList.clear();
        String action = req.getParameter("crud");

        if(action.equals("read")) {
            String empid = req.getParameter("empnum");
            if (empid == null || empid.isEmpty())
                employeeList = employeedao.findall();
            else
                employeeList.add(employeedao.findbyid(Long.parseLong(empid)));

            req.setAttribute("employees", employeeList);
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
        if(action.equals("create")){
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
        if (action.equals("update")){
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

        if (action.equals("delete")) {
            employeedao.delete(Long.parseLong(req.getParameter("empnumber")));
            req.setAttribute("message", "Employee is deleted.");
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
        if (action.equals("edit")){
            Employee employee = employeedao.findbyid(Long.parseLong(req.getParameter("empnumber")));
            req.setAttribute("empobjt",employee);
            req.getRequestDispatcher("/EmployeeEdit.jsp").forward(req,resp);
        }
        if (action.equals("mngrof")){
            employeeList = employeedao.besonderSelect(Long.parseLong(req.getParameter("managerof")));
            req.setAttribute("employees", employeeList);
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
        if (action.equals("rpto")){
            Employee employee = employeedao.findbyid(Long.parseLong(req.getParameter("reportto")));
            employeeList.add(employee);
            req.setAttribute("employees",employeeList);
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
        if (action.equals("report")){
            try {
                String path ="/home/afshin/projects/java/SadafPrj/Hiber/Git/src/main/resources/Jasper/Employee.jrxml";
                JasperReport jreport = JasperCompileManager.compileReport(path);
                JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(employeedao.findall());
                JasperPrint jprint = JasperFillManager.fillReport(jreport, null, jcs);
                JasperViewer.viewReport(jprint, false);
            } catch (JRException e) {e.printStackTrace();}
            req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
        }
    }
}