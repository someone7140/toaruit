package com.rakuten.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="root")
public class ResponseRakutenSearchBean {
	
    private String count;
    private String page;
    private String first;
    private String last;
    private String hits;
    private String carrier;
    private String pageCount;
    private List<RakutenCdItemBean> cdItemList;
    
    @XmlElement(name="count")
    public String getCount(){
    	return this.count;
    }

    public void setCount(String count) {
    	this.count = count;
    }
    
    @XmlElement(name="page")
    public String getPage(){
    	return this.page;
    }

    public void setPage(String page) {
    	this.page = page;
    }
    
    @XmlElement(name="first")
    public String getFirst(){
    	return this.first;
    }

    public void setFirst(String first) {
    	this.first = first;
    }
    
    @XmlElement(name="last")
    public String getLast(){
    	return this.last;
    }

    public void setLast(String last) {
    	this.last = last;
    }
   
    @XmlElement(name="hits")
    public String getHits(){
    	return this.hits;
    }

    public void setHits(String hits) {
    	this.hits = hits;
    }
    
    @XmlElement(name="carrier")
    public String getCarrier(){
    	return this.carrier;
    }

    public void setCarrier(String carrier) {
    	this.carrier = carrier;
    }
    
    @XmlElement(name="pageCount")
    public String getPageCount(){
    	return this.pageCount;
    }

    public void setPageCount(String pageCount) {
    	this.pageCount = pageCount;
    }
    
    @XmlElementWrapper(name="Items")
    @XmlElement(name="Item")
    public List<RakutenCdItemBean> getCdItemList() {
        return cdItemList;
    }
    
    public void setCdItemList( List<RakutenCdItemBean> cdItemList) {
        this.cdItemList = cdItemList;
    }
}
