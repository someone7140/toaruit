
package com.chat.api;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import com.hotpepper.api.HotPepperApiUtil;
import com.hotpepper.bean.ResponseHotPepperGourmetBean;
import com.music.search.MusicUtil;


public class FoodOutSearchServlet extends HttpServlet {

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
      	String location = request.getParameter("location");
      	
		// 食べ物か地名がNULL
		if( ChatUtil.isEmpty(foodName) || ChatUtil.isEmpty(location)){
			request.setAttribute("checkResult" , "failure");
            // 結果画面へ遷移
	        RequestDispatcher rd = request.getRequestDispatcher("/food_jsp/food_out_search.jsp");
	        rd.forward(request,response);
		}else{
			String responseXml = HotPepperApiUtil.getHotPepperXml(foodName, lowprice, highprice, location ,"1", request);
			//テスト用出力
			// System.out.println(responseXml);
			if(ChatUtil.isEmpty(responseXml)){
				// NULLの場合、チェック結果failure
				request.setAttribute("checkResult" , "failure");
				
			}else{
        		// 文字列からオブジェクト化
				ResponseHotPepperGourmetBean responseHotPepperGourmetBean = JAXB.unmarshal(new StringReader(responseXml), ResponseHotPepperGourmetBean.class);
				// 件数が0件の場合
				if(responseHotPepperGourmetBean.getResults_returned().equals("0")){
					// チェック結果はsuccess
					request.setAttribute("checkResult" , "success");
					// pagecountをnullでセット
					request.setAttribute("pageCount" , null);
				}else{
					// HTMLを取得
					String responseHtml = HotPepperApiUtil.getHotPepperGourmetHtml(responseHotPepperGourmetBean);
					if(MusicUtil.isEmpty(responseHtml)){
						// NULLの場合は失敗
						request.setAttribute("checkResult" , "failure");
					}else{
						// チェック結果はsuccess
						request.setAttribute("checkResult" , "success");
						// HTMLをセット
						request.setAttribute("responseHtml" , responseHtml);
						// pagecountをセット
						request.setAttribute("pageCount" , responseHotPepperGourmetBean.getResults_available());
						// 食事名をセット
						request.setAttribute("foodName" , foodName);
						// 最低価格をセット
						request.setAttribute("lowprice" , lowprice);
						// 最高価格をセット
						request.setAttribute("highprice" , highprice);
						// 地名をセット
						request.setAttribute("location" , location);
					}

				}
			}
			// 結果画面へ遷移
	        RequestDispatcher rd = request.getRequestDispatcher("food_jsp/food_out_search.jsp");
	        rd.forward(request,response);
		}


    }

}
