package com.helman.Controller;

import com.helman.Dao.Paymentdao;
import com.helman.Entity.Payment;
import com.helman.Entity.PaymentPK;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PaymentAct", urlPatterns = {"/PaymentAct"})
public class PaymentCon extends HttpServlet {
    Paymentdao paymentdao = new Paymentdao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PaymentPK paymentPK = new PaymentPK();
        List<Payment> paymentList = new ArrayList<>();

        String custnumber= req.getParameter("custnum");
        String checknumber= req.getParameter("checknum");

        if (custnumber.isEmpty() || checknumber.isEmpty()){
            paymentList = paymentdao.findAll();
        }else {
            paymentPK.setCustomerNumber(Integer.parseInt(custnumber));
            paymentPK.setCheckNumber(checknumber);
            paymentList.add(paymentdao.findById(paymentPK));
        }
        req.setAttribute("payment", paymentList);
        req.getRequestDispatcher("/Payment.jsp").forward(req,resp);
    }
}
