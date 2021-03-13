package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Orderdetail;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/4/21
  @Time 3:46 AM
  Created by Intellije IDEA
  Description: With Token Authentication
*/

public class OrderdetailRstTest {
    final String restServicePath = "http://localhost:8080/order/rest/orderdetail";

    @Test
    public void findall() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        ObjectMapper mapper = new ObjectMapper();
        List<Orderdetail> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Orderdetail>>() {
        });
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) for (Orderdetail temp : list ) System.out.println(temp);
    }

    @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("find").path("10100").path("S10_1600");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        ObjectMapper mapper = new ObjectMapper();
        Orderdetail obj = mapper.readValue(response.readEntity(String.class), new TypeReference<Orderdetail>() {
        });
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) System.out.println(obj);
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Orderdetail od = new Orderdetail();
        od.setOrderNumber(10100);
        od.setProductCode("S10_1600");
        od.setQuantityOrdered(6);
        od.setPriceEach(new BigDecimal(166.66));
        od.setOrderLineNumber(1);
        FilterProvider filters = new SimpleFilterProvider().addFilter("Orderdetailfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(od.getfilters()));
        String orderdetailJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(od);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).post(Entity.json(orderdetailJson));
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus()==200) System.out.println(response.readEntity(String.class));
    }

    @Test
    public void update() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Orderdetail od = new Orderdetail();
        od.setOrderNumber(10100);
        od.setProductCode("S10_1600");
        od.setQuantityOrdered(7);
        od.setPriceEach(new BigDecimal(17.7));
        od.setOrderLineNumber(1);
        FilterProvider filters = new SimpleFilterProvider().addFilter("Orderdetailfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(od.getfilters()));
        String orderdetailJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(od);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).put(Entity.json(orderdetailJson));
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) System.out.println(response.readEntity(String.class));
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("10100").path("S10_1600");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).delete();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }
}