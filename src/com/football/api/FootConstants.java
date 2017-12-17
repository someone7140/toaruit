

package com.football.api;

import java.util.HashMap;
import java.util.Map;

public class FootConstants {
    // Competition取得用のURL
    public static final String FOOT_COMPETITON_URL = "http://api.football-data.org/v1/competitions";
    // Team取得用のURL
    public static final String FOOT_TEAM_URL = "http://api.football-data.org/v1/teams/";
    // 過去Fixture取得用のURL
    public static final String FOOT_PAST_FIXTURE_URL = "http://api.football-data.org/v1/fixtures/";
    // 試合完了のステータス
    public static final String FIXTURE_STATUS_FINISHED = "FINISHED";
    // プレイヤーのポジションのソート番号マップ
 	public static final Map<String ,String> PLAYER_POSITIONSORT_MAP;
     static {
         HashMap<String ,String> map = new HashMap<String ,String>();
         map.put("Keeper", "1");
         map.put("Centre-Back", "2");
         map.put("Left-Back", "3");
         map.put("Right-Back", "4");
         map.put("Defensive Midfield", "5");
         map.put("Central Midfield", "6");
         map.put("Left Midfield", "7");
         map.put("Right Midfield", "8");
         map.put("Attacking Midfield", "9");
         map.put("Left Wing","10");
         map.put("Right Wing","11");
         map.put("Secondary Striker","12");
         map.put("Centre-Forward","13");
         PLAYER_POSITIONSORT_MAP = map;
     }

}


