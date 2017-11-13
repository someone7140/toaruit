
package com.music.bean.graceReq;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class ClientBean{
    
    public ClientBean() {}

    private String cmd;
    private String clientId;
    
    @XmlAttribute(name="CMD")
    public String getCmd() {
    	return this.cmd;
    }

    public void setCmd(String cmd) {
    	this.cmd = cmd;
    }
    
	@XmlElement(name="CLIENT")
    public String getClientId() {
    	return this.clientId;
    }

    public void setClientId(String clientId) {
    	this.clientId = clientId;
    }

}
