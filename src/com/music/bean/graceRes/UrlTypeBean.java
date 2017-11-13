
package com.music.bean.graceRes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class UrlTypeBean{
    
    public UrlTypeBean() {}

    private String type;
    private String size;
    private String width;
    private String height;
    private String urlText;

    @XmlAttribute(name="TYPE")
    public String getType() {
    	return this.type;
    }

    public void setType(String type) {
    	this.type = type;
    }
    
    @XmlAttribute(name="SIZE")
    public String getSize() {
    	return this.size;
    }

    public void setSize(String size) {
    	this.size = size;
    }
    
    @XmlAttribute(name="WIDTH")
    public String getWidth() {
    	return this.width;
    }

    public void setWidth(String width) {
    	this.width = width;
    }
    
    @XmlAttribute(name="HEIGHT")
    public String getHeight() {
    	return this.height;
    }

    public void setHeight(String height) {
    	this.height = height;
    }
    
    @XmlValue
    public String getUrlText() {
    	return this.urlText;
    }

    public void setUrlText(String urlText) {
    	this.urlText = urlText;
    }


}
