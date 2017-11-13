$(function(){
  // 読み込み時は一覧部分をすべて非表示
  $("div").each(function() {
	    $(this).hide();
  });
  
  // 都道府県チェックボックスが変わったとき
  $("input[type='checkbox']").change(function(){
		// クラスより都道府県IDを取得
		var inputClass = $(this).attr("class");
		// チェック有無を取得
		var chkFlg = $(this).prop("checked");
		// divでループ。
		$("div").each(function() {
			var divClass = $(this).attr("class");
			// カンマが含まれるか
		    if(divClass.indexOf(",") != -1){
				// カンマでスプリット
		    	var divClassArray = divClass.split(",");
		    	// 取得した配列でループ
		    	for (var prop in divClassArray) {
		    		divClassArray[prop];
		            // IDが同じ場合
		            if(inputClass == divClassArray[prop]){
		    			// チェック判定
		    			if(chkFlg){
		    				$(this).show();
		    			}else{
		    				$(this).hide();
		    			}
		            }
		    	}
			}else{
				// IDが同じ場合
				if(inputClass == divClass){
					// チェック判定
					if(chkFlg){
						$(this).show();
					}else{
						$(this).hide();
					}
				}
			}
		 });
	
  });
});

