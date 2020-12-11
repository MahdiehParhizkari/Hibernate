package com.helman.Controller;

import com.helman.Dao.Orderdetaildao;
import com.helman.Entity.Orderdetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderdetailAct", urlPatterns = {"/OrderdetailAct"})
public class OrderdetailCon extends HttpServlet {
    Orderdetaildao orderdetaildao = new Orderdetaildao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Orderdetail> orderdetailList = orderdetaildao.findAll();
        req.setAttribute("orderdetail", orderdetaildao);
        req.getRequestDispatcher("/Orderdetail.jsp");
    }
}
