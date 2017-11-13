
package com.music.bean.graceReq;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class RangeBean{
    
    public RangeBean() {}

    private String startRange;
    private String endRange;
    
	@XmlElement(name="START")
    public String getStartRange() {
    	return this.startRange;
    }

    public void setStartRange(String startRange) {
    	this.startRange = startRange;
    }
    
	@XmlElement(name="END")
    public String getEndRange() {
    	return this.endRange;
    }

    public void setEndRange(String endRange) {
    	this.endRange = endRange;
    }

}
