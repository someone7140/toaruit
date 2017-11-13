

package com.giin.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wikipedia.util.WikipediaConstants;
import com.wikipedia.util.WikipediaUtil;

public class SyuuGiinUtil {

    /*
     *衆議院の任期を抽出するメソッド
    */
    public static String getSyuugiinNinki(String syuugiinWiki) {
    	// 改行を取り除く
    	String syuugiinTarget = syuugiinWiki.replaceAll("\n" , " ");
    	// 任期の見出し位置を取得
    	int ninkiMidashiEndPos = WikipediaUtil.getKugiriPos (syuugiinTarget , GiinConstants.NINKI, WikipediaConstants.WIKIPEDIA_MIDASHI , WikipediaConstants.WIKIPEDIA_MIDASHI );
    	// 任期が含むブロックを取得
    	String ninkiBlock = WikipediaUtil.getKugiriBlock (syuugiinTarget.substring(ninkiMidashiEndPos, syuugiinTarget.length()) , GiinConstants.YEAR_PATTERN, WikipediaConstants.WIKITABLE_START , WikipediaConstants.WIKITABLE_END);
    	// 任期開始日の位置を取得
    	int ninkiKaishibiEndPos = WikipediaUtil.getKugiriPos (ninkiBlock , GiinConstants.MONTHDAY_PATTERN, WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.VERTICAL_PATTERN);
    	// 任期終了日のブロックを取得
    	String ninkiSyuuryouBlock = WikipediaUtil.getKugiriBlock (ninkiBlock.substring(ninkiKaishibiEndPos, ninkiBlock.length()), GiinConstants.MONTHDAY_PATTERN, "", WikipediaConstants.VERTICAL_PATTERN);
    	// 年を取得
    	String syuugiinNinkiYear = WikipediaUtil.getWikiTableCell( WikipediaUtil.getKugiriBlock (ninkiSyuuryouBlock, GiinConstants.YEAR_PATTERN, WikipediaConstants.WIKITABLE_CELL_START_ESCAPE, WikipediaConstants.WIKITABLE_CELL_END_ESCAPE) );
    	// 年の位置を取得
    	int ninkiYearEndPos = WikipediaUtil.getKugiriPos (ninkiSyuuryouBlock, GiinConstants.YEAR_PATTERN, WikipediaConstants.WIKITABLE_CELL_START_ESCAPE, WikipediaConstants.WIKITABLE_CELL_END_ESCAPE);
     	// 月日を取得
    	String syuugiinNinkiMonthDay = WikipediaUtil.getWikiTableCell( WikipediaUtil.getKugiriBlock (ninkiSyuuryouBlock.substring(ninkiYearEndPos, ninkiSyuuryouBlock.length()), GiinConstants.MONTHDAY_PATTERN, WikipediaConstants.WIKITABLE_CELL_START_ESCAPE, WikipediaConstants.WIKITABLE_CELL_END_ESCAPE) );
        
    	return syuugiinNinkiYear + syuugiinNinkiMonthDay;
    }
    
