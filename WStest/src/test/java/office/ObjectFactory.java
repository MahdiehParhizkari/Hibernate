
package office;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the office package. 
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

    private final static QName _OfficeXML_QNAME = new QName("http://Webservice.helman.com/", "OfficeXML");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: office
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Office }
     * 
     */
    public Office createOffice() {
        return new Office();
    }

    /**
     * Create an instance of {@link OfficeArray }
     * 
     */
    public OfficeArray createOfficeArray() {
        return new OfficeArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Office }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Office }{@code >}
     */
    @XmlElementDecl(namespace = "http://Webservice.helman.com/", name = "OfficeXML")
    public JAXBElement<Office> createOfficeXML(Office value) {
        return new JAXBElement<Office>(_OfficeXML_QNAME, Office.class, null, value);
    }

}
