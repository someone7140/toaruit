function checkInputWord(word){
      // 未入力だった場合、NG
      if(word == null || word.length == 0){
         return false;
      }else{
         return true;
      }
}

function displayInputText(word){
    // 入力内容を表示
	$("#chatarea").append("<tr><td></td><td class = \"userarea\"><img src=\"image/fukidashi028.jpg\" width=\"330\" height=\"230\"/><span class = \"userword\">"+ word + "</span></td></tr>");
}

function getApiResult(inputWord , sessionClearFlg, changeTarget){
    // ajaxでクエリ送信
	var query = {};
    query["inputWord"] = inputWord;
    query["sessionClearFlg"] = sessionClearFlg;
    query["changeTarget"] = changeTarget;
	  // サーブレットへPOST
    $.post("/chatrequest", query, getApiResponse);
}

function getApiResponse(resp){
	// responseを区切り文字で分割
	var respArray = resp.split("|");
	// 画面に回答内容を表示
	$("#chatarea").append(getAnswer(respArray));
	// 最下段へスクロール
	scrollBottom();
	// URLのdummy判定
	if(respArray[6] != "dummy"){
		// 別タブで遷移
		window.open(respArray[6], "_blank");
		// 画面に再検索の案内を表示
		$("#chatarea").append("<tr><td class = \"machinearea\"><img src=\"image/r-fukidashi028.jpg\" width=\"330\" height=\"230\"/><span class = \"machineword\">再度検索する場合は、変更項目を選択の上再送信ください。</span></td><td></td></tr>");
		// 最下段へスクロール
		scrollBottom();
	}
	

}

function getAnswer(respArray){
	// レスポンス内容により、変更コンボボックスの表示を判定
	dispComboBox(respArray[0], respArray[1]);
	// 入力内容の表示
	dispInputArea(respArray[3], respArray[4], respArray[5]);
	// 回答を返す
	return  "<tr><td class = \"machinearea\"><img src=\"image/r-fukidashi028.jpg\" width=\"330\" height=\"230\"/><span class = \"machineword\">"+ respArray[2] + "</span></td><td></td></tr>";


}

function dispComboBox(sessionState , chatRoot){
	// コンボの子要素を取得
	var comboChilren = $("#changeselect").children();
    // セッション状態が地名が見つからず、項目がない場合
	if(sessionState == "location_notfind" && comboChilren.length == 0){
		// 地名のコンボを追加
		$("#changeselect").append("<option value=\"location\">地名</option>");
		// 地名を選択させる
		$("#changeselect").val("location");
	}
	
	// セッション状態が終了している場合
	if(sessionState == "apiai_end"){
		// 項目がない場合
		if(comboChilren.length == 0){
			// ルートが外食
			if(chatRoot == "outhouse"){
				// 食事と価格と地名をコンボへ加える
				$("#changeselect").append("<option value=\"food\">食べたいもの</option>");
				$("#changeselect").append("<option value=\"price\">価格</option>");
				$("#changeselect").append("<option value=\"location\">地名</option>");
			// ルートがネット
			}else if(chatRoot == "net"){
				// 食事と価格をコンボへ加える
				$("#changeselect").append("<option value=\"food\">食べたいもの</option>");
				$("#changeselect").append("<option value=\"price\">価格</option>");
			// ルートが出前
			}else{
				// 食事と地名をコンボへ加える
				$("#changeselect").append("<option value=\"food\">食べたいもの</option>");
				$("#changeselect").append("<option value=\"location\">地名</option>");
			}
			// コンボを表示
			  $("#selectarea").show();
		}else{
			// 配下要素の1個目が食事でない
			if(comboChilren.eq(0).val() != "food"){
				  // コンボボックスの設定をクリア
				  $("#changeselect").empty();
				  // 食事と地名をコンボへ加える
				  $("#changeselect").append("<option value=\"food\">食べたいもの</option>");
				  $("#changeselect").append("<option value=\"location\">地名</option>");
				  // コンボを表示
				  $("#selectarea").show();
			}
			
		}
	}
	
	
	
}

function dispInputArea(foodInput , locationInput, priceInput){
	  // 入力内容をクリア
	  $("#inputarea").empty();
	  var input = "";
	  // 食事の入力判定
	  if(checkInputWord(foodInput)){
		  input = input + "食べたいもの:" + foodInput;
	  }
	  
	  // 地名の入力判定
	  if(checkInputWord(locationInput) && locationInput != "null"){
		  if(checkInputWord(input)){
			  input = input + "、";
		  }
		  input = input + "地名:" + locationInput;
	  }
	  
	  // 価格の入力判定
	  if(checkInputWord(priceInput) && priceInput != "指定なし" && checkInputWord(input)){
		  if(checkInputWord(input)){
			  input = input + "、";
		  }
		  input = input + "価格:" + priceInput;
	  }
	  
	  $("#inputarea").append(input);

}

function scrollBottom(){
	// スクロールにかかる時間
	var time = 500;
	// 移動先となる要素を取得
    var target = $("#endarea");
    // 移動先となる値
    var targetY = target.offset().top
 // スクロールアニメーション
    $('html,body').animate({scrollTop: targetY}, time, 'swing');

}
