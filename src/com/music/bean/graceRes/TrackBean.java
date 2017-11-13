
package com.music.bean.graceRes;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


// @XmlType
@XmlRootElement(name="TRACK")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackBean{
    
    public TrackBean() {}
    @XmlElement(name="TRACK_NUM")
    private String trackNum;
    @XmlElement(name="GN_ID")
    private String gnId;
    @XmlElement(name="TITLE")
    private String title;
    @XmlElement(name="MOOD")
    private List<TrackMoodBean> trackMoodList;
    @XmlElement(name="TEMPO")
    private List<TrackTempoBean> trackTempoList;

    
//	@XmlElement(name="TRACK_NUM")
    public String getTrackNum() {
    	return this.trackNum;
    }

    public void setTrackNum(String trackNum) {
    	this.trackNum = trackNum;
    }
    
//	@XmlElement(name="GN_ID")
    public String getGnId() {
    	return this.gnId;
    }

    public void setGnId(String gnId) {
    	this.gnId = gnId;
    }
    
//    @XmlElement(name="TITLE")
    public String getTitle() {
    	return this.title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }
    
//    @XmlElementWrapper
//    @XmlElement(name="MOOD")
    public List<TrackMoodBean> getTrackMoodList() {
    	return this.trackMoodList;
    }

    public void setTrackMoodList(List<TrackMoodBean> trackMoodList) {
    	this.trackMoodList = trackMoodList;
    }
    
 //   @XmlElementWrapper
 //   @XmlElement(name="TEMPO")
    public List<TrackTempoBean> getTrackTempoList() {
    	return this.trackTempoList;
    }

    public void setTrackTempoList(List<TrackTempoBean> trackTempoList) {
    	this.trackTempoList = trackTempoList;
    }
    

}