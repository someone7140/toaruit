
package com.music.bean.graceReq;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class AuthBean{
    
    public AuthBean() {}

    private String clientId;
    private String userId;

    
	@XmlElement(name="CLIENT")
    public String getClientId() {
    	return this.clientId;
    }

    public void setClientId(String clientId) {
    	this.clientId = clientId;
    }
    
    @XmlElement(name="USER")
    public String getUserId() {
    	return this.userId;
    }

    public void setUserId(String userId) {
    	this.userId = userId;
    }


}
