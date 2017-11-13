package com.music.bean;

import java.util.List;

public class ResultMusicBean {
	
	private String title;
	private String artistName;
	private String artistImage;
	private List<String> moodList;
	private List<String> tempoList;
	
    public String getTitle() {
    	return this.title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }

    public String getArtistName() {
    	return this.artistName;
    }

    public void setArtistName(String artistName) {
    	this.artistName = artistName;
    }
    
    public String getArtistImage() {
    	return this.artistImage;
    }

    public void setArtistImage(String artistImage) {
    	this.artistImage = artistImage;
    }
    
    public List<String> getMoodList() {
    	return this.moodList;
    }

    public void setMoodList(List<String> moodList) {
    	this.moodList = moodList;
    }
    
    public List<String> getTempoList() {
    	return this.tempoList;
    }

    public void setTempoList(List<String> tempoList) {
    	this.tempoList = tempoList;
    }
    
}
