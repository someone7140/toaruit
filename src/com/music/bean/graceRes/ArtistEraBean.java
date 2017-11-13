
package com.music.bean.graceRes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class ArtistEraBean{
    
    public ArtistEraBean() {}

    private String ord;
    private String id;
    private String eraText;

    @XmlAttribute(name="ORD")
    public String getOrd() {
    	return this.ord;
    }

    public void setOrd(String ord) {
    	this.ord = ord;
    }
    
    @XmlAttribute(name="ID")
    public String getId() {
    	return this.id;
    }

    public void setId(String id) {
    	this.id = id;
    }
    
    @XmlValue
    public String getEraText() {
    	return this.eraText;
    }

    public void setEraText(String eraText) {
    	this.eraText = eraText;
    }


}
