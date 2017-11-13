
package com.rakuten.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.rakuten.bean.response.RakutenCdItemBean;
import com.rakuten.bean.response.RakutenIchibaItemBean;
import com.rakuten.bean.response.ResponseRakutenIchibaBean;
import com.rakuten.bean.response.ResponseRakutenSearchBean;

public class RakutenApiUtil {

    public static String getRakutenSearchCdXml(String artist, String page ,HttpServletRequest request) {
        HttpURLConnection connection = null;
 	   // リクエストからコンテキストを取得
        ServletContext context = request.getSession().getServletContext();
    	try{
    		// CD検索用のURL設定
    		String searchCdUrl = RakutenConstants.RAKUTEN_CD_URL
		         	           + "applicationId=" + context.getInitParameter("RAKUTEN_APP_ID")
			                   + "&affiliateId=" + context.getInitParameter("RAKUTEN_AFFIL_ID")
    				           + "&format=xml"
    				           + "&elements="
    				           // スペースをURLパラメータ用に変換
    				           + "&artistName=" + artist.replace(" ", "%20")
    				           + "&hits=30"
    				           + "&page=" + page
    				           + "&sort=-releaseDate"
    				           ;
    		
            URL url = new URL(searchCdUrl);

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

                // レスポンスのXMLを取得。
                String oneLine = null;
                String responseXml = "";
                while( true ){
                	oneLine = bufferedReader.readLine();
                	// 行がNULLの場合
                	if(oneLine == null){
                		// レスポンスへ書き出す
                		break;
                	// レスポンスのHTMLへ行を追加
                	}else{
                		responseXml += oneLine;
                	}
                }
                return responseXml;
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
    
    public static String getRakutenSearchIchibaXml(String food, String lowprice, String highprice, String page, HttpServletRequest request) {
        HttpURLConnection connection = null;
 	   // リクエストからコンテキストを取得
        ServletContext context = request.getSession().getServletContext();
    	try{
    		int lowpriceInt = getLowpriceInt(lowprice);
    		int highpriceInt = getHighpriceInt(lowprice , highprice);
    		// 楽天市場検索用のURL設定
    		String searchIchibaUrl = RakutenConstants.RAKUTEN_ICHIBA_URL
    				           + "applicationId=" + context.getInitParameter("RAKUTEN_APP_ID")
    				           + "&affiliateId=" + context.getInitParameter("RAKUTEN_AFFIL_ID")
    				           + "&format=xml"
    				           + "&elements="
    				           // URLパラメータ用に変換
    				           + "&keyword=" + URLEncoder.encode(food ,"UTF-8")
    				           + "&hits=30"
    				           + "&page=" + page
    				           // 食べ物のジャンルID
    				           + "&genreId=100227"
    				           // URLパラメータ用に変換
    				           + "&sort=" + URLEncoder.encode("+itemPrice"  ,"UTF-8");
    		// lowpriceの判定
    		if(lowpriceInt != 0){
    			searchIchibaUrl = searchIchibaUrl + "&minPrice=" + lowprice;
    		}
    		// highpriceの判定
    		if(highpriceInt != 0){
    			searchIchibaUrl = searchIchibaUrl + "&maxPrice=" + highprice;
    		}
    		
            URL url = new URL(searchIchibaUrl);

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

                // レスポンスのXMLを取得。
                String oneLine = null;
                String responseXml = "";
                while( true ){
                	oneLine = bufferedReader.readLine();
                	// 行がNULLの場合
                	if(oneLine == null){
                		// レスポンスへ書き出す
                		break;
                	// レスポンスのHTMLへ行を追加
                	}else{
                		responseXml += oneLine;
                	}
                }
                return responseXml;
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
    
    public static String getRakutenResponseHtml(ResponseRakutenSearchBean responseRakutenBean) {
    	String responseHtml = "";
    	// Itemのリストを取得
    	List<RakutenCdItemBean> itemBeanList = responseRakutenBean.getCdItemList();
    	for (RakutenCdItemBean itemBean : itemBeanList) {
    		// trタグのスタート
    		responseHtml = responseHtml + "<tr>";
    		// タイトルのセット
    		responseHtml = responseHtml + "<td>" + itemBean.getTitle() + "</td>";
    		// CD画像のセット
    		responseHtml = responseHtml + "<td><a href =\"" + itemBean.getAffiliateUrl()  + "\" target=\"_blank\"><img src=\"" + itemBean.getSmallImageUrl()  + "\" /></a></td>";		
    		// プレイリストのセット
    		responseHtml = responseHtml + "<td>";
    		// プレイリストの曲リストを取得
    		StringTokenizer st = new StringTokenizer(itemBean.getPlayList(), RakutenConstants.RAKUTEN_PLAYLIST_DELIM);
    	    int length = st.countTokens();
    	    String token = "";
    	    try{
    	    	// tableタグの付与
    	    	responseHtml = responseHtml + "<table>";
        	    for (int i = 0; i < length; i++) {
          	      // 曲名でURLパラメータを付与
        	      token = st.nextToken();
        	      // 偶数の場合
        	      if(i % 2 == 0){
              	      responseHtml = responseHtml + "<tr><td><a href = \"/musicsearch?searchTrack=" +  URLEncoder.encode(token,"UTF-8") + "&searchNonDispFlg=true\" target=\"_blank\">" + token  + "</a></td>";
        	      }else{
              	      responseHtml = responseHtml + "<td><a href = \"/musicsearch?searchTrack=" +  URLEncoder.encode(token,"UTF-8") + "&searchNonDispFlg=true\" target=\"_blank\">" + token  + "</a></td></tr>";
        	      }

          	    }
        	    // 個数が偶数の場合
        	    if( (length - 1) % 2 == 0){
        	    	responseHtml = responseHtml + "<td></td></tr>";
        	    }
    	    	responseHtml = responseHtml + "</table>";
    	    }catch (Exception e){
    	    	return null;
    	    }

    		responseHtml = responseHtml + "</td>";
    		// trタグのエンド
    		responseHtml = responseHtml + "</tr>";
    	}
    	
    	return responseHtml;
    	
    }
    
    
    public static String getRakutenIchibaResponseHtml(ResponseRakutenIchibaBean responseRakutenIchibaBean) {
    	String responseHtml = "";
    	// Itemのリストを取得
    	List<RakutenIchibaItemBean> itemBeanList = responseRakutenIchibaBean.getIchibaItemList();
    	for (RakutenIchibaItemBean itemBean : itemBeanList) {
    		// trタグのスタート
    		responseHtml = responseHtml + "<tr>";
    		// 商品名のセット
    		responseHtml = responseHtml + "<td><a href =\"" + itemBean.getAffiliateUrl()  + "\" target=\"_blank\">" +  itemBean.getItemName()  + "</a></td>";
    		// 商品画像のセット(一番最初の画像)
    		responseHtml = responseHtml + "<td><img src=\"" + itemBean.getSmallImageUrls().get(0)  + "\" /></td>";
    		// ショップ名のセット
    		responseHtml = responseHtml + "<td><a href =\"" + itemBean.getShopAffiliateUrl()  + "\" target=\"_blank\">" +  itemBean.getShopName()  + "</a></td>";
    		// 価格のセット
    		responseHtml = responseHtml + "<td>" + itemBean.getItemPrice()  + "</td>";
    		// 商品説明のセット
    		responseHtml = responseHtml + "<td>" + itemBean.getItemCaption()  + "</td>";

    		// trタグのエンド
    		responseHtml = responseHtml + "</tr>";
    	}
    	
    	return responseHtml;
    	
    }
    
    
    // lowpriceをチェックして数値型を返す
    public static int getLowpriceInt(String lowpriceStr){
    	try{
    		return Integer.parseInt(lowpriceStr);
    	}catch(Exception e){
    		return 0;
    	}
    }
    
    // highriceをチェックして数値型を返す
    public static int getHighpriceInt(String lowpriceStr, String highpriceStr){
    	try{
    		int lowpriceInt = Integer.parseInt(lowpriceStr);
    		int highpriceInt = Integer.parseInt(highpriceStr);
    		// highpriceとlowpriceを比較
    		if(lowpriceInt < highpriceInt){
    			return highpriceInt;
    		}else{
    			return 0;
    		}
    	}catch(Exception e){
    		return 0;
    	}
    }

}
