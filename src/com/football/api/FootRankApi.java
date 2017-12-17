
package com.football.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FootRankApi extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		// リクエストパラメータからシーズン年とリーグを取得
      	String seasonYear = request.getParameter("seasonYear");
      	String league = request.getParameter("league");
		//文字コードセット
		response.setContentType("text/html; charset=UTF-8");
		// どちらかがNULL
		if( FootBallUtil.isEmpty(seasonYear) || FootBallUtil.isEmpty(league)){
    		// レスポンスへエラーを書き出す
            response.getWriter().println("<font color=\"red\"> ※検索に失敗しました。</font>");
		}else{
			String responseHtml = FootBallUtil.getFootRankFromApi(seasonYear, league, request);
			//テスト用出力
			// System.out.println(responseXml);
			if(FootBallUtil.isEmpty(responseHtml)){
	    		// レスポンスへエラーを書き出す
	            response.getWriter().println("<font color=\"red\"> ※検索に失敗しました。</font>");
			}else{
        		// レスポンスへHTMLを書き出す
	            response.getWriter().println(responseHtml);
			}

		}
    }

}
