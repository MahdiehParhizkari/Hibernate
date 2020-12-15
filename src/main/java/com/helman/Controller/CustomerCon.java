package com.helman.Controller;

import com.helman.Dao.Customerdao;
import com.helman.Entity.Customer;
import com.helman.Entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerAct", urlPatterns = {"/CustomerAct"})
public class CustomerCon extends HttpServlet {
    Customerdao customerdao = new Customerdao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");

        if(crud.equals("read")) {
            List<Customer> customerList = new ArrayList<>();
            String customerNum = req.getParameter("custnum");

            if (customerNum == null || customerNum.isEmpty())
                customerList = customerdao.findall();
            else
                customerList.add(customerdao.findById(Integer.parseInt(customerNum)));

            req.setAttribute("customers", customerList);
            req.getRequestDispatcher("/Customer.jsp").forward(req, resp);
        }
        if (crud.equals("create")){
            Customer customer = new Customer();
            customer.setCustomerNumber(Integer.parseInt(req.getParameter("custnum")));
            customer.setCustomerName(req.getParameter("custname"));
            customer.setContactLastName(req.getParameter("conlname"));
            customer.setContactFirstName(req.getParameter("confname"));
            customer.setPhone(req.getParameter("phone"));
            customer.setAddressLine1(req.getParameter("addl1"));
            customer.setAddressLine2(req.getParameter("addl2"));
            customer.setCity(req.getParameter("city"));
            customer.setState(req.getParameter("state"));
            customer.setPostalCode(req.getParameter("poscode"));
            customer.setCountry(req.getParameter("count"));
            customer.setSalesRepEmployeeNumber(Integer.parseInt(req.getParameter("srempnum")));
            customer.setCreditLimit(new BigDecimal(req.getParameter("credlim")));
            customerdao.insert(customer);
            req.setAttribute("message", "Customer is added.");
            req.getRequestDispatcher("/Customer.jsp").forward(req, resp);
        }
        if (crud.equals("update")){
            Customer customer = new Customer();
            customer.setCustomerNumber(Integer.parseInt(req.getParameter("custnum")));
            customer.setCustomerName(req.getParameter("custname"));
            customer.setContactLastName(req.getParameter("conlname"));
            customer.setContactFirstName(req.getParameter("confname"));
            customer.setPhone(req.getParameter("phone"));
            customer.setAddressLine1(req.getParameter("addl1"));
            customer.setAddressLine2(req.getParameter("addl2"));
            customer.setCity(req.getParameter("city"));
            customer.setState(req.getParameter("state"));
            customer.setPostalCode(req.getParameter("poscode"));
            customer.setCountry(req.getParameter("count"));
            customer.setSalesRepEmployeeNumber(Integer.parseInt(req.getParameter("srempnum")));
            customer.setCreditLimit(new BigDecimal(req.getParameter("credlim")));
            customerdao.update(customer);
            req.setAttribute("message", "updated");
            req.getRequestDispatcher("/Customer.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");
        if(crud.equals("delete")){
            customerdao.delete(Integer.parseInt(req.getParameter("custnum")));
            req.setAttribute("message" , "Customer is deleted.");
            req.getRequestDispatcher("/Customer.jsp").forward(req, resp);
        }
        if (crud.equals("edit")) {
            Customer customer = customerdao.findById(Integer.parseInt(req.getParameter("custnum")));
            req.setAttribute("cust", customer);
            req.getRequestDispatcher("/CustomerEdit.jsp").forward(req, resp);
        }
    }
}
