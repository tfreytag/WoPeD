
package org.woped.qualanalysis.paraphrasing.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ProcessToText", targetNamespace = "http://server/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProcessToText {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "toText", targetNamespace = "http://server/", className = "org.woped.qualanalysis.paraphrasing.webservice.ToText")
    @ResponseWrapper(localName = "toTextResponse", targetNamespace = "http://server/", className = "org.woped.qualanalysis.paraphrasing.webservice.ToTextResponse")
    public String toText(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}