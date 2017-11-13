
package com.chat.api;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import com.rakuten.api.RakutenApiUtil;
import com.rakuten.bean.response.ResponseRakutenIchibaBean;


public class FoodNetSearchApi extends HttpServlet {

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
        // パラメータからページ数を取得
     	String page = request.getParameter("page");
      	
		// 食べ物がNULL
		if( ChatUtil.isEmpty(foodName) ){
			// 何もしない
		}else{
			String responseXml = RakutenApiUtil.getRakutenSearchIchibaXml(foodName, lowprice, highprice, page, request);
			//テスト用出力
			// System.out.println(responseXml);
			if(ChatUtil.isEmpty(responseXml)){
				// 何もしない
				
			}else{
        		// 文字列からオブジェクト化
				ResponseRakutenIchibaBean responseRakutenIchibaBean = JAXB.unmarshal(new StringReader(responseXml), ResponseRakutenIchibaBean.class);
				// 件数が0件の場合
				if(responseRakutenIchibaBean.getPageCount().equals("0")){
					// 何もしない
				}else{
					// HTMLを取得
					String responseHtml = RakutenApiUtil.getRakutenIchibaResponseHtml(responseRakutenIchibaBean);
					if(!ChatUtil.isEmpty(responseHtml)){
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
