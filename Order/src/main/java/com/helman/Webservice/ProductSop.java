package com.helman.Webservice;/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/31/21
  @Time 4:16 AM
  Created by Intellije IDEA
  Description: Authorization with token
*/

import com.helman.Dao.Productdao;
import com.helman.Entity.Product;
import com.helman.General.Log4j;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

@WebService(name = "ProductInt", serviceName = "ProductSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ProductSop {
    Productdao productdao = new Productdao();
    Security security = new Security();

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    @WebResult(name = "products")
    public Product[] findall(){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            List<Product> products = productdao.findall();
            Product[] itemsArray = new Product[products.size()];
            Log4j.logger.info("{}.{}|Try: Send records to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return products.toArray(itemsArray);
        }catch (Exception e) {
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    @WebResult(name = "product")
    public Product findbyid(@WebParam(name = "productCode") String procode){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Product product = productdao.findById(procode);
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return product;
        }catch (Exception e) {
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <insert xmlns="http://Webservice.helman.com/">
            <Product xmlns="">
            	<productCode xmlns="">S10_1602</productCode>
            	<productName xmlns="">1999</productName>
            	<productLine xmlns="">Motorcycles</productLine>
            	<productScale xmlns="">1:10</productScale>
            	<productVendor xmlns="">Max</productVendor>
            	<productDescription xmlns="">uhu</productDescription>
            	<quantityInStock xmlns="">777</quantityInStock>
            	<buyPrice xmlns="">777.77</buyPrice>
            	<MSRP xmlns="">77.70</MSRP>
            </Product>
        </insert>
    </Body>
</Envelope>
    */
    @WebMethod
    @WebResult(name = "returnStatus")
    public String insert(@WebParam(name = "Product") Product product){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            String returnStatus = productdao.insert(product);
            Log4j.logger.info("{}.{}|Try: record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <update xmlns="http://Webservice.helman.com/">
            <Product xmlns="">
            	<productCode xmlns="">S10_1602</productCode>
            	<productName xmlns="">1999</productName>
            	<productLine xmlns="">Motorcycles</productLine>
            	<productScale xmlns="">1:10</productScale>
            	<productVendor xmlns="">Maxxxxxxxxx</productVendor>
            	<productDescription xmlns="">uhu</productDescription>
            	<quantityInStock xmlns="">6666</quantityInStock>
            	<buyPrice xmlns="">888.88</buyPrice>
            	<MSRP xmlns="">88.80</MSRP>
            </Product>
        </update>
    </Body>
</Envelope>*/
    @WebMethod
    @WebResult(name = "returnStatus")
    public String update (@WebParam(name = "Product") Product product){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            String returnStatus = productdao.update(product);
            Log4j.logger.info("{}.{}|Try: record is updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch(Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID + e.toString();
        }
    }

    /*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <delete xmlns="http://Webservice.helman.com/">
            <productCode xmlns="">S10_1602</productCode>
        </delete>
    </Body>
</Envelope>*/
    @WebMethod
    @WebResult(name = "returnStatus")
    public String delete(@WebParam(name = "productCode") String procode){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]","");
            if (!security.tokenAuthCheck(token)) return null;

            Integer returnStatus = productdao.delete(procode);
            Log4j.logger.info("{}.{}|Try: record is deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e) {
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();;
            return "Your Trace number is" + UUID + e.toString();
        }
    }
}
