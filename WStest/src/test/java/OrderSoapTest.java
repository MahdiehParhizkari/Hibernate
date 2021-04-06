import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import order.Order;
import order.OrderArray;
import order.OrderInt;
import order.OrderSrv;
import org.junit.Test;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSoapTest {
    URL url = new URL("http://localhost:8080/order/soap/order");
    OrderSrv OSrv = new OrderSrv(url);
    OrderInt OInt = OSrv.getOrderIntPort();


    public OrderSoapTest() throws MalformedURLException {}
    public String toString(Order order) {
        return "Order{" +
                "orderNumber=" + order.getOrderNumber() +
                ", orderDate=" +order.getOrderDate() +
                ", requiredDate=" +order.getRequiredDate() +
                ", shippedDate=" +order.getShippedDate() +
                ", status='" + order.getStatus() + '\'' +
                ", comments='" + order.getComments() + '\'' +
                ", customerNumber=" + order.getCustomerNumber() +
                '}';
    }

    @Test
    public void findallTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        OrderArray orderArray = OInt.findall();
        List<Order> orders = orderArray.getItem();
        for (Order order : orders)
            System.out.println(toString(order));
    }

    @Test
    public void findbyidTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Order order= OInt.findbyid(10099);
        System.out.println(toString(order));
    }


    @Test
    public void insertTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

/*        Order order = new Order();
        order.setOrderNumber(10099);
        order.setOrderDate("2020-11-24T23:28:56.330Z");
        order.setRequiredDate("2020-11-24T23:28:56.330Z");
        order.setShippedDate("2020-11-24T23:28:56.330Z");
        order.setStatus("Cancelled");
        order.setComments("We must pay it soon");
        order.setCustomerNumber(481);

        String returnStatus = OInt.insert(order);
        System.out.println(returnStatus);*/
    }

    @Test
    public void updateTest() {
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        /*Order order = new Order();
        order.setOrderNumber(10426);
        order.setOrderDate("2017-07-17T23:28:56.330Z");
        order.setRequiredDate("2018-08-18T23:28:56.330Z");
        order.setShippedDate("2019-09-19T23:28:56.330Z");
        order.setStatus("Shipped");
        order.setComments("We can't pay it soon");
        order.setCustomerNumber(496);

        String returnStatus = OInt.update(order);
        System.out.println(returnStatus);*/
    }

    @Test
    public void deleteTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        String returnStatus = OInt.delete(10099);
        System.out.println(returnStatus);
    }
}
