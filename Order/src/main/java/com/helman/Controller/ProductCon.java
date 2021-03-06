package com.helman.Controller;

import com.helman.Dao.Productdao;
import com.helman.Entity.Product;
import com.helman.General.Logback;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductAct", urlPatterns = {"/ProductAct"})
public class ProductCon extends HttpServlet {
    Productdao productdao = new Productdao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
        List<Product> productList = new ArrayList<>();
        String productcode = req.getParameter("procode");
        String crud = req.getParameter("crud");

        try {
            if (crud.equals("read")) {
                if (productcode.isEmpty()) productList = productdao.findall();
                else productList.add(productdao.findById(productcode));
                req.setAttribute("product", productList);
            }
            if (crud.equals("add")) {
                Product product = new Product();
                product.setProductCode(req.getParameter("procode"));
                product.setProductName(req.getParameter("proname"));
                product.setProductLine(req.getParameter("proline"));
                product.setProductScale(req.getParameter("proscale"));
                product.setProductVendor(req.getParameter("provendor"));
                product.setProductDescription(req.getParameter("prodesc"));
                product.setQuantityInStock(Integer.parseInt(req.getParameter("quanstock")));
                product.setBuyPrice(new BigDecimal(req.getParameter("buyp")));
                product.setMSRP(new BigDecimal(req.getParameter("msrp")));
                productdao.insert(product);
                req.setAttribute("message", "Product is added.");
            }
            if (crud.equals("update")) {
                Product pro = productdao.findById(req.getParameter("procode"));
                pro.setProductName(req.getParameter("proname"));
                pro.setProductLine(req.getParameter("proline"));
                pro.setProductScale(req.getParameter("proscale"));
                pro.setProductVendor(req.getParameter("provendor"));
                pro.setProductDescription(req.getParameter("prodesc"));
                pro.setQuantityInStock(Integer.parseInt(req.getParameter("qins")));
                pro.setBuyPrice(new BigDecimal(req.getParameter("buyprice")));
                pro.setMSRP(new BigDecimal(req.getParameter("msrp")));
                productdao.update(pro);
                req.setAttribute("message", "product is updated.");
            }
            req.getRequestDispatcher("/Product.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");

        try {
            if (!SecurityAPI.isLogin(req)) {req.getRequestDispatcher("index.jsp").forward(req, resp); return;}
            if (crud.equals("delete")) {
                Product pro = productdao.findById(req.getParameter("procode"));
                productdao.delete("pro");
                req.setAttribute("message", "Product is deleted.");
                req.getRequestDispatcher("/Product.jsp").forward(req, resp);
            }
            if (crud.equals("edit")) {
                Product p = productdao.findById(req.getParameter("productcode"));
                req.setAttribute("productobj", p);
                req.getRequestDispatcher("/ProductEdit.jsp").forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
