
package productline;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the productline package. 
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

    private final static QName _ProductlineXML_QNAME = new QName("http://Webservice.helman.com/", "ProductlineXML");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: productline
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Productline }
     * 
     */
    public Productline createProductline() {
        return new Productline();
    }

    /**
     * Create an instance of {@link ProductlineArray }
     * 
     */
    public ProductlineArray createProductlineArray() {
        return new ProductlineArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Productline }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Productline }{@code >}
     */
    @XmlElementDecl(namespace = "http://Webservice.helman.com/", name = "ProductlineXML")
    public JAXBElement<Productline> createProductlineXML(Productline value) {
        return new JAXBElement<Productline>(_ProductlineXML_QNAME, Productline.class, null, value);
    }

}
