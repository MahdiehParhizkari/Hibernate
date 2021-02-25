package com.helman.General;

/*@project order
@Author Mahdieh Parhizkari
@Date 1/9/21
@Time 2:33AM
        Created by Intellije IDEA
        Description:JPA-Criteria*/

import com.helman.Entity.User;
import javax.servlet.http.HttpServletRequest;

public class GeneralFunction {
    public static boolean login(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("sessionUser");
        if (user != null) return true;
        else return false;
    }
}
