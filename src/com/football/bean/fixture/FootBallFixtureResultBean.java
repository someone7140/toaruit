
package com.football.bean.fixture;

public class FootBallFixtureResultBean  {
    
    private String goalsHomeTeam;
    private String goalsAwayTeam;
    FootBallFixtureResultHalfTimeBean halfTime;
    
    
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
    
    public FootBallFixtureResultHalfTimeBean getHalfTime() {
    	return this.halfTime;
    }

    public void setHalfTime(FootBallFixtureResultHalfTimeBean halfTime) {
    	this.halfTime = halfTime;
    }
    
    
    @Override
    public String toString() {
        return "FootBallFixtureResultBean{" +
                "goalsHomeTeam='" + goalsHomeTeam + "'" +
                ", goalsAwayTeam='" + goalsAwayTeam + "'" +
                ", halfTime='" + halfTime + "'" +
                '}';
    }
}
        
