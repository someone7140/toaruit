
package com.music.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.music.bean.graceReq.AuthBean;
import com.music.bean.graceReq.MusicQueryBean;


@XmlRootElement(name="QUERIES")
public class RequestMusicBean {
	
    private AuthBean authBean;
    private String lang;
    private MusicQueryBean musicQueryBean;
    
	@XmlElement(name="AUTH")
    public AuthBean getAuthBean(){
    	return this.authBean;
    }

    public void setAuthBean(AuthBean authBean) {
    	this.authBean = authBean;
    }
    
	@XmlElement(name="LANG")
    public String getLang(){
    	return this.lang;
    }

    public void setLang(String lang) {
    	this.lang = lang;
    }
    
	@XmlElement(name="QUERY")
    public MusicQueryBean getMusicQueryBean(){
    	return this.musicQueryBean;
    }

    public void setMusicQueryBean(MusicQueryBean musicQueryBean) {
    	this.musicQueryBean = musicQueryBean;
    }
}
