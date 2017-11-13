
package com.twitter.search;

import java.util.List;

public class DayTotalSummary {

    private List<DayTotal> dayTotalArrayList;
    private int dayCount;
    private String summaryDay;

    public DayTotalSummary() {}

    public List<DayTotal> getDayTotalArrayList() {
    	return this.dayTotalArrayList;
    }

    public void setDayTotal(List<DayTotal> dayTotalArrayList) {
    	this.dayTotalArrayList = dayTotalArrayList;
    }
    
    public int getDayCount() {
    	return this.dayCount;
    }

    public void setDayCount(int dayCount) {
    	this.dayCount = dayCount;
    }
    
    public String getSummaryDay() {
    	return this.summaryDay;
    }

    public void setWordDay(String summaryDay) {
    	this.summaryDay = summaryDay;
    }

}
