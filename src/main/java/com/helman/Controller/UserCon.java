package com.helman.Controller;

import com.helman.Dao.Userdao;
import com.helman.Entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserAct" , urlPatterns = {"/UserAct"})
public class UserCon extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Userdao userdao = new Userdao();
        List<User> userList = new ArrayList<>();
        String crud = req.getParameter("crud");

        if (crud.equals("read")){
           String id = req.getParameter("id");
           if (id == null || id.isEmpty())
               userList = userdao.findAll();
           else
               userList.add(userdao.findById(Integer.parseInt("id")));
           req.setAttribute("Users", userList);
           req.getRequestDispatcher("/User.jsp").forward(req, resp);
        }
        if (crud.equals("add")){
            User user = new User();
            user.setId(Integer.parseInt(req.getParameter("id")));
            user.setUsername(req.getParameter("un"));
            user.setPassword(req.getParameter("pw"));
            user.setEmployeefk(Integer.parseInt(req.getParameter("empfk")));
            userdao.insert(user);
            req.setAttribute("message", "User is added.");
            req.getRequestDispatcher("/User.jsp").forward(req, resp);
        }
        if (crud.equals("update")){
            User user = userdao.findById(Integer.parseInt(req.getParameter("id")));
            user.setId(Integer.parseInt(req.getParameter("id")));
            user.setUsername(req.getParameter("un"));
            user.setPassword(req.getParameter("pw"));
            user.setEmployeefk(Integer.parseInt(req.getParameter("empfk")));
            userdao.update(user);
            req.setAttribute("message", "Updated.");
            req.getRequestDispatcher("/User.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Userdao userdao = new Userdao();
        String crud = req.getParameter("crud");

        if (crud.equals("delete")){
            User user = userdao.findById(Integer.parseInt(req.getParameter("id")));
            userdao.delete(user);
            req.setAttribute("message", "User is deleted.");
            req.getRequestDispatcher("/User.jsp").forward(req, resp);
        }
        if (crud.equals("edit")){
            User user = userdao.findById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("us", user);
            req.getRequestDispatcher("/UserEdit.jsp").forward(req, resp);
        }
    }
}
