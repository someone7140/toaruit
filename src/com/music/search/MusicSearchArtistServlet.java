
package com.music.search;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXB;

import com.rakuten.api.RakutenApiUtil;
import com.rakuten.bean.response.ResponseRakutenSearchBean;


public class MusicSearchArtistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		// セッションからアーティスト名を取得
		HttpSession session = request.getSession(true);
		String searchArtist = (String)session.getAttribute("searchArtist");
		// NULL判定
		if(MusicUtil.isEmpty(searchArtist)){
            // 検索画面へ遷移
	        RequestDispatcher rd = request.getRequestDispatcher("/musicsearch");
	        rd.forward(request,response);
		}else{
			String responseXml = RakutenApiUtil.getRakutenSearchCdXml(searchArtist, "1" ,request);
			//テスト用出力
			// System.out.println(responseXml);
			if(MusicUtil.isEmpty(responseXml)){
				// NULLの場合、チェック結果failure
				request.setAttribute("checkResult" , "failure");
				
			}else{
        		// 文字列からオブジェクト化
				ResponseRakutenSearchBean responseRakutenBean = JAXB.unmarshal(new StringReader(responseXml), ResponseRakutenSearchBean.class);
				// 件数が0件の場合
				if(responseRakutenBean.getPageCount().equals("0")){
					// チェック結果はsuccess
					request.setAttribute("checkResult" , "success");
					// pagecountをnullでセット
					request.setAttribute("pageCount" , null);
				}else{
					// HTMLを取得
					String responseHtml = RakutenApiUtil.getRakutenResponseHtml(responseRakutenBean);
					if(MusicUtil.isEmpty(responseHtml)){
						// NULLの場合は失敗
						request.setAttribute("checkResult" , "failure");
					}else{
						// チェック結果はsuccess
						request.setAttribute("checkResult" , "success");
						// HTMLをセット
						request.setAttribute("responseHtml" , responseHtml);
						// pagecountをセット
						request.setAttribute("pageCount" , responseRakutenBean.getPageCount());
					}

				}
			}
			// アーティスト検索画面へ遷移
	        RequestDispatcher rd = request.getRequestDispatcher("music_search_artist.jsp");
	        rd.forward(request,response);
		}


    }

}
