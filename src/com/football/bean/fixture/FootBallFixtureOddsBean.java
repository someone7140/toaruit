
package com.football.bean.fixture;

public class FootBallFixtureOddsBean  {
    
    private String homeWin;
    private String draw;
    private String awayWin;
    
    public String getHomeWin() {
    	return this.homeWin;
    }

    public void setHomeWin(String homeWin) {
    	this.homeWin = homeWin;
    }
    
    public String getDraw() {
    	return this.draw;
    }

    public void setDraw(String draw) {
    	this.draw = draw;
    }
    
    public String getAwayWin() {
    	return this.awayWin;
    }

    public void setAwayWin(String awayWin) {
    	this.awayWin = awayWin;
    }
    
    @Override
    public String toString() {
        return "FootBallFixtureOddsBean{" +
                "homeWin='" + homeWin + "'" +
                ", draw='" + draw + "'" +
                ", awayWin='" + awayWin + "'" +
                '}';
    }
}
        
