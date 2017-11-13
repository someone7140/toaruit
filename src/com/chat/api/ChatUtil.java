package com.chat.api;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chat.location.LocationUtil;

import ai.api.model.AIOutputContext;
import ai.api.model.AIResponse;

public class ChatUtil {
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
     *コンテキストの文字列を返す
    */
    public static String getContextOutName(List<AIOutputContext> contextOutList) {
    	
    	String contextOutName = "";
    	// コンテキストのアウトは1つの前提
    	for (final AIOutputContext c : contextOutList) {
    		contextOutName = c.getName();
    	}
    	
    	return contextOutName;
    	
    }
    
    /*
     *APIAIとのやり取りの状態を判定
    */
    public static String getSessionState(String contextOutName) {
    	
    	// コンテキストの後ろ3文字がend
    	if(ChatConstants.CONTEXT_END.equals(contextOutName.substring(contextOutName.length() - 3))){
    		// 終わりの状態
    		return ChatConstants.SESSION_STATE_APIAIEND;

    	}else{
    		// プロセス中の状態
    		return ChatConstants.SESSION_STATE_APIAIPROCESS;
    	}
    	
    }
    
    /*
     *各種情報をセッションに格納
    */
    public static void setSessionValue(String sessionState ,String context, AIResponse aiResponse, String inputWord, HttpSession session) {
    	// セッションの状態を格納
    	session.setAttribute(ChatConstants.SESSION_KEY_STATE, sessionState);
    	
    	// AIResponseがNULLでない（APIAIとのやり取り中）
    	if(aiResponse != null){
    		// コンテキストがセッション1（食べ物を入力）
    		if("session1".equals(context)){
    			// foodのパラメータを取得
    			session.setAttribute(ChatConstants.SESSION_KEY_FOODNAME, aiResponse.getResult().getStringParameter(ChatConstants.APIAI_PARM_FOOD));
    			// System.out.println(aiResponse.getResult().getStringParameter(ChatConstants.APIAI_PARM_FOOD));
    		}
    		// コンテキストがセッション2-2（家の外）
    		else if("session2-2".equals(context)){
    			// ルートを家の外に設定
    			session.setAttribute(ChatConstants.SESSION_KEY_CHATROOT, ChatConstants.CHAT_ROOT_OUTHOUSE);
    		}
    		// コンテキストがセッション2-1-1（出前）
    		else if("session2-1-1".equals(context)){
    			// ルートを出前に設定
    			session.setAttribute(ChatConstants.SESSION_KEY_CHATROOT, ChatConstants.CHAT_ROOT_DELIVERY);
    		}
    		// コンテキストがセッション2-1-2（ネット）
    		else if("session2-1-2".equals(context)){
    			// ルートを出前に設定
    			session.setAttribute(ChatConstants.SESSION_KEY_CHATROOT, ChatConstants.CHAT_ROOT_NET);
    		}
    		// コンテキストがセッション3（価格）
    		else if("session3end".equals(context) || "session3".equals(context)){
    			// 最低価格を設定
    			if( !isEmpty(aiResponse.getResult().getStringParameter(ChatConstants.APIAI_PARM_LOWPRICE)) ){
    				session.setAttribute(ChatConstants.SESSION_KEY_LOWRPRISE, aiResponse.getResult().getStringParameter(ChatConstants.APIAI_PARM_LOWPRICE));
    			}
    				
    			// 最高価格を設定
    			if( !isEmpty(aiResponse.getResult().getStringParameter(ChatConstants.APIAI_PARM_HIGHPRICE)) ){
    				session.setAttribute(ChatConstants.SESSION_KEY_HIGHRPRISE, aiResponse.getResult().getStringParameter(ChatConstants.APIAI_PARM_HIGHPRICE));
    			}
    		}
    		// コンテキストがセッション4（地名）
    		else if("session4end".equals(context)){
    			String locationName = aiResponse.getResult().getStringParameter(ChatConstants.APIAI_PARM_LOCATIONNAME);
    			session.setAttribute(ChatConstants.SESSION_KEY_LOCATIONNAME, locationName);
    			// 出前の場合
    			if( ChatConstants.CHAT_ROOT_DELIVERY.equals(session.getAttribute(ChatConstants.SESSION_KEY_CHATROOT)) ){
    				// セッションに都道府県コードを設定
    				setSessionCityCode(locationName ,session);
    			}

    		}
    	}else{

    		// 変更対象が食べ物
    		if(ChatConstants.CHANGE_FOOD.equals(context)){
    			session.setAttribute(ChatConstants.SESSION_KEY_FOODNAME, inputWord);
    			// 
    		// 変更対象が価格
    		}else if(ChatConstants.CHANGE_PRICE.equals(context)){
    			setSessionPriceChange(inputWord ,session);
    		// 変更対象が地名
    		}else{
    			session.setAttribute(ChatConstants.SESSION_KEY_LOCATIONNAME, inputWord);
    		}
    		
    		// 出前の場合
			if( ChatConstants.CHAT_ROOT_DELIVERY.equals(session.getAttribute(ChatConstants.SESSION_KEY_CHATROOT)) ){
				// セッションに都道府県コードを設定
				setSessionCityCode((String)session.getAttribute(ChatConstants.SESSION_KEY_LOCATIONNAME) ,session);
			}
    	}

    }
    
