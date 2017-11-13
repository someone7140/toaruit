
package com.music.bean.graceRes;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


//@XmlType
@XmlRootElement(name="ALBUM")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlbumBean{
    
    public AlbumBean() {}
    
    @XmlElement(name="GN_ID")
    private String gnId;
    @XmlElement(name="ARTIST")
    private String artistName;
    @XmlElement(name="ARTIST_ORIGIN")
    private List<ArtistOriginBean> artistOriginList;
    @XmlElement(name="ARTIST_TYPE")
    private List<ArtistTypeBean> artistTypeList;
    @XmlElement(name="ARTIST_ERA")
    private List<ArtistEraBean> artistEraList;
    @XmlElement(name="TITLE")
    private String title;
    @XmlElement(name="PKG_LANG")
    private String pkgLang;
    @XmlElement(name="DATE")
    private String date;
    @XmlElement(name="GENRE")
    private List<ArbumGenreBean> arbumGenreList;
    @XmlElement(name="MATCHED_TRACK_NUM")
    private String matchedTrackNum;
    @XmlElement(name="TRACK_COUNT")
    private String trackCount;
    @XmlElement(name="TRACK")
    private TrackBean track;
    @XmlElement(name="URL")
    private List<UrlTypeBean> urlTypeBeanList;
    
//	@XmlElement(name="GN_ID")
    public String getGnId() {
    	return this.gnId;
    }

    public void setGnId(String gnId) {
    	this.gnId = gnId;
    }
    
 //   @XmlElement(name="ARTIST")
    public String getArtistName() {
    	return this.artistName;
    }

    public void setArtistName(String artistName) {
    	this.artistName = artistName;
    }
    
//    @XmlElementWrapper
//    @XmlElement(name="ARTIST_ORIGIN")
    public List<ArtistOriginBean> getArtistOriginList() {
    	return this.artistOriginList;
    }

    public void setArtistOriginList(List<ArtistOriginBean> artistOriginList) {
    	this.artistOriginList = artistOriginList;
    }
    
//    @XmlElementWrapper
//    @XmlElement(name="ARTIST_TYPE")
    public List<ArtistTypeBean> getArtistTypeList() {
    	return this.artistTypeList;
    }

    public void setArtistTypeList(List<ArtistTypeBean> artistTypeList) {
    	this.artistTypeList = artistTypeList;
    }
    
//    @XmlElementWrapper
//    @XmlElement(name="ARTIST_ERA")
    public List<ArtistEraBean> getArtistEraList() {
    	return this.artistEraList;
    }

    public void setArtistEraList(List<ArtistEraBean> artistEraList) {
    	this.artistEraList = artistEraList;
    }
    
//    @XmlElement(name="TITLE")
    public String getTitle() {
    	return this.title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }
    
//    @XmlElement(name="PKG_LANG")
    public String getPkgLang() {
    	return this.pkgLang;
    }

    public void setPkgLang(String pkgLang) {
    	this.pkgLang = pkgLang;
    }
    
//    @XmlElement(name="DATE")
    public String getDate() {
    	return this.date;
    }

    public void setDate(String date) {
    	this.date = date;
    }
    
//    @XmlElementWrapper
//    @XmlElement(name="GENRE")
    public List<ArbumGenreBean> getArbumGenreList() {
    	return this.arbumGenreList;
    }

    public void setArbumGenreList(List<ArbumGenreBean> arbumGenreList) {
    	this.arbumGenreList = arbumGenreList;
    }
    
//    @XmlElement(name="MATCHED_TRACK_NUM")
    public String getMatchedTrackNum() {
    	return this.matchedTrackNum;
    }

    public void setMatchedTrackNum(String matchedTrackNum) {
    	this.matchedTrackNum = matchedTrackNum;
    }
    
//    @XmlElement(name="TRACK_COUNT")
    public String getTrackCount() {
    	return this.trackCount;
    }

    public void setTrackCount(String trackCount) {
    	this.trackCount = trackCount;
    }
    
//    @XmlElement(name="TRACK")
    public TrackBean getTrack() {
    	return this.track;
    }

    public void setTrack(TrackBean track) {
    	this.track = track;
    }
    
//    @XmlElementWrapper
//    @XmlElement(name="URL")
    public List<UrlTypeBean> getUrlTypeBeanList() {
    	return this.urlTypeBeanList;
    }

    public void setTrackTempoList(List<UrlTypeBean> urlTypeBeanList) {
    	this.urlTypeBeanList = urlTypeBeanList;
    }
}
