//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.18 at 08:57:29 AM CEST 
//


package org.woped.file.yawlinterface.yawlmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TypeNameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TypeNameType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ncname" type="{http://www.yawlfoundation.org/yawlschema}NCNameType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeNameType")
public class TypeNameType {

    @XmlAttribute(name = "ncname")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String ncname;

    /**
     * Gets the value of the ncname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcname() {
        return ncname;
    }

    /**
     * Sets the value of the ncname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcname(String value) {
        this.ncname = value;
    }

}