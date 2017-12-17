
package com.football.bean.leaguetable;

public class FootBallLeagueTeamLinkBean  {
    
    private FootBallLeagueHrefBean team;

    public FootBallLeagueHrefBean getTeam() {
    	return this.team;
    }

    public void setTeam(FootBallLeagueHrefBean team) {
    	this.team = team;
    }
    
    
    @Override
    public String toString() {
        return "FootBallLeagueTeamLinkBean{" +
                "team='" + team + "'" +
                '}';
    }
}
        
