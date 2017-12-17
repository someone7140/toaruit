
package com.football.bean.fixture;

import java.util.Comparator;

public class FootBallFixtureInfoComparator implements Comparator<FootBallFixtureInfoBean> {
    
	//日付の降順でソート
    public int compare(FootBallFixtureInfoBean fixture_a, FootBallFixtureInfoBean fixture_b) {
        String date_a = fixture_a.getDate();
        String date_b = fixture_b.getDate();

        //降順でソート
        return date_b.compareTo(date_a);
    }
}
        
