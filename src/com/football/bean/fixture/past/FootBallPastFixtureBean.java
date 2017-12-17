
package com.football.bean.fixture.past;

import com.football.bean.fixture.FootBallFixtureInfoBean;

public class FootBallPastFixtureBean  {
    
	private FootBallFixtureInfoBean fixture;
    private FootBallPastFixtureHead2HeadBean head2head;
    
    public FootBallFixtureInfoBean getFixure() {
    	return this.fixture;
    }

    public void setFixure(FootBallFixtureInfoBean fixture) {
    	this.fixture = fixture;
    }
    
    public FootBallPastFixtureHead2HeadBean getHead2head() {
    	return this.head2head;
    }

    public void setHead2head(FootBallPastFixtureHead2HeadBean head2head) {
    	this.head2head = head2head;
    }
    

    @Override
    public String toString() {
        return "FootBallPastFixtureBean{" +
                "fixture='" + fixture + "'" +
                ", head2head='" + head2head + "'" +
                '}';
    }
}
        
