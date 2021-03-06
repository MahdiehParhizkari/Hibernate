package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Order;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/5/21
  @Time 2:45 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

public class OrderWsTest {
    final String restServicePath = "Http://localhost:8080/order/rest/order";
    @Test
    public void findall() throws IOException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            List<Order> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Order>>() {
            });
            System.out.println(list);
        }
    }

        @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("find").path("10098");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            Order object = mapper.readValue(response.readEntity(String.class), new TypeReference<Order>() {
            });
            System.out.println(object);
        }
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Order o = new Order();
        o.setOrderNumber(10098);
        o.setOrderDate(new Date());
        o.setRequiredDate(new Date());
        o.setShippedDate(new Date());
        o.setStatus("Shipped");
        o.setComments("uhu");
        o.setCustomerNumber(363);
        FilterProvider filters = new SimpleFilterProvider().addFilter("Orderfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("orderNumber", "orderDate", "requiredDate", "shippedDate", "status", "comments", "customerNumber"));
        String orderJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(o);
        Response response = invocationBuilder.post(Entity.json(orderJson));
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void update() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Order o = new Order();
        o.setOrderNumber(10098);
        o.setOrderDate(new Date());
        o.setRequiredDate(new Date());
        o.setShippedDate(new Date());
        o.setStatus("Shipped");
        o.setComments("uhu");
        o.setCustomerNumber(363);
        FilterProvider filters = new SimpleFilterProvider().addFilter("Orderfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("orderNumber", "orderDate", "requiredDate", "shippedDate", "status", "comments", "customerNumber"));
        String orderJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(o);
        Response response = invocationBuilder.put(Entity.json(orderJson));
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));

    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("10098");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));

    }
}