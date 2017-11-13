$(function(){

  // 年のコンボボックスを追加
  addYearCombo();
  
  
  // 順位表を表示するボタンが押されたとき
  $("#displayRank").click(function(){
	    // ボタンを無効化
	    $("#displayRank").attr('disabled', 'disabled');
	    // ランキング表は以下の要素を削除
	    $("#ranking").empty();
	    // ローディング画像を表示
		$("#ranking").append("<div id=\"loading\"><img src=\"image/loading.gif\"></div>");
	    //ランキング取得のリクエストを送信
	    sendRequestRank();
  });
  
});

function addYearCombo(){
	//現在の年月を取得
	var nowDate = new Date();
	var nowMonth = nowDate.getMonth()+1;
	var nowYear = nowDate.getFullYear();
	// 月が9月より前
	if(nowMonth < 9){
		// 年を現在より-1する。
		nowYear = nowYear -1
	}
	// 現在年のシーズンを追加
	$("#seasoncombo").append("<option value=\"" + nowYear + "\">" + nowYear + "-" + (nowYear + 1) + "</option>");
	// 一年前のシーズンを追加
	$("#seasoncombo").append("<option value=\"" + (nowYear - 1) + "\">" + (nowYear - 1) + "-" + nowYear +  "</option>");
	// 二年前のシーズンを追加
	$("#seasoncombo").append("<option value=\"" + (nowYear - 2) + "\">" + (nowYear - 2) + "-" + (nowYear - 1)  +  "</option>");
	// 三年前のシーズンを追加
	$("#seasoncombo").append("<option value=\"" + (nowYear - 3) + "\">" + (nowYear - 3) + "-" + (nowYear - 2)  +  "</option>");
}

function sendRequestRank(){
    // ajaxでクエリ送信
	var query = {};
    query["seasonYear"] = $("#seasoncombo").val();
    query["league"] = $("#leaguecombo").val();
	  // サーブレットへPOST
    $.post("/footrankapi", query, apiRankResponse);
}

function apiRankResponse(resp){
	// ローディング画像を削除
	$("#loading").remove();
	// responsehtmlを追加
	$("#ranking").append(resp);
	// HOME行を非表示
	$(".home").hide();
	// AWAY行を非表示
	$(".away").hide();
	// HOME&AWAYの表示または非表示が押されたときのアクションを追加」
	$(".homeawaydisp").click(function(){
	        // IDを取得
		    var idValue = $(this).attr("id");
		    // HOMEの行ID
		    var homeId = "#home" + idValue;
		    // AWAYの行ID
		    var awayId = "#away" + idValue;
		    
		    // 文字が表示
		    if( $(this).val() == "表示"){
		    	// HOME行を表示
		    	$(homeId).show();
		    	// AWAY行を表示
		    	$(awayId).show();
		    	// ボタン名を非表示
		    	$(this).val("非表示");
		    // 文字が非表示
		    }else{
		    	// HOME行を非表示
		    	$(homeId).hide();
		    	// AWAY行を非表示
		    	$(awayId).hide();
		    	// ボタン名を表示
		    	$(this).val("表示");
		    }
	 });
	 // ボタンを有効化
	 $("#displayRank").removeAttr('disabled');

}