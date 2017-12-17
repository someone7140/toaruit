
package com.football.bean.leaguetable;

public class FootBallLeagueTeamBean  {
    
    private FootBallLeagueTeamLinkBean _links;
    private String position;
    private String teamName;
    private String crestURI;
    private String playedGames;
    private String points;
    private String goals;
    private String goalsAgainst;
    private String goalDifference;
    private String wins;
    private String draws;
    private String losses;
    private FootBallLeagueResultBean home;
    private FootBallLeagueResultBean away;
    
    public FootBallLeagueTeamLinkBean get_links() {
    	return this._links;
    }

    public void set_links(FootBallLeagueTeamLinkBean _links) {
    	this._links = _links;
    }
    
    public String getPosition() {
    	return this.position;
    }

    public void setPosition(String position) {
    	this.position = position;
    }
    
    public String getTeamName() {
    	return this.teamName;
    }

    public void setTeamName(String teamName) {
    	this.teamName = teamName;
    }
    
    public String getCrestURI() {
    	return this.crestURI;
    }

    public void setCrestURI(String crestURI) {
    	this.crestURI = crestURI;
    }
    
    public String getPlayedGames() {
    	return this.playedGames;
    }

    public void setPlayedGames(String playedGames) {
    	this.playedGames = playedGames;
    }
    
    public String getPoints() {
    	return this.points;
    }

    public void setPoints(String points) {
    	this.points = points;
    }
    
    public String getGoals() {
    	return this.goals;
    }

    public void setGoals(String goals) {
    	this.goals = goals;
    }
    
    public String getGoalsAgainst() {
    	return this.goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
    	this.goalsAgainst = goalsAgainst;
    }
    
    public String getGoalDifference() {
    	return this.goalDifference;
    }

    public void setGoalDifference(String goalDifference) {
    	this.goalDifference = goalDifference;
    }
    
    public String getWins() {
    	return this.wins;
    }

    public void setWins(String wins) {
    	this.wins = wins;
    }
    
    public String getDraws() {
    	return this.draws;
    }

    public void setDraws(String draws) {
    	this.draws = draws;
    }
    
    public String getLosses() {
    	return this.losses;
    }

    public void setLosses(String losses) {
    	this.losses = losses;
    }
    
    public FootBallLeagueResultBean getHome() {
    	return this.home;
    }

    public void setHome(FootBallLeagueResultBean home) {
    	this.home = home;
    }
    
    
    public FootBallLeagueResultBean getAway() {
    	return this.away;
    }

    public void setAway(FootBallLeagueResultBean away) {
    	this.away = away;
    }
    
    @Override
    public String toString() {
        return "FootBallLeagueTeamBean{" +
                "_links='" + _links + "'" +
                ", position='" + position + "'" +
                ", teamName='" + teamName + "'" +
                ", crestURI='" + crestURI + "'" +
                ", playedGames='" + playedGames + "'" +
                ", points='" + points + "'" +
                ", goals='" + goals + "'" +
                ", goalsAgainst='" + goalsAgainst + "'" +
                ", goalDifference='" + goalDifference + "'" +
                ", wins='" + wins + "'" +
                ", draws='" + draws + "'" +
                ", losses='" + losses + "'" +
                ", home='" + home + "'" +
                ", away='" + away + "'" +
                '}';
    }
}
        
