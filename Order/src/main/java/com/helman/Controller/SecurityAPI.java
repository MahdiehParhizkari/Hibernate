package com.helman.Controller;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/6/21
  @Time 12:53 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import com.helman.Entity.User;
import com.helman.General.Logback;
import javax.servlet.http.HttpServletRequest;

public class SecurityAPI {
    public static boolean isLogin(HttpServletRequest req){
        Logback.logger.trace("GeneralFunc.login : Enter to method!");
        User user = (User) req.getSession(true).getAttribute("sessionUser");
        if(user != null){
            Logback.logger.info("GeneralFunc.login : User {} is Detected",user.getUsername());
            return true;
        }else {
            Logback.logger.warn("login : User is not Detected");
            return false;
        }

    }
}