package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/5/21
  @Time 11:31 PM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import com.helman.Dao.Userdao;
import com.helman.Entity.User;
import com.helman.General.Logback;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.bouncycastle.util.encoders.Base64;

public class Security {
    protected boolean basicAuthCheck(String encodedUserPassword){
        try{
            //Decode username and password
            String credential = new String(Base64.decode(encodedUserPassword));
            if (credential == null || credential.isEmpty()|| credential.trim().equals(":")) return false;
            else {
                String username = credential.substring(0, credential.indexOf(":"));
                String password = credential.substring(credential.indexOf(":")+1);
                Userdao userdao = new Userdao();
                User user = userdao.login(username);
                if (user == null || !password.equals(user.getPassword())){
                    Logback.logger.info("{}.{}|Username or password are not correct", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                    return false;
                }else {
                    Logback.logger.info("{}.{}| User {} and password are correct",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(), user.getUsername());
                    return true;
                }
            }
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    protected boolean tokenAuthCheck (String token){
        try{
            Claims claims = Jwts.parser().setSigningKey("sharekeyisafshin").parseClaimsJws(token).getBody();
            System.out.println(claims);
            Logback.logger.info("{}.{}| Token is correct", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return true;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            return false;
        }
    }
}