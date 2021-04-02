package com.helman.Webservice;

import org.junit.Test;
import javax.xml.ws.WebServiceClient;
import java.net.URL;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 4/1/21
  @Time 8:47 PM
  Created by Intellije IDEA
  Description: without any authentication
                1. Add @XmlRootElement,@XmlAccessorType annotation to Entity for mapping obj to XML and vice versa
                2. Develop Soap service
                3. Test service via Postman
                4. Build Project and Deploy it via Tomee
                5. Add codehaus.mojo library in POM Plugin
                3. Create(wsimport) Stub by Maven build
                4. Develop client side(test)
*/

@WebServiceClient(name = "CustomerSrvClient", wsdlLocation = "http://localhost:8080/order/soap/customer")
public class CustomerSopTest {
    /*String soapServicePath = "http://localhost:8080/order/soap/customer";
    URL url = new URL(soapServicePath);
    CustomerSrv CSrv = new CustomerSrv(url);
    CustomerInt CInt = CSrv.getCustomerIntPort();*/


    @Test
    public void findall() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}