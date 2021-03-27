package com.helman.Webservice;/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/27/21
  @Time 11:49 PM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import com.helman.Dao.Orderdao;
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

@WebService(name = "OrderInt", serviceName = "OrderSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class OrderSop {
    Orderdao orderdao = new Orderdao();
    Security security = new Security();

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    @WebResult(name = "orders")
    public Order[] findall(){
        try {
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replaceFirst("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            List<Order> orders = orderdao.findall();
            Order[] itemsArray = new Order[orders.size()];
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return orders.toArray(itemsArray);
        }catch(Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <findbyid xmlns="http://Webservice.helman.com/">
                <orderNumber xmlns="">10099</orderNumber>
            </findbyid>
        </Body>
    </Envelope>*/
    @WebMethod
    @WebResult(name = "order")
    public Order findbyid(@WebParam(name = "orderNumber") Integer ordnum){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Order order = orderdao.findById(ordnum);
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return order;
        } catch (Exception e) {
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

/*<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <insert xmlns="http://Webservice.helman.com/">
            <Order xmlns="">
	            <orderNumber xmlns="">10099</orderNumber>
	            <orderDate xmlns="">2020-10-15T23:28:56.782Z</orderDate>
	            <requiredDate xmlns="">2021-11-16T23:28:56.782Z</requiredDate>
	            <shippedDate xmlns="">2022-12-17T23:28:56.782Z</shippedDate>
	            <status xmlns="">Cancelled</status>
	            <comments xmlns="">We must pay it soon</comments>
	            <customerNumber xmlns="">481</customerNumber>
            </Order>
        </insert>
    </Body>
</Envelope>*/

    @WebMethod
    @WebResult(name = "returnStatus")
    public String insert(@WebParam(name = "Order") Order order){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Integer returnStatus = orderdao.insert(order);
            Log4j.logger.info("{}.{}|Try: record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        }catch (Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}| UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" +UUID + e.toString();
        }
    }

    @WebMethod
    @WebResult(name = "returnStatus")
    public String update (@WebParam(name = "Order") Order order){
        try {
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]","");
            if (!security.tokenAuthCheck(token)) return null;

            Integer returnStatus = orderdao.update(order);
            Log4j.logger.info("{}.{}|Try: record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
    } catch(Exception e){
            String UUID = java.util.UUID.randomUUID().toString();
            Log4j.logger.error("{}.{}|UUID:{} - Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), UUID, e.getMessage());
            e.printStackTrace();
            return "Your Trace number is" + UUID + e.toString();
        }
    }

    @WebMethod
    @WebResult(name = "returnStatus")
    public String delete(@WebParam(name = "orderNumber") Integer ordnum){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]","");
            if (!security.tokenAuthCheck(token)) return null;

            Integer returnStatus = orderdao.delete(ordnum);
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
