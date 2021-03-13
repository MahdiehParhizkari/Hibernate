package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Payment;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/5/21
  @Time 7:23 PM
  Created by Intellije IDEA
  Description: With Token Authentication
*/

public class PaymentRstTest {
    private String restServicePath = "http://localhost:8080/order/rest/payment";

    @Test
    public void findall() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        ObjectMapper mapper = new ObjectMapper();
        List<Payment> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Payment>>() {
        });
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) for (Payment temp : list) System.out.println(temp);
    }

    @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("find").path("103").path("DB66666");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        ObjectMapper mapper = new ObjectMapper();
        Payment obj = mapper.readValue(response.readEntity(String.class), new TypeReference<Payment>() {
        });
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) {
            System.out.println(obj);
        }
    }

    @Test
    public void insert() throws IOException, ParseException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Payment p = new Payment();
        p.setCustomerNumber(103);
        p.setCheckNumber("HQ77777");
        p.setPaymentDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-11-20T23:00:00.330Z"));
        p.setAmount(new BigDecimal(7));
        FilterProvider filters = new SimpleFilterProvider().addFilter("Paymentfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(p.getfilters()));
        //Map obj to json
        String paymentJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(p);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).post(Entity.json(paymentJson));
        System.out.println(response.getStatus());
        if (response.getStatus()==200) System.out.println(response.readEntity(String.class));
    }

    @Test
    public void update() throws JsonProcessingException, ParseException {
            Client client = ClientBuilder.newClient();
            String token = SecurityTest.getToken(client, "admin", "123");
            if (token.equals("0")) return;

            WebTarget webTarget = client.target(restServicePath).path("update");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Payment p = new Payment();
            p.setCustomerNumber(103);
            p.setCheckNumber("HQ77777");
            p.setPaymentDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-11-24T23:28:56.330Z"));
            p.setAmount(new BigDecimal(6));
            FilterProvider filters = new SimpleFilterProvider().addFilter("Paymentfilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(p.getfilters()));
            String paymentJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(p);
            Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).put(Entity.json(paymentJson));
            System.out.println(response.getStatus());
            if (response.getStatus()==200) System.out.println(response.readEntity(String.class));
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("delete").path("103").path("HQ77777");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).delete();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }
}