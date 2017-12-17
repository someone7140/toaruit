

package com.wikipedia.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WikipediaUtil {

    /*
     *GET通信してXMLを取得するメソッド
    */
    public static InputStream getWikipediaXML(String word) throws  Exception{
        HttpURLConnection connection = null;
    	try{
    		// WikipediaのURL設定
            URL url = new URL(WikipediaConstants.WIKIPEDIA_URL_FHALF + word + WikipediaConstants.WIKIPEDIA_URL_LHALF);

            // コネクションをオープン
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // レスポンスが来た場合は処理続行
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // InputStreamを返す
                return connection.getInputStream();
            }else{
            	Exception e = new IOException();
            	throw e;
            }

    	}catch(Exception e){
    		throw e;
    	}finally{
            if (connection != null) {
                connection.disconnect();
            }
    	}
    }
    
    
    /*
     *GET通信して英語の言語リンクのXMLを取得するメソッド
    */
    public static InputStream getWikipediaXMLLanglinkEn(String wordEn ,String countLimit) throws  Exception{
        HttpURLConnection connection = null;
    	try{
    		// WikipediaのURL設定
    		String urlWikipediaStr = WikipediaConstants.WIKIPEDIA_URL_ENTOJP_FHALF + wordEn + WikipediaConstants.WIKIPEDIA_URL_ENTOJP_LHALF + countLimit;
    		// スペースをエンコード
    		urlWikipediaStr = urlWikipediaStr.replace(" " , "%20");
            URL url = new URL(urlWikipediaStr);

            // コネクションをオープン
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // レスポンスが来た場合は処理続行
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // InputStreamを返す
                return connection.getInputStream();
            }else{
            	Exception e = new IOException();
            	throw e;
            }

    	}catch(Exception e){
    		throw e;
    	}finally{
            if (connection != null) {
                connection.disconnect();
            }
    	}
    }
    
    /*
     *XMLからコンテンツを取得するメソッド
    */
    public static String getWikipediaContents(InputStream is) throws  Exception{
    	try{
    		// GET通信で取得したXMLを読み込み
    		Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
    		
    		// タグのnameからコンテンツのノードを取得
    		Element rootElement=document.getDocumentElement();
    		NodeList nodeList = rootElement.getElementsByTagName("rev");
    		
    		//最初の1つめのXMLのノードの値を取得
    		Element elem = (Element)nodeList.item(0);
    		return elem.getFirstChild().getNodeValue();

    	}catch(Exception e){
    		throw e;
    	}
    	
    }
    
    /*
     *区切り文字で囲まれるあるワードの位置を返すメソッド
    */
    public static int getKugiriPos (String target , String searchKeyWord , String kugiriStartWord, String kugiriEndWord) {
        // 正規表現の設定
    	String regex = kugiriStartWord + ".*?" + searchKeyWord + ".*?" + kugiriEndWord;
    	Pattern ptrn = Pattern.compile(regex ,Pattern.MULTILINE);
    	Matcher matcher = ptrn.matcher(target);
    	
    	int kugiriPos = 0;
    	
    	// 正規表現の検索
    	while(matcher.find()){
            // 終わりの文字位置を設定
        	kugiriPos =  matcher.end();
        	// 1回でbreak
        	break;
    	}
    	
    	return kugiriPos;

    }
    
    /*
     *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
    */
    public static String getKugiriBlock (String target , String searchKeyWord , String kugiriStartWord, String kugiriEndWord) {
        // 正規表現の設定
    	String regex = kugiriStartWord + ".*?" + searchKeyWord + ".*?" + kugiriEndWord;
    	Pattern ptrn = Pattern.compile(regex ,Pattern.MULTILINE);
    	Matcher matcher = ptrn.matcher(target);
    	
    	String kugiriBlock = "";
    	
    	// 正規表現の検索
    	while(matcher.find()){
        	// 対象の文字列を含む区切りを設定
    		kugiriBlock =  target.substring(matcher.start() ,matcher.end());
    		// 1回でbreak
    		break;
    	}
    	
    	return kugiriBlock;

    }

    /*
     *wikiTableの[[]]で囲まれた文字を取得
    */
    public static String getWikiTableCell (String word ) {
        // 2回replace
    	return word.replace(WikipediaConstants.WIKITABLE_CELL_START, "").replace(WikipediaConstants.WIKITABLE_CELL_END , "");

    }
    
    /*
     *wikiTableの()で囲まれた文字を取得
    */
    public static String getWikiContentsKakko (String word ) {
        // 2回replace
    	return word.replace(WikipediaConstants.WIKITABLE_KAKKO_START, "").replace(WikipediaConstants.WIKITABLE_KAKKO_END , "");

    }
    
    
    /*
     *wikipediaのリンクを付与
    */
    public static String getWikiLinkWith (String word ) {
        // aタグを付与
    	return "<a href =\"" + WikipediaConstants.WIKIPEDIA_URL_LINK + word + "\" target=\"_blank\"> " + word +"</a>";

    }
    
    /*
     *英語のキーワードから日本語のキーワードを返す
    */
    public static String getWordEnToJp(String wordEn) {
    	try {
            // 英語のキーワードで参照できる言語リンクのXML
        	InputStream is = getWikipediaXMLLanglinkEn(wordEn, "80");
        	// GET通信で取得したXMLを読み込み
    		Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
    		
    		// タグ「ll」から各国の言語のキーワードを取得
    		Element rootElement=document.getDocumentElement();
    		NodeList nodeList = rootElement.getElementsByTagName("ll");
    		// 取得したノードでループ
    		for(int x = 0 ,size= nodeList.getLength(); x < size; x++) {
    			// 言語がja
                if( WikipediaConstants.WIKIPEDIA_JP_LANG.equals(nodeList.item(x).getAttributes().getNamedItem("lang").getNodeValue()) ) {
                	// nodeのvalueを返す
            		Element elem = (Element)nodeList.item(x);
            		return elem.getFirstChild().getNodeValue();
                }
            }
    		// jaがなかったらnull
    		return null;
    	}catch(Exception e) {
    		// exception発生時はNULL
    		return null;
    	}
    }
    
}
