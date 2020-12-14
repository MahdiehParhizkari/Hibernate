package com.helman.Controller;

import com.helman.Dao.Orderdao;
import com.helman.Entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "orderAct", urlPatterns = {"/orderAct"})
public class OrderCon extends HttpServlet {
    Orderdao orderdao = new Orderdao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = new ArrayList<>();
        String ordernumber = req.getParameter("ordnum");
        String Aligholi = ordernumber;

        if (ordernumber == null || ordernumber.isEmpty())
            orderList = orderdao.findall();
        else
            orderList.add(orderdao.findById(Integer.parseInt(ordernumber)));

        req.setAttribute("Order", orderList);
        req.getRequestDispatcher("/Order.jsp").forward(req, resp);
    }
}
