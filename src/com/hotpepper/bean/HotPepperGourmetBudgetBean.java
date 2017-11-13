
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperGourmetBudgetBean extends HotPepperGourmetCommonBean{
    
    public HotPepperGourmetBudgetBean() {}

    private String average;

    @XmlElement(name="average")
    public String getAverage() {
    	return this.average;
    }

    public void setAverage(String average) {
    	this.average = average;
    }
        

}
