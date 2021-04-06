
package productline;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ProductlineInt", targetNamespace = "http://Webservice.helman.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProductlineInt {


    /**
     * 
     * @return
     *     returns productline.ProductlineArray
     */
    @WebMethod
    @WebResult(name = "productlines", partName = "productlines")
    @Action(input = "http://Webservice.helman.com/ProductlineInt/findallRequest", output = "http://Webservice.helman.com/ProductlineInt/findallResponse")
    public ProductlineArray findall();

    /**
     * 
     * @param productLine
     * @return
     *     returns productline.Productline
     */
    @WebMethod
    @WebResult(name = "productline", partName = "productline")
    @Action(input = "http://Webservice.helman.com/ProductlineInt/findbyidRequest", output = "http://Webservice.helman.com/ProductlineInt/findbyidResponse")
    public Productline findbyid(
        @WebParam(name = "productLine", partName = "productLine")
        String productLine);

    /**
     * 
     * @param productline
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "returnStatus", partName = "returnStatus")
    @Action(input = "http://Webservice.helman.com/ProductlineInt/updateRequest", output = "http://Webservice.helman.com/ProductlineInt/updateResponse")
    public String update(
        @WebParam(name = "productline", partName = "productline")
        Productline productline);

    /**
     * 
     * @param productLine
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "returnStatus", partName = "returnStatus")
    @Action(input = "http://Webservice.helman.com/ProductlineInt/deleteRequest", output = "http://Webservice.helman.com/ProductlineInt/deleteResponse")
    public String delete(
        @WebParam(name = "productLine", partName = "productLine")
        String productLine);

    /**
     * 
     * @param productline
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "returnStatus", partName = "returnStatus")
    @Action(input = "http://Webservice.helman.com/ProductlineInt/insertRequest", output = "http://Webservice.helman.com/ProductlineInt/insertResponse")
    public String insert(
        @WebParam(name = "productline", partName = "productline")
        Productline productline);

}
