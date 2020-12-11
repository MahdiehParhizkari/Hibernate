package com.helman.Controller;

import com.helman.Dao.Paymentdao;
import com.helman.Entity.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentAct", urlPatterns = {"/PaymentAct"})
public class PaymentCon extends HttpServlet {
    Paymentdao paymentdao = new Paymentdao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Payment> paymentList = paymentdao.findAll();
        req.setAttribute("payment", paymentList);
        req.getRequestDispatcher("/Payment.jsp").forward(req,resp);
    }
}
