

package com.giin.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.wikipedia.util.WikipediaConstants;
import com.wikipedia.util.WikipediaUtil;

public class GiinUtil {

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
     *議員の名前を編集して返すメソッド
    */
    public static String getGiinNameEdited(String inputName) {
    	String outputName = inputName;
    	//バーティカルバーが含まれる場合は前半部だけ
    	if(inputName.indexOf(WikipediaConstants.VERTICAL_BAR) != 1){
    		String[] tempName = inputName.split(WikipediaConstants.VERTICAL_PATTERN);
    		outputName = tempName[0];
    	}
    	// Wikipedeliaのリンクを付与してリターン
    	return WikipediaUtil.getWikiLinkWith(outputName);
    }
    
    
    /*
     *議員を都道府県毎に格納して返すメソッド
    */
    public static List<TodoufukenGiinBean> getTodoufukenGiinList(List<SyuugiinBean> syuugiinList , List<SangiinBean> sangiinList, List<ChijiBean> chijiList) {
    	List<TodoufukenGiinBean> todoufukenGiinList = new ArrayList<TodoufukenGiinBean>();
    	String todoufuken = null;
    	String todoufukenId = null;
    	boolean isTempGiinInclude = true;
    	String tempTodoufuken = null;
    	Entry<Integer,String > tempEntry = null;
    	// 都道府県でループ
    	for(Iterator<Entry<Integer,String >> todoufukenIterator = GiinConstants.TODOUHUKEN_MAP.entrySet().iterator(); todoufukenIterator.hasNext(); ) {
    		tempEntry = ((Entry<Integer,String >)todoufukenIterator.next());
    		todoufuken = tempEntry.getValue();
    		todoufukenId = Integer.toString( tempEntry.getKey() );
    		tempTodoufuken = null;
			List<SyuugiinBean> tempSyuugiinList = new ArrayList<SyuugiinBean>();
    		// 衆議院リストでループ
    		for (SyuugiinBean syuugiinBean : syuugiinList) {
        		isTempGiinInclude = false;
    			// 都道府県取得
    			tempTodoufuken = getLastTodoufuken(syuugiinBean.getTodouhuken());
    			// 名称一致判定
    			if(todoufuken.equals(tempTodoufuken)){
    				// IDを取得
    				todoufukenId = getTodoufukenIdFromName(syuugiinBean.getTodouhuken());
    				// 議員リストでループ
    				for(TodoufukenGiinBean tempTodoufukenGiinBean : todoufukenGiinList){
        			    // IDでの議員bean有無判定
    					if( todoufukenId.equals(tempTodoufukenGiinBean.getTodouhukenId()) ){
    						isTempGiinInclude = true;
    						tempSyuugiinList = tempTodoufukenGiinBean.getSyuugiinList();
    						// null判定
    						if(tempSyuugiinList == null || tempSyuugiinList.size() ==0){
    	    					// 衆議院リストの新規作成
    	    					List<SyuugiinBean> newSyuugiinList = new ArrayList<SyuugiinBean>();
    	    					newSyuugiinList.add(syuugiinBean);
    							tempTodoufukenGiinBean.setSyuugiinList(newSyuugiinList);
    						}else{
    							tempSyuugiinList.add(syuugiinBean);
    							tempTodoufukenGiinBean.setSyuugiinList(tempSyuugiinList);
    						}
    					}
    				}
    				// 議員beanにない場合
    				if(!isTempGiinInclude){
    					TodoufukenGiinBean newTodoufukenGiinBean = new TodoufukenGiinBean();
    					newTodoufukenGiinBean.setTodouhukenId(todoufukenId);
    					newTodoufukenGiinBean.setTodouhukenName(syuugiinBean.getTodouhuken());
    					// 衆議院リストの新規作成
    					List<SyuugiinBean> newSyuugiinList = new ArrayList<SyuugiinBean>();
    					newSyuugiinList.add(syuugiinBean);
    					newTodoufukenGiinBean.setSyuugiinList(newSyuugiinList);
    					todoufukenGiinList.add(newTodoufukenGiinBean);
    				}
    			}
    		}
    		
			List<SangiinBean> tempSangiinList = new ArrayList<SangiinBean>();
    		// 参議院でループ
    		for (SangiinBean sangiinBean : sangiinList) {
        		isTempGiinInclude = false;
    			// 都道府県取得
    			tempTodoufuken = getLastTodoufuken(sangiinBean.getTodouhuken());
    			// 名称一致判定
    			if(todoufuken.equals(tempTodoufuken)){
    				// IDを取得
    				todoufukenId = getTodoufukenIdFromName(sangiinBean.getTodouhuken());
    				// 議員リストでループ
    				for(TodoufukenGiinBean tempTodoufukenGiinBean : todoufukenGiinList){
        			    // IDでの議員bean有無判定
    					if( todoufukenId.equals(tempTodoufukenGiinBean.getTodouhukenId()) ){
    						isTempGiinInclude = true;
    						tempSangiinList = tempTodoufukenGiinBean.getSangiinList();
    						// null判定
    						if(tempSangiinList == null || tempSangiinList.size() ==0){
    	    					// 参議院リストの新規作成
    	    					List<SangiinBean> newSangiinList = new ArrayList<SangiinBean>();
    	    					newSangiinList.add(sangiinBean);
    							tempTodoufukenGiinBean.setSangiinList(newSangiinList);
    						}else{
    							tempSangiinList.add(sangiinBean);
    							tempTodoufukenGiinBean.setSangiinList(tempSangiinList);
    						}
    					}
    				}
    				// 議員beanにない場合
    				if(!isTempGiinInclude){
    					TodoufukenGiinBean newTodoufukenGiinBean = new TodoufukenGiinBean();
    					newTodoufukenGiinBean.setTodouhukenId(todoufukenId);
    					newTodoufukenGiinBean.setTodouhukenName(sangiinBean.getTodouhuken());
    					// 参議院リストの新規作成
    					List<SangiinBean> newSangiinList = new ArrayList<SangiinBean>();
    					newSangiinList.add(sangiinBean);
    					newTodoufukenGiinBean.setSangiinList(newSangiinList);
    					todoufukenGiinList.add(newTodoufukenGiinBean);
    				}
    			}
    			
    		}
    		
			List<ChijiBean> tempChijiList = new ArrayList<ChijiBean>();
    		// 都道府県知事でループ
    		for (ChijiBean chijiBean : chijiList) {
        		isTempGiinInclude = false;
    			// 都道府県取得
    			tempTodoufuken = getLastTodoufuken(chijiBean.getTodouhuken());
    			// 名称一致判定
    			if(todoufuken.equals(tempTodoufuken)){
    				// IDを取得
    				todoufukenId = getTodoufukenIdFromName(chijiBean.getTodouhuken());
    				// 議員リストでループ
    				for(TodoufukenGiinBean tempTodoufukenGiinBean : todoufukenGiinList){
        			    // IDでの議員bean有無判定
    					if( todoufukenId.equals(tempTodoufukenGiinBean.getTodouhukenId()) ){
    						isTempGiinInclude = true;
    						tempChijiList = tempTodoufukenGiinBean.getChijiList();
    						// null判定
    						if(tempChijiList == null || tempChijiList.size() ==0){
    	    					// 都道府県知事リストの新規作成
    	    					List<ChijiBean> newChijiList = new ArrayList<ChijiBean>();
    	    					newChijiList.add(chijiBean);
    							tempTodoufukenGiinBean.setChijiList(newChijiList);
    						}else{
    							tempChijiList.add(chijiBean);
    							tempTodoufukenGiinBean.setChijiList(tempChijiList);
    						}
    					}
    				}
    				// 議員beanにない場合
    				if(!isTempGiinInclude){
    					TodoufukenGiinBean newTodoufukenGiinBean = new TodoufukenGiinBean();
    					newTodoufukenGiinBean.setTodouhukenId(todoufukenId);
    					newTodoufukenGiinBean.setTodouhukenName(chijiBean.getTodouhuken());
    					// 都道府県知事リストの新規作成
    					List<ChijiBean> newChijiList = new ArrayList<ChijiBean>();
    					newChijiList.add(chijiBean);
    					newTodoufukenGiinBean.setChijiList(newChijiList);
    					todoufukenGiinList.add(newTodoufukenGiinBean);
    				}
    			}

    		}
    	}
    	// ソート
    	Collections.sort(todoufukenGiinList, new TodoufukenBeanComp());
    	return todoufukenGiinList;
    }

