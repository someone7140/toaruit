
package com.music.bean.graceRes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class ArtistTypeBean{
    
    public ArtistTypeBean() {}

    private String ord;
    private String id;
    private String typeText;

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
    public String getTypeText() {
    	return this.typeText;
    }

    public void setTypeText(String typeText) {
    	this.typeText = typeText;
    }


}
