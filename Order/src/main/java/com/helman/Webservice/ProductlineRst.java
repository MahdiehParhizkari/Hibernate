package com.helman.Webservice;/*@project order
@Author Mahdieh Parhizkari
@Date 3/2/21
@Time 4:29 AM
Created by Intellije IDEA
 Description: Authorization with token*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Productlinedao;
import com.helman.Entity.Productline;
import com.helman.General.Logback;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/productline")
public class ProductlineRst {
    private Productlinedao productlinedao = new Productlinedao();
    Security sec = new Security();

    //http://localhost:8080/order/rest/productline/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(@Context HttpHeaders headers){
        List<Productline> productlineList= productlinedao.findall();
        Productline prol = new Productline();
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Productlinefilter" ,
                    SimpleBeanPropertyFilter.filterOutAllExcept(prol.getfilters()));
            String productlineJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(productlineList);
            Logback.logger.info("{}.{}|Try:All records send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(productlineJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/productline/find/boom
    @GET
    @Path("/find/{productLine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("productLine") String proline, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or passsword is wrong").build();
        Productline productline = productlinedao.findById(proline);

        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Productlinefilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(productline.getfilters()));
            String productLineJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(productline);
            Logback.logger.info("{}.{}|Try: Send record to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(productLineJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/productline/insert
    /*body:
    {
    "productLine":"boom1" ,
    "textDescription": "Wow",
    "htmlDescription": "http://local"
    }
     */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Productline productline, @Context HttpHeaders headers) {
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or passsword is wrong").build();

        try{
            String status = productlinedao.insert(productline);
            Logback.logger.info("{}.{}|Try: Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/productline/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Productline proline, @Context HttpHeaders headers) {
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try {
            Productline updatedProductline = productlinedao.findById(proline.getProductLine());
            updatedProductline.setProductLine(proline.getProductLine());
            updatedProductline.setTextDescription(proline.getTextDescription());
            updatedProductline.setHtmlDescription(proline.getHtmlDescription());
            updatedProductline.setImage(proline.getImage());
            String status = productlinedao.update(updatedProductline);
            Logback.logger.info("{}.{}|Try:Updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    //http://localhost:8080/order/rest/productline/boom1
    @DELETE
    @Path("/{productLine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("productLine") String proline, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try{
            Integer status = productlinedao.delete(productlinedao.findById(proline));
           Logback.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
          return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}

