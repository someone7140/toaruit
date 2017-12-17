
package com.football.bean.player;

import java.util.Comparator;

import com.football.api.FootConstants;

public class FootBallPlayerComparator implements Comparator<FootBallPlayerInfoBean> {
    
	//ポジションと背番号でソート
    public int compare(FootBallPlayerInfoBean player_a, FootBallPlayerInfoBean player_b) {
    	try {
        	// ポジションのソート番号があることを確認
    		if(FootConstants.PLAYER_POSITIONSORT_MAP.containsKey(player_a.getPosition()) && 
    				FootConstants.PLAYER_POSITIONSORT_MAP.containsKey(player_b.getPosition())) {
    			// ポジションに該当するソートを取得
    			int positionSort_a = Integer.parseInt(FootConstants.PLAYER_POSITIONSORT_MAP.get(player_a.getPosition()));
    			int positionSort_b = Integer.parseInt(FootConstants.PLAYER_POSITIONSORT_MAP.get(player_b.getPosition()));
    			// ポジションのソートが後ろの方が大きいとき
    			if(positionSort_a < positionSort_b) {
    				return -1;
    		    // ポジションのソートが前の方が大きいとき
    			}else if(positionSort_a > positionSort_b) {
        			return 1;
        		// 同じとき
        		}else {
        			// 背番号での比較
        			int jerseyNumber_a = 0;
        			int jerseyNumber_b = 0;
        			// 数字判定(1個目)
        			try {
        				jerseyNumber_a = Integer.parseInt(player_a.getJerseyNumber());
        			}catch(Exception e) {
        				// exceptionの時は1を返す
        				return 1;
        			}
        			// 数字判定(2個目)
        			try {
        				jerseyNumber_b = Integer.parseInt(player_b.getJerseyNumber());
        			}catch(Exception e) {
        				// exceptionの時は-1を返す
        				return -1;
        			}
        			// 背番号が後ろの方が大きいとき
        			if(jerseyNumber_a < jerseyNumber_b) {
        				return -1;
        		    // ポジションのソートが前の方が大きいとき
        			}else if(jerseyNumber_a > jerseyNumber_b) {
            			return 1;
            		// 同じとき
            		}else {
            			return 0;
            		}
        		}
    		}else {
    			// なければ0を返す
        		return 0;
    		}
    	}catch (Exception e) {
    		// exception時は0を返す
    		return 0;
    	}

    }
}
        
