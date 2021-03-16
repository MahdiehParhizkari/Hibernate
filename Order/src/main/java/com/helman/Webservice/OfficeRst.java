package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/28/21
//@Time 10:30PM
//        Created by Intellije IDEA
//        Description: Authorization with token

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Officedao;
import com.helman.Entity.Office;
import com.helman.General.Logback;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/office")
public class OfficeRst {
    Officedao officedao = new Officedao();
    Security sec = new Security();

    //http://localhost:8080/order/rest/office/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(@Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();

        try{
            List<Office> officeList = officedao.findAll();
            Office office = new Office();
            FilterProvider filters = new SimpleFilterProvider().addFilter("Officefilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(office.getfilters()));
            String officeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(officeList);
            Logback.logger.info("{}.{}|Try:Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(officeJson).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
    //http://localhost:8080/order/rest/office/find/10
    @GET
    @Path("/find/{officeCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("officeCode") String ofcode, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();

        try{
            Office office = officedao.findById(ofcode);
            FilterProvider filters = new SimpleFilterProvider().addFilter("Officefilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(office.getfilters()));
            String officeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(office);
            Logback.logger.info("{}.{}|Try:send a record to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(officeJson).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
    /*http://localhost:8080/order/rest/office/insert
    body:{
    "officeCode": "11",
    "city": "Tehran",
    "phone": "88089",
    "addressLine1": "shahrak",
    "country": "Iran",
    "postalCode": "1982347809",
    "territory": "Teh"
    }
    */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Office office, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();

       try{
           String returnstatus = officedao.insert(office);
           Logback.logger.info("{}.{}|Try:Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
           return Response.status(Response.Status.OK).entity(returnstatus).build();
        }catch (Exception e){
           Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
           e.printStackTrace();
           return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
       }
    }
    //http://localhost:8080/order/rest/office/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Office office, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();

        try{
            String returnstatus = officedao.update(officedao.findById(office.getOfficeCode()));
            return Response.status(Response.Status.OK).entity(returnstatus).build();
        }catch (Exception e){
                Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
                e.printStackTrace();
                return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
            }
    }
    //http://localhost:8080/order/rest/office/11
    @DELETE
    @Path("/{officeCode}")
    @PathParam(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("officeCode") String ofcode, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();

        try{
            Integer returnstatus = officedao.delete(officedao.findById(ofcode));
            return Response.status(Response.Status.OK).entity(returnstatus).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}