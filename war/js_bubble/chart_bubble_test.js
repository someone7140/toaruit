$(function(){
	// バブルチャートのデータ
	var bubleChartData = {
		datasets: [
		    {
			   // データ(1個目)
			   data: [{"x":10 ,"y":10, "r":30} ,],
			   // 色（1個目）
			   backgroundColor:[ "rgb(141,63,223)" ],
			   // ラベル
		       label: ["test1"] 
		    },
		    {
				// データ(2個目)
				data: [{"x":20 ,"y":20, "r":50} ,],
				// 色（2個目）
			    backgroundColor:[ "rgb(141,29,73)"],
				// ラベル（2個目）
			    label: ["test2"]  
			},
			{
				// データ(3個目)
				data: [{"x":30 ,"y":30, "r":70} ,],
				// 色（3個目）
			    backgroundColor:["rgb(16,230,73)"],
				// ラベル（3個目）
			    label: ["test3"]  
			}
		]};

	// オプション
	var options = {
		  // タイトル
          title: {
            display: true,
            text: 'バブルチャートテスト'
          },
          // スケール
          scales: {
        	  // x軸
              xAxes: [{
                  ticks: {max: 50, min: 0,stepSize: 10}
              }],
         	  // x軸
              yAxes: [{
                  ticks: {max: 50,min: 0,stepSize: 10}
              }]
          },
          // tooltip
          tooltips: {
              callbacks: {
                 label: function(t, d) {
                	var rLabel = d.datasets[t.datasetIndex].data[t.index].r;
                    return d.datasets[t.datasetIndex].label + 
                           ': (x軸:' + t.xLabel + ', y軸:' + t.yLabel + ', 円の大きさ:' + rLabel + ')';
                 }
              }
          },
          // クリック時の動作
          onClick: function(e) {
              var element = this.getElementAtEvent(e);

              // 1要素以上ある場合
              if (element.length > 0) {
                  // ラベル
                  var datasetLabel = this.config.data.datasets[element[0]._datasetIndex].label;
                  // データオブジェクト
                  var data = this.config.data.datasets[element[0]._datasetIndex].data[element[0]._index];
                  // アラートで値を表示
                  window.alert( "ラベル："+ datasetLabel + "、x軸："+ data.x + "、y軸："+ data.y + "、円の大きさ：" + data.r);
                  
              }
          }
	};
	
    // コンテキストのオブジェクト
	var ctx = $("#bubblechart")[0].getContext("2d");
	// バブルチャートの描画
	var bubbleChart = new Chart(ctx, 
	        {
			   type: 'bubble',
		       data: bubleChartData,
		       options: options
		    });
	
  
});
