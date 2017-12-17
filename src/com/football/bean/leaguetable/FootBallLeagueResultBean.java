
package com.football.bean.leaguetable;

public class FootBallLeagueResultBean  {
    
    private String goals;
    private String goalsAgainst;
    private String wins;
    private String draws;
    private String losses;
    
    
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
    
    
    @Override
    public String toString() {
        return "FootBallLeagueResultBean{" +
                "goals='" + goals + "'" +
                ", goalsAgainst='" + goalsAgainst + "'" +
                ", wins='" + wins + "'" +
                ", draws='" + draws + "'" +
                ", losses='" + losses + "'" +
                '}';
    }
}
        
