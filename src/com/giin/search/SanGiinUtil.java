

package com.giin.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wikipedia.util.WikipediaConstants;
import com.wikipedia.util.WikipediaUtil;

public class SanGiinUtil {
	/*
     *参議院の任期を抽出するメソッド
    */
    public static List<String> getSangiinNinki(String sangiinWiki) {
    	List<String> sangiinNinki = new ArrayList<String>();
    	// 改行を取り除く
    	String sangiinTarget = sangiinWiki.replaceAll("\n" , " ");
    	// 全角括弧を半角へ
    	sangiinTarget = sangiinTarget.replace(GiinConstants.ZENKAKU_KAKKO_START, WikipediaConstants.WIKITABLE_KAKKO_START).replace(GiinConstants.ZENKAKU_KAKKO_END, WikipediaConstants.WIKITABLE_KAKKO_END);
    	// スペースしか含まれないブロックを削除
    	sangiinTarget = sangiinTarget.replace(WikipediaConstants.VERTICAL_BAR + WikipediaConstants.WIKIPEDIA_SPACE + WikipediaConstants.VERTICAL_BAR, WikipediaConstants.VERTICAL_BAR);
    	// 比例の見出し位置を取得
    	int hireiMidashiEndPos = WikipediaUtil.getKugiriPos (sangiinTarget , WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.HIREI_SENSYUTSU_GIIN +WikipediaConstants.WIKIPEDIA_SPACE, WikipediaConstants.WIKIPEDIA_MIDASHI , WikipediaConstants.WIKIPEDIA_MIDASHI );
    	// 任期が含むブロックを取得
    	String ninkiBlock = WikipediaUtil.getKugiriBlock (sangiinTarget.substring(hireiMidashiEndPos, sangiinTarget.length()) , GiinConstants.NINKI_MANRYOU, WikipediaConstants.WIKITABLE_START , WikipediaConstants.WIKITABLE_END + WikipediaConstants.WIKITABLE_END);
    	// 変数設定
    	int ninkiPos = 1;
    	String ninkiVerticalBlock = null;
    	String tempNinkiBlock = ninkiBlock;
    	String tempYear = null;
    	String tempMonthDay = null;
		// 年の正規表現パターン
		Pattern ptrnYear = Pattern.compile("\\d+" + GiinConstants.YEAR_PATTERN,Pattern.MULTILINE);
		// 月日の正規表現パターン
		Pattern ptrnDayMonth = Pattern.compile("\\d+" + GiinConstants.MONTHDAY_PATTERN ,Pattern.MULTILINE);
    	// 任期取得を2回ループ
    	for(int i = 0; i < 2 ; i++){
    		// バーティカルバーで区切る
    		ninkiVerticalBlock = WikipediaUtil.getKugiriBlock (tempNinkiBlock , GiinConstants.NINKI_MANRYOU, WikipediaConstants.VERTICAL_PATTERN , WikipediaConstants.VERTICAL_PATTERN);
    		// 年のブロックを抽出
    		tempYear = WikipediaUtil.getKugiriBlock (ninkiVerticalBlock , GiinConstants.YEAR_PATTERN, WikipediaConstants.VERTICAL_PATTERN , WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE);
            // 正規表現にマッチ
    		Matcher matcherYear = ptrnYear.matcher(tempYear);
    		matcherYear.find();
    		tempYear = tempYear.substring(matcherYear.start() ,matcherYear.end());
    		// 月日を抽出
    		tempMonthDay = WikipediaUtil.getKugiriBlock (ninkiVerticalBlock , GiinConstants.MONTHDAY_PATTERN, WikipediaConstants.WIKITABLE_KAKKO_END_ESCAPE , WikipediaConstants.VERTICAL_PATTERN);
            // 正規表現にマッチ
     		Matcher matcherDayMonth  = ptrnDayMonth.matcher(tempMonthDay);
     		matcherDayMonth.find();
    		tempMonthDay = tempMonthDay.substring(matcherDayMonth.start() ,matcherDayMonth.end());
    		// リストにセット
    		sangiinNinki.add(tempYear + tempMonthDay);
    		// 次のブロック
    		ninkiPos = WikipediaUtil.getKugiriPos  (sangiinTarget , WikipediaConstants.WIKITABLE_START, WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.HIREI_SENSYUTSU_GIIN +WikipediaConstants.WIKIPEDIA_SPACE , WikipediaConstants.WIKITABLE_END);
    		tempNinkiBlock = WikipediaUtil.getKugiriBlock (sangiinTarget.substring(ninkiPos, sangiinTarget.length()),GiinConstants.NINKI_MANRYOU, "", WikipediaConstants.WIKITABLE_END + WikipediaConstants.WIKITABLE_END);
    	}
    	
    	return sangiinNinki;
    }
    
