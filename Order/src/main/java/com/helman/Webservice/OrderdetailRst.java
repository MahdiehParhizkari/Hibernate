package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 3/2/21
//@Time 3:22AM
//        Created by Intellije IDEA
//        Description: Authorization with token

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Orderdetaildao;
import com.helman.Entity.Orderdetail;
import com.helman.Entity.OrderdetailPK;
import com.helman.General.Logback;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orderdetail")
public class OrderdetailRst {
    private Orderdetaildao orderdetaildao = new Orderdetaildao();
    Security sec = new Security();
    //http://localhost:8080/order/rest/orderdetail/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(@Context HttpHeaders headers){
        List<Orderdetail> orderdetailList = orderdetaildao.findAll();
        Orderdetail od=new Orderdetail();

        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();

        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Orderdetailfilter",SimpleBeanPropertyFilter.filterOutAllExcept(od.getfilters()));
            String orderdetailJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(orderdetailList);
            Logback.logger.info("{}.{}|Try:All records send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(orderdetailJson).build();
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/orderdetail/find/10100/S18_1749
    @GET
    @Path("/find/{orderNumber}/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("orderNumber") Integer ordnum,
                             @PathParam("productCode")String procode,
                             @Context HttpHeaders headers){
        OrderdetailPK orderdetailPK = new OrderdetailPK(ordnum, procode);
        Orderdetail orderdetail = orderdetaildao.findById(orderdetailPK);
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Orderdetailfilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(orderdetail.getfilters()));
            String productlineJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(orderdetail);
            Logback.logger.info("{}.{}|Try:All records send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(productlineJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http:localhost:8080/order/rest/orderdetail/insert
    /*body:{
    "orderNumber": 10100,
    "productCode": "S10_1600",
    "quantityOrdered": 66,
    "priceEach": 666.6,
    "orderLineNumber": 1
    }
     */
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Orderdetail od, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();
        try {
            OrderdetailPK sta = orderdetaildao.insert(od);
            return Response.status(Response.Status.OK).entity(sta).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http:localhost:8080/order/rest/orderdetail/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Orderdetail od, @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();
        try {
            OrderdetailPK odPK = new OrderdetailPK(od.getOrderNumber(), od.getProductCode());
            Orderdetail updatedod = orderdetaildao.findById(odPK);
            updatedod.setOrderNumber(od.getOrderNumber());
            updatedod.setProductCode(od.getProductCode());
            updatedod.setQuantityOrdered(od.getQuantityOrdered());
            updatedod.setPriceEach(od.getPriceEach());
            updatedod.setOrderLineNumber(od.getOrderLineNumber());
            OrderdetailPK stat = orderdetaildao.update(updatedod);
            return Response.status(Response.Status.OK).entity(stat).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http://localhost:8080/order/rest/orderdetail/10100/S18_1749
    @DELETE
    @Path("/{orderNumber}/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam ("orderNumber") Integer ordnum, @PathParam("productCode") String procode,
                           @Context HttpHeaders headers){
        String token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0).substring("Bearer ".length()).trim();
        if (!sec.tokenAuthCheck(token))
            return Response.status(Response.Status.UNAUTHORIZED).entity("token not valid").build();
        try{
            OrderdetailPK orderdetailPK = new OrderdetailPK(ordnum, procode);
            Orderdetail od = orderdetaildao.findById(orderdetailPK);
            Integer status = orderdetaildao.delete(od);
            return Response.status(Response.Status.OK).entity(status).build();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
