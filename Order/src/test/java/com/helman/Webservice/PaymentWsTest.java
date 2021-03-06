package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Payment;
import com.helman.General.Logback;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/5/21
  @Time 7:23 PM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

public class PaymentWsTest {
    private String restServicePath = "Http://localhost:8080/order/rest/payment";

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
            List<Payment> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Payment>>() {
            });
            System.out.println(list);
        }
    }

    @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("find").path("103").path("HB66666");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            Payment obj = mapper.readValue(response.readEntity(String.class), new TypeReference<Payment>() {
            });
            System.out.println(obj);
        }
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        //client.register(feature);

        WebTarget webTarget = client.register(feature).target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Payment p = new Payment();
        p.setCustomerNumber(103);
        p.setCheckNumber("HQ77777");
        p.setPaymentDate(new Date());
        p.setAmount(new BigDecimal(7));
        FilterProvider filters = new SimpleFilterProvider().addFilter("Paymentfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("customerNumber", "checkNumber", "paymentDate", "amount"));
        //Map obj to json
        String paymentJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(p);
        Response response = invocationBuilder.post(Entity.json(paymentJson));
        System.out.println(response);
    }

    @Test
    public void update() {
        try {
            Client client = ClientBuilder.newClient();
            HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
            client.register(feature);

            WebTarget webTarget = client.target(restServicePath).path("insert");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Payment p = new Payment();
            p.setCustomerNumber(103);
            p.setCheckNumber("HQ77777");
            p.setPaymentDate(new Date());
            p.setAmount(new BigDecimal(7));
            FilterProvider filters = new SimpleFilterProvider().addFilter("Paymentfilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("customerNumber", "checkNumber", "paymentDate", "amount"));
            String paymentJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(p);
            Response response = invocationBuilder.put(Entity.json(paymentJson));
            System.out.println(response);
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("delete").path("103").path("HQ77777");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }
}