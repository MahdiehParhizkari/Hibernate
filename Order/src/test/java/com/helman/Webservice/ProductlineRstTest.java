package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Productline;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/6/21
  @Time 6:12 AM
  Created by Intellije IDEA
  Description: With Basic Authentication
*/

public class ProductlineRstTest {
    String restServicePath = "http://localhost:8080/order/rest/productline";

    @Test
    public void findall() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            List<Productline> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Productline>>() {
            });
            System.out.println(list);
        }
    }

    @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("find").path("boom1");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            Productline productline = mapper.readValue(response.readEntity(String.class), new TypeReference<Productline>() {
            });
            System.out.println(productline);
        }
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Productline pl = new Productline();
        pl.setProductLine("boom2");
        pl.setTextDescription("uhu");
        pl.setHtmlDescription("http://local");
        FilterProvider filters = new SimpleFilterProvider().addFilter("Productlinefilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("productLine", "textDescription", "htmlDescription", "image"));
        String productlineJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(pl);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).post(Entity.json(productlineJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void update() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Productline pl = new Productline();
        pl.setProductLine("boom2");
        pl.setTextDescription("uhuuuuuuuu");
        pl.setHtmlDescription("http://local1");
        FilterProvider filters = new SimpleFilterProvider().addFilter("Productlinefilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("productLine", "textDescription", "htmlDescription", "image"));
        String productlineJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(pl);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).put(Entity.json(productlineJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("boom2");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).delete();
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}