package com.helman.Controller;

import com.helman.Dao.Customerdao;
import com.helman.Entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerAct", urlPatterns = {"/CustomerAct"})
public class CustomerCon extends HttpServlet {
    Customerdao dao = new Customerdao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");
        List<Customer> customerList = new ArrayList<>();
        String customerNum = req.getParameter("custnum");
        if(customerNum == null || customerNum.isEmpty()){
            customerList = dao.findall();
        } else {
            customerList.add(dao.findById(Integer.parseInt(customerNum)));
        }
        req.setAttribute("customers", customerList);
        req.getRequestDispatcher("/Customer.jsp").forward(req, resp);
    }
}
