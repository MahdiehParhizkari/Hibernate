package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/10/21
  @Time 10:03 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.junit.Test;

public class ProductRstTest {
    final String restServicePath = "http://localhost:8080/order/rest/product";

    @Test
    public void findall(){
        Client client = ClientBuilder.newClient();

    }
}