
package com.football.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.football.bean.teaminfo.FootBallTeamInfoBean;


public class FootTeamRefServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		// リクエストパラメータからチームIDを取得
      	String teamId = request.getParameter("teamId");
		//文字コードセット
		response.setContentType("text/html; charset=UTF-8");
		// パラメータがNULL
		if( FootBallUtil.isEmpty(teamId)){
    		// 警告メッセージを表示
    		request.setAttribute("checkResult", "failure");
		}else{
			try {
				// teamのオブジェクトをAPIから取得
				FootBallTeamInfoBean teamInfoBean = FootBallUtil.getFootTeamInfoFromApi(teamId, request);
				// 直近の試合結果のHTMLを取得
				String recentlyGameResultHtml = FootBallUtil.getRecentlyGameResultFromApi(teamInfoBean.get_links().getFixtures().getHref(), teamId , request);
				// teamプレイヤーのHTMLを取得
				String playerHtml = FootBallUtil.getFootPlayerFromApi(teamInfoBean.get_links().getPlayers().getHref(), request);

				// HTMLのnull判定
				if(recentlyGameResultHtml== null || playerHtml == null){
					// 警告メッセージを表示
		    		request.setAttribute("checkResult", "failure");
				}else {
					// チーム名をリクエストにセット
					request.setAttribute("teamName", teamInfoBean.getName());
					// 直近の試合結果をリクエストにセット
					request.setAttribute("resultHtml", recentlyGameResultHtml);
					// プレイヤーのHTMLをリクエストにセット
					request.setAttribute("playerHtml", playerHtml);
				}
			}catch(Exception e) {
				// 警告メッセージを表示
	    		request.setAttribute("checkResult", "failure");
			}
		}		
		// チーム参照画面へ遷移
	    RequestDispatcher rd = request.getRequestDispatcher("/foot_jsp/football_team.jsp");
	    rd.forward(request,response);
    }

}
