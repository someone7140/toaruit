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
		    	if(currentPage < totalPage){
		    		// ローディング画像を表示
		    		$("#responseArtistSearch").append("<div id=\"loading\"><img src=\"image/loading.gif\"></div>");
		    		// 処理中フラグを立てる
		    		$("#processFlg").val("true");
		    		// リクエストを送信
		    		musicSearchArtistRequest(currentPage + 1);
		    	}
	    	}
	    }
	});

});

function musicSearchArtistRequest(page){
	var query = {};
    query["page"] = page;
	  // アーティスト検索用のサーブレットへPOST
    $.post("/musicsearchartistapi", query, musicSearchArtistResp);

}

function musicSearchArtistResp(resp){
	// ローディング画像を削除
	$("#loading").remove();
	// responsehtmlを追加
	$("#artistResult").append(resp);
	// 現状のpage数を取得
	var currentPage = parseInt( $("#currentpage").val() );
	// page数のhiddenを更新
	$("#currentpage").val(currentPage + 1);
	// 処理中フラグを戻す
	$("#processFlg").val("false");
	

}
