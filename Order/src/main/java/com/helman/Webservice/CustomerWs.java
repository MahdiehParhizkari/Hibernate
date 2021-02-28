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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/customer")
public class CustomerWs {
    private Customerdao customerdao = new Customerdao();
    private List<Customer> customerList = new ArrayList<>();
    //http://localhost:8080/order/rest/customer/findall
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(){
        customerList.clear();
        customerList = customerdao.findall();
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter(
                    "Customerfilter", SimpleBeanPropertyFilter.filterOutAllExcept("customerName", "contactLastName", "contactFirstName", "phone"));
            ObjectMapper mapper = new ObjectMapper();
            String customerJson = mapper.writer(filters).withDefaultPrettyPrinter().writeValueAsString(customerList);
            //String customerJson = mapper(Listof obj customer);
            Logback.logger.info("{}.{}|Try:Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(customerJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        }
    }
    //http://localhost:8080/order/rest/customer/find/104
    @GET
    @Path("find/{customerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("customerNumber") Integer customerNumber){
        Customer customer = customerdao.findById(customerNumber);
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter(
              "Customerfilter", SimpleBeanPropertyFilter.filterOutAllExcept("customerNumber", "customerName", "contactLastName", "contactFirstName", "phone"));
            String customerJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(customer);
            Logback.logger.info("{}.{}|Try: Send record to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(customerJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        }
    }
    /*http://localhost:8080/order/rest/customer/insert
    body:
    {
    "customerNumber": 102,
    "customerName": "Jack",
    "contactLastName": "maraghi",
    "contactFirstName": "Shahrzad",
    "phone": "+982188089786",
    "addressLine1":"Teh",
    "city": "Tehran",
    "country": "Iran"
    }*/
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer insert(Customer customer){
        Integer status = customerdao.insert(customer);
        Logback.logger.info("{}.{}|Try: Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }
    //http://localhost:8080/order/rest/customer/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer update(Customer customer){
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
        return status;
    }
    //http://localhost:8080/order/rest/customer/102
    @DELETE
    @Path("/{customerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer delete(@PathParam("customerNumber") Integer customerNumber){
        Integer status = customerdao.delete(customerNumber);
        Logback.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }
}