
package com.football.bean.teaminfo;

public class FootBallTeamInfoBean  {
    
	private FootBallTeamInfoLinksBean _links;
    private String name;
    private String code;
    private String shortName;
    private String squadMarketValue;
    private String crestUrl;

    public FootBallTeamInfoLinksBean get_links() {
    	return this._links;
    }

    public void set_links(FootBallTeamInfoLinksBean _links) {
    	this._links = _links;
    }
    
    public String getName() {
    	return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    
    public String getCode() {
    	return this.code;
    }

    public void setCode(String code) {
    	this.code = code;
    }
    
    public String getShortName() {
    	return this.shortName;
    }

    public void setShortName(String shortName) {
    	this.shortName = shortName;
    }
    
    public String getSquadMarketValue() {
    	return this.squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
    	this.squadMarketValue = squadMarketValue;
    }
    
    public String getCrestUrl() {
    	return this.crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
    	this.crestUrl = crestUrl;
    }
    
    
    @Override
    public String toString() {
        return "FootBallTeamInfoBean{" +
                "_links='" + _links + "'" +
                ", name='" + name + "'" +
                ", code='" + code + "'" +
                ", shortName='" + shortName + "'" +
                ", squadMarketValue='" + squadMarketValue + "'" +
                ", crestUrl='" + crestUrl + "'" +
                '}';
    }
}
        
