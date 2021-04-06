import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.junit.Test;
import productline.Productline;
import productline.ProductlineArray;
import productline.ProductlineInt;
import productline.ProductlineSrv;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductlineSoapTest {
    URL url = new URL("http://localhost:8080/order/soap/productline");
    ProductlineSrv PSrv = new ProductlineSrv(url);
    ProductlineInt PInt = PSrv.getProductlineIntPort();

    public ProductlineSoapTest() throws MalformedURLException {}

    public String toString(Productline productline) {
        return "Productline{" +
                "productLine='" + productline.getProductLine() + '\'' +
                ", textDescription='" + productline.getTextDescription() + '\'' +
                ", htmlDescription='" + productline.getHtmlDescription() + '\'' +
                '}';
    }

    @Test
    public void findallTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;


        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        ProductlineArray productlineArray = PInt.findall();
        List<Productline> productlines = productlineArray.getItem();
        for (Productline productline : productlines)
            System.out.println(toString(productline));
    }

    @Test
    public void findbyidTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Productline productline = PInt.findbyid("boom");
        System.out.println(toString(productline));
    }

    @Test
    public void insertTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client,"admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Productline productline = new Productline();
        productline.setProductLine("Boom2");
        productline.setTextDescription("Uhu");
        productline.setHtmlDescription("boom.com");
        String returnStatus = PInt.insert(productline);
        System.out.println(returnStatus);
    }

    @Test
    public void updateTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Productline productline = PInt.findbyid("Boom2");
        productline.setTextDescription("Uhuuuuuuu");
        productline.setHtmlDescription("boommm.com");
        String returnStatus = PInt.update(productline);
        System.out.println(returnStatus);
    }


    @Test
    public void deleteTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        String returnStatus = PInt.delete("Boom2");
        System.out.println(returnStatus);
    }
}
