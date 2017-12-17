
package com.football.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FootPastFixtureServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		// リクエストパラメータからFixtureのIDを取得
      	String fixtureId = request.getParameter("fixtureId");
		// リクエストパラメータからteamのIDを取得
      	String teamId = request.getParameter("teamId");
		//文字コードセット
		response.setContentType("text/html; charset=UTF-8");
		// パラメータがNULL
		if( FootBallUtil.isEmpty(fixtureId) || FootBallUtil.isEmpty(teamId)){
    		// 警告メッセージを表示
    		request.setAttribute("checkResult", "failure");
		}else{
			try {
				// 過去の試合結果のHTMLを取得
				String pastGameResultHtml = FootBallUtil.getPastGameResultFromApi(fixtureId , teamId ,request);
				// HTMLのnull判定
				if(pastGameResultHtml== null){
					// 警告メッセージを表示
		    		request.setAttribute("checkResult", "failure");
				}else {
					// 試合結果をリクエストにセット
					request.setAttribute("resultHtml", pastGameResultHtml);
				}
			}catch(Exception e) {
				// 警告メッセージを表示
	    		request.setAttribute("checkResult", "failure");
			}
		}		
		// チーム参照画面へ遷移
	    RequestDispatcher rd = request.getRequestDispatcher("/foot_jsp/football_past_result.jsp");
	    rd.forward(request,response);
    }	
    
}
