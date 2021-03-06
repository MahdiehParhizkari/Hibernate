package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Customer;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/2/21
  @Time 9:17 PM
  Created by Intellije IDEA
  Description: JPA - Criteria
 */

public class CustomerWsTest {
    final String restServicePath = "http://localhost:8080/order/rest/customer";
    @Test
    public void findall() throws IOException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatusInfo() + "->" + response.getStatus());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            List<Customer> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Customer>>() {
            });
            System.out.println(list);

        }
    }

    @Test
    public void findById() throws IOException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("find").path("103");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatusInfo() + "->" + response.getStatus());
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            Customer customer = mapper.readValue(response.readEntity(String.class), new TypeReference<Customer>() {
            });
            System.out.println(customer);
        }
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Customer cus = new Customer();
        cus.setCustomerNumber(102);
        cus.setCustomerName("Helman");
        cus.setContactLastName("Parhizkari");
        cus.setContactFirstName("Sadaf");
        cus.setPhone("+982188089");
        cus.setAddressLine1("Street 1");
        cus.setAddressLine2("Street2");
        cus.setCity("Shiraz");
        cus.setState("Teh");
        cus.setPostalCode("198666666");
        cus.setCountry("Iraq");
        cus.setSalesRepEmployeeNumber(1166);
        cus.setCreditLimit(new BigDecimal("666666"));
        FilterProvider filters = new SimpleFilterProvider().addFilter("Customerfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("customerNumber", "customerName", "contactLastName", "contactFirstName", "phone", "addressLine1", "city", "country"));
        String customerJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(cus);
        Response response = invocationBuilder.post(Entity.json(customerJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));

    }

    @Test
    public void update() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("Admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Customer cus = new Customer();
        cus.setCustomerNumber(102);
        cus.setCustomerName("Heli");
        cus.setContactLastName("Parhiz");
        cus.setContactFirstName("Sadi");
        cus.setPhone("+982188089");
        cus.setAddressLine1("Street 1666");
        cus.setAddressLine2("Street2666");
        cus.setCity("Mashhad");
        cus.setState("Mash");
        cus.setPostalCode("6666666666");
        cus.setCountry("Iran");
        cus.setSalesRepEmployeeNumber(1166);
        cus.setCreditLimit(new BigDecimal("77777"));
        FilterProvider filters = new SimpleFilterProvider().addFilter("Customerfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("customerNumber", "customerName", "contactLastName", "contactFirstName", "phone", "addressLine1", "city", "country"));
        String customerJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(cus);
        Response response = invocationBuilder.put(Entity.json(customerJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));

    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("102");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}