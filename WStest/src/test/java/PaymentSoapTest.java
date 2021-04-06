import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.junit.Test;
import payment.*;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class PaymentSoapTest {
    URL url = new URL("http://localhost:8080/order/soap/payment");
    PaymentSrv PSrv = new PaymentSrv(url);
    PaymentInt PInt = PSrv.getPaymentIntPort();

    public PaymentSoapTest() throws MalformedURLException {}
    public String toString(Payment payment) {
        return "Payment{" +
                "customerNumber=" + payment.getCustomerNumber() +
                ", checkNumber='" + payment.getCheckNumber() + '\'' +
                ", paymentDate=" + payment.getPaymentDate() +
                ", amount=" + payment.getAmount() +
                '}';
    }

    @Test
    public void findallTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        PaymentArray paymentArray = PInt.findall();
        List<Payment> payments = paymentArray.getItem();
        for (Payment payment : payments)
            System.out.println(toString(payment));
    }

    @Test
    public void findbyidTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Payment payment = PInt.findbyid(103, "DB66666");
        System.out.println(toString(payment));
    }

    @Test
    public void insertTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

/*        Payment payment = new Payment();
        payment.setCustomerNumber(103);
        payment.setCheckNumber("HD66666");
        payment.setPaymentDate(2004-10-19);
        payment.setAmount(new BigDecimal(6606.6));

        PaymentPK paymentPK = PInt.insert(payment);
        System.out.println(paymentPK);*/
    }

    @Test
    public void updateTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        /*Payment payment = PInt.findbyid(103, "HD66666");
        payment.setPaymentDate(2004-10-19);
        payment.setAmount(new BigDecimal(6606.6));

        PaymentPK paymentPK = PInt.update(payment);
        System.out.println(paymentPK);*/
    }

    @Test
    public void deleteTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        String returnStatus = PInt.delete(103, "DB66666");
        System.out.println(returnStatus);
    }
}
