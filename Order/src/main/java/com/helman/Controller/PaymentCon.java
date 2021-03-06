package com.helman.Controller;

import com.helman.Dao.JRsqlFunction;
import com.helman.Dao.Paymentdao;
import com.helman.Entity.Payment;
import com.helman.Entity.PaymentPK;
import com.helman.General.GregorianDate;
import com.helman.General.Logback;
import net.sf.jasperreports.engine.JRException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "PaymentAct", urlPatterns = {"/PaymentAct"})
public class PaymentCon extends HttpServlet {
    Paymentdao paymentdao = new Paymentdao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        PaymentPK paymentPK = new PaymentPK();
        List<Payment> paymentList = new ArrayList<>();

        String custnumber= req.getParameter("custnum");
        String checknumber= req.getParameter("checknum");
        String crud = req.getParameter("crud");

        try {
            if (crud.equals("read")) {
                if (custnumber.isEmpty() || checknumber.isEmpty()) {
                    paymentList = paymentdao.findAll();
                } else {
                    paymentPK.setCustomerNumber(Integer.parseInt(custnumber));
                    paymentPK.setCheckNumber(checknumber);
                    paymentList.add(paymentdao.findById(paymentPK));
                }
                req.setAttribute("payment", paymentList);
                req.getRequestDispatcher("/Payment.jsp").forward(req, resp);
            }
            if (crud.equals("Add")) {
                Payment payment = new Payment();
                payment.setCustomerNumber(Integer.parseInt(custnumber));
                payment.setCheckNumber(checknumber);
                String shamsidate = req.getParameter("pdate");
                int year = Integer.parseInt(shamsidate.substring(0, 4));
                int month = Integer.parseInt(shamsidate.substring(5, 7));
                int day = Integer.parseInt(shamsidate.substring(8, 10));
                Date d = GregorianDate.shamsi2miladi(year, month, day);
                payment.setPaymentDate(d);
                payment.setAmount(new BigDecimal(req.getParameter("amount")));
                paymentdao.insert(payment);
                req.setAttribute("message", "payment is added.");
                req.getRequestDispatcher("/Payment.jsp").forward(req, resp);
            }
            if (crud.equals("update")) {
                PaymentPK pPK = new PaymentPK();
                pPK.setCustomerNumber(Integer.parseInt(custnumber));
                pPK.setCheckNumber(checknumber);
                Payment payment = paymentdao.findById(pPK);
    /*            String Shamsidate = req.getParameter("pdate");
                int y = Integer.parseInt(Shamsidate.substring(0,4));
                int m = Integer.parseInt(Shamsidate.substring(5,7));
                int d = Integer.parseInt(Shamsidate.substring(8,10));
                Date D = GregorianDate.shamsi2miladi(y, m, d);*/
                try {
                    payment.setPaymentDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("pdate")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                payment.setAmount(new BigDecimal(req.getParameter("amount")));
                paymentdao.update(payment);
                req.setAttribute("message", "payment is updated.");
                req.getRequestDispatcher("/Payment.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        String crud = req.getParameter("crud");
        String custnum= req.getParameter("custnum");
        String checknum = req.getParameter("checknum");

        try {
            if (crud.equals("delete")) {
                PaymentPK pPK = new PaymentPK();
                pPK.setCustomerNumber(Integer.parseInt(custnum));
                pPK.setCheckNumber(checknum);
                Payment payment = paymentdao.findById(pPK);
                paymentdao.delete(payment);
                req.setAttribute("message", "Payment is deleted.");
                req.getRequestDispatcher("/Payment.jsp").forward(req, resp);
            }
            if (crud.equals("edit")) {
                PaymentPK pPk = new PaymentPK();
                pPk.setCustomerNumber(Integer.parseInt(custnum));
                pPk.setCheckNumber(checknum);
                Payment payment = paymentdao.findById(pPk);
                req.setAttribute("pay", payment);
                req.getRequestDispatcher("/PaymentEdit.jsp").forward(req, resp);
            }
            if (crud.equals("report")) {
                String path = req.getSession().getServletContext().getRealPath("/WEB-INF/Report/Payment.jrxml");
                Map<String, Object> parameters = new HashMap<String, Object>();
                try {
                    parameters.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fromdate")));
                    parameters.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("todate")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    JRsqlFunction.viewReport(path, parameters);
                } catch (JRException | SQLException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/PaymentReport.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}