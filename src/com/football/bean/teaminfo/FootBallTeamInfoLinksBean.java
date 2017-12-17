
package com.football.bean.teaminfo;

public class FootBallTeamInfoLinksBean  {
    
	private FootBallTeamInfoHrefBean self;
	private FootBallTeamInfoHrefBean fixtures;
	private FootBallTeamInfoHrefBean players;
	
    public FootBallTeamInfoHrefBean getSelf() {
    	return this.self;
    }

    public void setSelf(FootBallTeamInfoHrefBean self) {
    	this.self = self;
    }
    
    public FootBallTeamInfoHrefBean getFixtures() {
    	return this.fixtures;
    }

    public void setFixtures(FootBallTeamInfoHrefBean fixtures) {
    	this.fixtures = fixtures;
    }
    
    public FootBallTeamInfoHrefBean getPlayers() {
    	return this.players;
    }

    public void setPlayers(FootBallTeamInfoHrefBean players) {
    	this.players = players;
    }
    
    @Override
    public String toString() {
        return "_links{" +
                "self='" + self + "'" +
                ", fixtures='" + fixtures + "'" +
                ", players='" + players + "'" +
                '}';
    }
}
        