    /*
     *各種情報をレスポンス内容をセット
    */
    public static String getUserResponse(String sessionState ,HttpSession session, String answer, HttpServletRequest request) {
    	String userResponse = "";
    	// セッション状態を追加
    	userResponse = userResponse + sessionState + "|";
    	// チャットのルートを追加
    	if(session != null){
        	userResponse = userResponse + session.getAttribute(ChatConstants.SESSION_KEY_CHATROOT) + "|";
    	}else{
        	userResponse = userResponse + "|";
    	}
    	// 回答内容を追加
    	// セッションの状態が地名が見つからない場合
    	if(ChatConstants.SESSION_STATE_LOCATION_NOTFIND.equals(sessionState)){
        	userResponse = userResponse + ChatConstants.ANSWER_LOCATION_NOTFIND + "|";
    	}else{
        	userResponse = userResponse + answer + "|";
    	}

    	// 食べ物名を追加
    	if(session != null){
        	userResponse = userResponse + session.getAttribute(ChatConstants.SESSION_KEY_FOODNAME) + "|";
    	}else{
    		userResponse = userResponse + "|";
    	}
    	// 地名を追加
    	if(session != null){
    		userResponse = userResponse + session.getAttribute(ChatConstants.SESSION_KEY_LOCATIONNAME) + "|";
    	}else{
    		userResponse = userResponse + "|";
    	}
    	
    	// 価格を追加
    	if(session != null){
        	if( isEmpty((String)session.getAttribute(ChatConstants.SESSION_KEY_LOWRPRISE)) ){
        		// 指定なしの場合
        		userResponse = userResponse + "指定なし" + "|";
        	}else if( !isEmpty((String)session.getAttribute(ChatConstants.SESSION_KEY_LOWRPRISE)) 
        			&& !isEmpty((String)session.getAttribute(ChatConstants.SESSION_KEY_HIGHRPRISE)) ){
        		// 範囲指定の場合
        		userResponse = userResponse + session.getAttribute(ChatConstants.SESSION_KEY_LOWRPRISE) + "円から" + session.getAttribute(ChatConstants.SESSION_KEY_HIGHRPRISE)+  "円" +"|";
        	}else{
        		userResponse = userResponse + session.getAttribute(ChatConstants.SESSION_KEY_LOWRPRISE) + "円" +"|";
        	}
    	}else{
    		userResponse = userResponse + "|";
    	}

    	// 遷移先URL
    	userResponse = userResponse + getMoveUrl(session , request);
    	
    	return userResponse;
    }
    
