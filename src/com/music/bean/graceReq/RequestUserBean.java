
package com.music.bean.graceReq;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="QUERIES")
public class RequestUserBean {
	
    private ClientBean clientBean;
    
	@XmlElement(name="QUERY")
    public ClientBean getClientBean(){
    	return this.clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
    	this.clientBean = clientBean;
    }
}
