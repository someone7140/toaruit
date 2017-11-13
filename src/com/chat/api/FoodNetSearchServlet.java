
package com.chat.api;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import com.music.search.MusicUtil;
import com.rakuten.api.RakutenApiUtil;
import com.rakuten.bean.response.ResponseRakutenIchibaBean;


public class FoodNetSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    	doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		// リクエストパラメータから食べたいものと価格を取得
      	String foodName = request.getParameter("foodName");
      	String lowprice = request.getParameter("lowprice");
      	String highprice = request.getParameter("highprice");
      	
		// 食べ物がNULL
		if( ChatUtil.isEmpty(foodName) ){
			request.setAttribute("checkResult" , "failure");
            // 結果画面へ遷移
	        RequestDispatcher rd = request.getRequestDispatcher("/food_jsp/food_net_search.jsp");
	        rd.forward(request,response);
		}else{
			String responseXml = RakutenApiUtil.getRakutenSearchIchibaXml(foodName, lowprice, highprice, "1", request);
			//テスト用出力
			// System.out.println(responseXml);
			if(ChatUtil.isEmpty(responseXml)){
				// NULLの場合、チェック結果failure
				request.setAttribute("checkResult" , "failure");
				
			}else{
        		// 文字列からオブジェクト化
				ResponseRakutenIchibaBean responseRakutenIchibaBean = JAXB.unmarshal(new StringReader(responseXml), ResponseRakutenIchibaBean.class);
				// 件数が0件の場合
				if(responseRakutenIchibaBean.getPageCount().equals("0")){
					// チェック結果はsuccess
					request.setAttribute("checkResult" , "success");
					// pagecountをnullでセット
					request.setAttribute("pageCount" , null);
				}else{
					// HTMLを取得
					String responseHtml = RakutenApiUtil.getRakutenIchibaResponseHtml(responseRakutenIchibaBean);
					if(MusicUtil.isEmpty(responseHtml)){
						// NULLの場合は失敗
						request.setAttribute("checkResult" , "failure");
					}else{
						// チェック結果はsuccess
						request.setAttribute("checkResult" , "success");
						// HTMLをセット
						request.setAttribute("responseHtml" , responseHtml);
						// pagecountをセット
						request.setAttribute("pageCount" , responseRakutenIchibaBean.getPageCount());
						// 食事名をセット
						request.setAttribute("foodName" , foodName);
						// 最低価格をセット
						request.setAttribute("lowprice" , lowprice);
						// 最高価格をセット
						request.setAttribute("highprice" , highprice);
					}

				}
			}
			// 結果画面へ遷移
	        RequestDispatcher rd = request.getRequestDispatcher("food_jsp/food_net_search.jsp");
	        rd.forward(request,response);
		}


    }

}
