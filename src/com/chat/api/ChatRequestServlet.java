package com.chat.api;

/***********************************************************************************************************************
 *
 * API.AI Java SDK - client-side libraries for API.AI
 * =================================================
 *
 * Copyright (C) 2017 by Speaktoit, Inc. (https://www.speaktoit.com)
 * https://www.api.ai
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ai.api.model.AIResponse;
import ai.api.web.AIServiceServlet;


/**
 * Servlet implementation class AIServiceServlet
 */
//@WebServlet(urlPatterns = {"/ai"}, initParams = {
//    @WebInitParam(name = ChatRequestServlet.PARAM_API_AI_KEY,
//        value = "1bea0a262c924f43bf87a88e4a69cd94")})
public class ChatRequestServlet extends AIServiceServlet {
  private static final long serialVersionUID = 1L;
 
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  	// セッションを取得
  	HttpSession session = request.getSession(true);
  	// パラメータからユーザ入力内容を取得
  	String inputWord = request.getParameter("inputWord");
  	String sessionClearFlg = request.getParameter("sessionClearFlg");
  	String changeTarget = request.getParameter("changeTarget");
  	// ユーザへのレスポンス文字列
  	String userResponse ="";
  	String sessionState = "";
  	String contextOutName = "";
    try {
    	// セッションクリアフラグでの判定
    	if("false".equals(sessionClearFlg)){
    		// コンボボックスの送信がない場合
    		if(ChatUtil.isEmpty(changeTarget)){
        		// api.aiへリクエストを送信
        		AIResponse aiResponse = request(inputWord, session);
        		// コンテキストの取得
        		contextOutName = ChatUtil.getContextOutName(aiResponse.getResult().getContexts());
        		// コンテキストを見てapi.aiとのやり取り中か判定する。
        		sessionState = ChatUtil.getSessionState(contextOutName);
        		// セッションに値をセット
        		ChatUtil.setSessionValue(sessionState , contextOutName , aiResponse , null ,session);
        		// 状態と返答内容を送信
        		userResponse = ChatUtil.getUserResponse((String)session.getAttribute(ChatConstants.SESSION_KEY_STATE), session,  aiResponse.getResult().getFulfillment().getSpeech(), request);
    		}else{
        		// セッションに値をセット
        		ChatUtil.setSessionValue((String)session.getAttribute(ChatConstants.SESSION_KEY_STATE), changeTarget , null ,inputWord , session);
        		// 状態と返答内容を送信
        		userResponse =  ChatUtil.getUserResponse((String)session.getAttribute(ChatConstants.SESSION_KEY_STATE) ,session,  ChatConstants.ANSWER_REINPUT, request);
    		}

    	}else{
    		// セッションを破棄
    		session.invalidate();
    		// 初期状態の回答
        	userResponse = ChatUtil.getUserResponse(ChatConstants.SESSION_STATE_APIAIPROCESS , null, ChatConstants.ANSWER_RESTART, request);
    	}
        // responseへ書き込み
		response.setContentType("text/html; charset=UTF-8");
        response.getWriter().append(userResponse);
        
    } catch (Exception e) {
        // エラーであることを返却
    	userResponse = ChatUtil.getUserResponse(ChatConstants.SESSION_STATE_ERROR ,session, ChatConstants.ANSWER_ERROR, request);
        // responseへ書き込み
		response.setContentType("text/html; charset=UTF-8");
        response.getWriter().append(userResponse);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
