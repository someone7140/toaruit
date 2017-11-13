
package com.giin.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wikipedia.util.WikipediaUtil;


public class GiinSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	// 都道府県Mapをリクエストに格納
    	request.setAttribute("todouhukenList", GiinConstants.TODOUHUKEN_MAP);
    	try{
        	// 衆議院議員の一覧をWikipediaから取得
        	String syuugiinWiki = WikipediaUtil.getWikipediaContents(WikipediaUtil.getWikipediaXML(GiinConstants.SYUUGIIN_ICHIRAN));
        	// 衆議院の任期満了日を抽出
        	String syuugiinNinki = SyuuGiinUtil.getSyuugiinNinki(syuugiinWiki);
        	// 衆議院議員の一覧を抽出
        	List<SyuugiinBean> syuugiinList = SyuuGiinUtil.getSyuugiinIchiran(syuugiinWiki , syuugiinNinki);
        	
        	// 参議院の一覧をWikipediaから取得
        	String sangiinWiki = WikipediaUtil.getWikipediaContents(WikipediaUtil.getWikipediaXML(GiinConstants.SANGIIN_ICHIRAN));
        	// 参議院の任期満了日を抽出
        	List<String> sangiinNinkiList = SanGiinUtil.getSangiinNinki(sangiinWiki);
        	// 衆議院議員の一覧を抽出
        	List<SangiinBean> sangiinList = SanGiinUtil.getSangiinIchiran(sangiinWiki , sangiinNinkiList);
        	
        	// 都道府県知事の一覧をWikipediaから取得
        	String chijiWiki = WikipediaUtil.getWikipediaContents(WikipediaUtil.getWikipediaXML(GiinConstants.CHIJI_ICHIRAN));
        	// 都道府県知事の一覧を抽出
        	List<ChijiBean> chijiList = ChijiUtil.getChijiIchiran(chijiWiki);
        	
        	// beanを都道府県舞に格納
        	List<TodoufukenGiinBean> todoufukenGiinList = GiinUtil.getTodoufukenGiinList(syuugiinList, sangiinList, chijiList);
        	// リストをリクエストに設定
        	request.setAttribute("todoufukenGiinList", todoufukenGiinList);
        	request.setAttribute("errorflg", null);
    	}catch(Exception e){
    		// Exceptionの場合はNULLをセット
    		e.printStackTrace();
        	request.setAttribute("todoufukenGiinList", null);
        	request.setAttribute("errorflg", GiinConstants.WIKI_ERROR_FLG);
    		
    	}

        RequestDispatcher rd = request.getRequestDispatcher("giin_search.jsp");
        rd.forward(request,response);

    }

}
