package com.music.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXB;

public class MusicUtil {
	
    /*
     *XMLでpostを行う
    */
    public static String getPostResult(String requestXml){
    	HttpURLConnection connection = null;
    	try{
        	// URLをオブジェクト化
        	URL targetUrl = new URL(MusicConstants.GRACENOTE_URL);
        	// コネクションオブジェクトを作成
        	connection = (HttpURLConnection)targetUrl.openConnection();
        	// POSTを指定
        	connection.setRequestMethod("POST");
            // リダイレクトを無効にする
        	connection.setInstanceFollowRedirects(false);
        	// コネクションへの書き込みを有効化
        	connection.setDoOutput(true);
            String size = String.valueOf(requestXml.getBytes("UTF-8").length);
            connection.setRequestProperty("Content-Length", size);
            // ヘッダーを設定
            connection.setRequestProperty("Accept-Language","ja;q=0.7,en;q=0.3");
            connection.setRequestProperty("If-Modified-Since","Thu, 01 Jun 1970 00:00:00 GMT");
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Cache-Control","no-cache");
            connection.setRequestProperty("Content-Type","application/xml;charset=UTF-8");
            
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),
                    StandardCharsets.UTF_8));
            // XMLを設定
            writer.write(requestXml);
            writer.flush();
            // レスポンス結果確認
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            	// ストリームから結果取得
            	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            	// StringBuilderで文字列をアペンド
            	StringBuilder buf = new StringBuilder();
            	String line = null;
                while((line = reader.readLine())!=null) {
                    buf.append(line);
                }
                // 文字列を返す
                return new String(buf);
            }else{
            	Exception e = new IOException();
            	throw e;
            }
            
    	}catch(Exception e){
    		// exceptionの場合はnullを返す
    		return null;
    	}finally{
            if (connection != null) {
                connection.disconnect();
            }
    	}

    }
    
    /*
     *XMLオブジェクトを文字列化
    */
    public static String getXMLToString(Object obj){
    	// StringWriterで出力
    	StringWriter sw = new StringWriter();
    	JAXB.marshal(obj, sw);
    	// 文字列化してreturn
    	return sw.toString();
    	
    }
    
    /*
     *空かどうかを判定するメソッド
    */
    public static boolean isEmpty(String word) {
    	// nullまたは空文字の場合
        if(word == null || "".equals(word)){
        	return true;
        }else{
        	return false;
        }
    }

}
