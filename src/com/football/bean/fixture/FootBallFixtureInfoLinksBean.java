
package com.football.bean.fixture;

public class FootBallFixtureInfoLinksBean  {
    
	private FootBallFixtureHrefBean self;
	private FootBallFixtureHrefBean competition;
	private FootBallFixtureHrefBean homeTeam;
	private FootBallFixtureHrefBean awayTeam;
	
    public FootBallFixtureHrefBean getSelf() {
    	return this.self;
    }

    public void setSelf(FootBallFixtureHrefBean self) {
    	this.self = self;
    }
    
    public FootBallFixtureHrefBean getTeams() {
    	return this.competition;
    }

    public void setTeams(FootBallFixtureHrefBean competition) {
    	this.competition = competition;
    }
    
    public FootBallFixtureHrefBean getHomeTeam() {
    	return this.homeTeam;
    }

    public void setHomeTeam(FootBallFixtureHrefBean homeTeam) {
    	this.homeTeam = homeTeam;
    }
    
    public FootBallFixtureHrefBean getAwayTeam() {
    	return this.awayTeam;
    }

    public void setAwayTeam(FootBallFixtureHrefBean awayTeam) {
    	this.awayTeam = awayTeam;
    }
    
    
    @Override
    public String toString() {
        return "_links{" +
                "self='" + self + "'" +
                ", competition='" + competition + "'" +
                ", homeTeam='" + homeTeam + "'" +
                ", awayTeam='" + awayTeam + "'" +
                '}';
    }
}
        
