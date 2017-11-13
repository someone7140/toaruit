
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperGourmetPhotoBean {
    
    public HotPepperGourmetPhotoBean() {}

    private HotPepperGourmetPhotoPcUrlBean pc;
    private HotPepperGourmetPhotoUrlBean mobile;
    
    @XmlElement(name="pc")
    public HotPepperGourmetPhotoPcUrlBean getPc() {
    	return this.pc;
    }

    public void setPc(HotPepperGourmetPhotoPcUrlBean pc) {
    	this.pc = pc;
    }
    
    @XmlElement(name="mobile")
    public HotPepperGourmetPhotoUrlBean getMobile() {
    	return this.mobile;
    }

    public void setMobile(HotPepperGourmetPhotoUrlBean mobile) {
    	this.mobile = mobile;
    }
    
}
