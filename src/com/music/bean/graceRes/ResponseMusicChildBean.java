package com.music.bean.graceRes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType
public class ResponseMusicChildBean {
	
	private AlbumBean albumBean;
	private String responseStatus;
	
	
	@XmlElement(name="ALBUM")
    public AlbumBean getAlbumBean(){
    	return this.albumBean;
    }

    public void setAlbumBean(AlbumBean albumBean) {
    	this.albumBean = albumBean;
    }

	@XmlAttribute(name="STATUS")
    public String getResponseStatus(){
    	return this.responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
    	this.responseStatus = responseStatus;
    }


}
