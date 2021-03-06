package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/5/21
  @Time 11:31 PM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/login")
public class LoginWs {
    Security sec = new Security();
    final long amountToAdd = 10l;

    //http:localhost:8080/order/rest/login/check
    @GET
    @Path("/check")
    public Response echo (@Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer".length()).trim();

        return true;
    }
}