$(function(){
    // ウィンドウのスクロール
	$(window).scroll(function (ev) {
		var scrollHeight = $(document).height();
		var scrollPosition = $(window).height() + $(window).scrollTop();
		// 最下段（高さの1%未満）
	    if ( (scrollHeight - scrollPosition) < scrollHeight / 100) {
	    	// 処理中フラグを取得
	    	var processFlg = $("#processFlg").val();
	    	// pageのnotnull判定と処理中の判定
	    	if($("#currentpage").val() != null && $("#pageCount").val() != null && processFlg != null && processFlg == "false"){
		    	// 現状のpage数を取得
		    	var currentPage = parseInt( $("#currentpage").val() );
		    	// トータルpage数を取得
		    	var totalPage = parseInt( $("#pageCount").val() );
		    	
		    	// 現状よりトータルが多い場合
		    	if( (currentPage + 10 ) < totalPage){
		    		// ローディング画像を表示
		    		$("#foodOutSearch").append("<div id=\"loading\"><img src=\"image/loading.gif\"></div>");
		    		// 処理中フラグを立てる
		    		$("#processFlg").val("true");
		    		// リクエストを送信
		    		foodOutSearchRequest(currentPage + 10);
		    	}
	    	}
	    }
	});

});

function foodOutSearchRequest(page){
	var query = {};
    query["page"] = page;
    query["foodName"] =  $("#foodName").val();
    query["lowprice"] =  $("#lowprice").val();
    query["highprice"] =  $("#highprice").val();
    query["location"] =  $("#location").val();
	  // 食事ネット検索用のサーブレットへPOST
    $.post("/foodoutsearchapi", query, foodOutSearchResp);

}

function foodOutSearchResp(resp){
	// ローディング画像を削除
	$("#loading").remove();
	// responsehtmlを追加
	$("#foodOutResult").append(resp);
	// 現状のpage数を取得
	var currentPage = parseInt( $("#currentpage").val() );
	// page数のhiddenを更新
	$("#currentpage").val(currentPage + 10);
	// 処理中フラグを戻す
	$("#processFlg").val("false");
	

}
