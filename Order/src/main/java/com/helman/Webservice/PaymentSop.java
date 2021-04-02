package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/29/21
  @Time 3:17 PM
  Created by Intellije IDEA
  Description: Authorization with token
*/

import com.helman.Dao.Paymentdao;
import com.helman.Entity.Payment;
import com.helman.Entity.PaymentPK;
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

@WebService(name = "PaymentInt", serviceName = "PaymentSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PaymentSop {
    Paymentdao paymentdao = new Paymentdao();
    Security security = new Security();

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    @WebResult(name = "payments")
    public Payment[] findall(){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            List<Payment> payments = paymentdao.findAll();
            Payment[] itemsArray = new Payment[payments.size()];
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return payments.toArray(itemsArray);
        }catch(Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    @WebResult
    public Payment findbyid(@WebParam(name = "customerNumber") Integer custnum,
                            @WebParam(name = "checkNumber") String checknum){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Payment payment = paymentdao.findById(new PaymentPK(custnum, checknum));
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return payment;
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
            <Payment xmlns="">
                <customerNumber xmlns="">103</customerNumber>
                <checkNumber xmlns="">DB66666</checkNumber>
                <paymentDate xmlns="">2020-11-24T23:00:00+03:30</paymentDate>
                <amount xmlns="">66.00</amount>
            </Payment>
        </insert>
    </Body>
</Envelope>
*/
    @WebMethod
    @WebResult(name = "PaymentPK")
    public PaymentPK insert(@WebParam(name = "Payment") Payment payment){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            PaymentPK paymentPK = paymentdao.insert(payment);
            Log4j.logger.info("{}.{}|Try:record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return paymentPK;
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <update xmlns="http://Webservice.helman.com/">
            <Payment xmlns="">
                <customerNumber xmlns="">103</customerNumber>
                <checkNumber xmlns="">DB777777</checkNumber>
                <paymentDate xmlns="">2020-11-24T23:00:00+03:30</paymentDate>
                <amount xmlns="">77.77</amount>
            </Payment>
        </update>
    </Body>
</Envelope>
*/
    @WebMethod
    @WebResult(name = "PaymentPK")
    public PaymentPK update(@WebParam(name = "Payment") Payment payment){
        try {
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            PaymentPK paymentPK = paymentdao.update(payment);
            Log4j.logger.info("{}.{}|Try:record is inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return paymentPK;
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    @WebResult(name = "returnStatus")
    public String delete(@WebParam(name = "customerNumber") Integer cusnum,
                         @WebParam(name = "checkNumber") String checknum){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String token = http_headers.get("Authorization").toString().replaceFirst("Bearer ", "")
                    .replaceFirst("\\[", "").replace("]", "");
            if (!security.tokenAuthCheck(token)) return null;

            Payment p = paymentdao.findById(new PaymentPK(cusnum, checknum));
            Integer returnStatus = paymentdao.delete(p);
            Log4j.logger.info("{}.{}|Try: deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}