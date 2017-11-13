$(function(){

  // 送信ボタンが押されたとき
  $("#chatbutton").click(function(){
	  //リクエストを送信
	  sendRequest();
  });
  
  // テキストボックスでenterが押されたとき
  $("#chatinput").keypress( function ( e ) {
	   //13がenterキー
	   if ( e.which == 13 ) {
		  //リクエストを送信
		  sendRequest();
	   }
  } );
  
  // 最初からやりなおすボタンが押されたとき
  $("#clearbutton").click(function(){
	  //セッションクリアを行う
	  clearSession();
	  // コンボボックスの設定をクリア
	  $("#changeselect").empty();
	  // 入力内容をクリア
	  $("#inputarea").empty();
	  //コンボボックスを非表示
	  $("#selectarea").hide();
  });
  
  
});

function sendRequest(){
	// 空白チェック
	if(checkInputWord($("#chatinput").val())){
		// 入力内容を画面へ表示
		displayInputText($("#chatinput").val());
		// コンボの選択内容
		var comboSelect = $("#changeselect").val();
		if( !checkInputWord(comboSelect) ){
			// コンボの内容空白でリクエスト送受信の結果を反映
			getApiResult($("#chatinput").val(), "false", "");
		}else{
			getApiResult($("#chatinput").val(), "false", comboSelect);
		}

		
	}

	// テキストボックスの内容をクリア
	$("#chatinput").val("");
	
}

function clearSession(){
	// セッションクリアのリクエスト
	getApiResult("", "true", "");
	// 最下段へスクロール
	scrollBottom();
	// テキストボックスの内容をクリア
	$("#chatinput").val("");

}