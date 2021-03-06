package com.helman.Controller;

import com.helman.Dao.JRsqlFunction;
import com.helman.Dao.Orderdao;
import com.helman.Dao.Orderdetaildao;
import com.helman.Entity.Order;
import com.helman.Entity.Orderdetail;
import com.helman.Entity.OrderdetailPK;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderdetailAct", urlPatterns = {"/OrderdetailAct"})
public class OrderdetailCon extends HttpServlet {
    Orderdetaildao orderdetaildao = new Orderdetaildao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        List<Orderdetail> orderdetailList = new ArrayList<>();
        String ordernumber = req.getParameter("ordnum");
        String productcode = req.getParameter("procode");
        String crud = req.getParameter("crud");

        try {
            if (crud.equals("read")) {
                if (ordernumber.isEmpty() || productcode.isEmpty())
                    orderdetailList = orderdetaildao.findAll();
                else {
                    OrderdetailPK orderdetailPK = new OrderdetailPK();
                    orderdetailPK.setOrderNumber(Integer.parseInt(ordernumber));
                    orderdetailPK.setProductCode(productcode);
                    orderdetailList.add(orderdetaildao.findById(orderdetailPK));
                }
                req.setAttribute("orderdetail", orderdetailList);
                req.getRequestDispatcher("/Orderdetail.jsp").forward(req, resp);
            }
            if (crud.equals("add")) {
                Orderdetail od = new Orderdetail();
                od.setOrderNumber(Integer.parseInt(req.getParameter("ordnum")));
                od.setProductCode(req.getParameter("procode"));
                od.setQuantityOrdered(Integer.parseInt(req.getParameter("qord")));
                od.setPriceEach(new BigDecimal(req.getParameter("pe")));
                od.setOrderLineNumber(Integer.parseInt(req.getParameter("oln")));
                orderdetaildao.insert(od);
                req.setAttribute("message", "New orderdetail is added.");
                req.getRequestDispatcher("/Orderdetail.jsp").forward(req, resp);
            }
            if (crud.equals("update")) {
                OrderdetailPK orderdetailPK = new OrderdetailPK();
                orderdetailPK.setOrderNumber(Integer.parseInt(ordernumber));
                orderdetailPK.setProductCode(productcode);
                Orderdetail orderdetail = orderdetaildao.findById(orderdetailPK);
                orderdetail.setQuantityOrdered(Integer.parseInt(req.getParameter("quanord")));
                orderdetail.setPriceEach(new BigDecimal(req.getParameter("peach")));
                orderdetail.setOrderLineNumber(Integer.parseInt(req.getParameter("ordlnum")));
                orderdetaildao.update(orderdetail);
                req.setAttribute("message", "Updated.");
                req.getRequestDispatcher("/Orderdetail.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        String crud = req.getParameter("crud");
        String ordernumber = req.getParameter("onum");
        String productcode = req.getParameter("pcode");

        try {
            if (crud.equals("delete")) {
                OrderdetailPK odPK = new OrderdetailPK();
                odPK.setOrderNumber(Integer.parseInt(ordernumber));
                odPK.setProductCode(productcode);
                Orderdetail orderdetail = orderdetaildao.findById(odPK);
                orderdetaildao.delete(orderdetail);
                req.setAttribute("message", "Orderdetail is deleted.");
                req.getRequestDispatcher("/Orderdetail.jsp").forward(req, resp);
            }
            if (crud.equals("edit")) {
                OrderdetailPK odPK = new OrderdetailPK();
                odPK.setOrderNumber(Integer.parseInt(ordernumber));
                odPK.setProductCode(productcode);
                Orderdetail od = orderdetaildao.findById(odPK);
                req.setAttribute("orderdetail", od);
                req.getRequestDispatcher("/OrderdetailEdit.jsp").forward(req, resp);
            }
            if (crud.equals("report")) {
                String path = req.getSession().getServletContext().getRealPath("/WEB-INF/Report/Orderdetail.jrxml");
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("orderNum", Integer.parseInt(req.getParameter("ordernumber")));
                try {
                    JRsqlFunction.viewReport(path, parameters);
                } catch (JRException | SQLException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/Orderdetail.jsp").forward(req, resp);
            }
            if (crud.equals("factor")) {
                /*Grandtotal variable properties:
                Calculation: SUM
                Experesion: $V{totalprice}
                Initial value experesion: 0
                Reset type: Group
                Report Properties:
                Float Column footer: True
                 */
                String path = req.getSession().getServletContext().getRealPath("/WEB-INF/Report/SaleFactor.jrxml");
                Orderdao orderdao = new Orderdao();
                Order order = orderdao.findById(Integer.parseInt(req.getParameter("ordernumber")));
                String param_date = GregorianDate.shamsiStr(GregorianDate.miladi2shamsi(order.getOrderDate()));
                String param_cName = order.getCustomer().getCustomerName();
                String param_eName = order.getCustomer().getEmployee().getFirstName() + " " + order.getCustomer().getEmployee().getLastName();

                Map<String, Object> myvalues = new HashMap<String, Object>();
                myvalues.put("Date", param_date);
                myvalues.put("CustomerName", param_cName);
                myvalues.put("EmployeeName", param_eName);
                myvalues.put("orderNum", order.getOrderNumber());

                try {
                    JRsqlFunction.viewReport(path, myvalues);
                } catch (JRException | SQLException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/OrderdetailReport.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
