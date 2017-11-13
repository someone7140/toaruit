
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperBudgetBean{
    //
    public HotPepperBudgetBean() {}

    private String code;
    private String name;

    @XmlElement(name="code")
    public String getCode() {
    	return this.code;
    }

    public void setCode(String code) {
    	this.code = code;
    }
    
    @XmlElement(name="name")
    public String getName() {
    	return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    

}
