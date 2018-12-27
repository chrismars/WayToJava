
package cn.edu.whu.waytojavaeurekaclient.service.validatecode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.edu.whu.waytojavaeurekaclient.service.validatecode package. 
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

    private final static QName _Base64Binary_QNAME = new QName("http://WebXml.com.cn/", "base64Binary");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.edu.whu.waytojavaeurekaclient.service.validatecode
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnValidateImage }
     * 
     */
    public EnValidateImage createEnValidateImage() {
        return new EnValidateImage();
    }

    /**
     * Create an instance of {@link SmallValidateImageResponse }
     * 
     */
    public SmallValidateImageResponse createSmallValidateImageResponse() {
        return new SmallValidateImageResponse();
    }

    /**
     * Create an instance of {@link EnValidateByte }
     * 
     */
    public EnValidateByte createEnValidateByte() {
        return new EnValidateByte();
    }

    /**
     * Create an instance of {@link EnValidateByteResponse }
     * 
     */
    public EnValidateByteResponse createEnValidateByteResponse() {
        return new EnValidateByteResponse();
    }

    /**
     * Create an instance of {@link CnValidateByteResponse }
     * 
     */
    public CnValidateByteResponse createCnValidateByteResponse() {
        return new CnValidateByteResponse();
    }

    /**
     * Create an instance of {@link SmallValidateByteResponse }
     * 
     */
    public SmallValidateByteResponse createSmallValidateByteResponse() {
        return new SmallValidateByteResponse();
    }

    /**
     * Create an instance of {@link CnValidateByte }
     * 
     */
    public CnValidateByte createCnValidateByte() {
        return new CnValidateByte();
    }

    /**
     * Create an instance of {@link EnValidateImageResponse }
     * 
     */
    public EnValidateImageResponse createEnValidateImageResponse() {
        return new EnValidateImageResponse();
    }

    /**
     * Create an instance of {@link CnValidateImageResponse }
     * 
     */
    public CnValidateImageResponse createCnValidateImageResponse() {
        return new CnValidateImageResponse();
    }

    /**
     * Create an instance of {@link CnValidateImage }
     * 
     */
    public CnValidateImage createCnValidateImage() {
        return new CnValidateImage();
    }

    /**
     * Create an instance of {@link SmallValidateImage }
     * 
     */
    public SmallValidateImage createSmallValidateImage() {
        return new SmallValidateImage();
    }

    /**
     * Create an instance of {@link SmallValidateByte }
     * 
     */
    public SmallValidateByte createSmallValidateByte() {
        return new SmallValidateByte();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebXml.com.cn/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

}
