
package com.football.bean.fixture;

import java.util.List;

public class FootBallFixtureBean  {
    
	private FootBallFixtureTeamLinksBean _links;
    private String season;
    private String count;
    private List<FootBallFixtureInfoBean> fixtures;
    
    public FootBallFixtureTeamLinksBean get_links() {
    	return this._links;
    }

    public void set_links(FootBallFixtureTeamLinksBean _links) {
    	this._links = _links;
    }
    
    public String getSeason() {
    	return this.season;
    }

    public void setSeason(String season) {
    	this.season = season;
    }
    
    public String getCount() {
    	return this.count;
    }

    public void setCount(String count) {
    	this.count = count;
    }
    
    public List<FootBallFixtureInfoBean> getFixtures() {
    	return this.fixtures;
    }

    public void setFixtures(List<FootBallFixtureInfoBean> fixtures) {
    	this.fixtures = fixtures;
    }
    
    
    @Override
    public String toString() {
        return "FootBallFixtureBean{" +
                "_links='" + _links + "'" +
                ", season='" + season + "'" +
                ", count='" + count + "'" +
                ", fixtures='" + fixtures + "'" +
                '}';
    }
}
        
