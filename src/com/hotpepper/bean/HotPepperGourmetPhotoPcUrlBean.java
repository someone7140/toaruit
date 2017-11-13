
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperGourmetPhotoPcUrlBean extends HotPepperGourmetPhotoUrlBean{
    
    public HotPepperGourmetPhotoPcUrlBean() {}

    private String m;
    
    @XmlElement(name="m")
    public String getM() {
    	return this.m;
    }

    public void setM(String m) {
    	this.m = m;
    }
    
}
