
package orderdetail;

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
@WebService(name = "OrderdetaitInt", targetNamespace = "http://Webservice.helman.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OrderdetaitInt {


    /**
     * 
     * @return
     *     returns orderdetail.OrderdetailArray
     */
    @WebMethod
    @WebResult(name = "Orderdetails", partName = "Orderdetails")
    @Action(input = "http://Webservice.helman.com/OrderdetaitInt/findallRequest", output = "http://Webservice.helman.com/OrderdetaitInt/findallResponse")
    public OrderdetailArray findall();

    /**
     * 
     * @param orderNumber
     * @param productCode
     * @return
     *     returns orderdetail.Orderdetail
     */
    @WebMethod
    @WebResult(name = "Orderdetail", partName = "Orderdetail")
    @Action(input = "http://Webservice.helman.com/OrderdetaitInt/findbyidRequest", output = "http://Webservice.helman.com/OrderdetaitInt/findbyidResponse")
    public Orderdetail findbyid(
        @WebParam(name = "orderNumber", partName = "orderNumber")
        int orderNumber,
        @WebParam(name = "productCode", partName = "productCode")
        String productCode);

    /**
     * 
     * @param orderdetail
     * @return
     *     returns orderdetail.OrderdetailPK
     */
    @WebMethod
    @WebResult(name = "OrderdetailPK", partName = "OrderdetailPK")
    @Action(input = "http://Webservice.helman.com/OrderdetaitInt/updateRequest", output = "http://Webservice.helman.com/OrderdetaitInt/updateResponse")
    public OrderdetailPK update(
        @WebParam(name = "Orderdetail", partName = "Orderdetail")
        Orderdetail orderdetail);

    /**
     * 
     * @param orderNumber
     * @param productCode
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "ReturnStatus", partName = "ReturnStatus")
    @Action(input = "http://Webservice.helman.com/OrderdetaitInt/deleteRequest", output = "http://Webservice.helman.com/OrderdetaitInt/deleteResponse")
    public String delete(
        @WebParam(name = "orderNumber", partName = "orderNumber")
        int orderNumber,
        @WebParam(name = "productCode", partName = "productCode")
        String productCode);

    /**
     * 
     * @param orderdetail
     * @return
     *     returns orderdetail.OrderdetailPK
     */
    @WebMethod
    @WebResult(name = "OrderdetailPK", partName = "OrderdetailPK")
    @Action(input = "http://Webservice.helman.com/OrderdetaitInt/insertRequest", output = "http://Webservice.helman.com/OrderdetaitInt/insertResponse")
    public OrderdetailPK insert(
        @WebParam(name = "Orderdetail", partName = "Orderdetail")
        Orderdetail orderdetail);

}
