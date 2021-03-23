package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/22/21
  @Time 4:58 AM
  Created by Intellije IDEA
  Description: With Basic Authentication
*/

import com.helman.Dao.Customerdao;
import com.helman.Entity.Customer;
import com.helman.General.Log4j;
import com.helman.General.Logback;

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

@WebService(name = "CustomerInt", serviceName = "CustomerSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CustomerSop {
    Customerdao customerdao = new Customerdao();
    Security sec = new Security();

    @Resource
    WebServiceContext wsctx;

    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <findall xmlns="http://Webservice.helman.com/"/>
        </Body>
    </Envelope>
    */
    @WebMethod
    @WebResult(name = "Customers")
    public Customer[] findall() {
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            List<Customer> customerList= customerdao.findall();
            Customer[] itemsArray = new Customer[customerList.size()];
            Log4j.logger.info("{}.{}|Try: Send records to Soap",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return customerList.toArray(itemsArray);
        } catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <findById xmlns="http://Webservice.helman.com/">
                <customerNumber xmlns="">102</customerNumber>
            </findById>
        </Body>
    </Envelope>
    */
    @WebMethod
    @WebResult(name = "Customer")
    public Customer findById(@WebParam(name = "customerNumber") Integer custnum){
        try {
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            Customer customer = customerdao.findById(custnum);
            Log4j.logger.info("{}.{}|Try:Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return customer;
        }catch(Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <insert xmlns="http://Webservice.helman.com/">
                <customer xmlns="">
                	<customerNumber xmlns="">102</customerNumber>
            	    <customerName xmlns="">Jack</customerName>
            	    <contactLastName xmlns="">maraghi</contactLastName>
            	    <contactFirstName xmlns="">Shahi</contactFirstName>
            	    <phone xmlns="">+982188089</phone>
            	    <addressLine1 xmlns="">Teh</addressLine1>
            	    <addressLine2 xmlns="">Tehran</addressLine2>
            	    <city xmlns="">Tehran</city>
            	    <state xmlns=""></state>
            	    <postalCode xmlns="">7777777777</postalCode>
            	    <country xmlns="">Iran</country>
            	    <salesRepEmployeeNumber xmlns=""></salesRepEmployeeNumber>
            	    <creditLimit xmlns=""></creditLimit>
                </customer>
            </insert>
        </Body>
    </Envelope>
    */
    @WebMethod
    @WebResult(name = "ReturnStatus")
    public String insert(@WebParam(name = "customer") Customer customer){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            Integer returnStatus = customerdao.insert(customer);
            Log4j.logger.info("{}.{}|Try: Record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Logback.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID+ e.toString();
        }
    }
    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <update xmlns="http://Webservice.helman.com/">
            <customer xmlns="">
                	<customerNumber xmlns="">102</customerNumber>
            	    <customerName xmlns="">Jack</customerName>
            	    <contactLastName xmlns="">maraghi</contactLastName>
            	    <contactFirstName xmlns="">Shahi</contactFirstName>
            	    <phone xmlns="">+9821666666</phone>
            	    <addressLine1 xmlns="">Teh</addressLine1>
            	    <addressLine2 xmlns="">Tehran</addressLine2>
            	    <city xmlns="">Tehran</city>
            	    <state xmlns=""></state>
            	    <postalCode xmlns="">666666666</postalCode>
            	    <country xmlns="">Iran</country>
            	    <salesRepEmployeeNumber xmlns="">1166</salesRepEmployeeNumber>
            	    <creditLimit xmlns=""></creditLimit>
                </customer>
        </update>
    </Body>
</Envelope>
    */
    @WebMethod
    @WebResult(name = "ReturnStatus")
    public String update(@WebParam(name = "customer") Customer customer){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            Integer returnStatus = customerdao.update(customer);
            Log4j.logger.info("{}.{}|Try: record is updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Logback.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" +UUID + e.toString();
        }
    }
    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
                <customerNumber xmlns="">102</customerNumber>
            </delete>
        </Body>
    </Envelope>
    */
    @WebMethod
    @WebResult(name = "ReturnStatus")
    public String delete(@WebParam(name = "customerNumber") Integer custnum){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            Integer returnStatus = customerdao.delete(custnum);
            Log4j.logger.info("{}.{}|Try:Record is deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        }catch (Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Logback.logger.error("{}.{}|UUID:{} - EXception:{}", this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" +UUID+e.toString();
        }
    }
}
