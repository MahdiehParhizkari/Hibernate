package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/22/21
  @Time 4:59 AM
  Created by Intellije IDEA
  Description: With Basic Authentication
*/

import com.helman.Dao.Orderdetaildao;
import com.helman.Entity.Orderdetail;
import com.helman.Entity.OrderdetailPK;
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

@WebService(name = "OrderdetaitInt", serviceName = "OrderdetailSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class OrderdetailSop {
    Orderdetaildao dao = new Orderdetaildao();
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
    @WebResult(name = "Orderdetails")
    public Orderdetail[] findall(){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "").
                    replaceFirst("\\[", "").replace("]","");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            List<Orderdetail> orderdetailList = dao.findAll();
            Orderdetail[] ItemsArray = new Orderdetail[orderdetailList.size()];
            Log4j.logger.info("{}.{}|Try:Send records to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return orderdetailList.toArray(ItemsArray);
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <findbyid xmlns="http://Webservice.helman.com/">
                <orderNumber xmlns="">10100</orderNumber>
                <productCode xmlns="">S10_1600</productCode>
            </findbyid>
        </Body>
    </Envelope>
    */
    @WebMethod
    @WebResult(name = "Orderdetail")
    public Orderdetail findbyid(@WebParam(name = "orderNumber") Integer ordernum,
                                @WebParam(name = "productCode") String procode) {
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            Orderdetail orderdetail = dao.findById(new OrderdetailPK(ordernum, procode));
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return orderdetail;
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <insert xmlns="http://Webservice.helman.com/">
                <Orderdetail  xmlns="">
	                <orderNumber xmlns="">10100</orderNumber>
	                <productCode xmlns="">S10_1600</productCode>
	                <quantityOrdered xmlns="">2</quantityOrdered>
	                <priceEach xmlns="">166.06</priceEach>
	                <orderLineNumber xmlns="">3</orderLineNumber>
                </Orderdetail>
            </insert>
        </Body>
    </Envelope>
    */
    @WebMethod
    @WebResult(name = "OrderdetailPK")
    public OrderdetailPK insert(@WebParam(name = "Orderdetail") Orderdetail orderdetail){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            OrderdetailPK orderdetailPK = dao.insert(orderdetail);
            Log4j.logger.info("{}.{}|Try:record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return orderdetailPK;
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @WebMethod
    @WebResult(name = "OrderdetailPK")
    public OrderdetailPK update(@WebParam(name = "Orderdetail") Orderdetail orderdetail){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            OrderdetailPK orderdetailPK = dao.update(orderdetail);
            Log4j.logger.info("{}.{}|Try:ecord is updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return orderdetailPK;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }




    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <delete xmlns="http://Webservice.helman.com/">
                <orderNumber xmlns="">10100</orderNumber>
                <productCode xmlns="">S10_1600</productCode>
            </delete>
        </Body>
    </Envelope>
    */
    @WebMethod
    @WebResult(name = "ReturnStatus")
    public String delete(@WebParam(name = "orderNumber") Integer ordernum,
                         @WebParam(name = "productCode") String procode) {
        try{
            Map http_header = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_header.get("Authorization").toString().replaceFirst("Basic ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!sec.basicAuthCheck(encodUsrPwd)) return null;

            Orderdetail od = dao.findById(new OrderdetailPK(ordernum, procode));
            Integer returnStatus = dao.delete(od);
            Log4j.logger.info("{}.{}|Try: deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}
