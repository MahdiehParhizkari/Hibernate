package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Order;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/5/21
  @Time 2:45 AM
  Created by Intellije IDEA
  Description: With Token Authentication
*/

public class OrderRstTest {
    final String restServicePath = "Http://localhost:8080/order/rest/order";
    @Test
    public void findall() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        ObjectMapper mapper = new ObjectMapper();
        List<Order> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Order>>() {
        });
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) for (Order temp : list) System.out.println(temp);
    }

        @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("find").path("10098");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            Order object = mapper.readValue(response.readEntity(String.class), new TypeReference<Order>() {
            });
            System.out.println(object);
        }
    }

    @Test
    public void insert() throws JsonProcessingException, ParseException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Order o = new Order();
        o.setOrderNumber(10098);
        o.setOrderDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-02-15T23:00:00.330Z"));
        o.setRequiredDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-04-10T23:00:00.330Z"));
        o.setShippedDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-06-05T23:00:00.330Z"));
        o.setStatus("Shipped");
        o.setComments("uhu");
        o.setCustomerNumber(363);
        FilterProvider filters = new SimpleFilterProvider().addFilter("Orderfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(o.getfilters()));
        String orderJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(o);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).post(Entity.json(orderJson));
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus()==200) System.out.println(response.readEntity(String.class));
    }

    @Test
    public void update() throws JsonProcessingException, ParseException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Order o = new Order();
        o.setOrderNumber(10098);
        o.setOrderDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-10-24T23:00:00.330Z"));
        o.setRequiredDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-11-24T23:00:00.330Z"));
        o.setShippedDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-12-24T23:00:00.330Z"));
        o.setStatus("Shipped");
        o.setComments("uhu");
        o.setCustomerNumber(363);
        FilterProvider filters = new SimpleFilterProvider().addFilter("Orderfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(o.getfilters()));
        String orderJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(o);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).put(Entity.json(orderJson));
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus()==200) System.out.println(response.readEntity(String.class));
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("10098");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).delete();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }
}