	/*
     *参議院の一覧を抽出するメソッド
    */
    public static List<SangiinBean> getSangiinIchiran(String sangiinWiki, List<String> sangiinNinkiList) {
    	// 改行を取り除く
    	String sangiinTarget = sangiinWiki.replaceAll("\n" , " ");
    	// 全角ハイフンを半角ハイフンへ置き換え
    	sangiinTarget = sangiinTarget.replace(GiinConstants.ZENKAKU_HAIFUN , WikipediaConstants.WIKIPEDIA_HAIFUN);
    	// 全角スペースを半角へ
    	sangiinTarget = sangiinTarget.replace(GiinConstants.ZENKAKU_SPACE, WikipediaConstants.WIKIPEDIA_SPACE);
    	// 全角括弧を半角へ
    	sangiinTarget = sangiinTarget.replace(GiinConstants.ZENKAKU_KAKKO_START, WikipediaConstants.WIKITABLE_KAKKO_START).replace(GiinConstants.ZENKAKU_KAKKO_END, WikipediaConstants.WIKITABLE_KAKKO_END);
    	// スペースしか含まれないブロックを削除
    	sangiinTarget = sangiinTarget.replace(WikipediaConstants.VERTICAL_BAR + WikipediaConstants.WIKIPEDIA_SPACE + WikipediaConstants.VERTICAL_BAR, WikipediaConstants.VERTICAL_BAR);
    	// ハイフンとスペースしか含まれないブロックを削除
    	sangiinTarget = sangiinTarget.replace(WikipediaConstants.VERTICAL_BAR + WikipediaConstants.WIKIPEDIA_HAIFUN + WikipediaConstants.WIKIPEDIA_SPACE + WikipediaConstants.VERTICAL_BAR, WikipediaConstants.VERTICAL_BAR);
    	
    	// 変数設定
    	List<SangiinBean> sangiinList = new ArrayList<SangiinBean>();
    	int sangiinBlockPos = 0;
    	String tempSangiinBlock = sangiinTarget;
    	
    	// 政党の一覧を取得
    	List<String> seitouList = getSangiinSeitouList(sangiinTarget);
    	// 選挙区のブロックを取得
    	sangiinBlockPos =  WikipediaUtil.getKugiriPos (sangiinTarget , WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.SENKYOKU_SENSYUTSU_GIIN + WikipediaConstants.WIKIPEDIA_SPACE, WikipediaConstants.WIKIPEDIA_MIDASHI , WikipediaConstants.WIKIPEDIA_MIDASHI );
    	tempSangiinBlock = WikipediaUtil.getKugiriBlock (tempSangiinBlock.substring(sangiinBlockPos, tempSangiinBlock.length()) , "", WikipediaConstants.WIKITABLE_START , WikipediaConstants.WIKITABLE_END);
    	// 選挙区の議員をリストに追加
    	addSangiinSenkyokuList(sangiinList , seitouList, sangiinNinkiList, tempSangiinBlock);
    	// 比例の見出し位置を取得
    	sangiinBlockPos = WikipediaUtil.getKugiriPos (sangiinTarget , WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.HIREI_SENSYUTSU_GIIN +WikipediaConstants.WIKIPEDIA_SPACE, WikipediaConstants.WIKIPEDIA_MIDASHI , WikipediaConstants.WIKIPEDIA_MIDASHI );
    	// 任期が含むブロックを取得
    	tempSangiinBlock = WikipediaUtil.getKugiriBlock (sangiinTarget.substring(sangiinBlockPos, sangiinTarget.length()) , GiinConstants.NINKI_MANRYOU, WikipediaConstants.WIKITABLE_START , GiinConstants.WORD_KAISEN);
    	// 1ブロック目の比例議員を追加
    	addSangiinHireiList(sangiinList , seitouList, sangiinNinkiList, tempSangiinBlock);
    	// 比例区の2ブロック目を取得
    	sangiinBlockPos = WikipediaUtil.getKugiriPos  (sangiinTarget , WikipediaConstants.WIKITABLE_START, WikipediaConstants.WIKIPEDIA_SPACE + GiinConstants.HIREI_SENSYUTSU_GIIN +WikipediaConstants.WIKIPEDIA_SPACE , GiinConstants.WORD_KAISEN);
    	tempSangiinBlock = WikipediaUtil.getKugiriBlock (sangiinTarget.substring(sangiinBlockPos, sangiinTarget.length()) , "", WikipediaConstants.WIKITABLE_START , GiinConstants.WORD_KAISEN);
    	// 2ブロック目の比例議員を追加
    	addSangiinHireiList(sangiinList , seitouList, sangiinNinkiList, tempSangiinBlock);
    	
    	return sangiinList;
    }
    
    
	/*
     *参議院政党の一覧を抽出するメソッド
    */
    public static List<String> getSangiinSeitouList(String sangiinTarget) {
    	List<String> seitouList = new ArrayList<String>();
    	// 会派別の見出し位置を取得
    	int kaihabetuSyozokuEndPos = WikipediaUtil.getKugiriPos (sangiinTarget , GiinConstants.KAIHABETU_SYOZOKU, WikipediaConstants.WIKIPEDIA_MIDASHI , WikipediaConstants.WIKIPEDIA_MIDASHI );
    	// 政党のブロック取得
    	String seitouBlock = WikipediaUtil.getKugiriBlock (sangiinTarget.substring(kaihabetuSyozokuEndPos, sangiinTarget.length()) , GiinConstants.NINZUU_MEI, WikipediaConstants.WIKITABLE_START , WikipediaConstants.WIKITABLE_END);
    	// 変数設定
    	int seitouPos = 1;
    	String tempSeitouBlock = seitouBlock;
    	String seitouVerticalBlock = null;
    	String seitouName = null;
    	String[] tempSetouArray;
    	// バーティカルバーで区切ってループ
    	while(seitouPos != 0){
    		// バーティカルバーで区切る
    		seitouVerticalBlock = WikipediaUtil.getKugiriBlock (tempSeitouBlock , "", WikipediaConstants.VERTICAL_PATTERN , WikipediaConstants.VERTICAL_PATTERN);
    		// 政党名が含まれるか判定
    		if(seitouVerticalBlock.indexOf(GiinConstants.NINZUU_MEI) != -1 ||
    		   seitouVerticalBlock.indexOf(GiinConstants.NINZUU_NIN) != -1 ||
    		   seitouVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_START) != -1 ||
    		   seitouVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_END) != -1 ||
    		   seitouVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_MIDASHI) != -1){
    			// 区切り文字が含まれている場合
    			if(seitouVerticalBlock.indexOf(GiinConstants.SEITOU_KUGIRI) != -1 || seitouVerticalBlock.indexOf(GiinConstants.ZENKAKU_KUTEN) != -1 ){
    				if(seitouVerticalBlock.indexOf(GiinConstants.SEITOU_KUGIRI) != -1){
        				tempSetouArray = seitouVerticalBlock.split(GiinConstants.SEITOU_KUGIRI);
    				}else{
        				tempSetouArray = seitouVerticalBlock.split(GiinConstants.ZENKAKU_KUTEN);
    				}
    				for(String tempSeitouName : tempSetouArray){
    					seitouName = getSeitouEdited(tempSeitouName);
        				// リストに含まれているか判断
        				if(!isSeitouListRegistered(seitouName ,seitouList)){
        					seitouList.add(seitouName);
        				}
    				}
    			}else{
    				seitouName = getSeitouEdited(seitouVerticalBlock);
    				// リストに含まれているか判断
    				if(!isSeitouListRegistered(seitouName ,seitouList)){
    					seitouList.add(seitouName);
    				}
    			}
    		}
    		
            // ブロックの長さが取得位置より長い場合、次の位置を設定
    		if(tempSeitouBlock.length() > seitouPos){
    			seitouPos = WikipediaUtil.getKugiriPos (tempSeitouBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    			tempSeitouBlock = WikipediaUtil.getKugiriBlock (tempSeitouBlock.substring(seitouPos, tempSeitouBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKITABLE_END);
    		}else{
    			// 位置を0に設定
    			seitouPos = 0;
    		}
    	}
    	
    	
    	return seitouList;
    }
    
	/*
     *参議院政党の名称を編集するメソッド
    */
    public static String getSeitouEdited(String seitouBlock) {
    	String seitouName = seitouBlock;
		// 数字の正規表現パターン
		Pattern ptrn = Pattern.compile("\\d", Pattern.MULTILINE);
		// 括弧の正規表現パターン
		Pattern ptrKkakko = Pattern.compile(WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE, Pattern.MULTILINE);
    	// 括弧が含まれる場合
    	if(seitouName.indexOf(WikipediaConstants.WIKITABLE_KAKKO_START) != -1){
    		// 括弧が含まれる位置を取得
    		Matcher matcher = ptrKkakko.matcher(seitouName);
    		if(matcher.find()){
        		// 括弧の前まで文字列取得
        		seitouName = seitouName.substring(0 , matcher.start());
    		}
    	}
    	// 人数が含まれる場合
    	if(seitouName.indexOf(GiinConstants.NINZUU_MEI) != -1 || seitouName.indexOf(GiinConstants.NINZUU_NIN) != -1){
    		// 数字が含まれる位置を取得
    		Matcher matcher = ptrn.matcher(seitouName);
    		if(matcher.find()){
        		// 数字の前まで文字列取得
        		seitouName = seitouName.substring(0 , matcher.start());
    		}
    	}
    	// 見出しを除去
    	seitouName = seitouName.replace(WikipediaConstants.WIKITABLE_MIDASHI ,"");
    	// バーティカルバーを除去
    	seitouName = seitouName.replace(WikipediaConstants.VERTICAL_BAR ,"");
    	// スペースを除去
    	seitouName = seitouName.replace(WikipediaConstants.WIKIPEDIA_SPACE ,"");
    	// BRタグを除去
    	seitouName = seitouName.replace(WikipediaConstants.WIKIPEDELIA_BR_TAG ,"");
    	seitouName = seitouName.replace(WikipediaConstants.WIKIPEDELIA_BR_TAG_WITHSPACE ,"");
    	// wikitableのセルを除去
    	seitouName =  WikipediaUtil.getWikiTableCell (seitouName) ;
    	// シングルコーテーションを除去
    	seitouName = seitouName.replace(WikipediaConstants.WIKIPEDIA_SINGLE_QUOT ,"");
    	
    	return seitouName;
    }

	/*
     *参議院政党の名称を議員のブロックから取得するメソッド
    */
    public static String getSeitouFromBlock(String sangiinBlock ,List<String> seitouList) {
    	String seitouName = "";
    	String seitouNameSingle = "";
    	String[] tempSetouArray;
		// 区切り文字が含まれている場合
		if(sangiinBlock.indexOf(GiinConstants.SEITOU_KUGIRI) != -1 || sangiinBlock.indexOf(GiinConstants.ZENKAKU_KUTEN) != -1 ){
			if(sangiinBlock.indexOf(GiinConstants.SEITOU_KUGIRI) != -1){
				tempSetouArray = sangiinBlock.split(GiinConstants.SEITOU_KUGIRI);
			}else{
				tempSetouArray = sangiinBlock.split(GiinConstants.ZENKAKU_KUTEN);
			}
			for(String tempSeitouName : tempSetouArray){
				// 政党のリストでループ
				for(String tempListSeitou : seitouList){
					if(tempSeitouName.indexOf(tempListSeitou) != -1){
						seitouNameSingle = tempListSeitou;
						if(GiinUtil.isEmpty(seitouName)){
							seitouName = seitouNameSingle;
						}else{
							seitouName = seitouName + GiinConstants.SEITOU_KUGIRI + seitouNameSingle;
						}
					}
				}


			}
		}else{
			// 政党のリストでループ
			for(String tempListSeitou : seitouList){
				if(sangiinBlock.indexOf(tempListSeitou) != -1){
					seitouName = tempListSeitou;
				}
			}

		}
    	return seitouName;
    }
    
	/*
     *参議院政党がリストに登録されているか
    */
    public static boolean isSeitouListRegistered(String seitouName ,List<String> seitouList) {
    	for(String tempSeitou : seitouList){
    		if(tempSeitou.equals(seitouName)){
    			return true;
    		}
    	}
    	return false;
    }

	/*
     *文字列がリストに登録されている政党に合致するか
    */
    public static boolean isSeitouListInclude(String targetBlock ,List<String> seitouList) {
    	for(String tempSeitou : seitouList){
    		if(targetBlock.indexOf(tempSeitou) != -1){
    			return true;
    		}
    	}
    	return false;
    }
    
	/*
     *参議院議員の選挙区議員リストを追加するメソッド
    */
    public static void addSangiinSenkyokuList(List<SangiinBean> sangiinList ,List<String> seitouList, List<String> sangiinNinkiList, String senkyokuSangiinBlock) {
    	// 変数設定
    	int senkyokuPos = 1;
    	String senkyokuVerticalBlock = null;
    	String tempSenkyokuBlock = senkyokuSangiinBlock;
    	String ninkiManryoubi = null;
    	String todoufuken = null;
    	String senkyoku = null;
    	String seitouName = null;
    	boolean giinNameGetFlg = false;
    	boolean seitouSetFlg = false;
    	String tempGiinNameBlock = null;
    	String tempGiinName = null;
    	String tempSenkyoku = null;
    	// バーティカルバーで区切ってループ
    	while(senkyokuPos != 0){
    		giinNameGetFlg = true;
    		// バーティカルバーで区切る
    		senkyokuVerticalBlock = WikipediaUtil.getKugiriBlock (tempSenkyokuBlock , "", WikipediaConstants.VERTICAL_PATTERN , WikipediaConstants.VERTICAL_PATTERN);
            // 選挙区・都道府県が含まれるか判定
    		if(senkyokuVerticalBlock.indexOf(GiinConstants.WORD_KU) != -1){
        		for(Iterator<Entry<Integer,String >> todoufukenIterator = GiinConstants.TODOUHUKEN_MAP.entrySet().iterator(); todoufukenIterator.hasNext(); ){
        			tempSenkyoku = ((Entry<Integer,String >)todoufukenIterator.next()).getValue();
            		if(senkyokuVerticalBlock.indexOf(tempSenkyoku) != -1 ){
            		    // 選挙区はバーティカルバーとwikitableセルと見出しを除去
            			senkyoku = WikipediaUtil.getWikiTableCell(senkyokuVerticalBlock.replace(WikipediaConstants.VERTICAL_BAR ,"").replace(WikipediaConstants.WIKITABLE_MIDASHI ,""));
            			// 都道府県は「選挙区」を除去
            			todoufuken = senkyoku.replace(GiinConstants.WORD_SENKYOKU, "");
            			// 議員取得フラグはfalse
            			giinNameGetFlg = false;
            		}
        		}
    		}
    		
    		// 任期満了日が含まれるか判定
            for(String tempNinki : sangiinNinkiList){
            	// 任期満了日の年で判定
            	if(senkyokuVerticalBlock.indexOf(tempNinki.substring(0,4)) != -1){
            		ninkiManryoubi = tempNinki;
        			// 議員取得フラグはfalse
        			giinNameGetFlg = false;
            	}
            }
    		
    		// 政党が含まれるか判定
    		if(isSeitouListInclude(senkyokuVerticalBlock ,seitouList) ){
    			// 括弧で囲まれた内容を取得
    			seitouName = WikipediaUtil.getKugiriBlock (senkyokuVerticalBlock , "", WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE , WikipediaConstants.WIKITABLE_KAKKO_END_ESCAPE);
    			// 政党名を取得
    			seitouName = getSeitouFromBlock(seitouName, seitouList);
    			
    			//政党セットフラグがtrue
    			if(seitouSetFlg){
    				// リストにセット
    				SangiinBean sangiinBean = new SangiinBean();
    				// 選挙区分を設定
    				sangiinBean.setSenkyoKubun(GiinConstants.SYOUSENKYOKU_ID);
    				// 都道府県を設定
    				sangiinBean.setTodouhuken(todoufuken);
    				// 選挙区を設定
    				sangiinBean.setSenkyoku(senkyoku);
    				// 任期満了日を設定
    				sangiinBean.setNinkiManryoubi(ninkiManryoubi);
    				// 政党を設定
    				sangiinBean.setSeitou(seitouName);
    				// 議員名を設定
    				sangiinBean.setGiinName(tempGiinName);
    				// リストへ追加
    				sangiinList.add(sangiinBean);
    				// 政党名を初期化
    				seitouName = null;
    				// 議員取得フラグはfalse
        			giinNameGetFlg = false;
    				// 政党セットフラグをfalse
    				seitouSetFlg = false;
    			}
    		}
    		
    		// 議員名を取得
    		if(giinNameGetFlg  &&
    		   senkyokuVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_START) != -1){
    			// wikitableセルのエンドが含まれている場合
    			if(senkyokuVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_END) != -1){
    				tempGiinNameBlock = WikipediaUtil.getKugiriBlock (senkyokuVerticalBlock , "", WikipediaConstants.WIKITABLE_CELL_START_ESCAPE , WikipediaConstants.WIKITABLE_CELL_END_ESCAPE);
    			}else{
    				tempGiinNameBlock = senkyokuVerticalBlock;
    			}
    			// 議員名の編集
    			tempGiinName = tempGiinNameBlock.replace(WikipediaConstants.VERTICAL_CONSTANTS, "");
    			tempGiinName = GiinUtil.getGiinNameEdited( WikipediaUtil.getWikiTableCell(tempGiinName) );
    			// 政党名が含まれない場合かつ政党がnull出ない場合
    			if(!isSeitouListInclude(tempGiinNameBlock ,seitouList) && !GiinUtil.isEmpty(seitouName)){
    				SangiinBean sangiinBean = new SangiinBean();
    				// 選挙区分を設定
    				sangiinBean.setSenkyoKubun(GiinConstants.SYOUSENKYOKU_ID);
    				// 都道府県を設定
    				sangiinBean.setTodouhuken(todoufuken);
    				// 選挙区を設定
    				sangiinBean.setSenkyoku(senkyoku);
    				// 任期満了日を設定
    				sangiinBean.setNinkiManryoubi(ninkiManryoubi);
    				// 政党を設定
    				sangiinBean.setSeitou(seitouName);
    				// 議員名を設定
    				sangiinBean.setGiinName(tempGiinName);
    				// リストへ追加
    				sangiinList.add(sangiinBean);
    				// 政党名を初期化
    				seitouName = null;
    			}else{
    				// 政党セットフラグをtrue
    				seitouSetFlg = true;
    			}
    		}
    		
            // ブロックの長さが取得位置より長い場合、次の位置を設定
    		if(tempSenkyokuBlock.length() > senkyokuPos){
    			senkyokuPos = WikipediaUtil.getKugiriPos (tempSenkyokuBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    			tempSenkyokuBlock = WikipediaUtil.getKugiriBlock (tempSenkyokuBlock.substring(senkyokuPos, tempSenkyokuBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKITABLE_END);
    		}else{
    			// 位置を0に設定
    			senkyokuPos = 0;
    		}
    	}
    }

	/*
     *参議院議員の比例議員リストを追加するメソッド
    */
    public static void addSangiinHireiList(List<SangiinBean> sangiinList ,List<String> seitouList, List<String> sangiinNinkiList, String hireiSangiinBlock) {
    	// 変数設定
    	int hireiPos = 1;
    	String hireiVerticalBlock = null;
    	String tempHireiBlock = hireiSangiinBlock;
    	String ninkiManryoubi = null;
    	String seitouName = null;
    	String tempGiinNameBlock = null;
    	boolean giinNameGetFlg = false;
    	boolean beforSeitouFlg = false;
    	// バーティカルバーで区切ってループ
    	while(hireiPos != 0){
    		giinNameGetFlg = true;
    		// バーティカルバーで区切る
    		hireiVerticalBlock = WikipediaUtil.getKugiriBlock (tempHireiBlock , "", WikipediaConstants.VERTICAL_PATTERN , WikipediaConstants.VERTICAL_PATTERN);
    		// 任期満了日が含まれるか判定
    		if(hireiVerticalBlock.indexOf(GiinConstants.NINKI_MANRYOU) != -1){
    		   // 任期満了日の判定
               for(String tempNinki : sangiinNinkiList){
            	   // 任期満了日の年で判定
            	   if(hireiVerticalBlock.indexOf(tempNinki.substring(0,4)) != -1){
            		  ninkiManryoubi = tempNinki;
            	   }
               }
               // 議員取得フラグをfalse
               giinNameGetFlg = false;
               // 直前政党フラグをfalse
               beforSeitouFlg = false;
    	    }
    	
    		// 政党が含まれるかつ政党フラグ判定
    		if(isSeitouListInclude(hireiVerticalBlock ,seitouList) && !beforSeitouFlg){
    			// 括弧が含まれている場合
    			if(hireiVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_KAKKO_START) != -1){
    				hireiVerticalBlock = WikipediaUtil.getKugiriBlock (hireiVerticalBlock , "", "" , WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE);
    			}
    			// 政党名を取得
    			seitouName = getSeitouFromBlock(hireiVerticalBlock, seitouList);
                // 議員取得フラグをfalse
                giinNameGetFlg = false;
                // 直前政党フラグをtrue
                beforSeitouFlg = true;
    		}
    		
    		// 議員名を取得
    		if(giinNameGetFlg && 
    			hireiVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_START) != -1 && 
    			!isSeitouListInclude(hireiVerticalBlock ,seitouList)){
    			// wikitableセルのエンドが含まれている場合
    			if(hireiVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_END) != -1){
    				tempGiinNameBlock = WikipediaUtil.getKugiriBlock (hireiVerticalBlock , "", WikipediaConstants.WIKITABLE_CELL_START_ESCAPE , WikipediaConstants.WIKITABLE_CELL_END_ESCAPE);
    			}else{
    				tempGiinNameBlock = hireiVerticalBlock;
    			}
    			// 接頭字がバーティカルバーの場合
    			if(tempGiinNameBlock.substring(0 ,1 ).equals(WikipediaConstants.VERTICAL_BAR) ){
    				// 最初の1文字を取り除く
    				tempGiinNameBlock = tempGiinNameBlock.substring(1 ,tempGiinNameBlock.length());
    			}
				SangiinBean sangiinBean = new SangiinBean();
				// 選挙区分を設定
				sangiinBean.setSenkyoKubun(GiinConstants.HIREI_ID);
				// 都道府県を設定
				sangiinBean.setTodouhuken(GiinConstants.TODOUHUKEN_MAP.get(GiinConstants.HIREI_ZENKOKU_MAPID));
				// 選挙区を設定
				sangiinBean.setSenkyoku(GiinConstants.TODOUHUKEN_MAP.get(GiinConstants.HIREI_ZENKOKU_MAPID));
				// 任期満了日を設定
				sangiinBean.setNinkiManryoubi(ninkiManryoubi);
				// 政党を設定
				sangiinBean.setSeitou(seitouName);
				// 議員名を設定
				sangiinBean.setGiinName( GiinUtil.getGiinNameEdited( WikipediaUtil.getWikiTableCell(tempGiinNameBlock) ) );
				
				// リストへ追加
				sangiinList.add(sangiinBean);
	            // 直前政党フラグをfalse
	            beforSeitouFlg = false;
    		}
            // ブロックの長さが取得位置より長い場合、次の位置を設定
		    if(tempHireiBlock.length() > hireiPos){
		    	hireiPos = WikipediaUtil.getKugiriPos (tempHireiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
			    tempHireiBlock = WikipediaUtil.getKugiriBlock (tempHireiBlock.substring(hireiPos, tempHireiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, GiinConstants.WORD_KAISEN);
		    }else{
			    // 位置を0に設定
		    	hireiPos = 0;
		    }
         }
    }
}
