

package com.twitter.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUtil {

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
     *記号かどうかを判定するメソッド
    */
    public static boolean checkKigo(String str){
    	  Pattern p = Pattern.compile("^[\\p{Punct}]*$");
    	  Matcher m = p.matcher(str);
    	  return m.find();
    	}
    /*
     *twitterにアクセスし検索結果を取得
    */
    public static TwitterResult getTwitterResult(String searchWord,String datteFrom,String dateTo, HttpServletRequest request) {
    	try {
    	   // リクエストからコンテキストを取得
           ServletContext context = request.getSession().getServletContext();
		   // tokenの生成
		   ConfigurationBuilder builder = new ConfigurationBuilder();
		   builder.setOAuthConsumerKey(context.getInitParameter("TWITTER_CONSUMER_KEY"));
		   builder.setOAuthConsumerSecret(context.getInitParameter("TWITTER_SECRET_KEY"));
		   builder.setApplicationOnlyAuthEnabled(true);
		   OAuth2Token token = new TwitterFactory(builder.build()).getInstance().getOAuth2Token();
		   // twitterインスタンスの生成   		   
		   ConfigurationBuilder cb = new ConfigurationBuilder();
	       cb.setApplicationOnlyAuthEnabled(true);
		   Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		   // jdk1.7対応用
	       twitter.setOAuthConsumer(context.getInitParameter("TWITTER_CONSUMER_KEY"), context.getInitParameter("TWITTER_SECRET_KEY"));
	       //tokenの設定
	       twitter.setOAuth2Token(token);
	       // Queryの生成
	       Query query = new Query(searchWord);
	       query.setCount(100);
	       query.since(datteFrom);
	       query.until(dateTo);
	       query.setResultType(Query.RECENT);
	       // 変数定義
	       String tweetTxt = null;
	       String tweetTxtAfterEdit = null;
	       Date tweetDate = null;
	       SimpleDateFormat sdfTweetDay = new SimpleDateFormat("yyyy年MM月dd日");
	       List<TotalWork> totalWorkList = new ArrayList<TotalWork>();
	       int tweetTargetCount = 0;
	       // 1000件（10ページ）最大数
		   for (int i = 1; i <= 10; i++) {
			   // 検索実行
			   QueryResult result = twitter.search(query);
			   for (Status tweetStat : result.getTweets()) {
				   tweetTxt = tweetStat.getText();
				   tweetDate = tweetStat.getCreatedAt();
				   tweetTargetCount++;
				   // ハッシュタグとURLの削除
	    	       StringTokenizer sta = new StringTokenizer(tweetTxt, " ");
	    	       //トークンの出力
				   while(sta.hasMoreTokens()) {
					   tweetTxtAfterEdit = sta.nextToken();
						if(tweetTxtAfterEdit.indexOf("#") == -1 && tweetTxtAfterEdit.indexOf("http") == -1
								&& tweetTxtAfterEdit.indexOf("RT") == -1 && tweetTxtAfterEdit.indexOf("@") == -1){
							// 集計オブジェクトの登録
							TotalWork totalWork = new TotalWork();
							totalWork.setWordText(tweetTxtAfterEdit);
							totalWork.setWordDay(sdfTweetDay.format(tweetDate));
							totalWorkList.add(totalWork);
						}
					}
			   }
			   // 検索結果がまだある場合
			   if(result.hasNext()){
					query = result.nextQuery();
				}else{
					break;
				}
		   }
		   TwitterResult twitterResult = new TwitterResult();
		   twitterResult.setTotalWorkList(totalWorkList);
		   twitterResult.setTweetTargetCount(tweetTargetCount);
		   return twitterResult;
		   
    	 }catch(TwitterException e) {
	    		return null;
      }
    }
    /*
     *kuromojiの分解結果を取得
    */
    public static List<TotalWork> getKuromojiResult(List<TotalWork> totalWorkList) {
        // 変数設定
    	List<TotalWork> totalWorkAfterKuro = new ArrayList<TotalWork>();
	    String tokenFeature = null;
	    String featureResult = null;
	    boolean exceptFlag = false;
	    StringTokenizer sta = null;
	    Tokenizer tokenizer = Tokenizer.builder().build();
	    List<Token> tokensNormal = null;
	    // ワードのリストでループ
	    for (TotalWork inputTotalWork : totalWorkList) {
	    	// kuromojiで解析
	    	tokensNormal = tokenizer.tokenize(inputTotalWork.getWordText());
	    	for (Token token : tokensNormal) {
	    		tokenFeature = token.getAllFeatures();
	    		sta = new StringTokenizer(tokenFeature, ",");
	    	    //最初の句だけ取得
	    		featureResult = sta.nextToken();
                //除外句の判定
	    		for (String exceptFeature : TwitterConstants.EXCEPT_TARGET) {
	    			// 除外句と一致
	    			if(exceptFeature.equals(featureResult)){
	    				exceptFlag = true;
	    				break;
	    			}else{
	    				exceptFlag = false;
	    			}
	    		}
	    		// 記号を含む除外判定
	    		if(exceptFlag == false && checkKigo(token.getSurfaceForm())){
    				exceptFlag = true;
	    		}
	    		// 除外があった場合は飛ばす
	    		if (exceptFlag){
	    			break;
	    		}else{
		        	TotalWork tempTotalwork = new TotalWork();
		        	tempTotalwork.setWordText(token.getSurfaceForm());
		        	tempTotalwork.setWordDay(inputTotalWork.getWordDay());
		        	totalWorkAfterKuro.add(tempTotalwork);
		        	// test用
		        	//System.out.println(token.getSurfaceForm());
		        	//System.out.println(token.getAllFeatures());
	    		}
	    	}
	    }
	    return totalWorkAfterKuro;
    }
    
    /*
     *WordTotalのコレクションクラス
    */
    private static final class WordTotalComp implements Comparator<WordTotal> {
        @Override
	    public int compare(WordTotal w1, WordTotal w2) {
	        return Integer.compare(w1.getWordCount(), w2.getWordCount());
   	    }

    }
    
    /*
     *kuromojiの分解結果を語句毎に集計
    */
    public static List<WordTotal> getWordTotalList(List<TotalWork> totalWorkAfterKuro ,String searchWord) {
        // 変数設定
    	List<WordTotal> wordTotalResult = new ArrayList<WordTotal>();
    	boolean searchResultFlg = false;
	    // kuromojiの分解結果でループ
	    for (TotalWork inputTotalWork : totalWorkAfterKuro) {
	    	searchResultFlg = false;
	    	// リストのキーワードに検索キーワードを含む場合は除外
	    	if(searchWord.indexOf(inputTotalWork.getWordText()) == -1){
	    		// ワード集計リスト内の有無を確認
	    		for (WordTotal tempWordTotalResult : wordTotalResult) {
	    			// 語句の判定
	    			if(tempWordTotalResult.getWordText().equals(inputTotalWork.getWordText())){
	    				// カウントを1追加する
	    				tempWordTotalResult.setWordCount(tempWordTotalResult.getWordCount() + 1);
	    				searchResultFlg = true;
	    				break;
	    			}
	    			
	    		}
	    		// リストにない場合新規追加
	    		if(!searchResultFlg){
		    		WordTotal addWordTotal = new WordTotal();
		    		addWordTotal.setWordText(inputTotalWork.getWordText());
		    		addWordTotal.setWordCount(1);
		    		wordTotalResult.add(addWordTotal);
	    		}

	    	}
	    }
	    // 件数判定の上返却
	    if(wordTotalResult.size() == 0){
	    	return null;
	    }else{
	    	// ソート
	    	Collections.sort(wordTotalResult, new WordTotalComp());
	    	Collections.reverse(wordTotalResult);
	    	return wordTotalResult;
	    }
    
    }

    /*
     *DayTotalのコレクションクラス
    */
    private static final class DayTotalComp implements Comparator<DayTotal> {
        @Override
	    public int compare(DayTotal w1, DayTotal w2) {
	        return Integer.compare(w1.getWordCount(), w2.getWordCount());
   	    }

    }
    
    /*
     *DayTotalSummaryのコレクションクラス
    */
    private static final class DayTotalSummaryComp implements Comparator<DayTotalSummary> {
    	
        @Override
	    public int compare(DayTotalSummary w1, DayTotalSummary w2) {
        	// String→Date型の変換フォーマット
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        	Date date1 = null;
        	Date date2 = null;
        	// 型変換
        	try{
            	date1 = sdf.parse(w1.getSummaryDay());
            	date2 = sdf.parse(w2.getSummaryDay());
        	}catch(Exception e){
        		// 例外発生時はスルー(nullで落とす)
        	}
	        return date1.compareTo(date2);
   	    }
    }
    
    /*
     *kuromojiの分解結果を語句・日付毎に集計
    */
    public static List<DayTotalSummary> getDayTotalList(List<TotalWork> totalWorkAfterKuro ,String searchWord) {
        // 変数設定
    	List<DayTotalSummary> dayTotalSummaryList = new ArrayList<DayTotalSummary>();
    	boolean dayResultFlg = false;
    	boolean wordResultFlg = false;
    	List<DayTotal> dayTotalList = null;
	    // kuromojiの分解結果でループ
	    for (TotalWork inputTotalWork : totalWorkAfterKuro) {
	    	dayResultFlg = false;
	    	// リストのキーワードに検索キーワードを含む場合は除外
	    	if(searchWord.indexOf(inputTotalWork.getWordText()) == -1){
	    		// 日付集計リスト内の有無を確認
	    		for (DayTotalSummary tempDayTotalSummary : dayTotalSummaryList) {
	    			// 日付の同一判定
	    			if(tempDayTotalSummary.getSummaryDay().equals(inputTotalWork.getWordDay())){
	    				// 日付が見つかった場合
	    				dayResultFlg = true;
	    				// ワードフラグを初期化
	    				wordResultFlg = false;
		    			dayTotalList = tempDayTotalSummary.getDayTotalArrayList();
		    		    // さらに日付毎のリストでループ
		    			for (DayTotal tempDayTotalResult : dayTotalList) {
		    				if(tempDayTotalResult.getWordText().equals(inputTotalWork.getWordText())){
		    					// ワードが見つかった場合
		    					wordResultFlg = true;
			    				// カウントを1追加する
		    					tempDayTotalResult.setWordCount(tempDayTotalResult.getWordCount() + 1);
		    					// ワードのループ終了
		    					break;
		    				}
		    				
		    			}
	    	    		// ワードが見つからなかった場合
	    	    		if(!wordResultFlg){
	    	    			// ワードオブジェクトの新規作成
	    		    		DayTotal dayTotalAdd = new DayTotal();
	    		    		dayTotalAdd.setWordText(inputTotalWork.getWordText());
	    		    		dayTotalAdd.setWordCount(1);
	    		    		dayTotalAdd.setWordDay(inputTotalWork.getWordDay());
	    		    		dayTotalList.add(dayTotalAdd);
	    		    		// 日付オブジェクトの件数更新
	    		    		tempDayTotalSummary.setDayCount(tempDayTotalSummary.getDayCount() + 1);
	    	    		}
		    			// 日付のループ終了
		    			break;
	    			}
	    		}
	    		// 日付が見つからなかった場合
	    		if(!dayResultFlg){
	    			// 日付オブジェクトの新規作成
	    			DayTotalSummary dayTotalSummaryAdd = new DayTotalSummary();
	    			dayTotalSummaryAdd.setWordDay(inputTotalWork.getWordDay());
	    			dayTotalSummaryAdd.setDayCount(1);
	    			// ワードオブジェクトの新規作成
		    		DayTotal dayTotalAdd = new DayTotal();
		    		dayTotalAdd.setWordText(inputTotalWork.getWordText());
		    		dayTotalAdd.setWordCount(1);
		    		dayTotalAdd.setWordDay(inputTotalWork.getWordDay());
		    		// 日付オブジェクトへリスト追加
		    		List<DayTotal> dayTotalListAdd = new ArrayList<DayTotal>();
		    		dayTotalListAdd.add(dayTotalAdd);
		    		dayTotalSummaryAdd.setDayTotal(dayTotalListAdd);
		    		dayTotalSummaryList.add(dayTotalSummaryAdd);
	    		}
	    	}
	    }
	    // 件数判定の上返却
	    if(dayTotalSummaryList.size() == 0){
	    	return null;
	    }else{
	    	// ワードリストのソート
	    	for (DayTotalSummary sortDayTotalSummary : dayTotalSummaryList) {
		    	// ソート
		    	Collections.sort(sortDayTotalSummary.getDayTotalArrayList(), new DayTotalComp());
		    	Collections.reverse(sortDayTotalSummary.getDayTotalArrayList());
	    	}
	    	// 日付リストのソート
	    	Collections.sort(dayTotalSummaryList, new DayTotalSummaryComp());
	    	Collections.reverse(dayTotalSummaryList);
	    	return dayTotalSummaryList;
	    }
    }	
    
}
