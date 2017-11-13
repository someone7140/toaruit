
package com.music.search;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXB;

import com.rakuten.api.RakutenApiUtil;
import com.rakuten.bean.response.ResponseRakutenSearchBean;


public class MusicSearchArtistApi extends HttpServlet {

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
		// パラメータからページ数を取得
		String page = request.getParameter("page");
		// NULL判定
		if(!MusicUtil.isEmpty(searchArtist) && !MusicUtil.isEmpty(page) ){
			String responseXml = RakutenApiUtil.getRakutenSearchCdXml(searchArtist, page, request);
			if(!MusicUtil.isEmpty(responseXml)){
				//テスト用出力
				//System.out.println(responseXml);
        		// 文字列からオブジェクト化
				ResponseRakutenSearchBean responseRakutenBean = JAXB.unmarshal(new StringReader(responseXml), ResponseRakutenSearchBean.class);
				// 件数が0件の場合
				if(responseRakutenBean.getPageCount().equals("0")){
					// 何もしない
				}else{
					// HTMLを取得
					String responseHtml = RakutenApiUtil.getRakutenResponseHtml(responseRakutenBean);
					// NULL判定
					if(!MusicUtil.isEmpty(responseHtml)){
						//文字コードセット
						response.setContentType("text/html; charset=UTF-8");
	            		// レスポンスへ書き出す
	            		response.getWriter().println(responseHtml);
					}

				}
			}

		}


    }

}
