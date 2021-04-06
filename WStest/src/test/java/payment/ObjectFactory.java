
package payment;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the payment package. 
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

    private final static QName _PaymentXML_QNAME = new QName("http://Webservice.helman.com/", "PaymentXML");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: payment
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link PaymentPK }
     * 
     */
    public PaymentPK createPaymentPK() {
        return new PaymentPK();
    }

    /**
     * Create an instance of {@link PaymentArray }
     * 
     */
    public PaymentArray createPaymentArray() {
        return new PaymentArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Payment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Payment }{@code >}
     */
    @XmlElementDecl(namespace = "http://Webservice.helman.com/", name = "PaymentXML")
    public JAXBElement<Payment> createPaymentXML(Payment value) {
        return new JAXBElement<Payment>(_PaymentXML_QNAME, Payment.class, null, value);
    }

}
