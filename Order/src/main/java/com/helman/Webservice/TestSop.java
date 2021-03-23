package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/22/21
  @Time 1:47 AM
  Created by Intellije IDEA
  Description: http://localhost:8080/order/soap/test
*/

import com.helman.General.Logback;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
/*
name: display on application server
serviceName: display in URL and WSDL
portName: display in WSDL
targetNamespace : XML namespace, display in WSDL and XSD*/
@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class TestSop {
    public TestSop() {}

    @WebMethod
    @WebResult(name="Output")
    public String Echo(@WebParam(name="Input") String name) {
        Logback.logger.info("{}.{}|Try: your input is: {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),name);
        return "JAX-WS: Hi " + name;
    }
}
/*
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <Echo xmlns="http://Webservice.afshin.com/">
            <Input xmlns="">Afshin</Input>
        </Echo>
    </Body>
</Envelope>
* */
