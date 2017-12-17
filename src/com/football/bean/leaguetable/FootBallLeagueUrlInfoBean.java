
package com.football.bean.leaguetable;

public class FootBallLeagueUrlInfoBean  {
    
    private FootBallLeagueHrefBean self;
    private FootBallLeagueHrefBean competition;
    
    public FootBallLeagueHrefBean getSelf() {
    	return this.self;
    }

    public void setSelf(FootBallLeagueHrefBean self) {
    	this.self = self;
    }
    
    public FootBallLeagueHrefBean getCompetition() {
    	return this.competition;
    }

    public void setCompetition(FootBallLeagueHrefBean competition) {
    	this.competition = competition;
    }
    
    @Override
    public String toString() {
        return "FootBallLeagueUrlInfoBean{" +
                "self='" + self + "'" +
                ", competition='" + competition + "'" +
                '}';
    }
}
        
