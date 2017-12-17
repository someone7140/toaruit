
package com.football.bean.fixture;

public class FootBallFixtureTeamLinksBean  {
    
	private FootBallFixtureHrefBean self;
	private FootBallFixtureHrefBean team;
	
    public FootBallFixtureHrefBean getSelf() {
    	return this.self;
    }

    public void setSelf(FootBallFixtureHrefBean self) {
    	this.self = self;
    }
    
    public FootBallFixtureHrefBean getTeams() {
    	return this.team;
    }

    public void setTeams(FootBallFixtureHrefBean team) {
    	this.team = team;
    }
    
    @Override
    public String toString() {
        return "_links{" +
                "self='" + self + "'" +
                ", team='" + team + "'" +
                '}';
    }
}
        
