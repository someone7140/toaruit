
package com.music.bean.graceReq;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class TextParamBean{
    
    public TextParamBean() {}

    private String text;
    private String type;

    @XmlAttribute(name="TYPE")
    public String getType() {
    	return this.type;
    }

    public void setType(String type) {
    	this.type = type;
    }
    
    @XmlValue
    public String getText() {
    	return this.text;
    }

    public void setText(String text) {
    	this.text = text;
    }


}
