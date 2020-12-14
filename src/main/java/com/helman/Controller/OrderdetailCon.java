package com.helman.Controller;

import com.helman.Dao.Orderdetaildao;
import com.helman.Entity.Orderdetail;
import com.helman.Entity.OrderdetailPK;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderdetailAct", urlPatterns = {"/OrderdetailAct"})
public class OrderdetailCon extends HttpServlet {
    Orderdetaildao orderdetaildao = new Orderdetaildao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Orderdetail> orderdetailList = new ArrayList<>();
        String ordernumber = req.getParameter("ordnum");
        String productcode = req.getParameter("procode");


        if (ordernumber.isEmpty() || productcode.isEmpty())
            orderdetailList = orderdetaildao.findAll();
        else{
            OrderdetailPK orderdetailPK = new OrderdetailPK();
            orderdetailPK.setOrderNumber(Integer.parseInt(ordernumber));
            orderdetailPK.setProductCode(productcode);
            orderdetailList.add(orderdetaildao.findById(orderdetailPK));
        }


        req.setAttribute("orderdetail", orderdetailList);
        req.getRequestDispatcher("/Orderdetail.jsp").forward(req, resp);
    }
}
