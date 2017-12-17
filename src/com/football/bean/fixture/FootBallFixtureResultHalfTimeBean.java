
package com.football.bean.fixture;

public class FootBallFixtureResultHalfTimeBean  {
    
    private String goalsHomeTeam;
    private String goalsAwayTeam;
    
    public String getGoalsHomeTeam() {
    	return this.goalsHomeTeam;
    }

    public void setGoalsHomeTeam(String goalsHomeTeam) {
    	this.goalsHomeTeam = goalsHomeTeam;
    }
    
    public String getGoalsAwayTeam() {
    	return this.goalsAwayTeam;
    }

    public void setGoalsAwayTeam(String goalsAwayTeam) {
    	this.goalsAwayTeam = goalsAwayTeam;
    }
    
    
    @Override
    public String toString() {
        return "FootBallFixtureResultHalfTimeBean{" +
                "goalsHomeTeam='" + goalsHomeTeam + "'" +
                ", goalsAwayTeam='" + goalsAwayTeam + "'" +
                '}';
    }
}
        
