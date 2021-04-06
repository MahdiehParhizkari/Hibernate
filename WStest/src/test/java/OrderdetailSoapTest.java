import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import orderdetail.*;
import org.junit.Test;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderdetailSoapTest {
    URL url = new URL("http://localhost:8080/order/soap/orderdetail");
    OrderdetailSrv OSrv = new OrderdetailSrv(url);
    OrderdetaitInt OInt = OSrv.getOrderdetaitIntPort();

    public OrderdetailSoapTest() throws MalformedURLException {}
    public String toString(Orderdetail orderdetail) {
        return "Orderdetail{" +
                "orderNumber=" + orderdetail.getOrderNumber() +
                ", productCode='" + orderdetail.getProductCode() + '\'' +
                ", quantityOrdered=" + orderdetail.getQuantityOrdered() +
                ", priceEach=" + orderdetail.getPriceEach() +
                ", orderLineNumber=" + orderdetail.getOrderLineNumber() +
                '}';
    }

    @Test
    public void findallTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        OrderdetailArray orderdetailArray = OInt.findall();
        List<Orderdetail> orderdetails = orderdetailArray.getItem();
        for (Orderdetail orderdetail : orderdetails)
            System.out.println(orderdetail);
    }

    @Test
    public void findbyidTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Orderdetail orderdetail = OInt.findbyid(10100, "S10_1600");
        System.out.println(orderdetail);
    }

    @Test
    public void insertTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Orderdetail orderdetail = new Orderdetail();
        orderdetail.setOrderNumber(10100);
        orderdetail.setProductCode("S10_1601");
        orderdetail.setQuantityOrdered(4);
        orderdetail.setOrderLineNumber(22);
        OrderdetailPK returnStatus = OInt.insert(orderdetail);
        System.out.println(returnStatus);
    }

    @Test
    public void updateTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Orderdetail orderdetail = OInt.findbyid(10100, "S10_1601");
        orderdetail.setQuantityOrdered(4);
        orderdetail.setOrderLineNumber(44);
        OrderdetailPK returnStatus = OInt.insert(orderdetail);
        System.out.println(returnStatus);
    }

    @Test
    public void deleteTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        String returnStatus = OInt.delete(10100, "S10_1600");
        System.out.println(returnStatus);
    }
}
