package com.helman.Controller;

import com.helman.Dao.Orderdao;
import com.helman.Entity.Order;
import com.helman.General.Logback;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "orderAct", urlPatterns = {"/orderAct"})
public class OrderCon extends HttpServlet {
    Orderdao orderdao = new Orderdao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        List<Order> orderList = new ArrayList<>();
        String ordernumber = req.getParameter("ordnum");
        String crud = req.getParameter("crud");

        try {
            if (crud.equals("read")) {
                if (ordernumber == null || ordernumber.isEmpty())
                    orderList = orderdao.findall();
                else
                    orderList.add(orderdao.findById(Integer.parseInt(ordernumber)));

                req.setAttribute("Order", orderList);
                req.getRequestDispatcher("/Order.jsp").forward(req, resp);
            }
            if (crud.equals("add")) {
                Order o = new Order();
                o.setOrderNumber(Integer.parseInt(req.getParameter("onum")));
    /*            String shamsidate1 = req.getParameter("odate");
                o.setOrderDate(GregorianDate.picker2miladi(shamsidate1));
                String shamsidate2 = req.getParameter("rdate");
                o.setRequiredDate(GregorianDate.picker2miladi(shamsidate2));
                String shamsidate3 = req.getParameter("sdate");
                o.setShippedDate(GregorianDate.picker2miladi(shamsidate3));*/
                try {
                    o.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("odate")));
                    o.setRequiredDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("rdate")));
                    o.setShippedDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("sdate")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                o.setStatus(req.getParameter("status"));
                o.setComments(req.getParameter("com"));
                o.setCustomerNumber(Integer.parseInt(req.getParameter("custnum")));
                orderdao.insert(o);
                req.setAttribute("message", "Order is added.");
                req.getRequestDispatcher("/Order.jsp").forward(req, resp);
            }
            if (crud.equals("update")) {
                Order ord = orderdao.findById(Integer.parseInt(req.getParameter("ordernum")));
                ord.setOrderNumber(Integer.parseInt(req.getParameter("ordernum")));
                try {
                    ord.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("odate")));
                    ord.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("rdate")));
                    ord.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("sdate")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ord.setStatus(req.getParameter("status"));
                ord.setComments(req.getParameter("com"));
                ord.setCustomerNumber(Integer.parseInt(req.getParameter("cusnum")));
                orderdao.update(ord);
                req.setAttribute("message", "Updated");
                req.getRequestDispatcher("/Order.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        String crud = req.getParameter("crud");

        try {
            if (crud.equals("delete")) {
                orderdao.delete(Integer.parseInt(req.getParameter("onum")));
                req.setAttribute("message", "Order is deleted.");
                req.getRequestDispatcher("/Order.jsp").forward(req, resp);
            }
            if (crud.equals("edit")) {
                Order order = orderdao.findById(Integer.parseInt(req.getParameter("onum")));
                req.setAttribute("orderobj", order);
                req.getRequestDispatcher("/OrderEdit.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}