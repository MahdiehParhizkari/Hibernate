import customer.Customer;
import customer.CustomerArray;
import customer.CustomerInt;
import customer.CustomerSrv;
import org.junit.Test;

import org.bouncycastle.util.encoders.Base64;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class CustomerSoaptest {
    URL url = new URL("http://localhost:8080/order/soap/customer");
    CustomerSrv CSrv = new CustomerSrv(url);
    CustomerInt CInt = CSrv.getCustomerIntPort();

    public CustomerSoaptest() throws MalformedURLException {}

    public String toString(Customer customer) {
        return "Customer{" +
                "customerNumber=" + customer.getCustomerNumber() +
                ", customerName='" + customer.getCustomerName() + '\'' +
                ", contactLastName='" + customer.getContactLastName() + '\'' +
                ", contactFirstName='" + customer.getContactFirstName() + '\'' +
                ", phone='" + customer.getPhone() + '\'' +
                ", addressLine1='" + customer.getAddressLine1() + '\'' +
                ", addressLine2='" + customer.getAddressLine2() + '\'' +
                ", city='" + customer.getCity() + '\'' +
                ", state='" + customer.getState() + '\'' +
                ", postalCode='" + customer.getPostalCode() + '\'' +
                ", country='" + customer.getCountry() + '\'' +
                ", salesRepEmployeeNumber=" + customer.getSalesRepEmployeeNumber() +
                ", creditLimit=" + customer.getCreditLimit() +
                '}';
    }

    @Test
    public void findallTest() throws MalformedURLException {
        Map<String, Object> req_ctx = ((BindingProvider)CInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        
        CustomerArray customers = CInt.findall();
        List<Customer> customerList = customers.getItem();
        for (Customer customer : customerList)
            System.out.println(toString(customer));
    }

    @Test
    public void findbyidTest(){
        Map<String, Object> req_ctx = ((BindingProvider)CInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Customer customer = CInt.findById(102);
        System.out.println(toString(customer));
    }

    @Test
    public void insertTest(){
        Map<String, Object> req_ctx = ((BindingProvider)CInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Customer customer = new Customer();
        customer.setCustomerNumber(104);
        customer.setCustomerName("Helman");
        customer.setContactLastName("Parhiz");
        customer.setContactFirstName("Sadaf");
        customer.setPhone("0214435");
        customer.setAddressLine1("Somaye");
        customer.setAddressLine2("west");
        customer.setCity("Qazvin");
        customer.setPostalCode("123456789");
        customer.setCountry("Iran");
        customer.setSalesRepEmployeeNumber(1166);
        customer.setCreditLimit(new BigDecimal("666666"));
        String returnStatus = CInt.insert(customer);
        System.out.println(returnStatus);
    }

    @Test
    public void updateTest(){
        Map<String, Object> req_ctx = ((BindingProvider)CInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Customer cus = CInt.findById(104);
        cus.setCustomerName("Helman1");
        cus.setContactLastName("Pari");
        cus.setContactFirstName("Sad");
        cus.setPhone("0214430");
        cus.setAddressLine1("Somaye1");
        cus.setAddressLine2("east");
        cus.setCity("Qazvin");
        cus.setPostalCode("987654321");
        cus.setCountry("Iran");
        cus.setSalesRepEmployeeNumber(1166);
        cus.setCreditLimit(new BigDecimal("666666"));
        String returnStatus = CInt.update(cus);
        System.out.println(returnStatus);
    }

    @Test
    public void deleteTest(){
        Map<String, Object> req_ctx = ((BindingProvider)CInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        String returnStatus = CInt.delete(104);
        System.out.println(returnStatus);
    }
}
