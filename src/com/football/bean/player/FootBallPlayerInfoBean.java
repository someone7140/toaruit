
package com.football.bean.player;

public class FootBallPlayerInfoBean  {
    
    private String name;
    private String position;
    private String jerseyNumber;
    private String dateOfBirth;
    private String nationality;
    private String contractUntil;
    private String marketValue;
    
    public String getName() {
    	return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    
    public String getPosition() {
    	return this.position;
    }

    public void setPosition(String position) {
    	this.position = position;
    }
    
    public String getJerseyNumber() {
    	return this.jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
    	this.jerseyNumber = jerseyNumber;
    }
    
    public String getDateOfBirth() {
    	return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
    	this.dateOfBirth = dateOfBirth;
    }
    
    public String getNationality() {
    	return this.nationality;
    }

    public void setNationality(String nationality) {
    	this.nationality = nationality;
    }
    
    public String getContractUntil() {
    	return this.contractUntil;
    }

    public void setContractUntil(String contractUntil) {
    	this.contractUntil = contractUntil;
    }
    
    public String getMarketValue() {
    	return this.marketValue;
    }

    public void setMarketValue(String marketValue) {
    	this.marketValue = marketValue;
    }
    
    @Override
    public String toString() {
        return "FootBallPlayerInfoBean{" +
                "name='" + name + "'" +
                "position='" + position + "'" +
                '}';
    }
}
        
