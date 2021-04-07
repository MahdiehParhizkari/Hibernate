
package orderdetail;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the orderdetail package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OrderdetailPKXML_QNAME = new QName("http://Webservice.helman.com/", "OrderdetailPKXML");
    private final static QName _OrderdetailXML_QNAME = new QName("http://Webservice.helman.com/", "OrderdetailXML");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: orderdetail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderdetailPK }
     * 
     */
    public OrderdetailPK createOrderdetailPK() {
        return new OrderdetailPK();
    }

    /**
     * Create an instance of {@link Orderdetail }
     * 
     */
    public Orderdetail createOrderdetail() {
        return new Orderdetail();
    }

    /**
     * Create an instance of {@link OrderdetailArray }
     * 
     */
    public OrderdetailArray createOrderdetailArray() {
        return new OrderdetailArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderdetailPK }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OrderdetailPK }{@code >}
     */
    @XmlElementDecl(namespace = "http://Webservice.helman.com/", name = "OrderdetailPKXML")
    public JAXBElement<OrderdetailPK> createOrderdetailPKXML(OrderdetailPK value) {
        return new JAXBElement<OrderdetailPK>(_OrderdetailPKXML_QNAME, OrderdetailPK.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Orderdetail }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Orderdetail }{@code >}
     */
    @XmlElementDecl(namespace = "http://Webservice.helman.com/", name = "OrderdetailXML")
    public JAXBElement<Orderdetail> createOrderdetailXML(Orderdetail value) {
        return new JAXBElement<Orderdetail>(_OrderdetailXML_QNAME, Orderdetail.class, null, value);
    }

}
