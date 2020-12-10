package com.helman.Controller;

import com.helman.Dao.Orderdao;
import com.helman.Entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Act", urlPatterns = {"/Act"})
public class OrderCon extends HttpServlet {
    Orderdao orderdao = new Orderdao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = orderdao.findall();
        req.setAttribute("Order", orderList);
        req.getRequestDispatcher("/Order.jsp").forward(req, resp);
    }
}
