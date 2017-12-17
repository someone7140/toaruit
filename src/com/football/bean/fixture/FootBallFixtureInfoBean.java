
package com.football.bean.fixture;

public class FootBallFixtureInfoBean  {
    
	private FootBallFixtureInfoLinksBean _links;
    private String date;
    private String status;
    private String matchday;
    private String homeTeamName;
    private String awayTeamName;
    FootBallFixtureResultBean result;
    FootBallFixtureOddsBean odds;
    
    public FootBallFixtureInfoLinksBean get_links() {
    	return this._links;
    }

    public void set_links(FootBallFixtureInfoLinksBean _links) {
    	this._links = _links;
    }
    
    public String getDate() {
    	return this.date;
    }

    public void setDate(String date) {
    	this.date = date;
    }
    
    public String getStatus() {
    	return this.status;
    }

    public void setStatus(String status) {
    	this.status = status;
    }
    
    public String getMatchday() {
    	return this.matchday;
    }

    public void setMatchday(String matchday) {
    	this.matchday = matchday;
    }
    
    public String getHomeTeamName() {
    	return this.homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
    	this.homeTeamName = homeTeamName;
    }
    
    public String getAwayTeamName() {
    	return this.awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
    	this.awayTeamName = awayTeamName;
    }
    
    public FootBallFixtureResultBean getResult() {
    	return this.result;
    }

    public void setResult(FootBallFixtureResultBean result) {
    	this.result = result;
    }
    
    
    public FootBallFixtureOddsBean getOdds() {
    	return this.odds;
    }

    public void setOdds(FootBallFixtureOddsBean odds) {
    	this.odds = odds;
    }
    
    @Override
    public String toString() {
        return "FootBallFixtureInfoBean{" +
                "_links='" + _links + "'" +
                ", date='" + date + "'" +
                ", status='" + status + "'" +
                ", matchday='" + matchday + "'" +
                ", homeTeamName='" + homeTeamName + "'" +
                ", awayTeamName='" + awayTeamName + "'" +
                ", result='" + result + "'" +
                ", odds='" + odds + "'" +
                '}';
    }
}
        
