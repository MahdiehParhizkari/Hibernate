package com.helman.Controller;

import com.helman.Dao.Productlinedao;
import com.helman.Entity.Productline;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductlineAct", urlPatterns = {"/ProductlineAct"})
public class ProductlinesCon extends HttpServlet {
    Productlinedao productlinedao = new Productlinedao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Productline> productlineList = productlinedao.findall();
        req.setAttribute("productline", productlineList);
        req.getRequestDispatcher("/Productline.jsp").forward(req, resp);
    }
}
