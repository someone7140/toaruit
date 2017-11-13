
package com.music.bean.graceRes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class ArbumGenreBean{
    
    public ArbumGenreBean() {}

    private String ord;
    private String num;
    private String id;
    private String genreText;

    @XmlAttribute(name="ORD")
    public String getOrd() {
    	return this.ord;
    }

    public void setOrd(String ord) {
    	this.ord = ord;
    }
    
    
    @XmlAttribute(name="NUM")
    public String getNum() {
    	return this.num;
    }

    public void setNum(String num) {
    	this.num = num;
    }

    @XmlAttribute(name="ID")
    public String getId() {
    	return this.id;
    }

    public void setId(String id) {
    	this.id = id;
    }

    
    @XmlValue
    public String getGenreText() {
    	return this.genreText;
    }

    public void setGenreText(String genreText) {
    	this.genreText = genreText;
    }


}
