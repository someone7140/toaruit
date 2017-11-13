package com.music.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.music.bean.graceRes.ResponseMusicChildBean;


@XmlRootElement(name="RESPONSES")
public class ResponseMusicBean {
	
	private ResponseMusicChildBean responseMusicChildBean;
	
	@XmlElement(name="RESPONSE")
    public ResponseMusicChildBean getResponseMusicChildBean(){
    	return this.responseMusicChildBean;
    }

    public void setResponseMusicChildBean(ResponseMusicChildBean responseMusicChildBean) {
    	this.responseMusicChildBean = responseMusicChildBean;
    }

}
