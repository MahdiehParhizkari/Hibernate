package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/11/21
  @Time 7:54 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SecurityTest {
    final static String restServicePath = "http://localhost:8080/order/rest/login";

    protected static String getToken(Client client, String usr, String pass){
        try{
            String token = Files.readString(Paths.get(System.getProperty("user.dir"), "src/main/webapp/statics/", "token.tkn"));
            if (token == null || token.isEmpty()) return "0";
            WebTarget webTarget = client.target(restServicePath).path("check");
            Invocation.Builder invocationBuilder = webTarget.request();
            Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
            System.out.println(response.getStatus()+response.readEntity(String.class));
            if (response.getStatus() == 200) return token;
            else {
                HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(usr, pass);
                webTarget = client.register(feature).target(restServicePath).path("token");
                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
                response = invocationBuilder.get();
                token = response.getHeaderString(HttpHeaders.AUTHORIZATION);
                Files.writeString(Paths.get(System.getProperty("user.dir"),"/src/main/webapp/statics/", "token.tkn"), token);
                System.out.println("New Token is stored in file");
                return token;
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "0";
        }
    }
}