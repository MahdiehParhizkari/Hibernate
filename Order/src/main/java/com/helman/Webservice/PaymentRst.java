package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/25/21
//@Time 9:27PM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Paymentdao;
import com.helman.Entity.Payment;
import com.helman.Entity.PaymentPK;
import com.helman.General.Logback;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/payment")
public class PaymentRst {
    private Paymentdao paymentdao = new Paymentdao();

    //http://localhost:8080/order/rest/payment/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(@Context HttpHeaders headers) {
        String UsrPwdEncoded = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).replaceFirst("Basic ", " ");
        try {
            FilterProvider filters = new SimpleFilterProvider().addFilter("Paymentfilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("customerNumber", "checkNumber", "paymentDate", "amount"));
            List<Payment> paymentList = paymentdao.findAll();
            String paymentJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(paymentList);
            Logback.logger.info("{}.{}|Try: All records send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(paymentJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/payment/find/103/DB933704
    @GET
    @Path("/find/{customerNumber}/{checkNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("customerNumber") Integer custnum,
                             @PathParam("checkNumber") String checknum) {
        PaymentPK paymentPK = new PaymentPK(custnum, checknum);
        Payment payment = paymentdao.findById(paymentPK);
        try {
            FilterProvider filters = new SimpleFilterProvider().addFilter("Paymentfilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("customerNumber", "checkNumber", "paymentDate", "amount"));
            String paymentJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(payment);
            Logback.logger.info("{}.{}|Try:A record send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(paymentJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /*http://localhost:8080/order/rest/payment/insert
    Body:
    {
    "customerNumber": 103,
    "checkNumber": "DB66666",
    "paymentDate": "2020-11-24T23:28:56.782Z",
    "amount": 6
    }
     */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Payment payment) {
        try {
            PaymentPK paymentPK = paymentdao.insert(payment);
            return Response.status(Response.Status.OK).entity(paymentPK).build();
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/payment/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Payment payment) {
        try {
            PaymentPK paymentPK = new PaymentPK(payment.getCustomerNumber(), payment.getCheckNumber());
            Payment updatedPayment = paymentdao.findById(paymentPK);
            updatedPayment.setCustomerNumber(payment.getCustomerNumber());
            updatedPayment.setCheckNumber(payment.getCheckNumber());
            updatedPayment.setPaymentDate(payment.getPaymentDate());
            updatedPayment.setAmount(payment.getAmount());
            PaymentPK stat = paymentdao.update(updatedPayment);
            return Response.status(Response.Status.OK).entity(stat).build();
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/payment/delete/103/MA765515
    @DELETE
    @Path("/delete/{customerNumber}/{checkNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("customerNumber") Integer cusnum,
                           @PathParam("checkNumber") String checknum) {
        try {
            PaymentPK pPK = new PaymentPK(cusnum, checknum);
            Payment p = paymentdao.findById(pPK);
            Integer stat = paymentdao.delete(p);
            return Response.status(Response.Status.OK).entity(stat).build();
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}