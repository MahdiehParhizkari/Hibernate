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
    Officedao dao = new Officedao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Office> officeList = new ArrayList<>();
        String officecode = req.getParameter("offcode");

        if (officecode == null || officecode.isEmpty())
            officeList = dao.findAll();
        else
            officeList.add(dao.findById("officecode"));

        req.setAttribute("Offices", officeList);
        req.getRequestDispatcher("/Office.jsp").forward(req, resp);
    }
}
