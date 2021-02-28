package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/28/21
//@Time 10:30PM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Officedao;
import com.helman.Entity.Office;
import com.helman.General.Logback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/office")
public class OfficeWs {
    Officedao officedao = new Officedao();

    //http:localhost:8080/order/rest/office/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(){
        List<Office> officeList = officedao.findAll();
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Officefilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("officeCode", "city", "phone", "country"));
            String officeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(officeList);
            Logback.logger.info("{}.{}|Try:Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(officeJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    //http:localhost:8080/order/rest/office/find/10
    @GET
    @Path("/find/{officeCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("officeCode") String ofcode){
        Office office = officedao.findById(ofcode);
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Officefilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("officeCode", "city", "phone", "addressLine1", "addressLine2", "state", "country", "postalCode"));
            String officeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(office);
            Logback.logger.info("{}.{}|Try:send a record to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(officeJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    /*http://localhost:8080/order/rest/office/insert
    body:
    {
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
    public String insert(Office office){
       String status = officedao.insert(office);
       Logback.logger.info("{}.{}|Try:Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
       return status;
    }
    //http://localhost:8080/order/rest/office/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Office office){
        Office updatedOffice = officedao.findById(office.getOfficeCode());
        updatedOffice.setOfficeCode(office.getOfficeCode());
        updatedOffice.setCity(office.getCity());
        updatedOffice.setPhone(office.getPhone());
        updatedOffice.setAddressLine1(office.getAddressLine1());
        updatedOffice.setAddressLine2(office.getAddressLine2());
        updatedOffice.setState(office.getState());
        updatedOffice.setCountry(office.getCountry());
        updatedOffice.setPostalCode(office.getPostalCode());
        updatedOffice.setTerritory(office.getTerritory());
        String status = officedao.update(updatedOffice);
        return status;
    }
    //http://localhost:8080/order/rest/office/11
    @DELETE
    @Path("/{officeCode}")
    @PathParam(MediaType.APPLICATION_JSON)
    public Integer delete(@PathParam("officeCode") String ofcode){
        Integer status = officedao.delete(officedao.findById(ofcode));
        return status;
    }
}