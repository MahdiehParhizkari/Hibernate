import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.junit.Test;
import product.Product;
import product.ProductArray;
import product.ProductInt;
import product.ProductSrv;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ProductSoapTest {
    URL url = new URL("http://localhost:8080/order/soap/product");
    ProductSrv PSrv = new ProductSrv(url);
    ProductInt PInt = PSrv.getProductIntPort();

    public ProductSoapTest() throws MalformedURLException {}

    public String toString(Product product) {
        return "Product{" +
                "productCode='" + product.getProductCode() + '\'' +
                ", productName='" + product.getProductName() + '\'' +
                ", productLine='" + product.getProductLine() + '\'' +
                ", productScale='" + product.getProductScale() + '\'' +
                ", productVendor='" + product.getProductVendor() + '\'' +
                ", productDescription='" + product.getProductDescription() + '\'' +
                ", quantityInStock=" + product.getQuantityInStock() +
                ", buyPrice=" + product.getBuyPrice() +
                ", MSRP=" + product.getMSRP() +
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

        ProductArray productArray = PInt.findall();
        List<Product> products = productArray.getItem();
        for (Product product : products)
            System.out.println(toString(product));
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

        Product product = PInt.findbyid("S10_1600");
        System.out.println(toString(product));
    }

    @Test
    public void insertTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)PInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Product product = new Product();
        product.setProductCode("S10_1602");
        product.setProductName("1999");
        product.setProductLine("Motorcycles");
        product.setProductScale("1:10");
        product.setProductVendor("max");
        product.setProductDescription("uhuu");
        product.setQuantityInStock(66);
        product.setBuyPrice(new BigDecimal(77.77));
        product.setMSRP(new BigDecimal(77.7));
        String returnStatus = PInt.insert(product);
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

        Product product = PInt.findbyid("S10_1602");
        product.setProductName("1999");
        product.setProductLine("Motorcycles");
        product.setProductScale("1:10");
        product.setProductVendor("max max max");
        product.setProductDescription("uhuuuuuuuuu");
        product.setQuantityInStock(66);
        product.setBuyPrice(new BigDecimal(77.77));
        product.setMSRP(new BigDecimal(77.7));
        String returnStatus = PInt.update(product);
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

        String returnStatus = PInt.delete("S10_1602");
        System.out.println(returnStatus);
    }
}
