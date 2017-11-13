

package com.chat.location;

import java.util.HashMap;
import java.util.Map;

public class LocationConstants {

    // 都道府県マップ
	public static final Map<String ,String> TODOUHUKEN_MAP;
    static {
        HashMap<String ,String> map = new HashMap<String ,String>();
        map.put("01", "北海道");
        map.put("02", "青森");
        map.put("03", "岩手");
        map.put("04", "宮城");
        map.put("05", "秋田");
        map.put("06", "山形");
        map.put("07", "福島");
        map.put("08", "茨城");
        map.put("09", "栃木");
        map.put("10","群馬");
        map.put("11","埼玉");
        map.put("12","千葉");
        map.put("13","東京");
        map.put("14","神奈川");
        map.put("15","新潟");
        map.put("16","富山");
        map.put("17","石川");
        map.put("18","福井");
        map.put("19","山梨");
        map.put("20","長野");
        map.put("21","岐阜");
        map.put("22","静岡");
        map.put("23","愛知");
        map.put("24","三重");
        map.put("25","滋賀");
        map.put("26","京都");
        map.put("27","大阪");
        map.put("28","兵庫");
        map.put("29","奈良");
        map.put("30","和歌山");
        map.put("31","鳥取");
        map.put("32","島根");
        map.put("33","岡山");
        map.put("34","広島");
        map.put("35","山口");
        map.put("36","徳島");
        map.put("37","香川");
        map.put("38","愛媛");
        map.put("39","高知");
        map.put("40","福岡");
        map.put("41","佐賀");
        map.put("42","長崎");
        map.put("43","熊本");
        map.put("44","大分");
        map.put("45","宮崎");
        map.put("46","鹿児島");
        map.put("47","沖縄");
        TODOUHUKEN_MAP = map;
    }
    
    // 市区町村取得用のURL
	public static final String CITY_GETURL = "http://www.land.mlit.go.jp/webland/api/CitySearch?area=";

}


