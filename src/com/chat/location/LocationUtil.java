package com.chat.location;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class LocationUtil {
    /*
     *地名からコードを取得する。
    */
    public static String getLocationCodeFromName(String locationName) {
    	// 都道府県取得用の変数
    	String todoufukenCode = "";
    	Entry<String ,String> tempTodoufuken =  null;
    	boolean todoufukenGetFlg = false;
    	
    	// 入力した地名に都道府県が含まれているか。
    	for(Iterator<Entry<String,String >> todoufukenIterator = LocationConstants.TODOUHUKEN_MAP.entrySet().iterator(); todoufukenIterator.hasNext(); ){
			tempTodoufuken = ((Entry<String,String >)todoufukenIterator.next());
			// 含まれている場合はコードを取得
			if(locationName.indexOf(tempTodoufuken.getValue()) != -1 ){
				todoufukenCode = tempTodoufuken.getKey();
				todoufukenGetFlg = true;
				break;
			}
		}
    	
    	// 都道府県が含まれていない場合はnullを返す
    	if(!todoufukenGetFlg){
    		return null;
    	}else{
    		// 都道府県内市区町村一覧取得APIから取得したものを返す
        	return getCityCode(todoufukenCode , locationName);
    	}
    	
    }
    
    /*
     *都道府県内市区町村一覧取得APIから入力したものがあるか判定し返す。
    */
    public static String getCityCode(String todoufukenCode, String inputLocation) {
        HttpURLConnection connection = null;
    	try{
    		// 市区町村検索用のURL設定
    		String searchUrl = LocationConstants.CITY_GETURL + todoufukenCode;
            URL url = new URL(searchUrl);

            // コネクションをオープン
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // タイムアウト時間を設定
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000) ;
            // レスポンスが来た場合は処理続行
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // InputStreamを返す
                InputStreamReader inputStreamReader = new InputStreamReader( connection.getInputStream(), "UTF-8" );
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

                // レスポンスのJsonを取得。
                String oneLine = null;
                String responseJson = "";
                while( true ){
                	oneLine = bufferedReader.readLine();
                	// 行がNULLの場合
                	if(oneLine == null){
                		// レスポンスへ書き出す
                		break;
                	// レスポンスのJsonへ行を追加
                	}else{
                		responseJson += oneLine;
                	}
                }
                
                // GsonでJsonからオブジェクトに変換
                Gson gson = new Gson();
                CityInfoBean cityIndoBean = gson.fromJson(responseJson ,CityInfoBean.class);
                
                // 市区町村のリストと入力内容を比較
                for (CityDataBean cityDataBean : cityIndoBean.getData()) {
                	// 含まれている場合はコードを返す
        			if(inputLocation.indexOf(cityDataBean.getName()) != -1 ){
        				return cityDataBean.getId();
        			}
                }
                
                // 市区町村がない場合はnullを返す
                return null;
            }else{
            	return null;
            }

    	}catch(Exception e){
    		e.printStackTrace(); 
    		return null;
    	}finally{
            if (connection != null) {
                connection.disconnect();
            }
    	}
    }
    
}
