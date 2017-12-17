package com.football.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.football.bean.competition.FootBallCompetitionBean;
import com.football.bean.fixture.FootBallFixtureBean;
import com.football.bean.fixture.FootBallFixtureInfoBean;
import com.football.bean.fixture.FootBallFixtureInfoComparator;
import com.football.bean.fixture.FootBallFixtureResultBean;
import com.football.bean.fixture.FootBallFixtureResultHalfTimeBean;
import com.football.bean.fixture.past.FootBallPastFixtureBean;
import com.football.bean.leaguetable.FootBallLeagueResultBean;
import com.football.bean.leaguetable.FootBallLeagueTableBean;
import com.football.bean.leaguetable.FootBallLeagueTeamBean;
import com.football.bean.player.FootBallPlayerBean;
import com.football.bean.player.FootBallPlayerComparator;
import com.football.bean.player.FootBallPlayerInfoBean;
import com.football.bean.teaminfo.FootBallTeamInfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wikipedia.util.WikipediaConstants;
import com.wikipedia.util.WikipediaUtil;

public class FootBallUtil{
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
     *jsonを取得する接続用メソッド
    */
    public static String geJsonUsingApi(String inputUrl , HttpServletRequest request) {
        HttpURLConnection connection = null;
    	try{
    		// URLオブジェクトの作成
            URL url = new URL(inputUrl);
            // コネクションをオープン
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // タイムアウト時間を設定
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000) ;
            // リクエストヘッダーを設定
            ServletContext context = request.getSession().getServletContext();
            String authToken = context.getInitParameter("FOOT_AUTH_TOKEN");
            connection.setRequestProperty("X-Auth-Token", authToken);
            // レスポンスが来た場合は処理続行
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // InputStreamを返す
                InputStreamReader inputStreamReader = new InputStreamReader( connection.getInputStream(), "UTF-8" );
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

                // レスポンスのXMLを取得
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
    
    
    /*
     *順位表のHTMLデータを取得
    */
    public static String getFootRankFromApi(String seasonYear, String league ,HttpServletRequest request) {
    	try{
            // CompetitionのJSONデータを取得
        	String competitionJson = getFootDataCompetitionJson(seasonYear ,request);
        	// Gsonのオブジェクト
        	Gson competitionGson = new Gson();
        	// 変換する型情報を用意する
        	Type competitionListType = new TypeToken<List<FootBallCompetitionBean>>(){}.getType();
        	List<FootBallCompetitionBean> competitionList = competitionGson.fromJson(competitionJson, competitionListType);
        	// league取得用のURL
        	String leagueUrl = "";
        	for(FootBallCompetitionBean competitionBean : competitionList ){
        		// 引数のリーグと同じ
        		if(league.equals(competitionBean.getLeague())){
        			// リーグ取得用のURLをセット
        			leagueUrl = competitionBean.get_links().getLeagueTable().getHref();
        		}
        	}
        	// league取得用のURLの空文字判定
        	if(isEmpty(leagueUrl)){
        		// NULLを返す
        		return null;
        	}
        	
        	// leagueTableの結果を取得
        	String leagueJson = geJsonUsingApi(leagueUrl , request);
        	// Gsonのオブジェクト
        	Gson leagueGson = new Gson();
        	// 変換する型情報を用意する
        	FootBallLeagueTableBean leagueTable = leagueGson.fromJson(leagueJson, FootBallLeagueTableBean.class);
        	// 順位表のHTMLを返す
        	return getLeagueTableHtml(leagueTable ,request);

    	}catch(Exception e){
    		// Exceptionの場合はNULLを返す
        	return null;
    	}

    }
    
    
    /*
     *football-data.orgからチームデータのオブジェクトを取得
    */
    public static FootBallTeamInfoBean getFootTeamInfoFromApi(String teamId ,HttpServletRequest request) {
    	try{
            // teamのJSONデータを取得
        	String teamJson = geJsonUsingApi(FootConstants.FOOT_TEAM_URL +  teamId ,request);
        	// Gsonのオブジェクト
        	Gson teamGson = new Gson();
        	// 変換する型情報を用意する
        	FootBallTeamInfoBean teamBean = teamGson.fromJson(teamJson, FootBallTeamInfoBean.class);
        	// チームのオブジェクトを返す
        	return teamBean;

    	}catch(Exception e){
    		// Exceptionの場合はNULLを返す
        	return null;
    	}

    }
    
    /*
     **football-data.orgからプレイヤーの一覧を取得
    */
    public static String getFootPlayerFromApi(String playerUrl ,HttpServletRequest request) {
    	try{
            // playerのJSONデータを取得
        	String playerJson = geJsonUsingApi(playerUrl ,request);
        	// Gsonのオブジェクト
        	Gson playerGson = new Gson();
        	// 変換する型情報を用意する
        	FootBallPlayerBean playerBean = playerGson.fromJson(playerJson, FootBallPlayerBean.class);
        	// null判定判定
        	if(playerBean == null){
        		// NULLを返す
        		return null;
        	}
        	// プレイヤーの一覧のHTMLを返す
        	return getPlayerHtml(playerBean ,request);

    	}catch(Exception e){
    		// Exceptionの場合はNULLを返す
        	return null;
    	}

    }
    

    /*
     **football-data.orgから直近の試合情報結果を取得
    */
    public static String getRecentlyGameResultFromApi(String fixturesUrl ,String teamId, HttpServletRequest request) {
    	try{
            // fixturesUrlのJSONデータを取得
        	String fixturesJson = geJsonUsingApi(fixturesUrl ,request);
        	// Gsonのオブジェクト
        	Gson fixtureGson = new Gson();
        	// 変換する型情報を用意する
        	FootBallFixtureBean fixtureBean = fixtureGson.fromJson(fixturesJson, FootBallFixtureBean.class);
        	// null判定判定
        	if(fixtureBean == null){
        		// NULLを返す
        		return null;
        	}
        	// Urlから
        	// 試合結果の一覧のHTMLを返す
        	return getRecentlyGameResultHtml(fixtureBean ,teamId , request);

    	}catch(Exception e){
    		// Exceptionの場合はNULLを返す
    		// e.printStackTrace();
        	return null;
    	}

    }
    
    /*
     *football-data.orgからCompetitionのデータを取得
    */
    public static String getFootDataCompetitionJson(String seasonYear,HttpServletRequest request ) {
    	// 取得用のURL設定
    	String footCompetitionUrl = FootConstants.FOOT_COMPETITON_URL
                                  + "?season=" + seasonYear;
    	// Jsonのテキストを返す
    	return geJsonUsingApi(footCompetitionUrl , request);
    }

    /*
     *playerBeanのオブジェクトからHTMLを返す
    */
    public static String getPlayerHtml(FootBallPlayerBean playerBean ,HttpServletRequest request) {
    	// NULL判定
    	if(playerBean == null){
    		return null;
    	}else{
    		String responseHtml = "";
    		// ヘッダー
        	responseHtml = responseHtml + "<br/><br/>【プレイヤー一覧】<br/>";
            // 列タイトル
        	responseHtml = responseHtml + "<table border =\"1\"><tr><td>背番号</td><td>ポジション</td><td>名前</td><td>誕生日</td><td>国籍</td><td>契約期限</td></tr>";
        	// リストをソート
        	List<FootBallPlayerInfoBean> playerInfoList = playerBean.getPlayers();
        	Collections.sort(playerInfoList ,new FootBallPlayerComparator());
        	// 選手情報のリストでループ
        	for(FootBallPlayerInfoBean playerInfoBean : playerInfoList) {
        		// 行開始タグ
        		responseHtml = responseHtml + "<tr>";
        		//背番号
        		responseHtml = responseHtml + "<td>" + playerInfoBean.getJerseyNumber() +"</td>";
        		//ポジション
        		responseHtml = responseHtml + "<td>" + playerInfoBean.getPosition() +"</td>";
        		//名前
        		responseHtml = responseHtml + "<td><a href=\"" + getPlayerLink(playerInfoBean ,request) + "\" target=\"_blank\">" + playerInfoBean.getName() +"</a></td>";
        		//誕生日
        		responseHtml = responseHtml + "<td>" + playerInfoBean.getDateOfBirth() +"</td>";
        		//国籍
        		responseHtml = responseHtml + "<td>" + playerInfoBean.getNationality() +"</td>";
        		//契約期限
        		responseHtml = responseHtml + "<td>" + playerInfoBean.getContractUntil() +"</td>";
        		// 行終了タグ
        		responseHtml = responseHtml + "</tr>";
        	}
    		// tableタグ閉じ
        	responseHtml = responseHtml + "</table>";
        	return responseHtml;
    	}
    }
    
    
    /*
     *fixtureBeanのオブジェクトからHTMLを返す
    */
    public static String getRecentlyGameResultHtml(FootBallFixtureBean fixtureBean ,String teamId, HttpServletRequest request) {
    	// NULL判定
    	if(fixtureBean == null){
    		return null;
    	}else{
    		// 表示用のリストを作成
    		List<FootBallFixtureInfoBean> fixtureDisplayList = getDisplayFixture(fixtureBean.getFixtures());
    		String responseHtml = "";
    		// ヘッダー
        	responseHtml = responseHtml + "<br/><br/>【直近の試合結果】<br/>";
            // 列タイトル
        	responseHtml = responseHtml + "<table border =\"1\"><tr><td>試合日</td><td>ホームチーム</td><td>アウェイチーム</td><td>試合結果(ホーム-アウェイ)</td><td>前半まで(ホーム-アウェイ)</td><td>過去の対戦結果</td></tr>";
        	// ループ回数
        	int i = 0;
        	// 試合のリストでループ
        	for(FootBallFixtureInfoBean fixtureInfoBean : fixtureDisplayList) {
        		// 行開始タグ
        		// ループが5以上
        		if(i >= 5) {
        			// 初期非表示のクラスを付与
            		responseHtml = responseHtml + "<tr class =\"inithide\">";
        		}else {
            		responseHtml = responseHtml + "<tr>";
        		}
        	
        		//試合日
        		responseHtml = responseHtml + "<td>" + fixtureInfoBean.getDate() +"</td>";
        		//ホームチーム
        		//自チームの場合
        		if( teamId.equals( getIdFromUrl(fixtureInfoBean.get_links().getHomeTeam().getHref()) ) ) {
        			// リンク無し
        			responseHtml = responseHtml + "<td>" + fixtureInfoBean.getHomeTeamName() +"</td>";
        		}else {
        			//リンクをつける
        			responseHtml = responseHtml + "<td><a href=\"" + getLeagueTeamUrl(fixtureInfoBean.get_links().getHomeTeam().getHref(), request) + "\" target=\"_blank\">" + fixtureInfoBean.getHomeTeamName() +"</a></td>";
        		}
        		//アウェイチーム
        		//自チームの場合
        		if( teamId.equals( getIdFromUrl(fixtureInfoBean.get_links().getAwayTeam().getHref()) ) ) {
        			// リンク無し
        			responseHtml = responseHtml + "<td>" + fixtureInfoBean.getAwayTeamName() +"</td>";
        		}else {
        			//リンクをつける
        			responseHtml = responseHtml + "<td><a href=\"" + getLeagueTeamUrl(fixtureInfoBean.get_links().getAwayTeam().getHref(), request) + "\" target=\"_blank\">" + fixtureInfoBean.getAwayTeamName() +"</a></td>";
        		}
        		//試合結果
        		FootBallFixtureResultBean fixtureResult = fixtureInfoBean.getResult();
        		// null判定
        		if(fixtureResult == null) {
        			// 情報なし
        			responseHtml = responseHtml + "<td>情報なし</td><td>情報なし</td>";
        		}else {
        			responseHtml = responseHtml + "<td>" + fixtureResult.getGoalsHomeTeam() + "-"+ fixtureResult.getGoalsAwayTeam()+"</td>";
            		//前半まで
            		FootBallFixtureResultHalfTimeBean fixtureHalfResult = fixtureResult.getHalfTime();
            		// null判定
            		if(fixtureHalfResult == null) {
            			// 情報なし
            			responseHtml = responseHtml + "<td>情報なし</td>";
            		}else {
                		responseHtml = responseHtml + "<td>" + fixtureHalfResult.getGoalsHomeTeam() + "-"+ fixtureHalfResult.getGoalsAwayTeam()+"</td>";
            		}
        		}

        		//過去の対戦成績
        		responseHtml = responseHtml + "<td><a href=\"" + getPastFixtureUrl(fixtureInfoBean.get_links().getSelf().getHref(), teamId , request) + "\" target=\"_blank\">過去の対戦結果の参照</a></td>";
        		// 行終了タグ
        		responseHtml = responseHtml + "</tr>";
        		i = i+ 1;
        	}
        	
    		// tableタグ閉じ
        	responseHtml = responseHtml + "</table>";
        	return responseHtml;
    	}
    }
    
    /*
     *leagueTableのオブジェクトからHTMLを返す
    */
    public static String getLeagueTableHtml(FootBallLeagueTableBean leagueTable ,HttpServletRequest request) {
    	// NULL判定
    	if(leagueTable == null){
    		return null;
    	}else{
        	String responseHtml = "";
        	FootBallLeagueResultBean homeResult;
        	FootBallLeagueResultBean awayResult;
        	
            // ヘッダー
        	responseHtml = responseHtml + "<br/><br/>【" + leagueTable.getLeagueCaption() + "】<br/>";
            // 列タイトル
        	responseHtml = responseHtml + "<table border =\"1\"><tr><td>順位</td><td>チーム名</td><td>試合数</td><td>勝点</td><td>勝利数</td><td>引分数</td><td>敗北数</td><td>得点</td><td>失点</td><td>HOME＆AWAY結果表示</td></tr>";
        	// リーグテーブルのチームリストを取得
        	List<FootBallLeagueTeamBean> leagueTeamList = leagueTable.getStanding();
        	// チームの情報でループ
        	for(FootBallLeagueTeamBean leagueTeamBean :leagueTeamList){
        		//順位
        		responseHtml = responseHtml +  "<tr><td>" + leagueTeamBean.getPosition() +"</td>";
        		// チーム名
        		responseHtml = responseHtml +  "<td><a href =\"" + getLeagueTeamUrl(leagueTeamBean.get_links().getTeam().getHref() ,request)  + "\" target=\"_blank\">" +  leagueTeamBean.getTeamName() + "</a></td>";
        		// 試合数
        		responseHtml = responseHtml +  "<td>" + leagueTeamBean.getPlayedGames() +"</td>";
        		// 勝点
        		responseHtml = responseHtml +  "<td>" + leagueTeamBean.getPoints() +"</td>";
        		// 勝利数
        		responseHtml = responseHtml +  "<td>" + leagueTeamBean.getWins() +"</td>";
        		// 引き分け数
        		responseHtml = responseHtml +  "<td>" + leagueTeamBean.getDraws() +"</td>";
        		// 敗北数
        		responseHtml = responseHtml +  "<td>" + leagueTeamBean.getLosses() +"</td>";
        		// 得点
        		responseHtml = responseHtml +  "<td>" + leagueTeamBean.getGoals() +"</td>";
        		// 失点
        		responseHtml = responseHtml +  "<td>" + leagueTeamBean.getGoalsAgainst() +"</td>";
        		// HOME＆AWAY結果表示
        		responseHtml = responseHtml +  "<td><input type=\"button\" class=\"homeawaydisp\" id=\"" + leagueTeamBean.getPosition() +"\" value=\"表示\" /></td></tr>";
        		
        		// homeオブジェクト取得
        		homeResult = leagueTeamBean.getHome();
        		// home行を挿入
        		responseHtml = responseHtml +  "<tr bgcolor=\"springgreen\" class=\"home\" id=\"home"+ leagueTeamBean.getPosition()  +"\"><td colspan=\"4\">HOME結果</td>";
        		// home勝利数
        		responseHtml = responseHtml +  "<td>" + homeResult.getWins() +"</td>";
        		// home引き分け数
        		responseHtml = responseHtml +  "<td>" + homeResult.getDraws() +"</td>";
        		// home敗北数
        		responseHtml = responseHtml +  "<td>" + homeResult.getLosses() +"</td>";
        		// home得点
        		responseHtml = responseHtml +  "<td>" + homeResult.getGoals() +"</td>";
        		// home失点
        		responseHtml = responseHtml +  "<td>" + homeResult.getGoalsAgainst() +"</td>";
        		// 閉じタグ
        		responseHtml = responseHtml +  "<td rowspan=\"2\"></td></tr>";
        		
        		// awayオブジェクト取得
        		awayResult = leagueTeamBean.getAway();
        		// home行を挿入
        		responseHtml = responseHtml +  "<tr bgcolor=\"springgreen\" class=\"away\" id=\"away"+ leagueTeamBean.getPosition()  +"\"><td colspan=\"4\">AWAY結果</td>";
        		// home勝利数
        		responseHtml = responseHtml +  "<td>" + awayResult.getWins() +"</td>";
        		// home引き分け数
        		responseHtml = responseHtml +  "<td>" + awayResult.getDraws() +"</td>";
        		// home敗北数
        		responseHtml = responseHtml +  "<td>" + awayResult.getLosses() +"</td>";
        		// home得点
        		responseHtml = responseHtml +  "<td>" + awayResult.getGoals() +"</td>";
        		// home失点
        		responseHtml = responseHtml +  "<td>" + awayResult.getGoalsAgainst() +"</td>";
        		// 閉じタグ
        		responseHtml = responseHtml +  "</tr>";
        	
        	}
        	
        	// tableタグ閉じ
        	responseHtml = responseHtml + "</table>";
        	return responseHtml;
    	}

    }
    
    /*
     *leagueTableのURLから遷移先のサーブレットURLを作成
    */
    public static String getLeagueTeamUrl(String apiTeamUrl ,HttpServletRequest request) {
		// リクエストから現在のURLを取得
	    String netMoveUrl = request.getRequestURL().toString();
	    // 末尾の遷移先をreplace
	    netMoveUrl = netMoveUrl.replace("footrankapi" , "footteamref");
	    // apiから取得するteamのURLからIDを取得
	    String teamId = getIdFromUrl(apiTeamUrl);
	    //teamのIDをURLへ付与
	    netMoveUrl = netMoveUrl + "?teamId=" + teamId;
	    return netMoveUrl;

    }
    
    /*
     *playerInfoから遷移先のサーブレットURLを作成
    */
    public static String getPlayerLink(FootBallPlayerInfoBean playerInfoBean ,HttpServletRequest request) {
		// リクエストから現在のURLを取得
	    String netMoveUrl = request.getRequestURL().toString();
	    // 末尾の遷移先をreplace
	    netMoveUrl = netMoveUrl.replace("footteamref" , "footplayerref");
	    //Playerの名前をURLへ付与
	    netMoveUrl = netMoveUrl + "?playername=" + playerInfoBean.getName();
	    return netMoveUrl;

    }
    
    /*
     *FootBallFixtureBeanから表示用のリストを作成
    */
    public static List<FootBallFixtureInfoBean> getDisplayFixture(List<FootBallFixtureInfoBean> fixtureInfoList) {
    	// 表示用のリスト
    	List<FootBallFixtureInfoBean> displayFixtureInfoList = new ArrayList<FootBallFixtureInfoBean>();
    	
    	// fixtureInfoのリストでループ
    	for(FootBallFixtureInfoBean fixtureInfo : fixtureInfoList) {
    		// fixtureInfoの試合ステータスが完了
    		if(FootConstants.FIXTURE_STATUS_FINISHED.equals(fixtureInfo.getStatus())) {
    			// 表示用リストに追加
    			displayFixtureInfoList.add(fixtureInfo);
    		}
    	}
        // 日付の降順でソート
    	Collections.sort(displayFixtureInfoList, new FootBallFixtureInfoComparator());
	    return displayFixtureInfoList;

    }
    
    /*
     *参照urlから末尾のIdを取得する
    */
    public static String getIdFromUrl(String apiUrl) {
	    return apiUrl.substring (apiUrl.lastIndexOf("/") + 1 , apiUrl.length());

    }
    
    /*
     *過去対戦のfixtureURLから遷移先のサーブレットURLを作成
    */
    public static String getPastFixtureUrl(String apiFixtureUrl ,String teamId ,HttpServletRequest request) {
		// リクエストから現在のURLを取得
	    String netMoveUrl = request.getRequestURL().toString();
	    // 末尾の遷移先をreplace
	    netMoveUrl = netMoveUrl.replace("footteamref" , "footpastfixture");
	    // apiから取得するURLからIDを取得
	    String fixtureId = getIdFromUrl(apiFixtureUrl);
	    //fixtureとteamのIDをURLへ付与
	    netMoveUrl = netMoveUrl + "?fixtureId=" + fixtureId + "&teamId=" + teamId;
	    return netMoveUrl;

    }
    
    /*
     *wikipediaから選手の英語名から日本語用のURLを取得
    */
    public static String getPlayerNameJpUrl(String playerNameEn) {
		// 日本語名を取得
	    String playerNameJp = WikipediaUtil.getWordEnToJp(playerNameEn);
	    // NULL判定
	    if(isEmpty(playerNameJp)) {
	    	return null;
	    }else {
	    	try {
		    	//エンコード
		    	String playerNameJpEncoded = URLEncoder.encode(playerNameJp, "UTF-8");
			    // URLを返す
			    return WikipediaConstants.WIKIPEDIA_URL_LINK + playerNameJpEncoded;
	    	}catch(Exception e) {
	    		// exception時nullを返す
		    	return null;
	    	}

	    }


    }
    
    /*
     *過去の試合のHTMLを取得
    */
    public static String getPastGameResultFromApi(String fixtureId, String teamId, HttpServletRequest request) {
    	try{
            // 過去試合のJSONデータを取得
        	String pasyFixtureJson = geJsonUsingApi(FootConstants.FOOT_PAST_FIXTURE_URL +  fixtureId ,request);
        	// Gsonのオブジェクト
        	Gson fixtureGson = new Gson();
        	// 変換する型情報を用意する
        	FootBallPastFixtureBean pastFixtureBean = fixtureGson.fromJson(pasyFixtureJson, FootBallPastFixtureBean.class);
        	// HTML情報を返す
        	return getPastGameResultHtmlFromJsonObj(pastFixtureBean , teamId, request);

    	}catch(Exception e){
    		// Exceptionの場合はNULLを返す
        	return null;
    	}


    }

    /*
     *pastFixtureBeanのオブジェクトからHTMLを返す
    */
    public static String getPastGameResultHtmlFromJsonObj(FootBallPastFixtureBean pastFixtureBean, String teamId ,HttpServletRequest request) {
    	// NULL判定
    	if(pastFixtureBean == null){
    		return null;
    	}else{
    		String responseHtml = "";
    		// ヘッダー
        	responseHtml = responseHtml + "<br/>【過去の対戦結果】<br/>";
            // 列タイトル
        	responseHtml = responseHtml + "<table border =\"1\"><tr><td>試合日</td><td>ホームチーム</td><td>アウェイチーム</td><td>試合結果(ホーム-アウェイ)</td><td>前半まで(ホーム-アウェイ)</td></tr>";
        	// 試合のリストを取得
        	List<FootBallFixtureInfoBean> fixtureList = getDisplayFixture(pastFixtureBean.getHead2head().getFixtures());
        	// 選手情報のリストでループ
        	for(FootBallFixtureInfoBean fixtureInfoBean : fixtureList) {
        		// 行開始タグ
        		responseHtml = responseHtml + "<tr>";
        		//試合日
        		responseHtml = responseHtml + "<td>" + fixtureInfoBean.getDate() +"</td>";
        		//ホームチーム
        		//自チームの場合
        		if( teamId.equals( getIdFromUrl(fixtureInfoBean.get_links().getHomeTeam().getHref()) ) ) {
        			// リンク無し
        			responseHtml = responseHtml + "<td>" + fixtureInfoBean.getHomeTeamName() +"</td>";
        		}else {
        			//リンクをつける
        			responseHtml = responseHtml + "<td><a href=\"" + getLeagueTeamUrl(fixtureInfoBean.get_links().getHomeTeam().getHref(), request) + "\" target=\"_blank\">" + fixtureInfoBean.getHomeTeamName() +"</a></td>";
        		}
        		//アウェイチーム
        		//自チームの場合
        		if( teamId.equals( getIdFromUrl(fixtureInfoBean.get_links().getAwayTeam().getHref()) ) ) {
        			// リンク無し
        			responseHtml = responseHtml + "<td>" + fixtureInfoBean.getAwayTeamName() +"</td>";
        		}else {
        			//リンクをつける
        			responseHtml = responseHtml + "<td><a href=\"" + getLeagueTeamUrl(fixtureInfoBean.get_links().getAwayTeam().getHref(), request) + "\" target=\"_blank\">" + fixtureInfoBean.getAwayTeamName() +"</a></td>";
        		}
        		//試合結果
        		FootBallFixtureResultBean fixtureResult = fixtureInfoBean.getResult();
        		// null判定
        		if(fixtureResult == null) {
        			// 情報なし
        			responseHtml = responseHtml + "<td>情報なし</td><td>情報なし</td>";
        		}else {
        			responseHtml = responseHtml + "<td>" + fixtureResult.getGoalsHomeTeam() + "-"+ fixtureResult.getGoalsAwayTeam()+"</td>";
            		//前半まで
            		FootBallFixtureResultHalfTimeBean fixtureHalfResult = fixtureResult.getHalfTime();
            		// null判定
            		if(fixtureHalfResult == null) {
            			// 情報なし
            			responseHtml = responseHtml + "<td>情報なし</td>";
            		}else {
                		responseHtml = responseHtml + "<td>" + fixtureHalfResult.getGoalsHomeTeam() + "-"+ fixtureHalfResult.getGoalsAwayTeam()+"</td>";
            		}
        		}
        		// 行終了タグ
        		responseHtml = responseHtml + "</tr>";
        	}
    		// tableタグ閉じ
        	responseHtml = responseHtml + "</table>";
        	return responseHtml;
    	}
    }

}