
package com.football.bean.competition;

public class FootBallCompetitionLinksBean  {
    
	private FootBallCompetitionHrefBean self;
	private FootBallCompetitionHrefBean teams;
	private FootBallCompetitionHrefBean fixtures;
	private FootBallCompetitionHrefBean leagueTable;
	
    public FootBallCompetitionHrefBean getSelf() {
    	return this.self;
    }

    public void setSelf(FootBallCompetitionHrefBean self) {
    	this.self = self;
    }
    
    public FootBallCompetitionHrefBean getTeams() {
    	return this.teams;
    }

    public void setTeams(FootBallCompetitionHrefBean teams) {
    	this.teams = teams;
    }
    
    public FootBallCompetitionHrefBean getFixtures() {
    	return this.fixtures;
    }

    public void setFixtures(FootBallCompetitionHrefBean fixtures) {
    	this.fixtures = fixtures;
    }
    
    public FootBallCompetitionHrefBean getLeagueTable() {
    	return this.leagueTable;
    }

    public void setLeagueTable(FootBallCompetitionHrefBean leagueTable) {
    	this.leagueTable = leagueTable;
    }
    
    @Override
    public String toString() {
        return "_links{" +
                "self='" + self + "'" +
                ", teams='" + teams + "'" +
                ", fixtures='" + fixtures + "'" +
                ", leagueTable='" + leagueTable + "'" +
                '}';
    }
}
        
