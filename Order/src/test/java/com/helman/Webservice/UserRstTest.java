package com.helman.Webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Userdao;
import com.helman.Entity.User;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/3/21
  @Time 1:23 AM
  Created by Intellije IDEA
  Description: Without any Authentication
*/

public class UserRstTest {
    final String restServicePath = "http://localhost:8080/order/rest/user";
    @Test
    public void findAll() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(restServicePath).path("all");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        //MAP JSON to List of User
        ObjectMapper mapper = new ObjectMapper();
        List<User> list = mapper.readValue(response.readEntity(String.class), new TypeReference<List<User>>() {
        });
        System.out.println(response.getStatusInfo() + "->" + response.getStatus());
        if (response.getStatus() == 200) for (User temp : list) System.out.println(temp);
    }

    @Test
    public void findById() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(restServicePath).path("find").path("3");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        ObjectMapper mapper = new ObjectMapper();
        User obj = mapper.readValue(response.readEntity(String.class), new TypeReference<User>() {
        });
        System.out.println(response.getStatusInfo() + "->" + response.getStatus());
        if (response.getStatus() == 200) System.out.println(obj);
    }

    @Test
    public void insert() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(restServicePath).path("insert");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        User user = new User();
        user.setId(5);
        user.setUsername("heli");
        user.setPassword("123");
        user.setEmployeefk(1056);
        //filter attribute to create JSON
        FilterProvider filters = new SimpleFilterProvider().addFilter("Userfilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                "Id", "username", "password", "employeefk"));
        String userJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(user);
        Response response = invocationBuilder.post(Entity.json(userJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
        //JSON String:
    /*        userJson = "{\n" +
                    "  \"idusers\" : 6,\n" +
                    "  \"username\" : \"heli\",\n" +
                    "  \"password\" : \"123\",\n" +
                    "  \"employeeid\" : 1056\n" +
                    "}";
    */
    }

    @Test
    public void update() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(restServicePath).path("update");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Userdao userdao = new Userdao();
        User user = userdao.findById(5);
        user.setUsername("Homa");
        user.setPassword("456");
        user.setEmployeefk(1076);
        FilterProvider filters = new SimpleFilterProvider().addFilter("Userfilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("Id", "username", "password", "employeefk"));
        String userJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(userJson);
        //JSON String:
/*        userJson = "{\n" +
                "  \"idusers\" : 6,\n" +
                "  \"username\" : \"Forough\",\n" +
                "  \"password\" : \"147\",\n" +
                "  \"employeeid\" : 1076\n" +
                "}";
*/
        Response response = invocationBuilder.put(Entity.json(userJson));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void delete() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(restServicePath).path("5");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}