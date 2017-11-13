

package com.chat.api;

public class ChatConstants {

    // 食事名のセッションキー
	public static final String SESSION_KEY_FOODNAME = "food_name";
    // 地名のセッションキー
	public static final String SESSION_KEY_LOCATIONNAME = "location_name";
    // 地名コードのセッションキー
	public static final String SESSION_KEY_LOCATIONCODE = "location_code";
	// 最低価格のセッションキー
	public static final String SESSION_KEY_LOWRPRISE = "low_price";
	// 最高価格のセッションキー
	public static final String SESSION_KEY_HIGHRPRISE = "high_price";
	// チャットのルートキー
	public static final String SESSION_KEY_CHATROOT = "chat_root";
	// セッション状態のキー
	public static final String SESSION_KEY_STATE = "session_state";
	
	// API.AIの処理中
	public static final String SESSION_STATE_APIAIPROCESS = "apiai_process";
	// API.AIの処理終了
	public static final String SESSION_STATE_APIAIEND = "apiai_end";
	// 地名が見つからない状態
	public static final String SESSION_STATE_LOCATION_NOTFIND = "location_notfind";
	// エラー
	public static final String SESSION_STATE_ERROR = "error";

	// 変更対象・食事
	public static final String CHANGE_FOOD = "food";
	// 変更対象・価格
	public static final String CHANGE_PRICE = "price";
	// 変更対象・地名
	public static final String CHANGE_LOCATION = "location";
	
	// エラー時の回答
	public static final String ANSWER_ERROR = "エラーが発生しました。再度入力内容を送信ください。";
	// 最初からやる場合の回答
	public static final String ANSWER_RESTART = "まずは食べたいものを入力してください。";
	// 再回答
	public static final String ANSWER_REINPUT = "検索しています、少々お待ちください。";
	// 地名が見つからない場合の回答
	public static final String ANSWER_LOCATION_NOTFIND = "地名が見つかりません、再度入力の上送信してください。";
	
	// コンテキストのend文字
	public static final String CONTEXT_END = "end";
	
	// 外食のルート
	public static final String CHAT_ROOT_OUTHOUSE = "outhouse";
	// 出前のルート
	public static final String CHAT_ROOT_DELIVERY= "delivery";
	// ネットショッピングのルート
	public static final String CHAT_ROOT_NET= "net";
	
	// APIAIのfoodパラメータ
	public static final String APIAI_PARM_FOOD = "food";
	// APIAIの最低価格パラメータ
	public static final String APIAI_PARM_LOWPRICE = "number1";
	// APIAIの最高価格パラメータ
	public static final String APIAI_PARM_HIGHPRICE = "number2";
	// APIAIの地名パラメータ
	public static final String APIAI_PARM_LOCATIONNAME = "city";
	
	// dummyのURL
	public static final String URL_DUMMY = "dummy";
	// 楽天デリバリーのURL
	public static final String URL_RAKUTEN_DELIVERY = "https://delivery.rakuten.co.jp/?module=Default&action=Area&";
	
}


