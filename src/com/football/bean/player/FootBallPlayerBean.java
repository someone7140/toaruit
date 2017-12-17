
package com.football.bean.player;

import java.util.List;

public class FootBallPlayerBean  {
    
	private FootBallPlayerLinksBean _links;
    private String count;
    private List<FootBallPlayerInfoBean> players;
    
    public FootBallPlayerLinksBean get_links() {
    	return this._links;
    }

    public void set_links(FootBallPlayerLinksBean _links) {
    	this._links = _links;
    }
    
    public String getCount() {
    	return this.count;
    }

    public void setCount(String count) {
    	this.count = count;
    }
    
    public List<FootBallPlayerInfoBean> getPlayers() {
    	return this.players;
    }

    public void setPlayers(List<FootBallPlayerInfoBean> players) {
    	this.players = players;
    }
    
    @Override
    public String toString() {
        return "FootBallPlayerBean{" +
                "_links='" + _links + "'" +
                ", count='" + count + "'" +
                ", players='" + players + "'" +
                '}';
    }
}
        
