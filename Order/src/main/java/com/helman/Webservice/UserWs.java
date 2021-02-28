package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/27/21
//@Time 3:32AM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Userdao;
import com.helman.Entity.User;
import com.helman.General.Logback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserWs {
    private Userdao userdao = new Userdao();
    private List<User> userList = new ArrayList<>();

    //http://localhost:8080/order/rest/user/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        userList.clear();
        userList = userdao.findAll();
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter(
                    "Userfilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "username", "password", "employeefk"));
            String userJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(userList);
            Logback.logger.info("{}.{}|Try:Send all records to RESTful", this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(userJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        }
    }
    //http://localhost:8080/order/rest/user/find/3
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id){
        User user = userdao.findById(id);
        //return user;
        try{
            //filter attribute to create JSON
            FilterProvider filters = new SimpleFilterProvider().addFilter(
                    "Userfilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "username", "password", "employeefk"));
            //Map object -> String
            String userJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(user);
            Logback.logger.info("{}.{}|Try:Send record to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(userJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        }
    }
    /* http://localhost:8080/order/rest/user/insert
    Body:
    {
    "id": 4,
    "username":"hely",
    "password":"123",
    "employeefk": 1056
    }
     */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer insert(User user){
        Integer status = userdao.insert(user);
        Logback.logger.info("{}.{}|Try:Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }
    //http://localhost:8080/order/rest/user/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer updateUser(User user){
        User updatedUser = userdao.findById(user.getId());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setEmployeefk(user.getEmployeefk());
        Integer status = userdao.update(updatedUser);
        Logback.logger.info("{}.{}|Try: Updated!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }
    //localhost:8080/order/rest/user/4
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer deleteUser(@PathParam("id") Integer id){
        Integer status = userdao.delete(userdao.findById(id));
        Logback.logger.info("{}.{}|Try: Deleted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }
}