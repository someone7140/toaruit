
package com.football.bean.player;

public class FootBallPlayerLinksBean  {
    
	private FootBallPlayerHrefBean self;
	private FootBallPlayerHrefBean team;
	
    public FootBallPlayerHrefBean getSelf() {
    	return this.self;
    }

    public void setSelf(FootBallPlayerHrefBean self) {
    	this.self = self;
    }
    
    public FootBallPlayerHrefBean getFixtures() {
    	return this.team;
    }

    public void setFixtures(FootBallPlayerHrefBean team) {
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
        
