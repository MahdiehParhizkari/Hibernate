package com.helman.Controller;

import com.helman.Dao.Productlinedao;
import com.helman.Entity.Productline;
import org.apache.commons.io.IOUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductlineAct", urlPatterns = {"/ProductlineAct"})
@MultipartConfig
public class ProductlinesCon extends HttpServlet {
    Productlinedao productlinedao = new Productlinedao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        List<Productline> productlineList = new ArrayList<>();
        String productline = req.getParameter("proline");
        String crud = req.getParameter("crud");

        if (crud.equals("read")) {
            if (productline.isEmpty() || productline==null)
                productlineList = productlinedao.findall();
            else
                productlineList.add(productlinedao.findById(productline));

            req.setAttribute("productline", productlineList);
            req.getRequestDispatcher("/Productline.jsp").forward(req, resp);
        }
        if (crud.equals("create")){
            Productline pl = new Productline();
            pl.setProductLine(req.getParameter("prol"));
            pl.setTextDescription(req.getParameter("tdesc"));
            pl.setHtmlDescription(req.getParameter("hdesc"));
            Part filePart = req.getPart("img");
            if (filePart != null)
                pl.setImage(IOUtils.toByteArray(filePart.getInputStream()));
            productlinedao.insert(pl);
            req.setAttribute("message", "Officeline is added");
            req.getRequestDispatcher("/Productline.jsp").forward(req, resp);
        }
        if (crud.equals("update")){
            Productline pl = productlinedao.findById(req.getParameter("pline"));
            pl.setTextDescription(req.getParameter("tdesc"));
            pl.setHtmlDescription(req.getParameter("hdesc"));
            Part filePart = req.getPart("img");
            if (filePart != null)
                pl.setImage(IOUtils.toByteArray(filePart.getInputStream()));
            productlinedao.update(pl);
            req.setAttribute("message", "Officeline is updated");
            req.getRequestDispatcher("/Productline.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        String crud = req.getParameter("crud");

        if (crud.equals("delete")){
            productlinedao.delete(productlinedao.findById(req.getParameter("proline")));
            req.setAttribute("message", "Office is deleted.");
            req.getRequestDispatcher("/Productline.jsp").forward(req, resp);
        }
        if (crud.equals("edit")){
            Productline pl = productlinedao.findById(req.getParameter("proline"));

            req.setAttribute("prolobj", pl);
            req.getRequestDispatcher("/ProductlineEdit.jsp").forward(req, resp);
        }
    }
}