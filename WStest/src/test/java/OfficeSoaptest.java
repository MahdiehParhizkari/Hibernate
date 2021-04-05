import office.Office;
import office.OfficeArray;
import office.OfficeInt;
import office.OfficeSrv;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeSoaptest {
    URL url = new URL("http://localhost:8080/order/soap/office");
    OfficeSrv OSrv = new OfficeSrv(url);
    OfficeInt OInt = OSrv.getOfficeIntPort();

    public OfficeSoaptest() throws MalformedURLException {}
    public String toString(Office office) {
        return "Office{" +
                "officeCode='" + office.getOfficeCode() + '\'' +
                ", city='" + office.getCity() + '\'' +
                ", phone='" + office.getPhone() + '\'' +
                ", addressLine1='" + office.getAddressLine1() + '\'' +
                ", addressLine2='" + office.getAddressLine2() + '\'' +
                ", state='" + office.getState() + '\'' +
                ", country='" + office.getCountry() + '\'' +
                ", postalCode='" + office.getPostalCode() + '\'' +
                ", territory='" + office.getTerritory() + '\'' +
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

        OfficeArray officeArray = OInt.findall();
        List<Office> offices = officeArray.getItem();
        for (Office office : offices)
            System.out.println(toString(office));
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

        Office office = OInt.findbyid("10");
        System.out.println(toString(office));
    }


    @Test
    public void insertTest (){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Office office = new Office();
        office.setOfficeCode("11");
        office.setCity("Tehran");
        office.setPhone("0214430");
        office.setAddressLine1("Somaye");
        office.setAddressLine2("west");
        office.setState("teh");
        office.setCountry("Iran");
        office.setPostalCode("1983333333");
        office.setTerritory("NA");

        String returnStatus = OInt.insert(office);
        System.out.println(returnStatus);
    }


    @Test
    public void updateTest(){
        Client client = ClientBuilder.newClient();
        String token = SecurityTest.getToken(client, "admin", "123");
        if (token.equals("0")) return;

        Map<String, Object> req_ctx = ((BindingProvider)OInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization" , Collections.singletonList(token));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Office office = OInt.findbyid("11");
        office.setCity("Tehran1");
        office.setPhone("0214435");
        office.setAddressLine1("Somaye1");
        office.setAddressLine2("east");
        office.setState("tehran");
        office.setCountry("Ir");
        office.setPostalCode("1983333333");
        office.setTerritory("NA");

        String returnStatus = OInt.update(office);
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

        String returnStatus = OInt.delete("11");
        System.out.println(returnStatus);
    }
}
