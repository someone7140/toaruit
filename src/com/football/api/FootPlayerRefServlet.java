
package com.football.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FootPlayerRefServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		// リクエストパラメータから選手名を取得
      	String playerNameEn = request.getParameter("playername");
		// パラメータがNULL
		if( FootBallUtil.isEmpty(playerNameEn)){
    		// エラーのHTMLへ遷移
		    RequestDispatcher rd = request.getRequestDispatcher("/foot_jsp/football_player_error.html");
		    rd.forward(request,response);
		}else{
			try {
				// 選手名の日本語参照URLをwikipediaから取得
				String wikipediaJpUrl = FootBallUtil.getPlayerNameJpUrl(playerNameEn);
				
				// HTMLのnull判定
				if(wikipediaJpUrl == null){
		    		// エラーのHTMLへ遷移
				    RequestDispatcher rd = request.getRequestDispatcher("/foot_jsp/football_player_error.html");
				    rd.forward(request,response);
				}else {
					// リダイレクト
					response.sendRedirect(wikipediaJpUrl);
				}
			}catch(Exception e) {
	    		// エラーのHTMLへ遷移
			    RequestDispatcher rd = request.getRequestDispatcher("/foot_jsp/football_player_error.html");
			    rd.forward(request,response);
			}
		}		
    }

}
