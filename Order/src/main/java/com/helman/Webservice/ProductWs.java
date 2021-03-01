package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 3/1/21
//@Time 3:48AM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.helman.Dao.Productdao;
import com.helman.Entity.Product;
import com.helman.General.Logback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
public class ProductWs {
    private Productdao productdao = new Productdao();
    //http://localhost:8080/order/rest/product/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(){
        List<Product> productList = productdao.findall();
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Productfilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("productCode", "productName", "productDescription", "quantityInStock"));
            String productJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(productList);
            Logback.logger.info("{}.{}|Try: Send all records to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(productJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //http:localhost:8080/order/rest/product/find/S10_1678
    @GET
    @Path("/find/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyid(@PathParam("productCode") String procode){
        Product product = productdao.findById(procode);
        try{
            FilterProvider filters = new SimpleFilterProvider().addFilter("Productfilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    "productCode", "productName", "productLine", "productScale", "productVendor", "productDescription", "quantityInStock", "buyPrice", "MSRP"));
            String productJson = (new ObjectMapper()).writer(filters).withDefaultPrettyPrinter().writeValueAsString(product);
            Logback.logger.info("{}.{}|Try: a recored send to RESTful", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return Response.status(Response.Status.OK).entity(productJson).build();
        } catch (JsonProcessingException e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /*http:localhost:8080/order/rest/product/insert
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
    public String insert(Product product){
        String status = productdao.insert(product);
        Logback.logger.info("{}.{}|Try:Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return status;
    }

    //http:localhost:8080/order/rest/product/update
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(Product product){
        Product updatedProduct = productdao.findById(product.getProductCode());
        updatedProduct.setProductCode(product.getProductCode());
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductLine(product.getProductLine());
        updatedProduct.setProductScale(product.getProductScale());
        updatedProduct.setProductVendor(product.getProductVendor());
        updatedProduct.setProductDescription(product.getProductDescription());
        updatedProduct.setQuantityInStock(product.getQuantityInStock());
        updatedProduct.setBuyPrice(product.getBuyPrice());
        updatedProduct.setMSRP(product.getMSRP());
        String status = productdao.update(updatedProduct);
        return status;
    }
    //http:localhost:8080/order/rest/product/S10_1601
    @DELETE
    @Path("/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer delete(@PathParam("productCode") String procode){
        Integer status = productdao.delete(procode);
        return status;
    }

}