    /*
     *衆議院の一覧を抽出するメソッド
    */
    public static List<SyuugiinBean> getSyuugiinIchiran(String syuugiinWiki , String syuugiinNinki) {
    	// 改行を取り除く
    	String syuugiinTarget = syuugiinWiki.replaceAll("\n" , " ");
    	List<SyuugiinBean> syuugiinList = new ArrayList<SyuugiinBean>();
    	// 変数設定
    	int blockMidashiEndPos =0;
    	String todoufukenBlock = "";
    	String todoufukenName = "";
    	String senkyokuName = null;
    	String senkyokuBlock = null;
    	String tempSenkyokuBlock = null;
    	String giinName = null;
    	String seitouName = null;
    	Entry<Integer,String > todouhukenBlockEntry = null;
    	String[] tempSenkyoku;
    	int todoufukenPos = 1;
    	String syuugiinBlockBefore = GiinConstants.SYUUGIIN_BLOCK.get(0);
    	Pattern todoufukenPtrn = null;
    	Matcher todoufukenMatcher = null;
    	// ブロック毎にループを回す
    	for (String syuugiinBlock : GiinConstants.SYUUGIIN_BLOCK) {
        	// ブロックが変わる時は比例を取得
        	if(!syuugiinBlockBefore.equals(syuugiinBlock)){
        		setListSyuugiinHirei(syuugiinList, syuugiinTarget.substring(blockMidashiEndPos, syuugiinTarget.length()), syuugiinBlockBefore, syuugiinNinki);
        	}
        	// ブロックの見出し位置を取得
        	blockMidashiEndPos = WikipediaUtil.getKugiriPos (syuugiinTarget , syuugiinBlock, WikipediaConstants.WIKIPEDIA_MIDASHI , WikipediaConstants.WIKIPEDIA_MIDASHI );
            // 地方ブロックの県でループ
        	for(Iterator<Entry<Integer,String >> blockIterator = GiinConstants.TODOUHUKEN_BLOCK_MAP.entrySet().iterator(); blockIterator.hasNext(); ) {
        		todouhukenBlockEntry = (Entry<Integer,String >)blockIterator.next();
        		// 見出しのブロックと比較
        		if(syuugiinBlock.equals(todouhukenBlockEntry.getValue())){
        			// 都道府県のMAPからKEYで値を取得
        			todoufukenName = GiinConstants.TODOUHUKEN_MAP.get(todouhukenBlockEntry.getKey());
        	    	// 該当の都道府県が含まれる小選挙区ブロックを取得
        			todoufukenBlock = WikipediaUtil.getKugiriBlock (syuugiinTarget.substring(blockMidashiEndPos, syuugiinTarget.length()) , todoufukenName, WikipediaConstants.WIKITABLE_START , WikipediaConstants.WIKITABLE_END);
                    // 都道府県の位置と検索文字列初期化
        			todoufukenPos = 1;
        			// 全角括弧を半角括弧に変換して代入
        			senkyokuBlock = todoufukenBlock.replace(GiinConstants.ZENKAKU_KAKKO_START, WikipediaConstants.WIKITABLE_KAKKO_START).replace(GiinConstants.ZENKAKU_KAKKO_END, WikipediaConstants.WIKITABLE_KAKKO_END);
        			tempSenkyokuBlock = senkyokuBlock;
        			// 都道府県の位置が取得できるまでループ
        			while(todoufukenPos != 0){
        				// 選挙区の位置とセルを取得
        				todoufukenPtrn =  Pattern.compile(WikipediaConstants.WIKITABLE_CELL_START_ESCAPE + todoufukenName + ".*?" +WikipediaConstants.WIKITABLE_CELL_END_ESCAPE ,Pattern.MULTILINE);
        				todoufukenMatcher = todoufukenPtrn.matcher(tempSenkyokuBlock);
        				todoufukenPos = 0;
        		    	
        		    	// 正規表現の検索
        		    	while(todoufukenMatcher.find()){
        		            // 終わりの文字位置を設定
        		    		todoufukenPos =  todoufukenMatcher.end();
        		    		senkyokuName = WikipediaUtil.getWikiTableCell( tempSenkyokuBlock.substring(todoufukenMatcher.start() ,todoufukenMatcher.end()) );
        		        	// 1回でbreak
        		        	break;
        		    	}
        				if(todoufukenPos != 0){
        					tempSenkyokuBlock = tempSenkyokuBlock.substring(todoufukenPos ,tempSenkyokuBlock.length());
        					// 選挙区に「区」のキーワードが含まれない場合
        					if(senkyokuName.indexOf(GiinConstants.WORD_KU) == -1){
        						// ループをcontinue
        						continue;
        					}
        					// 議員名と位置を取得
        					giinName = WikipediaUtil.getWikiTableCell( WikipediaUtil.getKugiriBlock (tempSenkyokuBlock, "", WikipediaConstants.WIKITABLE_CELL_START_ESCAPE, WikipediaConstants.WIKITABLE_CELL_END_ESCAPE) );
        					todoufukenPos = WikipediaUtil.getKugiriPos (tempSenkyokuBlock, "", WikipediaConstants.WIKITABLE_CELL_START_ESCAPE, WikipediaConstants.WIKITABLE_CELL_END_ESCAPE);
        					tempSenkyokuBlock = tempSenkyokuBlock.substring(todoufukenPos ,tempSenkyokuBlock.length());
        					// 政党名を取得
        					seitouName = WikipediaUtil.getWikiContentsKakko( WikipediaUtil.getKugiriBlock (tempSenkyokuBlock, "", WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE, WikipediaConstants.WIKITABLE_KAKKO_END_ESCAPE) );
        					todoufukenPos = WikipediaUtil.getKugiriPos (tempSenkyokuBlock, "", WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE, WikipediaConstants.WIKITABLE_KAKKO_END_ESCAPE);
        					tempSenkyokuBlock = tempSenkyokuBlock.substring(todoufukenPos ,tempSenkyokuBlock.length());
        					// Beanに格納
        					SyuugiinBean syuuginSenkyokuBean = new SyuugiinBean();
        					// 選挙区分を設定
        					syuuginSenkyokuBean.setSenkyoKubun(GiinConstants.SYOUSENKYOKU_ID);
        					// 地域ブロックを設定
        					syuuginSenkyokuBean.setBlockRegion(syuugiinBlock);
        					// 都道府県を設定
        					syuuginSenkyokuBean.setTodouhuken(todoufukenName);
        					// 選挙区を設定
        					tempSenkyoku = senkyokuName.split(WikipediaConstants.VERTICAL_PATTERN);
        					syuuginSenkyokuBean.setSenkyoku(tempSenkyoku[0]);
        					// 議員名を設定
        					syuuginSenkyokuBean.setGiinName(GiinUtil.getGiinNameEdited(giinName));
        					// 政党を設定
        					syuuginSenkyokuBean.setSeitou(seitouName);
        					// 任期満了日を設定
        					syuuginSenkyokuBean.setNinkiManryoubi(syuugiinNinki);
        					// リストに格納
        					syuugiinList.add(syuuginSenkyokuBean);
        				}
        				
        				
        			}
        		}
        	}
        // 変更前のブロックを保持
        syuugiinBlockBefore = syuugiinBlock;
        // ブロックが最後の場合は比例を取得
        if(syuugiinBlock.equals((String)GiinConstants.SYUUGIIN_BLOCK.get(10))){
    		setListSyuugiinHirei(syuugiinList, syuugiinTarget.substring(blockMidashiEndPos, syuugiinTarget.length()), syuugiinBlockBefore, syuugiinNinki);
        }
    	}
    	return syuugiinList;
    }
    
