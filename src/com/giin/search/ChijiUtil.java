

package com.giin.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wikipedia.util.WikipediaConstants;
import com.wikipedia.util.WikipediaUtil;

public class ChijiUtil {
	/*
     *都道府県知事の一覧を抽出するメソッド
    */
    public static List<ChijiBean> getChijiIchiran(String chijiWiki) {
    	// 改行を取り除く
    	String chijiTarget = chijiWiki.replaceAll("\n" , " ");
    	// 全角ハイフンを半角ハイフンへ置き換え
    	chijiTarget = chijiTarget.replace(GiinConstants.ZENKAKU_HAIFUN , WikipediaConstants.WIKIPEDIA_HAIFUN);
    	// 全角スペースを半角へ
    	chijiTarget = chijiTarget.replace(GiinConstants.ZENKAKU_SPACE, WikipediaConstants.WIKIPEDIA_SPACE);
    	// 全角括弧を半角へ
    	chijiTarget = chijiTarget.replace(GiinConstants.ZENKAKU_KAKKO_START, WikipediaConstants.WIKITABLE_KAKKO_START).replace(GiinConstants.ZENKAKU_KAKKO_END, WikipediaConstants.WIKITABLE_KAKKO_END);
    	// スペースしか含まれないブロックを削除
    	chijiTarget = chijiTarget.replace(WikipediaConstants.VERTICAL_BAR + WikipediaConstants.WIKIPEDIA_SPACE + WikipediaConstants.VERTICAL_BAR, WikipediaConstants.VERTICAL_BAR);
    	// ハイフンとスペースしか含まれないブロックを削除
    	chijiTarget = chijiTarget.replace(WikipediaConstants.VERTICAL_BAR + WikipediaConstants.WIKIPEDIA_HAIFUN + WikipediaConstants.WIKIPEDIA_SPACE + WikipediaConstants.VERTICAL_BAR, WikipediaConstants.VERTICAL_BAR);
    	
    	// 変数設定
    	List<ChijiBean> chijiList = new ArrayList<ChijiBean>();
    	int chijiBlockPos = 1;
    	String tempChijiBlock = chijiTarget;
    	String chijiVerticalBlock = null;
    	boolean todoufukenGetFlg = true;
    	boolean giinNameGetFlg = false;
    	boolean ninkiKaishiBiGetFlg = false;
    	boolean ninkiManryouBiGetFlg = false;
    	boolean ninkiGetFlg = false;
    	boolean koukouGetFlg = false;
    	boolean daigakuGetFlg = false;
    	boolean zensyokuGetFlg = false;
    	String tempTodoufuken = null;
    	String todoufuken = null;
    	String giinName = null;
    	String ninkiMnryouBi = null;
    	String tempYear = null;
    	String tempMonthDay = null;
    	String ninki = null;
    	String syussinKoukou = null;
    	String syussinDaigaku = null;
    	String zensyoku = null;
		// 年の正規表現パターン
		Pattern ptrnYear = Pattern.compile("\\d+" + GiinConstants.YEAR_PATTERN,Pattern.MULTILINE);
		// 月日の正規表現パターン
		Pattern ptrnDayMonth = Pattern.compile("\\d+" + GiinConstants.MONTHDAY_PATTERN ,Pattern.MULTILINE);
		// 数字の正規表現パターン
		Pattern ptrnNumber = Pattern.compile("\\d" ,Pattern.MULTILINE);
				
		
    	// 一覧の見出し位置を取得
    	int ichiranMidashiPos = WikipediaUtil.getKugiriPos (chijiTarget , GiinConstants.WORD_ICHIRAN, WikipediaConstants.WIKIPEDIA_MIDASHI , WikipediaConstants.WIKIPEDIA_MIDASHI );
    	// 知事一覧のブロックを取得
    	tempChijiBlock = WikipediaUtil.getKugiriBlock (chijiTarget.substring(ichiranMidashiPos, chijiTarget.length()) , "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
    	
    	// バーティカルバーで区切ってループ
    	while(chijiBlockPos != 0){
    		// バーティカルバーで区切る
    		chijiVerticalBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock , "", WikipediaConstants.VERTICAL_PATTERN , WikipediaConstants.VERTICAL_PATTERN);
			// バーティカルバーを取り除く
    		chijiVerticalBlock = chijiVerticalBlock.replace(WikipediaConstants.VERTICAL_BAR ,"");
			
    		// 都道府県の取得フラグ
    		if(todoufukenGetFlg){
    			for(Iterator<Entry<Integer,String >> todoufukenIterator = GiinConstants.TODOUHUKEN_MAP.entrySet().iterator(); todoufukenIterator.hasNext(); ){
    				tempTodoufuken = ((Entry<Integer,String >)todoufukenIterator.next()).getValue();
    				if(chijiVerticalBlock.indexOf(tempTodoufuken) != -1 ){
    					todoufuken = tempTodoufuken;
    					todoufukenGetFlg = false;
    					break;
    				}
    			}
    			// 都道府県を取得できた場合はcontinue
    			if(!todoufukenGetFlg){
    	            // ブロックの長さが取得位置より長い場合、次の位置を設定
    	    		if(tempChijiBlock.length() > chijiBlockPos){
    	    			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    	    			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
    	    		}else{
    	    			// 位置を0に設定
    	    			chijiBlockPos = 0;
    	    		}
    				continue;
    			}
    		}
    		
    		// Largerのキーワードがあったら議員名フラグ設定
    		if(chijiVerticalBlock.indexOf(GiinConstants.WORD_LARGER) != -1){
    			giinNameGetFlg = true;
                // ブロックの長さが取得位置より長い場合、次の位置を設定
        		if(tempChijiBlock.length() > chijiBlockPos){
        			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
        			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
        		}else{
        			// 位置を0に設定
        			chijiBlockPos = 0;
        		}
    			continue;
    		}
    		
    		// 議員名の取得フラグ
    		if(giinNameGetFlg){
    			// スペースを取り除く
    			giinName = chijiVerticalBlock.replace(WikipediaConstants.WIKIPEDIA_SPACE ,"");
    			// 中括弧を取り除く
    			giinName = giinName.replace(WikipediaConstants.WIKIPEDELIA_CHUKAKKO_END ,"");
    			// URL付与
    			giinName = GiinUtil.getGiinNameEdited( WikipediaUtil.getWikiTableCell(giinName) );

    			giinNameGetFlg = false;
    			// 任期開始日取得フラグをtrueへ
    			ninkiKaishiBiGetFlg= true;
                // ブロックの長さが取得位置より長い場合、次の位置を設定
        		if(tempChijiBlock.length() > chijiBlockPos){
        			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
        			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
        		}else{
        			// 位置を0に設定
        			chijiBlockPos = 0;
        		}
    			continue;
    		}
    		
    		// 任期開始日の取得フラグ
    		if(ninkiKaishiBiGetFlg){
    			// 月日が含まれる場合
    			if(WikipediaUtil.getKugiriPos (chijiVerticalBlock , "", "", GiinConstants.MONTHDAY_PATTERN) != 0){
    				ninkiKaishiBiGetFlg = false;
    				// 任期満了日フラグをtrueに
    				ninkiManryouBiGetFlg = true;
    	            // ブロックの長さが取得位置より長い場合、次の位置を設定
    	    		if(tempChijiBlock.length() > chijiBlockPos){
    	    			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    	    			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
    	    		}else{
    	    			// 位置を0に設定
    	    			chijiBlockPos = 0;
    	    		}
    				continue;
    			}
    		}
    		
    		// 任期満了日の取得フラグ
    		if(ninkiManryouBiGetFlg){
    			
    			//年が含まれる場合
    			if(WikipediaUtil.getKugiriPos (chijiVerticalBlock , "", "", GiinConstants.YEAR_PATTERN) != 0){
                    //年の正規表現にマッチ
    	    		Matcher matcherYear = ptrnYear.matcher(chijiVerticalBlock);
    	    		matcherYear.find();
    	    		tempYear = chijiVerticalBlock.substring(matcherYear.start() ,matcherYear.end());
    	            // ブロックの長さが取得位置より長い場合、次の位置を設定
    	    		if(tempChijiBlock.length() > chijiBlockPos){
    	    			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    	    			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
    	    		}else{
    	    			// 位置を0に設定
    	    			chijiBlockPos = 0;
    	    		}
    				continue;
    			}
    			// 月日が含まれる場合
    			if(WikipediaUtil.getKugiriPos (chijiVerticalBlock , "", "", GiinConstants.MONTHDAY_PATTERN) != 0){
    				ninkiManryouBiGetFlg = false;

    	    		// 月日の正規表現にマッチ
    	     		Matcher matcherDayMonth  = ptrnDayMonth.matcher(chijiVerticalBlock);
    	     		matcherDayMonth.find();
    	    		tempMonthDay = chijiVerticalBlock.substring(matcherDayMonth.start() ,matcherDayMonth.end());
    				
    	    		ninkiMnryouBi = tempYear + tempMonthDay;
    	    		// 任期取得フラグをtrue
    	    		ninkiGetFlg = true;
    	            // ブロックの長さが取得位置より長い場合、次の位置を設定
    	    		if(tempChijiBlock.length() > chijiBlockPos){
    	    			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    	    			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
    	    		}else{
    	    			// 位置を0に設定
    	    			chijiBlockPos = 0;
    	    		}
    				continue;
    			}
    			
    		}
    		
    		// 任期取得フラグ
    		if(ninkiGetFlg){
    			// 数字の正規表現判定
    			Matcher matcherNumber = ptrnNumber.matcher(chijiVerticalBlock);
    			if(matcherNumber.find()){
    				ninkiGetFlg = false;
    				ninki = chijiVerticalBlock.replace(WikipediaConstants.WIKIPEDIA_SPACE ,"");
    				// 高校取得フラグをtrue
    				koukouGetFlg = true;
    				// 出身高校を初期化
    				syussinKoukou = "";
    	            // ブロックの長さが取得位置より長い場合、次の位置を設定
    	    		if(tempChijiBlock.length() > chijiBlockPos){
    	    			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    	    			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
    	    		}else{
    	    			// 位置を0に設定
    	    			chijiBlockPos = 0;
    	    		}
    				continue;
    			}
    		}
    		
    		// 高校取得フラグ
    		if(koukouGetFlg){
    			// スペースを取り除く
    			chijiVerticalBlock = chijiVerticalBlock.replace(WikipediaConstants.WIKIPEDIA_SPACE ,"");
    			// 最後の2文字がwikitableのエンドセル
    			if(chijiVerticalBlock.length() > 2 && chijiVerticalBlock.substring(chijiVerticalBlock.length() - 2 ,chijiVerticalBlock.length()).equals(WikipediaConstants.WIKITABLE_CELL_END) ){
    				koukouGetFlg = false;
        			// 大学取得フラグをtrue
        			daigakuGetFlg = true;
    				// 出身高校の文字列を結合
    				syussinKoukou = syussinKoukou + GiinConstants.SEITOU_KUGIRI + WikipediaUtil.getWikiTableCell(chijiVerticalBlock);
    			}else{
    				// 中退の文字列を含まない場合
                    if(chijiVerticalBlock.indexOf(GiinConstants.WORD_CHUUTAI) == -1){
            			syussinKoukou = WikipediaUtil.getWikiTableCell(chijiVerticalBlock);
                    }
                    // wikitableセルが含まれない場合
                    if(chijiVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_START) == -1){
        				koukouGetFlg = false;
            			// 大学取得フラグをtrue
            			daigakuGetFlg = true;
                    }
    			}
                // ブロックの長さが取得位置より長い場合、次の位置を設定
        		if(tempChijiBlock.length() > chijiBlockPos){
        			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
        			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
        		}else{
        			// 位置を0に設定
        			chijiBlockPos = 0;
        		}
				continue;
    		}
    		
    		// 大学取得フラグ
    		if(daigakuGetFlg){
    			daigakuGetFlg = false;
    			// スペースを取り除く
    			chijiVerticalBlock = chijiVerticalBlock.replace(WikipediaConstants.WIKIPEDIA_SPACE ,"");
    			syussinDaigaku = WikipediaUtil.getWikiTableCell(chijiVerticalBlock);
    			// 前職取得フラグをtrue
    			zensyokuGetFlg = true;
                // ブロックの長さが取得位置より長い場合、次の位置を設定
        		if(tempChijiBlock.length() > chijiBlockPos){
        			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
        			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
        		}else{
        			// 位置を0に設定
        			chijiBlockPos = 0;
        		}
    			continue;
    		}
    		
    		// 前職取得フラグ
    		if(zensyokuGetFlg){
    			zensyokuGetFlg = false;
    			// スペースを取り除く
    			chijiVerticalBlock = chijiVerticalBlock.replace(WikipediaConstants.WIKIPEDIA_SPACE ,"");
    			zensyoku = WikipediaUtil.getWikiTableCell(chijiVerticalBlock);
    			// 都道府県取得フラグをtrue
    			todoufukenGetFlg = true;
    			
    			// Beanにセット
    			ChijiBean chijiBean = new ChijiBean();
    			chijiBean.setTodouhuken(todoufuken);
    			chijiBean.setGiinName(giinName);
    			chijiBean.setNinkiManryoubi(ninkiMnryouBi);
    			chijiBean.setNinki(ninki);
    			chijiBean.setSyussinKoukou(syussinKoukou);
    			chijiBean.setSyussinDaigaku(syussinDaigaku);
    			chijiBean.setZensyoku(zensyoku);
    			chijiList.add(chijiBean);
    		}
    		
            // ブロックの長さが取得位置より長い場合、次の位置を設定
    		if(tempChijiBlock.length() > chijiBlockPos){
    			chijiBlockPos = WikipediaUtil.getKugiriPos (tempChijiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    			tempChijiBlock = WikipediaUtil.getKugiriBlock (tempChijiBlock.substring(chijiBlockPos, tempChijiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKIPEDIA_MIDASHI + WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.WORD_KEIKOU);
    		}else{
    			// 位置を0に設定
    			chijiBlockPos = 0;
    		}
    	}
    	return chijiList;
    }
    
    
}
