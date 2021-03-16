package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 3/1/21
//@Time 3:48AM
//        Created by Intellije IDEA
//        Description: Authorization with token

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Productdao;
import com.helman.Entity.Product;
import com.helman.General.Logback;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/product")
public class ProductRst {
    private Productdao productdao = new Productdao();
    Security sec = new Security();
    Set<String> hash_Set = new HashSet<String>();

    //http://localhost:8080/order/rest/product/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(@Context HttpHeaders headers){
        hash_Set.add("productCode");
        hash_Set.add("productName");
        hash_Set.add("productLine");
        hash_Set.add("productScale");
        hash_Set.add("productVendor");
        hash_Set.add("productDescription");
        hash_Set.add("quantityInStock");
        hash_Set.add("buyPrice");
        hash_Set.add("MSRP");
        List<Product> productList = productdao.findall();

        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try{

            FilterProvider filters = new SimpleFilterProvider().addFilter("Productfilter",SimpleBeanPropertyFilter.filterOutAllExcept(hash_Set));
            String productJson = (new ObjectMapper()).setFilterProvider(filters).setSerializationInclusion(JsonInclude.Include.ALWAYS)
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true)
                    .writeValueAsString(productList);
            Logback.logger.info("{}.{}|Try: Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(productJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/product/find/S10_1678
    @GET
    @Path("/find/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("productCode") String procode, @Context HttpHeaders headers){
        hash_Set.add("productCode");
        hash_Set.add("productName");
        hash_Set.add("productLine");
        hash_Set.add("productScale");
        hash_Set.add("productVendor");
        hash_Set.add("productDescription");
        hash_Set.add("quantityInStock");
        hash_Set.add("buyPrice");
        hash_Set.add("MSRP");
        Product product = productdao.findById(procode);
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Productfilter", SimpleBeanPropertyFilter.filterOutAllExcept(hash_Set));
            String productJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(product);
            Logback.logger.info("{}.{}|Try: a recored send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(productJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /*http://localhost:8080/order/rest/product/insert
    body:
    {
    "productCode": "S10_1601",
    "productName": "1999",
    "productLine": "Motorcycles",
    "productScale": "1:10",
    "productVendor": "Min min min",
    "productDescription": "wow",
    "quantityInStock": 666,
    "buyPrice": 66.6,
    "MSRP": 66.66
    }
    */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Product product, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try {
            String status = productdao.insert(product);
            Logback.logger.info("{}.{}|Try:Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.info("{}.{}|Exception : {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("0").build();
        }
    }

    //http://localhost:8080/order/rest/product/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Product product, @Context HttpHeaders headers) {
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try {
            String status = productdao.update(product);
            Logback.logger.info("{}.{}|try : record is updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        } catch (Exception e) {
            Logback.logger.info("{}.{}|Exception : {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("0").build();
        }
    }
    //http://localhost:8080/order/rest/product/S10_1601
    @DELETE
    @Path("/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("productCode") String procode, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try{
            Integer status = productdao.delete(procode);
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.info("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("0").build();
        }
    }
}