package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Entity.Product;
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
  @Date 3/7/21
  @Time 1:23 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

public class ProductWsTest {
    String restServicePath = "http://localhost:8080/order/rest/product";

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
            List<Product> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<Product>>() {
            });
            System.out.println(list);
        }
    }

    @Test
    public void findbyid() throws IOException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("find").path("S10_1600");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(response.readEntity(String.class), new TypeReference<Product>() {
        });
        System.out.println(product);
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Product product = new Product();
        product.setProductCode("S10_1601");
        product.setProductName("1999");
        product.setProductLine("Motorcycles");
        product.setProductScale("1:10");
        product.setProductVendor("Min Min Min");
        product.setProductDescription("wow");
        product.setQuantityInStock(777);
        product.setBuyPrice(new BigDecimal("77"));
        product.setMSRP(new BigDecimal("77"));
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("Productfilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                "productCode", "productName", "productLine", "productScale", "productVendor", "productDescription", "quantityInStock", "buyPrice", "MSRP"));
        ObjectMapper mapper = new ObjectMapper();
        String productJson = mapper.writer(filterProvider).withDefaultPrettyPrinter().writeValueAsString(product);
        Response response = invocationBuilder.post(Entity.json(productJson));
        System.out.println(response.getStatus() +"->"+ response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void update() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Product product = new Product();
        product.setProductCode("S10_1601");
        product.setProductName("2020");
        product.setProductLine("Motorcycles");
        product.setProductScale("1:10");
        product.setProductVendor("Max Max Max");
        product.setProductDescription("uhu");
        product.setQuantityInStock(6666);
        product.setBuyPrice(new BigDecimal("66.6"));
        product.setMSRP(new BigDecimal("6.6"));
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("Productfilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                "productCode", "productName", "productLine", "productScale", "productVendor", "productDescription", "quantityInStock", "buyPrice", "MSRP"));
        ObjectMapper mapper = new ObjectMapper();
        String productJson = mapper.writer(filterProvider).withDefaultPrettyPrinter().writeValueAsString(product);
        Response response = invocationBuilder.put(Entity.json(productJson));
        System.out.println(response.getStatus() +"->"+ response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "123");
        client.register(feature);

        WebTarget webTarget = client.target(restServicePath).path("S10_1601");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        System.out.println(response.getStatus() + "->" + response.getStatusInfo());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void filter() throws JsonProcessingException {
        Product product = new Product();
        product.setProductCode("S10_1601");
        product.setProductName("1999");
        product.setProductLine("Motorcycles");
        product.setProductScale("1:10");
        product.setProductVendor("Min Min Min");
        product.setProductDescription("wow");
        product.setQuantityInStock(777);
        product.setBuyPrice(new BigDecimal("77"));
        product.setMSRP(new BigDecimal("77"));
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("Productfilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                "productCode", "productName", "productLine", "productScale", "productVendor", "productDescription", "quantityInStock", "buyPrice", "MSRP"));
        ObjectMapper mapper = new ObjectMapper();
        String productJson = mapper.writer(filterProvider).withDefaultPrettyPrinter().writeValueAsString(product);
        System.out.println(productJson);
    }
}