
package cn.edu.whu.waytojavaeurekaclient.service.validatecode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cnValidateByteResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cnValidateByteResult"
})
@XmlRootElement(name = "cnValidateByteResponse")
public class CnValidateByteResponse {

    protected byte[] cnValidateByteResult;

    /**
     * 获取cnValidateByteResult属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCnValidateByteResult() {
        return cnValidateByteResult;
    }

    /**
     * 设置cnValidateByteResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCnValidateByteResult(byte[] value) {
        this.cnValidateByteResult = value;
    }

}
