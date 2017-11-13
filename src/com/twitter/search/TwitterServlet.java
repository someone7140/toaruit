
package com.twitter.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TwitterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	// パラメータ格納
    	String searchWord = request.getParameter("searchword");
    	String dateFrom = request.getParameter("jquery-ui-datepicker-from");
    	String dateTo = request.getParameter("jquery-ui-datepicker-to");
    	String searchKind = request.getParameter("searchKind");
    	// request属性クリア
    	request.setAttribute("wordTotalList", null);
    	request.setAttribute("dayTotalList", null);
    	request.setAttribute("tweetTargetCount", null);
    	// パラメータをリクエストに再設定
    	if(TwitterUtil.isEmpty(searchWord)){
    		request.setAttribute("searchword", null);
        }else{
        	request.setAttribute("searchword",searchWord);
        }
    	if(TwitterUtil.isEmpty(dateFrom)){
    		request.setAttribute("dateFrom", null);
        }else{
        	request.setAttribute("dateFrom",dateFrom);
        }
    	if(TwitterUtil.isEmpty(dateTo)){
    		request.setAttribute("dateTo", null);
        }else{
        	request.setAttribute("dateTo",dateTo);
        }
    	if(TwitterUtil.isEmpty(searchKind)){
    		request.setAttribute("searchKind", null);
        }else{
        	request.setAttribute("searchKind",searchKind);
        }
        // ブランクチェック
    	if(TwitterUtil.isEmpty(searchWord) || TwitterUtil.isEmpty(dateFrom) || TwitterUtil.isEmpty(dateTo)){
    		request.setAttribute("checkResult", "false");
    	}else{
    		// Twitter接続
    		TwitterResult twitterResult = TwitterUtil.getTwitterResult(searchWord, dateFrom, dateTo, request);
		    // 成功判定
		    if(twitterResult == null){
	    		request.setAttribute("checkResult", "failure");
		    }else{
			    request.setAttribute("checkResult", null);
			    request.setAttribute("tweetTargetCount", Integer.toString(twitterResult.getTweetTargetCount()));
			    // kuromojiで分解
			    List<TotalWork> totalWorkAfterKuro =  TwitterUtil.getKuromojiResult(twitterResult.getTotalWorkList());

			    // 集計方法によって登録するオブジェクトを変える
			    if(TwitterConstants.SEARCHKIND_WORD.equals(searchKind)){
			    	List<WordTotal> wordTotalList = TwitterUtil.getWordTotalList(totalWorkAfterKuro,searchWord);
			    	request.setAttribute("wordTotalList", wordTotalList);
			    	// nullの場合メッセージを表示
			    	if(wordTotalList == null){
			    		request.setAttribute("checkResult", "zero");
			    	}
			    }else{
			    	List<DayTotalSummary> dayTotalList = TwitterUtil.getDayTotalList(totalWorkAfterKuro,searchWord);
			    	request.setAttribute("dayTotalList", dayTotalList);
			    	// nullの場合メッセージを表示
			    	if(dayTotalList == null){
			    		request.setAttribute("checkResult", "zero");
			    	}
			    }

		    }
		    

    	}
        RequestDispatcher rd = request.getRequestDispatcher("twitter.jsp");
        rd.forward(request,response);

    }

}
