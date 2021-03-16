package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 3/1/21
//@Time 1:50AM
//        Created by Intellije IDEA
//        Description: Authorization with token

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Orderdao;
import com.helman.Entity.Order;
import com.helman.General.Logback;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/order")
public class OrderRst {
    private Orderdao orderdao = new Orderdao();
    Security sec = new Security();
    //http://localhost:8080/order/rest/order/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(@Context HttpHeaders headers){
        List<Order> orderList = orderdao.findall();
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try{
            Order order = new Order();
            FilterProvider filters = new SimpleFilterProvider().addFilter("Orderfilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(order.getfilters()));
            String orderJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(orderList);
            Logback.logger.info("{}.{}|Try:Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(orderJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/order/find/10099
    @GET
    @Path("/find/{orderNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("orderNumber") Integer orderNum, @Context HttpHeaders headers){
        Order order = orderdao.findById(orderNum);
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Orderfilter" ,
                    SimpleBeanPropertyFilter.filterOutAllExcept(order.getfilters()));
            String orderJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(order);
            Logback.logger.info("{}.{}|Try: Record send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(orderJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /*http://localhost:8080/order/rest/order/insert
    body:
    {
    "orderNumber": 10098,
    "orderDate": "2020-10-24T23:28:56.782Z",
    "requiredDate": "2020-11-24T23:28:56.782Z",
    "shippedDate": "2020-12-24T23:28:56.782Z",
    "status": "Shipped",
    "comments": "wwowwowwoww",
    "customerNumber": 363
    }
     */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Order order, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();
        try{
            Integer status = orderdao.insert(order);
            Logback.logger.info("{}.{}|Try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/order/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Order order, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();
        try{
            Order updatedOrder = orderdao.findById(order.getOrderNumber());
            updatedOrder.setOrderNumber(order.getOrderNumber());
            updatedOrder.setOrderDate(order.getOrderDate());
            updatedOrder.setRequiredDate(order.getRequiredDate());
            updatedOrder.setShippedDate(order.getShippedDate());
            updatedOrder.setStatus(order.getStatus());
            updatedOrder.setComments(order.getComments());
            Integer status = orderdao.update(updatedOrder);
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/order/10098
    @DELETE
    @Path("/{orderNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("orderNumber") Integer ordnum, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();
        try {
            Integer status = orderdao.delete(ordnum);
            Logback.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}