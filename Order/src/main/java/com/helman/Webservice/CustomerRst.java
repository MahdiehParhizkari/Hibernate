package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/27/21
//@Time 5:07AM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Customerdao;
import com.helman.Entity.Customer;
import com.helman.General.Logback;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/customer")
public class CustomerRst {
    private Customerdao customerdao = new Customerdao();
    private List<Customer> customerList = new ArrayList<>();
    Security sec = new Security();

    //http://localhost:8080/order/rest/customer/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(@Context HttpHeaders headers){
        customerList.clear();
        customerList = customerdao.findall();
        String encodUsrPwd = headers.getRequestHeader("Authorization").get(0).replaceFirst("Basic ", "");
        if (!sec.basicAuthCheck(encodUsrPwd)) return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try{
            Customer customer = new Customer();
            FilterProvider filters = new SimpleFilterProvider().addFilter(
                    "Customerfilter", SimpleBeanPropertyFilter.filterOutAllExcept(customer.getfilters()));
            ObjectMapper mapper = new ObjectMapper();
            String customerJson = mapper.writer(filters).withDefaultPrettyPrinter().writeValueAsString(customerList);
            //String customerJson = mapper(List of obj customer);
            Logback.logger.info("{}.{}|Try:Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(customerJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
    //http://localhost:8080/order/rest/customer/find/104
    @GET
    @Path("find/{customerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("customerNumber") Integer customerNumber, @Context HttpHeaders headers){
        String encodUsrPwd = headers.getRequestHeader("Authorization").get(0).replaceFirst("Basic ", "");
        if (!sec.basicAuthCheck(encodUsrPwd)) return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        Customer customer = customerdao.findById(customerNumber);
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Customerfilter", SimpleBeanPropertyFilter.filterOutAllExcept(customer.getfilters()));
            String customerJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(customer);
            Logback.logger.info("{}.{}|Try: Send record to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(customerJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
    /*http://localhost:8080/order/rest/customer/insert
    body:
    {
    "customerNumber": 102,
    "customerName": "Jack",
    "contactLastName": "maraghi",
    "contactFirstName": "Shahi",
    "phone": "+982188089",
    "addressLine1":"Teh",
    "city": "Tehran",
    "country": "Iran"
    }*/
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Customer customer, @Context HttpHeaders headers){
        String encodUsrPwd = headers.getRequestHeader("Authorization").get(0).replaceFirst("Basic ", "");
        if (!sec.basicAuthCheck(encodUsrPwd)) return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try {
            Integer status = customerdao.insert(customer);
            Logback.logger.info("{}.{}|Try: Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }
    //http://localhost:8080/order/rest/customer/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Customer customer, @Context HttpHeaders headers){
        String encodUsrPwd = headers.getRequestHeader("Authorization").get(0).replaceFirst("Basic ", "");
        if (!sec.basicAuthCheck(encodUsrPwd)) return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();

        try {
            Customer updatedCustomer = customerdao.findById(customer.getCustomerNumber());
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setContactLastName(customer.getContactLastName());
            updatedCustomer.setContactFirstName(customer.getContactFirstName());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setAddressLine1(customer.getAddressLine1());
            updatedCustomer.setCity(customer.getCity());
            updatedCustomer.setCountry(customer.getCountry());
            Integer status = customerdao.update(updatedCustomer);
            Logback.logger.info("{}.{}|Try:Updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }
    //http://localhost:8080/order/rest/customer/102
    @DELETE
    @Path("/{customerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("customerNumber") Integer customerNumber, @Context HttpHeaders headers){
        String encodUsrPwd = headers.getRequestHeader("Authorization").get(0).replaceFirst("Basic ", "");
        if (!sec.basicAuthCheck(encodUsrPwd)) return Response.status(Response.Status.UNAUTHORIZED).entity("User or password is wrong").build();
        try {
            Integer status = customerdao.delete(customerNumber);
            Logback.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }
}