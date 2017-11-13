
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperGourmetUrlBean {
    
    public HotPepperGourmetUrlBean() {}

    private String pc;
    private String mobile;
    private String qr;
    
    @XmlElement(name="pc")
    public String getPc() {
    	return this.pc;
    }

    public void setPc(String pc) {
    	this.pc = pc;
    }
        
    @XmlElement(name="mobile")
    public String getMobile() {
    	return this.mobile;
    }

    public void setMobile(String mobile) {
    	this.mobile = mobile;
    }

    @XmlElement(name="qr")
    public String getQr() {
    	return this.qr;
    }

    public void setQr(String qr) {
    	this.qr = qr;
    }
    
}
