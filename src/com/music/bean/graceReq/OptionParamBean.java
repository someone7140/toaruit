
package com.music.bean.graceReq;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class OptionParamBean{
    
    public OptionParamBean() {}

    private String parameter;
    private String value;
    
	@XmlElement(name="PARAMETER")
    public String getParameter() {
    	return this.parameter;
    }

    public void setParameter(String parameter) {
    	this.parameter = parameter;
    }
    
	@XmlElement(name="VALUE")
    public String getValue() {
    	return this.value;
    }

    public void setValue(String value) {
    	this.value = value;
    }

}
