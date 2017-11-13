
package com.twitter.search;

import java.util.List;

public class TwitterResult {

    private int tweetTargetCount;
    private List<TotalWork> totalWorkList;

    public TwitterResult() {}

    public int getTweetTargetCount() {
    	return this.tweetTargetCount;
    }

    public void setTweetTargetCount(int tweetTargetCount) {
    	this.tweetTargetCount = tweetTargetCount;
    }
    
    public List<TotalWork> getTotalWorkList() {
    	return this.totalWorkList;
    }

    public void setTotalWorkList(List<TotalWork> totalWorkList) {
    	this.totalWorkList = totalWorkList;
    }
    
    

}