    /*
     *複数ある都道府県のうち最後を返すメソッド
    */
    public static String getLastTodoufuken(String todoufukenWord) {
    	// 区切り文字が含まれるか判定
    	if(todoufukenWord.indexOf(GiinConstants.SEITOU_KUGIRI) != -1){
    		// 区切り文字で区切る
    		String[] todoufukenArray = todoufukenWord.split(GiinConstants.SEITOU_KUGIRI);
    		return todoufukenArray[todoufukenArray.length -1];
    		
    	}else{
    		return todoufukenWord;
    	}

    }

    /*
     *都道府県名称からIDを返すメソッド
    */
    public static String getTodoufukenIdFromName(String todoufukenName) {
    	String todoufukenId = "";
    	String tempTodoufukenId = "";
    	String tempTodoufukenName = "";
    	// 都道府県名に区切り文字が含まれるか判定
    	if(todoufukenName.indexOf(GiinConstants.SEITOU_KUGIRI) != -1){
    		// 区切り文字で区切る
    		String[] todoufukenArray = todoufukenName.split(GiinConstants.SEITOU_KUGIRI);
    		// 分割した配列でループ
    		for(int i =0; i < todoufukenArray.length; i++){
            	// 都道府県でループ
            	for(Iterator<Entry<Integer,String >> todoufukenIterator = GiinConstants.TODOUHUKEN_MAP.entrySet().iterator(); todoufukenIterator.hasNext(); ) {
            		Entry<Integer,String > tempEntry = ((Entry<Integer,String >)todoufukenIterator.next());
            		tempTodoufukenName = tempEntry.getValue();
            		// 都道府県の一致判定
            		if(tempTodoufukenName.equals(todoufukenArray[i])){
            			tempTodoufukenId = Integer.toString( tempEntry.getKey() );
            			if(isEmpty(todoufukenId)){
            				todoufukenId = tempTodoufukenId;
            			}else{
                			todoufukenId = todoufukenId + GiinConstants.HANKAKU_KUTEN + tempTodoufukenId;
            			}

                		break;
            		}
            	}
    			
    		}
    		
    	}else{
        	// 都道府県でループ
        	for(Iterator<Entry<Integer,String >> todoufukenIterator = GiinConstants.TODOUHUKEN_MAP.entrySet().iterator(); todoufukenIterator.hasNext(); ) {
        		Entry<Integer,String > tempEntry = ((Entry<Integer,String >)todoufukenIterator.next());
        		tempTodoufukenName = tempEntry.getValue();
        		// 都道府県の一致判定
        		if(tempTodoufukenName.equals(todoufukenName)){
            		todoufukenId = Integer.toString( tempEntry.getKey() );
            		break;
        		}
        	}
    	}
        return todoufukenId;
    }
    
    /*
     *都道府県BeanのComparator
    */
    private static final class TodoufukenBeanComp implements Comparator<TodoufukenGiinBean> {
        @Override
	    public int compare(TodoufukenGiinBean w1, TodoufukenGiinBean w2) {
        	String tempTodoufukenIdw1 = "";
        	String tempTodoufukenIdw2 = "";
        	// カンマをドットにリプレースして小数点化
        	if((w1.getTodouhukenId()).indexOf(GiinConstants.HANKAKU_KUTEN) != -1){
        		tempTodoufukenIdw1 = (w1.getTodouhukenId()).replace(",", ".");
        	}else{
        		tempTodoufukenIdw1 = w1.getTodouhukenId();
        	}
        	if((w2.getTodouhukenId()).indexOf(GiinConstants.HANKAKU_KUTEN) != -1){
            	tempTodoufukenIdw2 = (w2.getTodouhukenId()).replace(",", ".");
        	}else{
        		tempTodoufukenIdw2 = w2.getTodouhukenId();
        	}

	        return Double.compare(Double.parseDouble(tempTodoufukenIdw1), Double.parseDouble(tempTodoufukenIdw2));
   	    }

    }
}
