
package com.chat.location;

import java.util.List;

public class CityInfoBean {

    private String status;
    private List<CityDataBean> data;
  
    public CityInfoBean() {}

    public String getStatus() {
    	return this.status;
    }

    public void setStatus(String status) {
    	this.status = status;
    }
    
    public List<CityDataBean> getData() {
    	return this.data;
    }

    public void setData(List<CityDataBean> data) {
    	this.data = data;
    }
    


}
