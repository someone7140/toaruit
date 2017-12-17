
package com.football.bean.leaguetable;

import java.util.List;

public class FootBallLeagueTableBean {
    
	private FootBallLeagueUrlInfoBean _links;
    private String leagueCaption;
    private String matchday;
    private List<FootBallLeagueTeamBean> standing;

    
    public FootBallLeagueUrlInfoBean get_links() {
    	return this._links;
    }

    public void set_links(FootBallLeagueUrlInfoBean _links) {
    	this._links = _links;
    }
    
    public String getLeagueCaption() {
    	return this.leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
    	this.leagueCaption = leagueCaption;
    }
    
    public String getMatchday() {
    	return this.matchday;
    }

    public void setMatchday(String matchday) {
    	this.matchday = matchday;
    }

    public List<FootBallLeagueTeamBean> getStanding() {
    	return this.standing;
    }

    public void setStanding(List<FootBallLeagueTeamBean> standing) {
    	this.standing = standing;
    }
    
    @Override
    public String toString() {
        return "FootBallLeagueTable{" +
                "_links='" + _links + "'" +
                ", leagueCaption='" + leagueCaption + "'" +
                ", matchday='" + matchday + "'" +
                ", standing='" + standing + "'" +
                '}';
    }
}
        
