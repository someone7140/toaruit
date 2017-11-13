
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperGourmetGenreBean extends HotPepperGourmetCommonBean{
    
    public HotPepperGourmetGenreBean() {}

    private String catchCopy;

    @XmlElement(name="catch")
    public String getCatch() {
    	return this.catchCopy;
    }

    public void setCatch(String catchCopy) {
    	this.catchCopy = catchCopy;
    }
        

}
