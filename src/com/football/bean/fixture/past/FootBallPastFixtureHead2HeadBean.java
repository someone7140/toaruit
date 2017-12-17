
package com.football.bean.fixture.past;

import java.util.List;

import com.football.bean.fixture.FootBallFixtureInfoBean;

public class FootBallPastFixtureHead2HeadBean  {
    
	private String count;
	private String timeFrameStart;
	private String timeFrameEnd;
	private String homeTeamWins;
	private String awayTeamWins;
	private String draws;
	private FootBallFixtureInfoBean lastHomeWinHomeTeam;
	private FootBallFixtureInfoBean lastWinHomeTeam;
	private FootBallFixtureInfoBean lastAwayWinAwayTeam;
	private FootBallFixtureInfoBean lastWinAwayTeam;
	private List<FootBallFixtureInfoBean> fixtures;
	
    public String getCount() {
    	return this.count;
    }

    public void setCount(String count) {
    	this.count = count;
    }
    
    public String getTimeFrameStart() {
    	return this.timeFrameStart;
    }

    public void setTimeFrameStart(String timeFrameStart) {
    	this.timeFrameStart = timeFrameStart;
    }
    
    public String getTimeFrameEnd() {
    	return this.timeFrameEnd;
    }

    public void setTimeFrameEnd(String timeFrameEnd) {
    	this.timeFrameEnd = timeFrameEnd;
    }
    
    public String getHomeTeamWins() {
    	return this.homeTeamWins;
    }

    public void setHomeTeamWins(String homeTeamWins) {
    	this.homeTeamWins = homeTeamWins;
    }
    
    public String getAwayTeamWins() {
    	return this.awayTeamWins;
    }

    public void setAwayTeamWins(String awayTeamWins) {
    	this.awayTeamWins = awayTeamWins;
    }
    
    public String getDraws() {
    	return this.draws;
    }

    public void setDraws(String draws) {
    	this.draws = draws;
    }
    
    public FootBallFixtureInfoBean getLastHomeWinHomeTeam() {
    	return this.lastHomeWinHomeTeam;
    }

    public void setLastHomeWinHomeTeam(FootBallFixtureInfoBean lastHomeWinHomeTeam) {
    	this.lastHomeWinHomeTeam = lastHomeWinHomeTeam;
    }
    
    public FootBallFixtureInfoBean getLastWinHomeTeam() {
    	return this.lastWinHomeTeam;
    }

    public void setLastWinHomeTeam(FootBallFixtureInfoBean lastWinHomeTeam) {
    	this.lastWinHomeTeam = lastWinHomeTeam;
    }
    
    public FootBallFixtureInfoBean getLastAwayWinAwayTeam() {
    	return this.lastAwayWinAwayTeam;
    }

    public void setLastAwayWinAwayTeam(FootBallFixtureInfoBean lastAwayWinAwayTeam) {
    	this.lastAwayWinAwayTeam = lastAwayWinAwayTeam;
    }
    
    public FootBallFixtureInfoBean getLastWinAwayTeam() {
    	return this.lastWinAwayTeam;
    }

    public void setLastWinAwayTeam(FootBallFixtureInfoBean lastWinAwayTeam) {
    	this.lastWinAwayTeam = lastWinAwayTeam;
    }
    
    public List<FootBallFixtureInfoBean> getFixtures() {
    	return this.fixtures;
    }

    public void setFixtures(List<FootBallFixtureInfoBean> fixtures) {
    	this.fixtures = fixtures;
    }
    
    @Override
    public String toString() {
        return "FootBallPastFixtureHead2HeadBean{" +
                "count='" + count + "'" +
                ", timeFrameStart='" + timeFrameStart + "'" +
                ", timeFrameEnd='" + timeFrameEnd + "'" +
                ", homeTeamWins='" + homeTeamWins + "'" +
                ", awayTeamWins='" + awayTeamWins + "'" +
                ", draws='" + draws + "'" +
                ", lastHomeWinHomeTeam='" + lastHomeWinHomeTeam + "'" +
                ", lastWinHomeTeam='" + lastWinHomeTeam + "'" +
                ", lastAwayWinAwayTeam='" + lastAwayWinAwayTeam + "'" +
                ", lastWinAwayTeam='" + lastWinAwayTeam + "'" +
                ", fixtures='" + fixtures + "'" +
                '}';
    }
}
        
