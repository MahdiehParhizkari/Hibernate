package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/28/21
//@Time 3:13AM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Employeedao;
import com.helman.Entity.Customer;
import com.helman.Entity.Employee;
import com.helman.General.Logback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class EmployeeWs {
    Employeedao employeedao = new Employeedao();
    //http:localhost:8080/order/rest/employee/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(){
        List<Employee> employeeList = employeedao.findall();
        //return employeeList;
        try{
            //filter some attributes
            FilterProvider filters = new SimpleFilterProvider().addFilter("Employeefilter", SimpleBeanPropertyFilter.filterOutAllExcept
                    ("employeeNumber", "lastName", "firstName", "email"));
            //(list)Obj -> Json
            //json = mapper(obj)
            String employeeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(employeeList);
            Logback.logger.info("{}.{}|Try:Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            //create req,resp and data put in response package
            return Response.status(Response.Status.OK).entity(employeeJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    //http://localhost8080/order/rest/employee/find/1004
    @GET
    @Path("/find/{employeeNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("employeeNumber") Long empnum){
        Employee employee = employeedao.findbyid(empnum);
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Employeefilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("employeeNumber", "lastName", "firstName", "email"));
            String employeeJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(employee);
            Logback.logger.info("{}.{}|Try:Send record to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(employeeJson).build();
        }catch (JsonProcessingException e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    /*http://localhost:8080/order/rest/employee/insert
    body:
    {
    "employeeNumber": 1001,
    "lastName": "Rad",
    "firstName": "Ali",
    "extension": "x5800",
    "email": "rad@gmail.com",
    "officeCode": "1",
    "jobTitle": "IT"
    }
     */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Long insert (Employee employee){
        Long status = (employeedao.insert(employee));
        Logback.logger.info("{}.{}|Try:Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return (status);
    }
    //http://localhost:8080/order/rest/employee/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Long update(Employee employee){
        Employee updatedEmployee = employeedao.findbyid(employee.getEmployeeNumber());
        updatedEmployee.setEmployeeNumber(employee.getEmployeeNumber());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setExtension(employee.getExtension());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setOfficeCode(employee.getOfficeCode());
        updatedEmployee.setJobTitle(employee.getJobTitle());
        Long status = employeedao.update(updatedEmployee);
        Logback.logger.info("{}.{}|Try: Updated!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }
    //http://localhost:8080/order/rest/employee/1000
    @DELETE
    @Path("/{employeeNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer delete(@PathParam("employeeNumber") Long empnum){
        Integer status = employeedao.delete(empnum);
        Logback.logger.info("{}.{}|Try:Deleted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }
}
