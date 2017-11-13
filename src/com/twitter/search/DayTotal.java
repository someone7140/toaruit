
package com.twitter.search;




public class DayTotal {

    private String wordText;
    private String wordDay;
    private int wordCount;

    public DayTotal() {}

    public String getWordText() {
    	return this.wordText;
    }

    public void setWordText(String wordText) {
    	this.wordText = wordText;
    }
    
    public int getWordCount() {
    	return this.wordCount;
    }

    public void setWordCount(int wordCount) {
    	this.wordCount = wordCount;
    }
    
    public String getWordDay() {
    	return this.wordDay;
    }

    public void setWordDay(String wordDay) {
    	this.wordDay = wordDay;
    }

}
