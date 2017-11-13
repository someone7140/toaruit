
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

import com.music.bean.ResponseMusicBean;


public class MusicSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	// ユーザID出力(テスト用)
    	// GracenoteApiUtil.outGracenoteUserId();
		// セッション取得
		HttpSession session = request.getSession(true);
    	// パラメータ格納
    	String searchArtist = request.getParameter("searchArtist");
    	String searchTrack = request.getParameter("searchTrack");
    	String searchNonDispFlg = request.getParameter("searchNonDispFlg");
    	// パラメータNULLでセッションにある場合
    	if(MusicUtil.isEmpty(searchArtist)){
    		searchArtist = (String)session.getAttribute("searchArtist");
    	}else{
    		// セッションをリセット
    		session.setAttribute("searchArtist", null);
    	}
    	// アーティスト検索結果画面遷移
    	boolean searchArtistForwrd = false;
    	// パラメータをリクエストに再設定
    	if(MusicUtil.isEmpty(searchArtist)){
    		request.setAttribute("searchArtist", null);
        }else{
        	request.setAttribute("searchArtist",searchArtist);
        }
    	if(MusicUtil.isEmpty(searchTrack)){
    		request.setAttribute("searchTrack", null);
        }else{
        	request.setAttribute("searchTrack",searchTrack);
        }
    	// 検索窓のパラメータ確認
    	if(!MusicUtil.isEmpty(searchArtist) && "true".equals(searchNonDispFlg)){
    		// 検索窓非表示のフラグを設定
    		request.setAttribute("searchNonDispFlg",searchNonDispFlg);
    	}
        // ブランクチェック
    	if(MusicUtil.isEmpty(searchArtist) && MusicUtil.isEmpty(searchTrack) ){
    		// 警告メッセージを表示
    		request.setAttribute("checkResult", "false");
    	// アーテイスト検索結果画面から来た
    	}else if("from_music_search_artist".equals(searchTrack)){
    		// リクエストの曲名をnull
    		request.setAttribute("searchTrack", null);
    	}else{
    		try{
        		// 曲名が入っている場合
        		if(!MusicUtil.isEmpty(searchTrack) ){
            		// リクエスト用のGracenoteAPIを実行
        			String responseXml = GracenoteApiUtil.getGracenoteMusicXml(searchArtist , searchTrack, null , request);
        			// テスト出力用
            		//System.out.println( responseXml );
            		// 文字列からオブジェクト化
            		ResponseMusicBean responseMusicBean= JAXB.unmarshal(new StringReader(responseXml), ResponseMusicBean.class);
            		// responseがアンマッチ
            		if(MusicConstants.RESPONSE_NO_MATCH.equals(responseMusicBean.getResponseMusicChildBean().getResponseStatus())){
            			// 曲が見つからなかった場合の警告メッセージ
            			request.setAttribute("checkResult", "zero");
            		}else{
            			// リクエストに結果をセット
            			request.setAttribute("resultMusicBean", GracenoteApiUtil.getGracenoteResultObj(responseMusicBean));
            		}
        		}else{
                    // セッションにセット
        			session.setAttribute("searchArtist", searchArtist);
        			// アーティスト検索結果画面へ遷移フラグセット
        			searchArtistForwrd = true;

        		}
    		}catch(Exception e){
    			// exceptionが発生した場合はfailure
    			request.setAttribute("checkResult", "failure");
    		}

    	}
    	
    	if(searchArtistForwrd){
			// アーティスト検索結果画面へ遷移
	        RequestDispatcher rd = request.getRequestDispatcher("/musicsearchartist");
	        rd.forward(request,response);
    	}else{
        	// 自画面遷移
            RequestDispatcher rd = request.getRequestDispatcher("music_search.jsp");
            rd.forward(request,response);
    	}


    }

}
