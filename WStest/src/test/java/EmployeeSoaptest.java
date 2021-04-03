import employee.Employee;
import employee.EmployeeArray;
import employee.EmployeeInt;
import employee.EmployeeSrv;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeSoaptest {
    URL url = new URL("http://localhost:8080/order/soap/employee");
    EmployeeSrv ESrv = new EmployeeSrv(url);
    EmployeeInt EInt = ESrv.getEmployeeIntPort();

    public EmployeeSoaptest() throws MalformedURLException {}

    public String toString(Employee employee) {
        return "Employee{" +
                "employeeNumber=" + employee.getEmployeeNumber() +
                ", lastName='" + employee.getLastName() + '\'' +
                ", firstName='" + employee.getFirstName() + '\'' +
                ", extension='" + employee.getExtension() + '\'' +
                ", email='" + employee.getEmail() + '\'' +
                ", officeCode='" + employee.getOfficeCode() + '\'' +
                ", reportsTo=" + employee.getReportsTo() +
                ", jobTitle='" + employee.getJobTitle() + '\'' +
                '}';
    }

    @Test
    public void findallTest(){
        Map<String, Object> req_ctx = ((BindingProvider)EInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic "+ new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        EmployeeArray employeeArray = EInt.findall();
        List<Employee> employees = employeeArray.getItem();
        for (Employee employee : employees)
            System.out.println(toString(employee));
    }

    @Test
    public void findbyidTest(){
        Map<String, Object> req_ctx = ((BindingProvider)EInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Employee employee = EInt.findbyid(1001);
        System.out.println(toString(employee));
    }

    @Test
    public void insertTest(){
        Map<String, Object> req_ctx = ((BindingProvider)EInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Employee employee = new Employee();
        employee.setEmployeeNumber(Long.valueOf(1001));
        employee.setLastName("Maraghi");
        employee.setFirstName("Ali");
        employee.setExtension("x5800");
        employee.setEmail("MA@gmail");
        employee.setOfficeCode("1");
        employee.setReportsTo(Long.valueOf(1002));
        employee.setJobTitle("nurse");
        Long returnStatus = EInt.insert(employee);
        System.out.println(returnStatus);
    }

    @Test
    public void updateTest(){
        Map<String, Object> req_ctx = ((BindingProvider)EInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization", Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        Employee employee = EInt.findbyid(1001l);
/*        employee.setLastName("Maraghiiii");
        employee.setFirstName("Mohammad");
        employee.setExtension("x5800");
        employee.setEmail("MoA@gmail");
        employee.setOfficeCode("1");
        employee.setReportsTo(Long.valueOf(1002));
        employee.setJobTitle("nurse1");*/
        Long returnStatus = EInt.update(employee);
        System.out.println(returnStatus);
    }

    @Test
    public void deleteTest(){
        Map<String, Object> req_ctx = ((BindingProvider)EInt).getRequestContext();
        Map<String, List<String>> headers = new HashMap<>();
        String credential = "Basic " + new String(Base64.encode("admin:123".getBytes()));
        headers.put("Authorization" , Collections.singletonList(credential));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        String returnStatus = EInt.delete(1001);
        System.out.println(returnStatus);
    }
}