package com.helman.Webservice;/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/28/21
  @Time 2:07 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import com.helman.Dao.Officedao;
import com.helman.Entity.Office;
import com.helman.Entity.Order;
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
import java.util.UUID;

@WebService(name = "OfficeInt", serviceName = "OfficeSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class OfficeSop {
    Officedao officedao = new Officedao();
    Security security = new Security();

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    @WebResult(name = "Offices")
    public Office[] findall(){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            List<Office> offices= officedao.findAll();
            Office[] itemsArray = new Office[offices.size()];
            Log4j.logger.info("{}.{}|Try: Send recordes to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return offices.toArray(itemsArray);
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    @WebResult(name = "Office")
    public Office findbyid(@WebParam(name = "officeCode") String ofcode){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Office office = officedao.findById(ofcode);
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return office;
        } catch (Exception e) {
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <insert xmlns="http://Webservice.helman.com/">
            <Office xmlns="">
    			<officeCode xmlns="">11</officeCode>
    			<city xmlns="">Tehran</city>
    			<phone xmlns="">88089</phone>
    			<addressLine1 xmlns="">shahrak</addressLine1>
    			<country xmlns="">Iran</country>
    			<postalCode xmlns="">1982347809</postalCode>
    			<territory xmlns="">Teh</territory>
    		</Office>
        </insert>
    </Body>
</Envelope>*/
    @WebMethod
    @WebResult(name = "returnStatus")
    public String insert (@WebParam(name = "Office") Office office){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ","")
                    .replaceFirst("\\[","").replace("]","");
            if(!security.tokenAuthCheck(token)) return null;

            String returnStatus = officedao.insert(office);
            Log4j.logger.info("{}.{}|Try: record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e) {
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception: {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID + e.toString();
        }
    }

    /*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <update xmlns="http://Webservice.helman.com/">
                <Office xmlns="">
    			    <officeCode xmlns="">11</officeCode>
    			    <city xmlns="">Tehran</city>
    			    <phone xmlns="">66666</phone>
    			    <addressLine1 xmlns="">shahrak1</addressLine1>
    			    <country xmlns="">Iran1</country>
    			    <postalCode xmlns="">1986666666</postalCode>
    			    <territory xmlns="">Teh1</territory>
    		    </Office>
            </update>
        </Body>
    </Envelope>*/
    @WebMethod
    @WebResult(name = "returnStatus")
    public String update(@WebParam(name = "Office") Office office){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token=http_headers.get("Authorization").toString().replaceFirst("Bearer ","")
                    .replaceFirst("\\[","").replace("]","");
            if(!security.tokenAuthCheck(token)) return null;

            String returnStatus = officedao.update(office);
            Log4j.logger.info("{}.{}|Try: record is updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e) {
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception: {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID + e.toString();
        }
    }

    @WebMethod
    @WebResult(name = "returnStatus")
    public String delete (@WebParam(name = "officeCode") String ofcode) {
        try {
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Integer returnStatus = officedao.delete(officedao.findById(ofcode));
            Log4j.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e) {
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID + e.toString();
        }
    }
}
