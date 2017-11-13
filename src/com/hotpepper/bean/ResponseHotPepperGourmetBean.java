
package com.hotpepper.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="results")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseHotPepperGourmetBean {
	@XmlElement(name="api_version")
	private String api_version;
	@XmlElement(name="results_available")
	private String results_available;
	@XmlElement(name="results_returned")
	private String results_returned;
	@XmlElement(name="results_start")
	private String results_start;
	@XmlElement(name="shop")
    private List<HotPepperShopBean> shopList;
	
	
    public String getApi_version(){
    	return this.api_version;
    }

    public void setApi_version(String api_version) {
    	this.api_version = api_version;
    }
	

	
    public String getResults_available(){
    	return this.results_available;
    }

    public void setResults_available(String results_available) {
    	this.results_available = results_available;
    }
    
	
    public String getResults_returned(){
    	return this.results_returned;
    }

    public void setResults_returned(String results_returned) {
    	this.results_returned = results_returned;
    }
    
	
    public String getResults_start(){
    	return this.results_start;
    }

    public void setResults_start(String results_start) {
    	this.results_start = results_start;
    }
    
    
    public List<HotPepperShopBean> getShopList() {
        return this.shopList;
    }
    
    public void setShopList( List<HotPepperShopBean> shopList) {
        this.shopList = shopList;
    }
}
