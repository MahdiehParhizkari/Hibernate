package com.helman.Controller;

import com.helman.Dao.Officedao;
import com.helman.Entity.Office;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OfficeAct", urlPatterns = {"/OfficeAct"})
public class OfficeCon extends HttpServlet {
    Officedao officedao = new Officedao();
    List<Office> officeList = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       officeList.clear();
        String crud = req.getParameter("crud");

        if (crud.equals("read")) {
            String officecode = req.getParameter("offcode");
            if (officecode == null || officecode.isEmpty())
                officeList = officedao.findAll();
            else
                officeList.add(officedao.findById("officecode"));

            req.setAttribute("Offices", officeList);
            req.getRequestDispatcher("/Office.jsp").forward(req, resp);
        }
        if (crud.equals("create")){
            Office office = new Office();
            office.setOfficeCode(req.getParameter("offcode"));
            office.setCity(req.getParameter("city"));
            office.setPhone(req.getParameter("phone"));
            office.setAddressLine1(req.getParameter("addl1"));
            office.setAddressLine2(req.getParameter("addl2"));
            office.setState(req.getParameter("state"));
            office.setCountry(req.getParameter("coun"));
            office.setPostalCode(req.getParameter("pcode"));
            office.setTerritory(req.getParameter("ter"));
            officedao.insert(office);
            req.setAttribute("message", "Office is added.");
            req.getRequestDispatcher("/Office.jsp").forward(req, resp);
        }
        if (crud.equals("update")){
            Office office = officedao.findById(req.getParameter("offcode"));
            office.setCity(req.getParameter("city"));
            office.setPhone(req.getParameter("phone"));
            office.setAddressLine1(req.getParameter("addl1"));
            office.setAddressLine2(req.getParameter("addl2"));
            office.setState(req.getParameter("state"));
            office.setCountry(req.getParameter("coun"));
            office.setPostalCode(req.getParameter("pcode"));
            office.setTerritory(req.getParameter("ter"));
            officedao.update(office);
            req.setAttribute("message", "Updated;");
            req.getRequestDispatcher("/Office.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        officeList.clear();
        String crud = req.getParameter("crud");

        if (crud.equals("delete")){
            Office office = officedao.findById(req.getParameter("offcode"));
            officedao.delete(office);
            req.setAttribute("message", "Office is deleted!");
            req.getRequestDispatcher("/Office.jsp").forward(req, resp);
        }
        if(crud.equals("edit")){
            Office office = officedao.findById(req.getParameter("offcode"));
            req.setAttribute("offobjct", office);
            req.getRequestDispatcher("/OfficeEdit.jsp").forward(req, resp);
        }
    }
}
