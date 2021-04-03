package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/31/21
  @Time 3:26 AM
  Created by Intellije IDEA
  Description: Authorization with token
*/

import com.helman.Dao.Productlinedao;
import com.helman.Entity.Productline;
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

@WebService(name = "ProductlineInt", serviceName = "ProductlineSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ProductlineSop {
    Productlinedao productlinedao = new Productlinedao();
    Security security = new Security();

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    @WebResult(name = "productlines")
    public Productline[] findall(){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replaceFirst("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            List<Productline> productlines = productlinedao.findall();
            Productline[] itemsArray = new Productline[productlines.size()];
            Log4j.logger.info("{}.{}|Try: Send records to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return productlines.toArray(itemsArray);
        }catch(Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    @WebResult(name = "productline")
    public Productline findbyid(@WebParam(name = "productLine") String proline){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;
            return productlinedao.findById(proline);

        }catch(Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <insert xmlns="http://Webservice.helman.com/">
            <productline xmlns="">
                <productLine xmlns="">boom123</productLine>
                <textDescription xmlns="">boombing666</textDescription>
                <htmlDescription xmlns="">http://boom.ir</htmlDescription>
                <image xmlns=""></image>
            </productline>
        </insert>
    </Body>
</Envelope>*/
    @WebMethod
    @WebResult(name = "returnStatus")
    public String insert (@WebParam(name = "productline") Productline proline){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            String returnStatus = productlinedao.insert(proline);
            Log4j.logger.info("{}.{}|Try: record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        }catch(Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <update xmlns="http://Webservice.helman.com/">
            <productline xmlns="">
                <productLine xmlns="">boom123</productLine>
                <textDescription xmlns="">boombing777</textDescription>
                <htmlDescription xmlns="">http://boom777.ir</htmlDescription>
                <image xmlns=""></image>
            </productline>
        </update>
    </Body>
</Envelope>*/
    @WebMethod
    @WebResult(name = "returnStatus")
    public String update (@WebParam(name = "productline") Productline proline){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            String returnStatus = productlinedao.update(proline);
            Log4j.logger.info("{}.{}|Try: record is updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        }catch(Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID + e.toString();
        }
    }

    @WebMethod
    @WebResult(name = "returnStatus")
    public String delete (@WebParam(name = "productLine") String productLine){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Integer returnStatus = productlinedao.delete(productlinedao.findById(productLine));
            Log4j.logger.info("{}.{}|Try: record is deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        }catch(Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID + e.toString();
        }
    }
}