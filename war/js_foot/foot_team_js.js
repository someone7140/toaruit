$(function(){

  // 過去の試合結果を非表示
  hidePastResult();
  
  
  // 過去の試合結果を表示するボタンが押されたとき
  $("#displayResult").click(function(){
     	// 初期非表示のクラスを表示
		$(".inithide").show();
	    // ボタンを非表示
		$(this).hide();
  });
  
});

function hidePastResult(){
	// 初期非表示のクラスを非表示
	$(".inithide").hide();
}
