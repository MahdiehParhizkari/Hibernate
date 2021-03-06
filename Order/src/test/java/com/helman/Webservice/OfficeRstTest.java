package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Office;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/3/21
  @Time 3:58 AM
  Created by Intellije IDEA
  Description: With Token Authentication
*/

public class OfficeRstTest {
    final String restServicePath = "http://localhost:8080/order/rest/office";
    @Test
    public void findall() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        //MAP JSON ti List of Office
        ObjectMapper mapper = new ObjectMapper();
        List<Office> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Office>>() {
        });
        System.out.println(response.getStatusInfo() + "->" + response.getStatus());
        if (response.getStatus() == 200) for (Office temp : list) System.out.println(temp);
    }

    @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("find").path("10");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).get();
        ObjectMapper mapper = new ObjectMapper();
        Office obj = mapper.readValue(response.readEntity(String.class), new TypeReference<Office>() {
        });
        System.out.println(response.getStatusInfo() + "->" + response.getStatus());
        if (response.getStatus() == 200)  System.out.println(obj);
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Office o = new Office();
        o.setOfficeCode("11");
        o.setCity("Tehran");
        o.setPhone("+9888089");
        o.setAddressLine1("Street 66");
        o.setAddressLine2("Street 666");
        o.setState("Teh");
        o.setCountry("Iran");
        o.setPostalCode("198666666");
        o.setTerritory("west");
        //filter attribute to create JSON
        FilterProvider filters = new SimpleFilterProvider().addFilter("Officefilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(o.getfilters()));
        // Map Object -> String
        String officeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(o);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).post(Entity.json(officeJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void update() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Office o = new Office();
        o.setOfficeCode("11");
        o.setCity("qazvin");
        o.setPhone("+982188089");
        o.setAddressLine1("Street 1");
        o.setAddressLine2("Street 2");
        o.setState("Qaz");
        o.setCountry("IR");
        o.setPostalCode("66666666");
        o.setTerritory("East");
        FilterProvider filters = new SimpleFilterProvider().addFilter("Officefilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(o.getfilters()));
        String officeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(o);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).put(Entity.json(officeJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        WebTarget webTarget = client.target(restServicePath).path("11");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.header(HttpHeaders.AUTHORIZATION, token).delete();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }
}