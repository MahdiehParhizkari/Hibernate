package com.helman.Controller;

/*@project order
@Author Mahdieh Parhizkari
@Date 1/11/21
@Time 12:34AM
        Created by Intellije IDEA
        Description:JPA-Criteria*/

import com.helman.Dao.Userdao;
import com.helman.Entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Dispatcher", urlPatterns = {"/Dispatcher"})
public class Dashboard extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");
        if (crud.equals("logout")){
            req.getSession(true).invalidate();
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
        if (crud.equals("into")){
            Userdao userdao = new Userdao();
            String pwd = req.getParameter("password");
            User user = userdao.login(req.getParameter("username"));
            HttpSession session = req.getSession(true);
            if (user==null || !pwd.equals(user.getPassword())) {
                session.setAttribute("message", "User or Password is incorrect");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                session.setAttribute("sessionUser", user);
                session.setAttribute("message", "Login successful!");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
    }
}