    /*
     *衆議院の比例一覧を抽出するメソッド
    */
    public static void setListSyuugiinHirei(List<SyuugiinBean> syuugiinList , String syuugiinWiki , String regionBlock ,String syuugiinNinki) {
    	// 全角ハイフンを半角ハイフンへ置き換え
    	String seitouBlock = syuugiinWiki.replace(GiinConstants.ZENKAKU_HAIFUN , WikipediaConstants.WIKIPEDIA_HAIFUN);
    	// 比例の見出し位置を取得
    	int hireiMidashiEndPos = WikipediaUtil.getKugiriPos (seitouBlock , GiinConstants.HIREI_SENSYUTSU_GIIN, WikipediaConstants.WIKIPEDIA_SEMI_COLON , WikipediaConstants.WIKIPEDIA_ASTALISK_ESCAPE);
    	// 政党のリストを作成
    	List<String> seitouList = new ArrayList<String>();
    	// 政党のブロックを取得
    	seitouBlock = WikipediaUtil.getKugiriBlock (seitouBlock.substring(hireiMidashiEndPos, seitouBlock.length()), "", WikipediaConstants.WIKIPEDIA_HAIFUN_ESCAPE, WikipediaConstants.WIKITABLE_START);
    	// 政党のブロック文字列からハイフンで始まる政党を取得
    	String seitouName = WikipediaUtil.getKugiriBlock (seitouBlock, "", WikipediaConstants.WIKIPEDIA_HAIFUN_ESCAPE, WikipediaConstants.WIKIPEDIA_SINGLE_QUOT);
    	int seitouPos = WikipediaUtil.getKugiriPos (seitouBlock, "", WikipediaConstants.WIKIPEDIA_HAIFUN_ESCAPE, WikipediaConstants.WIKIPEDIA_SINGLE_QUOT);
    	seitouList.add(seitouName.replace(WikipediaConstants.WIKIPEDIA_HAIFUN, "")
    			                 .replace(WikipediaConstants.WIKIPEDIA_SPACE, "")
    			                 .replace(WikipediaConstants.WIKIPEDIA_SINGLE_QUOT, ""));
    	// 政党のブロックから全角句点で始まる政党を取得
    	while(seitouPos != 0){
    		seitouBlock= WikipediaUtil.getKugiriBlock (seitouBlock.substring(seitouPos, seitouBlock.length()), "", GiinConstants.ZENKAKU_KUTEN, WikipediaConstants.WIKITABLE_START);
    		seitouName = WikipediaUtil.getKugiriBlock (seitouBlock, "", GiinConstants.ZENKAKU_KUTEN, WikipediaConstants.WIKIPEDIA_SINGLE_QUOT);
    		// 空白判定
    		if(!GiinUtil.isEmpty(seitouName)){
            	seitouList.add(seitouName.replace(GiinConstants.ZENKAKU_KUTEN, "")
   	                 .replace(WikipediaConstants.WIKIPEDIA_SPACE, "")
   	                 .replace(WikipediaConstants.WIKIPEDIA_SINGLE_QUOT, ""));
    		}

        	seitouPos = WikipediaUtil.getKugiriPos (seitouBlock, "", GiinConstants.ZENKAKU_KUTEN, WikipediaConstants.WIKIPEDIA_SINGLE_QUOT);
    	}
    	// 比例のブロックを取得
    	String hireiBlock = WikipediaUtil.getKugiriBlock (syuugiinWiki.substring(hireiMidashiEndPos, syuugiinWiki.length()) , "", WikipediaConstants.WIKITABLE_START , WikipediaConstants.WIKITABLE_END);
    	// 全角括弧を半角へ
    	hireiBlock = hireiBlock.replace(GiinConstants.ZENKAKU_KAKKO_START, WikipediaConstants.WIKITABLE_KAKKO_START).replace(GiinConstants.ZENKAKU_KAKKO_END, WikipediaConstants.WIKITABLE_KAKKO_END);
    	// スペースしか含まれないブロックを削除
    	hireiBlock = hireiBlock.replace(WikipediaConstants.VERTICAL_BAR + WikipediaConstants.WIKIPEDIA_SPACE + WikipediaConstants.VERTICAL_BAR, WikipediaConstants.VERTICAL_BAR);    	
    	// 変数設定
    	int hireiPos = 1;
    	String hireiVerticalBlock = null;
    	String syozokuSeitou = null;
    	String giinName = null;
    	String senkyou = null;
    	String tempTodoufuken = null;
    	String tempHireiBlock = hireiBlock;
		// 最初の文字がバーティカルバーでない 
		if(!WikipediaConstants.VERTICAL_CONSTANTS.equals(tempHireiBlock.substring(0, 1)) ){
			// バーティカルバーを付与
			tempHireiBlock = WikipediaConstants.VERTICAL_CONSTANTS + tempHireiBlock;
		}
    	// 党の名称もしくは議員名を取得できるまでループ
    	while(hireiPos != 0){
    		// バーティカルバーで区切る
    		hireiVerticalBlock = WikipediaUtil.getKugiriBlock (tempHireiBlock , "", WikipediaConstants.VERTICAL_PATTERN , WikipediaConstants.VERTICAL_PATTERN);
    		// 政党のリストでループを回す
    		for (String tempSyozokuSeitou : seitouList) {
				// 該当の政党が含まれる場合
				if(hireiVerticalBlock.indexOf(tempSyozokuSeitou) != -1){
					syozokuSeitou = tempSyozokuSeitou;
				}
    		}
    		// wikitableのセルが含まれるか
    		if(hireiVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_START) != -1 &&
    		   hireiVerticalBlock.indexOf(WikipediaConstants.WIKITABLE_CELL_END) != -1) {
				// Beanの設定
				SyuugiinBean syuuginHireiBean = new SyuugiinBean();
				// 選挙区分を設定
				syuuginHireiBean.setSenkyoKubun(GiinConstants.HIREI_ID);
				// 地域ブロックを設定
				syuuginHireiBean.setBlockRegion(regionBlock);
				// 政党を設定
				syuuginHireiBean.setSeitou(syozokuSeitou);
				// 任期満了日を設定
				syuuginHireiBean.setNinkiManryoubi(syuugiinNinki);
    			// 議員名を設定
    			giinName = WikipediaUtil.getKugiriBlock (hireiVerticalBlock , "", WikipediaConstants.WIKITABLE_CELL_START_ESCAPE , WikipediaConstants.WIKITABLE_CELL_END_ESCAPE);
    			syuuginHireiBean.setGiinName(GiinUtil.getGiinNameEdited(WikipediaUtil.getWikiTableCell(giinName)) );
    			// 選挙区が含まれるか判定
    			if(WikipediaUtil.getKugiriPos (hireiVerticalBlock , "", WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE , WikipediaConstants.WIKITABLE_KAKKO_END_ESCAPE) != 0){
    				senkyou = WikipediaUtil.getWikiContentsKakko(WikipediaUtil.getKugiriBlock(hireiVerticalBlock , "", WikipediaConstants.WIKITABLE_KAKKO_START_ESCAPE , WikipediaConstants.WIKITABLE_KAKKO_END_ESCAPE));
    				// 都道府県のマップでループ
    				for(Iterator<Entry<Integer,String >> todoufukenIterator = GiinConstants.TODOUHUKEN_MAP.entrySet().iterator(); todoufukenIterator.hasNext(); ) {
    					// マップから都道府県名を取得
    					tempTodoufuken = ((Entry<Integer,String >)todoufukenIterator.next()).getValue();
    					// 該当の都道府県もしくは右一文字を抜いた文字が含まれる
    					if(senkyou.indexOf(tempTodoufuken) != -1 || 
    				       senkyou.indexOf(tempTodoufuken.substring(0,tempTodoufuken.length() -1) ) != -1){
    						// 都道府県名を設定
    	    				syuuginHireiBean.setTodouhuken(tempTodoufuken);
    						// 選挙区を設定
    	    				syuuginHireiBean.setSenkyoku(senkyou);
    						
    						break;
    					}
    					
    				}
    				
    			}else{
    				// 都道府県に全国（比例）を設定
    				syuuginHireiBean.setTodouhuken(GiinConstants.TODOUHUKEN_MAP.get(GiinConstants.HIREI_ZENKOKU_MAPID));
    				// 選挙区にブロック名を設定
    				syuuginHireiBean.setSenkyoku(regionBlock);
    			}
        		// リストにbeanを追加
        		syuugiinList.add(syuuginHireiBean);
    		}
            // ブロックの長さが取得位置より長い場合、次の位置を設定
    		if(tempHireiBlock.length() > hireiPos){
    			hireiPos = WikipediaUtil.getKugiriPos (tempHireiBlock , "", "", WikipediaConstants.VERTICAL_PATTERN);
    			tempHireiBlock = WikipediaUtil.getKugiriBlock (tempHireiBlock.substring(hireiPos, tempHireiBlock.length()), "", WikipediaConstants.VERTICAL_PATTERN, WikipediaConstants.WIKITABLE_END);

    		}else{
    			// 位置を0に設定
    			hireiPos = 0;
    		}
    	}
    	
    }
}
