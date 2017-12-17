
package com.football.bean.competition;

public class FootBallCompetitionBean  {
    
	private FootBallCompetitionLinksBean _links;
    private String id;
    private String caption;
    private String league;
    private String year;
    private String currentMatchday;
    private String numberOfMatchdays;
    private String numberOfTeams;
    private String numberOfGames;
    private String lastUpdated;
    
    public FootBallCompetitionLinksBean get_links() {
    	return this._links;
    }

    public void set_links(FootBallCompetitionLinksBean _links) {
    	this._links = _links;
    }
    
    public String getId() {
    	return this.id;
    }

    public void setId(String id) {
    	this.id = id;
    }
    
    public String getCaption() {
    	return this.caption;
    }

    public void setCaption(String caption) {
    	this.caption = caption;
    }
    
    public String getLeague() {
    	return this.league;
    }

    public void setLeague(String league) {
    	this.league = league;
    }
    
    public String getYear() {
    	return this.year;
    }

    public void setYear(String year) {
    	this.year = year;
    }
    
    public String getCurrentMatchday() {
    	return this.currentMatchday;
    }

    public void setCurrentMatchday(String currentMatchday) {
    	this.currentMatchday = currentMatchday;
    }
    
    public String getNumberOfMatchdays() {
    	return this.numberOfMatchdays;
    }

    public void setNumberOfMatchdays(String numberOfMatchdays) {
    	this.numberOfMatchdays = numberOfMatchdays;
    }
    
    public String getNumberOfTeams() {
    	return this.numberOfTeams;
    }

    public void setNumberOfTeams(String numberOfTeams) {
    	this.numberOfTeams = numberOfTeams;
    }
    
    public String getNumberOfGames() {
    	return this.numberOfGames;
    }

    public void setNumberOfGames(String numberOfGames) {
    	this.numberOfGames = numberOfGames;
    }
    
    public String getLastUpdated() {
    	return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
    	this.lastUpdated = lastUpdated;
    }
    
    @Override
    public String toString() {
        return "FootBallCompetition{" +
                "_links='" + _links + "'" +
                ", id='" + id + "'" +
                ", caption='" + caption + "'" +
                ", league='" + league + "'" +
                ", year='" + year + "'" +
                ", currentMatchday='" + currentMatchday + "'" +
                ", numberOfMatchdays='" + numberOfMatchdays + "'" +
                ", numberOfTeams='" + numberOfTeams + "'" +
                ", numberOfGames='" + numberOfGames + "'" +
                ", lastUpdated='" + lastUpdated + "'" +
                '}';
    }
}
        
