
package com.hotpepper.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXB;

import com.hotpepper.bean.HotPepperBudgetBean;
import com.hotpepper.bean.HotPepperShopBean;
import com.hotpepper.bean.ResponseHotPepperBudgetBean;
import com.hotpepper.bean.ResponseHotPepperGourmetBean;

public class HotPepperApiUtil {
    
    public static String getHotPepperXml(String food, String lowprice, String highprice, String location, String page, HttpServletRequest request) {
        HttpURLConnection connection = null;
    	try{
     	   // リクエストからコンテキストを取得
            ServletContext context = request.getSession().getServletContext();
    		int lowpriceInt = getLowpriceInt(lowprice);
    		int highpriceInt = getHighpriceInt(lowprice , highprice);
    		// ホットペッパーグルメ検索用のURL設定
    		String searchHotPepperUrl = HotPepperConstants.HOTPEPPER_URL
    				           + "key=" + context.getInitParameter("HOTPEPPER_APP_ID")
    				           // URLパラメータ用に変換
    				           + "&keyword=" + URLEncoder.encode(food ,"UTF-8") + "," + URLEncoder.encode(location ,"UTF-8")
    				           + "&count=10"
    				           + "&start=" + page;
    		// 予算コードの取得
    		String budgetCode = getHoyPepperBudget(lowpriceInt, highpriceInt, request);
    		if(!isEmpty(budgetCode)){
    			searchHotPepperUrl = searchHotPepperUrl  + "&budget=" + budgetCode;
    		}
    		
            URL url = new URL(searchHotPepperUrl);

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
    
    public static String getHotPepperGourmetHtml(ResponseHotPepperGourmetBean responseHotPepperGourmetBean) {
    	String responseHtml = "";
    	// shopのリストを取得
    	List<HotPepperShopBean> shopList = responseHotPepperGourmetBean.getShopList();
    	for (HotPepperShopBean shopBean : shopList) {
    		// trタグのスタート
    		responseHtml = responseHtml + "<tr>";
    		// 店名のセット
    		responseHtml = responseHtml + "<td><a href =\"" + shopBean.getUrls().getPc()  + "\" target=\"_blank\">" +  shopBean.getName() + "</a></td>";
    		// 画像のセット
    		responseHtml = responseHtml + "<td><img src=\"" + shopBean.getLogo_image()  + "\" /></td>";		
    		// 住所
    		responseHtml = responseHtml + "<td>" + shopBean.getAddress()  + "</td>";
    		// 最寄り駅
    		responseHtml = responseHtml + "<td>" + shopBean.getStation_name()  + "</td>";
    		// 説明
    		responseHtml = responseHtml + "<td>" + shopBean.getCatch()  + "</td>";

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
    
    // lowprice・highriceからbudgetコードを取得する
    public static String getHoyPepperBudget(int lowpriceInt, int  highpriceInt , HttpServletRequest request){
    	HttpURLConnection connection = null;
    	// リクエストからコンテキストを取得
        ServletContext context = request.getSession().getServletContext();
    	// lowpriceが0
    	if(lowpriceInt == 0){
    		return null;
    	}else{
    		try{
        		// ホットペッパー予算検索用のURL設定
        		String searchHotPepperBudgetUrl = HotPepperConstants.HOTPEPPER_BUDGET_URL
        				           + "key=" +context.getInitParameter("HOTPEPPER_APP_ID");
        		
        		URL url = new URL(searchHotPepperBudgetUrl);

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
                    // xmlをオブジェクトに変換
                    ResponseHotPepperBudgetBean responseHotPepperBudgetBean = JAXB.unmarshal(new StringReader(responseXml), ResponseHotPepperBudgetBean.class);
                    // budgetのリストでループ
                    List<HotPepperBudgetBean> responseBudgetList = responseHotPepperBudgetBean.getBudgetList();
                    List<String> tempBudgetCodeList = new ArrayList<String>();
                    for (HotPepperBudgetBean budgetBean : responseBudgetList) {
                    	// 価格の判定
                    	if(budgetTargetJudge(lowpriceInt , highpriceInt , budgetBean.getName())){
                    		tempBudgetCodeList.add(budgetBean.getCode());
                    	}
                    	
                    	// Listサイズの判定(2以上)
                    	if(tempBudgetCodeList.size() >= 2){
                    		// ループ終了
                    		break;
                    	}
                    }

                    // listのサイズが1
                    if(tempBudgetCodeList.size() == 1){
                    	return tempBudgetCodeList.get(0);
                    }
                    // listのサイズが2
                    else if (tempBudgetCodeList.size() == 2){
                    	return tempBudgetCodeList.get(0) + "," + tempBudgetCodeList.get(1);
                    }else{
                    	return null;
                    }
                    
                }else{
                	return null;
                }
        	}catch(Exception e){
        		return null;
        	}
    	}
    	
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
    
    /*
     *価格が対象のBudgetコードの範囲であるか
    */
    public static boolean budgetTargetJudge(int lowpriceInt, int highpriceInt, String responseBudget) {
    	try{
    		// lowpriceが1501未満
    		if(lowpriceInt < 1501){
    			// 最低価格を1501へ設定
    			lowpriceInt = 1501;
    		}
    		// highpriceが1501未満
    		if(highpriceInt < 1501){
    			highpriceInt = lowpriceInt;
    		}
    		
    		//
    		// 円の文字を消す
    		String budgetTarget = responseBudget.replace("円", "");
    		// 「～」の位置を取得
    		int delimPos = budgetTarget.indexOf(HotPepperConstants.HOTPEPPER_BUDGET_DELIMITER);
    		// 上限価格の有無
    		if( (delimPos + 1) != budgetTarget.length()){
        		// 上限価格を取得
        		int budgetTargetHigh = Integer.parseInt( budgetTarget.substring(delimPos + 1, budgetTarget.length()) );
        		// 最低価格以下
        		if(lowpriceInt <= budgetTargetHigh){
            		// 下限価格の有無
            		if( delimPos != 0){
                		// 下限価格を取得
                		int budgetTargethigh = Integer.parseInt( budgetTarget.substring(0, delimPos) );
                		// 最高価格以上
                		if(highpriceInt >=  budgetTargethigh){
                			return true;
                		}
            		}else{
            			return true;
            		}
        		}
    		}else{
        		// 下限価格の有無
        		if( delimPos != 0){
            		// 下限価格を取得
            		int budgetTargethigh = Integer.parseInt( budgetTarget.substring(0, delimPos) );
            		// 最高価格以上
            		if(highpriceInt >=  budgetTargethigh){
            			return true;
            		}
        		}
    		}

    		// 上限・下限の条件に合致しない場合false
    		return false;
    	}catch(Exception e){
             return false;
    	}

    }

}
