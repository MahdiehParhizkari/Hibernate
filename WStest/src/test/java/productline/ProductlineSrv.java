
package productline;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ProductlineSrv", targetNamespace = "http://Webservice.helman.com/", wsdlLocation = "http://localhost:8080/order/soap/productline?wsdl")
public class ProductlineSrv
    extends Service
{

    private final static URL PRODUCTLINESRV_WSDL_LOCATION;
    private final static WebServiceException PRODUCTLINESRV_EXCEPTION;
    private final static QName PRODUCTLINESRV_QNAME = new QName("http://Webservice.helman.com/", "ProductlineSrv");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/order/soap/productline?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PRODUCTLINESRV_WSDL_LOCATION = url;
        PRODUCTLINESRV_EXCEPTION = e;
    }

    public ProductlineSrv() {
        super(__getWsdlLocation(), PRODUCTLINESRV_QNAME);
    }

    public ProductlineSrv(WebServiceFeature... features) {
        super(__getWsdlLocation(), PRODUCTLINESRV_QNAME, features);
    }

    public ProductlineSrv(URL wsdlLocation) {
        super(wsdlLocation, PRODUCTLINESRV_QNAME);
    }

    public ProductlineSrv(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PRODUCTLINESRV_QNAME, features);
    }

    public ProductlineSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProductlineSrv(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ProductlineInt
     */
    @WebEndpoint(name = "ProductlineIntPort")
    public ProductlineInt getProductlineIntPort() {
        return super.getPort(new QName("http://Webservice.helman.com/", "ProductlineIntPort"), ProductlineInt.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProductlineInt
     */
    @WebEndpoint(name = "ProductlineIntPort")
    public ProductlineInt getProductlineIntPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Webservice.helman.com/", "ProductlineIntPort"), ProductlineInt.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PRODUCTLINESRV_EXCEPTION!= null) {
            throw PRODUCTLINESRV_EXCEPTION;
        }
        return PRODUCTLINESRV_WSDL_LOCATION;
    }

}