    /*
     *都道府県市町村コードをセッションに格納
    */
    public static void setSessionCityCode(String locationName , HttpSession session){
    	// 地名から都道府県・市町村コードを取得
		String locationCode = LocationUtil.getLocationCodeFromName(locationName);
		// 地名コードがnullの場合
		if( isEmpty(locationCode) ){
			// セッションに空白をセット
			session.setAttribute(ChatConstants.SESSION_KEY_LOCATIONCODE ,"");
	    	// セッションの状態を地名が見つからない状態
	    	session.setAttribute(ChatConstants.SESSION_KEY_STATE, ChatConstants.SESSION_STATE_LOCATION_NOTFIND);
		}else{
			session.setAttribute(ChatConstants.SESSION_KEY_LOCATIONCODE ,locationCode);
			// api.apiとのやり取りは終了した状態
			session.setAttribute(ChatConstants.SESSION_KEY_STATE, ChatConstants.SESSION_STATE_APIAIEND);
		}
    }
    
    
    /*
     *変更価格をセッションに格納
    */
    public static void setSessionPriceChange(String priceWord , HttpSession session){
    	// 正規表現の設定
    	String regex = "[0-9]*";
    	Pattern ptrn = Pattern.compile(regex ,Pattern.MULTILINE);
    	Matcher matcher = ptrn.matcher(priceWord);

    	int matchCount = 0;
    	
    	// 正規表現の検索
    	while(matcher.find()){
    		// 最低価格
    		if(matchCount == 0 && !isEmpty(matcher.group())){
    			session.setAttribute(ChatConstants.SESSION_KEY_LOWRPRISE, matcher.group());
    			session.setAttribute(ChatConstants.SESSION_KEY_HIGHRPRISE, null);
    			matchCount++;
    		// 最高価格
    		}else if(matchCount > 0 && !isEmpty(matcher.group())){
    			session.setAttribute(ChatConstants.SESSION_KEY_HIGHRPRISE, matcher.group());
    			// ループ終了
    			break;
    	    }
    	
        }
    }	
    
    
    /*
     *遷移先URLを取得
    */
    public static String getMoveUrl(HttpSession session ,HttpServletRequest request){
    	// やりとりが終了している
    	if( ChatConstants.SESSION_STATE_APIAIEND.equals((String)session.getAttribute(ChatConstants.SESSION_KEY_STATE)) ){
    		// ルートが出前
    		if( ChatConstants.CHAT_ROOT_DELIVERY.equals((String)session.getAttribute(ChatConstants.SESSION_KEY_CHATROOT)) ){
    			// セッションから場所のコードを取得
    			String locationCode = (String)session.getAttribute(ChatConstants.SESSION_KEY_LOCATIONCODE);
    			// URLに場所のコードを付与
    			String deliveryUrl =  ChatConstants.URL_RAKUTEN_DELIVERY + "level1=" + locationCode.substring(0,2)
    			                   +  "&level2=" + locationCode.substring(2,5);
    			return deliveryUrl;
    		// ルートがネット
    		}else if( ChatConstants.CHAT_ROOT_NET.equals((String)session.getAttribute(ChatConstants.SESSION_KEY_CHATROOT)) ){
    			// リクエストから現在のURLを取得
    		    String netMoveUrl = request.getRequestURL().toString();
    		    // 末尾の遷移先をreplace
    		    netMoveUrl = netMoveUrl.replace("chatrequest" , "foodnetsearch");
    		    // パラメータをURLに付与
    		    netMoveUrl = netMoveUrl + "?foodName=" + session.getAttribute(ChatConstants.SESSION_KEY_FOODNAME)  + "&lowprice="+ session.getAttribute(ChatConstants.SESSION_KEY_LOWRPRISE) + "&highprice="+ session.getAttribute(ChatConstants.SESSION_KEY_HIGHRPRISE);
    		    
    	    	return netMoveUrl;
        	 // ルートが外食
        	}else if( ChatConstants.CHAT_ROOT_OUTHOUSE.equals((String)session.getAttribute(ChatConstants.SESSION_KEY_CHATROOT)) ){
        		// リクエストから現在のURLを取得
        		String outMoveUrl = request.getRequestURL().toString();
        		// 末尾の遷移先をreplace
        		outMoveUrl = outMoveUrl.replace("chatrequest" , "foodoutsearch");
        		// パラメータをURLに付与
        		outMoveUrl = outMoveUrl + "?foodName=" + session.getAttribute(ChatConstants.SESSION_KEY_FOODNAME)  + "&lowprice="+ session.getAttribute(ChatConstants.SESSION_KEY_LOWRPRISE) + "&highprice="+ session.getAttribute(ChatConstants.SESSION_KEY_HIGHRPRISE)
        		                        + "&location="+ session.getAttribute(ChatConstants.SESSION_KEY_LOCATIONNAME);
        		
        	    return outMoveUrl;
    	    }else{
    			return ChatConstants.URL_DUMMY;
    		}
    	}else{
    		return ChatConstants.URL_DUMMY;
    	}
    		
    }

}
