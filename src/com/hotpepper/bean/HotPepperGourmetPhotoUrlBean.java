
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperGourmetPhotoUrlBean {
    
    public HotPepperGourmetPhotoUrlBean() {}

    private String l;
    private String s;
    
    @XmlElement(name="l")
    public String getL() {
    	return this.l;
    }

    public void setL(String l) {
    	this.l = l;
    }
        
    @XmlElement(name="s")
    public String getS() {
    	return this.s;
    }

    public void setS(String s) {
    	this.s = s;
    }

    
}
