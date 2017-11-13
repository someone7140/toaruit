$(function(){
  // 整数以外は初期で非表示
  $("#sahenseisuu").css({ "display" :"" });
  $("#sahenseisuub").css({ "display" :"none" });
  $("#sahensyousuuten").css({ "display" :"none" });
  $("#sahensyousuub").css({ "display" :"none" });
  $("#sahenheihouseisuub").css({ "display" :"none" });
  $("#sahenroute").css({ "display" :"none" });
  $("#sahenheihoub").css({ "display" :"none" });
  $(".sahenshiki").css({ "display" :"none" });
  $("#sahenseisuucounter").css({ "display" :"none" });
  $("#sahenheihouseisuubcounter").css({ "display" :"none" });
  $("#sahenroutecounter").css({ "display" :"none" });
  $("#sahenheihoubcounter").css({ "display" :"none" });


  $("#uhenseisuu").css({ "display" :"" });
  $("#uhenseisuub").css({ "display" :"none" });
  $("#uhensyousuuten").css({ "display" :"none" });
  $("#uhensyousuub").css({ "display" :"none" });
  $("#uhenheihouseisuub").css({ "display" :"none" });
  $("#uhenroute").css({ "display" :"none" });
  $("#uhenheihoub").css({ "display" :"none" });
  $(".uhenshiki").css({ "display" :"none" });
  $("#uhenseisuucounter").css({ "display" :"none" });
  $("#uhenheihouseisuubcounter").css({ "display" :"none" });
  $("#uhenroutecounter").css({ "display" :"none" });
  $("#uhenheihoubcounter").css({ "display" :"none" });

  // プルダウンは整数を初期表示
  $("#kubunsahen").val("seisuu");
  $("#kubunuhen").val("seisuu");

  $("#manual").css({ "display" :"none" });
  $("#result").append("&nbsp;&nbsp;&nbsp;&nbsp;");

  $("#kubunsahen").change(function(){
      var kubunsahen = $("#kubunsahen option:selected").val();

      // 右辺か左辺が計算式の場合は、横幅を1700へ広げる
      if(kubunsahen =="shiki" || $("#kubunuhen option:selected").val() == "shiki"){
          $("#size").attr("width","1700");
      }else{
          $("#size").attr("width","1200");
      }
      // 整数の場合
      if( kubunsahen == "seisuu" ){
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenseisuub").css({ "display" :"none" });
           $("#sahensyousuuten").css({ "display" :"none" });
           $("#sahensyousuub").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });

      // 小数の場合
      }else if( kubunsahen == "syousuu"){
           $("#sahenseisuu").css({ "display" :"none" });
           $("#sahenseisuub").css({ "display" :"" });
           $("#sahensyousuuten").css({ "display" :"" });
           $("#sahensyousuub").css({ "display" :"" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });

      // 平方根の場合
      }else if( kubunsahen == "route"){
           $("#sahenseisuu").css({ "display" :"none" });
           $("#sahenseisuub").css({ "display" :"none" });
           $("#sahensyousuuten").css({ "display" :"none" });
           $("#sahensyousuub").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"" });
           $("#sahenroute").css({ "display" :"" });
           $("#sahenheihoub").css({ "display" :"" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });
      }else{
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenseisuub").css({ "display" :"none" });
           $("#sahensyousuuten").css({ "display" :"none" });
           $("#sahensyousuub").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });
           $(".sahenshiki").css({ "display" :"" });
           $("#sahenseisuucounter").css({ "display" :"" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });

           // プルダウンは整数を初期表示
           $("#sahenshikikubun").val("seisuu");
           $("#sahenshikikubuncounter").val("seisuu");

      }

   });

  $("#kubunuhen").change(function(){
      var kubunuhen = $("#kubunuhen option:selected").val();
      // 右辺か左辺が計算式の場合は、横幅を1700へ広げる
      if(kubunuhen =="shiki" || $("#kubunsahen option:selected").val() == "shiki"){
          $("#size").attr("width","1700");
      }else{
          $("#size").attr("width","1200");
      }
      // 整数の場合
      if( kubunuhen == "seisuu" ){
           $("#uhenseisuu").css({ "display" :"" });
           $("#uhenseisuub").css({ "display" :"none" });
           $("#uhensyousuuten").css({ "display" :"none" });
           $("#uhensyousuub").css({ "display" :"none" });
           $("#uhenheihouseisuub").css({ "display" :"none" });
           $("#uhenroute").css({ "display" :"none" });
           $("#uhenheihoub").css({ "display" :"none" });
           $(".uhenshiki").css({ "display" :"none" });
           $("#uhenseisuucounter").css({ "display" :"none" });
           $("#uhenheihouseisuubcounter").css({ "display" :"none" });
           $("#uhenroutecounter").css({ "display" :"none" });
           $("#uhenheihoubcounter").css({ "display" :"none" });

      // 小数の場合
      }else if( kubunuhen == "syousuu"){
           $("#uhenseisuu").css({ "display" :"none" });
           $("#uhenseisuub").css({ "display" :"" });
           $("#uhensyousuuten").css({ "display" :"" });
           $("#uhensyousuub").css({ "display" :"" });
           $("#uhenheihouseisuub").css({ "display" :"none" });
           $("#uhenroute").css({ "display" :"none" });
           $("#uhenheihoub").css({ "display" :"none" });
           $(".uhenshiki").css({ "display" :"none" });
           $("#uhenseisuucounter").css({ "display" :"none" });
           $("#uhenheihouseisuubcounter").css({ "display" :"none" });
           $("#uhenroutecounter").css({ "display" :"none" });
           $("#uhenheihoubcounter").css({ "display" :"none" });

      // 平方根の場合
      }else if( kubunuhen == "route"){
           $("#uhenseisuu").css({ "display" :"none" });
           $("#uhenseisuub").css({ "display" :"none" });
           $("#uhensyousuuten").css({ "display" :"none" });
           $("#uhensyousuub").css({ "display" :"none" });
           $("#uhenheihouseisuub").css({ "display" :"" });
           $("#uhenroute").css({ "display" :"" });
           $("#uhenheihoub").css({ "display" :"" });
           $(".uhenshiki").css({ "display" :"none" });
           $("#uhenseisuucounter").css({ "display" :"none" });
           $("#uhenheihouseisuubcounter").css({ "display" :"none" });
           $("#uhenroutecounter").css({ "display" :"none" });
           $("#uhenheihoubcounter").css({ "display" :"none" });

      }else{
           $("#uhenseisuu").css({ "display" :"" });
           $("#uhenseisuub").css({ "display" :"none" });
           $("#uhensyousuuten").css({ "display" :"none" });
           $("#uhensyousuub").css({ "display" :"none" });
           $("#uhenheihouseisuub").css({ "display" :"none" });
           $("#uhenroute").css({ "display" :"none" });
           $("#uhenheihoub").css({ "display" :"none" });
           $(".uhenshiki").css({ "display" :"" });
           $("#uhenseisuucounter").css({ "display" :"" });
           $("#uhenheihouseisuubcounter").css({ "display" :"none" });
           $("#uhenroutecounter").css({ "display" :"none" });
           $("#uhenheihoubcounter").css({ "display" :"none" });

           // プルダウンは整数を初期表示
           $("#uhenshikikubun").val("seisuu");
           $("#uhenshikikubuncounter").val("seisuu");

      }

   });


  $("#sahenshikikubun").change(function(){
      var kubunsahen = $("#sahenshikikubun option:selected").val();
      // 整数の場合
      if( kubunsahen == "seisuu" ){
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenseisuub").css({ "display" :"none" });
           $("#sahensyousuuten").css({ "display" :"none" });
           $("#sahensyousuub").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });

      }else{
           $("#sahenseisuu").css({ "display" :"none" });
           $("#sahenseisuub").css({ "display" :"none" });
           $("#sahensyousuuten").css({ "display" :"none" });
           $("#sahensyousuub").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"" });
           $("#sahenroute").css({ "display" :"" });
           $("#sahenheihoub").css({ "display" :"" });
      }

   });

  $("#sahenshikikubuncounter").change(function(){
      var kubunsahen = $("#sahenshikikubuncounter option:selected").val();
      // 整数の場合
      if( kubunsahen == "seisuu" ){
        $("#sahenseisuucounter").css({ "display" :"" });
        $("#sahenheihouseisuubcounter").css({ "display" :"none" });
        $("#sahenroutecounter").css({ "display" :"none" });
        $("#sahenheihoubcounter").css({ "display" :"none" });

      }else{
        $("#sahenseisuucounter").css({ "display" :"none" });
        $("#sahenheihouseisuubcounter").css({ "display" :"" });
        $("#sahenroutecounter").css({ "display" :"" });
        $("#sahenheihoubcounter").css({ "display" :"" });
      }

   });

  $("#uhenshikikubun").change(function(){
      var kubunuhen = $("#uhenshikikubun option:selected").val();
      // 整数の場合
      if( kubunuhen == "seisuu" ){
           $("#uhenseisuu").css({ "display" :"" });
           $("#uhenseisuub").css({ "display" :"none" });
           $("#uhensyousuuten").css({ "display" :"none" });
           $("#uhensyousuub").css({ "display" :"none" });
           $("#uhenheihouseisuub").css({ "display" :"none" });
           $("#uhenroute").css({ "display" :"none" });
           $("#uhenheihoub").css({ "display" :"none" });

      }else{
           $("#uhenseisuu").css({ "display" :"none" });
           $("#uhenseisuub").css({ "display" :"none" });
           $("#uhensyousuuten").css({ "display" :"none" });
           $("#uhensyousuub").css({ "display" :"none" });
           $("#uhenheihouseisuub").css({ "display" :"" });
           $("#uhenroute").css({ "display" :"" });
           $("#uhenheihoub").css({ "display" :"" });
      }

   });

  $("#uhenshikikubuncounter").change(function(){
      var kubunuhen = $("#uhenshikikubuncounter option:selected").val();
      // 整数の場合
      if( kubunuhen == "seisuu" ){
        $("#uhenseisuucounter").css({ "display" :"" });
        $("#uhenheihouseisuubcounter").css({ "display" :"none" });
        $("#uhenroutecounter").css({ "display" :"none" });
        $("#uhenheihoubcounter").css({ "display" :"none" });

      }else{
        $("#uhenseisuucounter").css({ "display" :"none" });
        $("#uhenheihouseisuubcounter").css({ "display" :"" });
        $("#uhenroutecounter").css({ "display" :"" });
        $("#uhenheihoubcounter").css({ "display" :"" });
      }

   });

  $("#calculate").click(function(){
      // テキストボックスの数字チェック
      if( checkNumber()){
             // 左辺のオブジェクト化
             var sahenKubun = $("#kubunsahen option:selected").val();
             if(sahenKubun == "seisuu"){
                var sahen = objectCreate(sahenKubun, $("#sahenenzan option:selected").val(), $("#sahenseisuu").val(), null );
             // 小数の場合
             }else if (sahenKubun == "syousuu"){
                var sahen = syousuuCreate(sahenKubun, $("#sahenenzan option:selected").val(), $("#sahenseisuub").val(), $("#sahensyousuub").val() );
             }
             // 平方根の場合
             else if (sahenKubun == "route"){
                var sahenheihouseisuub = $("#sahenheihouseisuub").val();
                // 整数部がnullの場合、1を代入
                if(sahenheihouseisuub == ""){
                   sahenheihouseisuub = 1;
                }
                var sahen = objectCreate(sahenKubun, $("#sahenenzan option:selected").val(), sahenheihouseisuub, $("#sahenheihoub").val() );
                sahen = adjustRoute(sahen);
             }
             else{
                var sahen;
                var sahenCounter;
                var sahenShikiKubun = $("#sahenshikikubun option:selected").val();
                // 整数の場合
                if(sahenShikiKubun == "seisuu"){
                   sahen = objectCreate(sahenShikiKubun, $("#sahenenzan option:selected").val(), $("#sahenseisuu").val(), null );
                // 平方根の場合
                }else{
                   var sahenheihouseisuub = $("#sahenheihouseisuub").val();
                   // 整数部がnullの場合、1を代入
                   if(sahenheihouseisuub == ""){
                      sahenheihouseisuub = 1;
                   }
                   sahen = objectCreate(sahenShikiKubun, $("#sahenenzan option:selected").val(), sahenheihouseisuub, $("#sahenheihoub").val() );
                   sahen = adjustRoute(sahen);
                }
                var sahenShikiKubunCounter = $("#sahenshikikubuncounter option:selected").val();
                // 整数の場合
                if(sahenShikiKubunCounter == "seisuu"){
                   sahenCounter = objectCreate(sahenShikiKubunCounter, $("#sahenenzancounter option:selected").val(), $("#sahenseisuucounter").val(), null );
                // 平方根の場合
                }else{
                   var sahenheihouseisuubCounter = $("#sahenheihouseisuubcounter").val();
                   // 整数部がnullの場合、1を代入
                   if(sahenheihouseisuubCounter == ""){
                      sahenheihouseisuubCounter = 1;
                   }
                   sahenCounter = objectCreate(sahenShikiKubunCounter, $("#sahenenzancounter option:selected").val(), sahenheihouseisuubCounter, $("#sahenheihoubcounter").val() );
                   sahenCounter = adjustRoute(sahenCounter);
                }
                sahen = adjustShiki(sahen, sahenCounter);
             }

             // 右辺のオブジェクト化
             var uhenKubun = $("#kubunuhen option:selected").val();
             if(uhenKubun == "seisuu"){
                var uhen = objectCreate($("#kubunuhen option:selected").val(), $("#uhenenzan option:selected").val(), $("#uhenseisuu").val(), null );
             // 小数の場合
             }else if (uhenKubun == "syousuu"){
                var uhen = syousuuCreate(uhenKubun, $("#uhenenzan option:selected").val(), $("#uhenseisuub").val(), $("#uhensyousuub").val() );
             }
             // 平方根の場合
             else if (uhenKubun == "route"){
                var uhenheihouseisuub = $("#uhenheihouseisuub").val();
                // 整数部がnullの場合、1を代入
                if(uhenheihouseisuub == ""){
                   uhenheihouseisuub = 1;
                }
                var uhen = objectCreate(uhenKubun, $("#uhenenzan option:selected").val(), uhenheihouseisuub, $("#uhenheihoub").val() );
                uhen = adjustRoute(uhen);
             }else{
                var uhen;
                var uhenCounter;
                var uhenShikiKubun = $("#uhenshikikubun option:selected").val();
                // 整数の場合
                if(uhenShikiKubun == "seisuu"){
                   uhen = objectCreate(uhenShikiKubun, $("#uhenenzan option:selected").val(), $("#uhenseisuu").val(), null );
                // 平方根の場合
                }else{
                   var uhenheihouseisuub = $("#uhenheihouseisuub").val();
                   // 整数部がnullの場合、1を代入
                   if(uhenheihouseisuub == ""){
                      uhenheihouseisuub = 1;
                   }
                   uhen = objectCreate(uhenShikiKubun, $("#uhenenzan option:selected").val(), uhenheihouseisuub, $("#uhenheihoub").val() );
                   uhen = adjustRoute(uhen);
                }
                var uhenShikiKubunCounter = $("#uhenshikikubuncounter option:selected").val();
                // 整数の場合
                if(uhenShikiKubunCounter == "seisuu"){
                   uhenCounter = objectCreate(uhenShikiKubunCounter, $("#uhenenzancounter option:selected").val(), $("#uhenseisuucounter").val(), null );
                // 平方根の場合
                }else{
                   var uhenheihouseisuubCounter = $("#uhenheihouseisuubcounter").val();
                   // 整数部がnullの場合、1を代入
                   if(uhenheihouseisuubCounter == ""){
                      uhenheihouseisuubCounter = 1;
                   }
                   uhenCounter = objectCreate(uhenShikiKubunCounter, $("#uhenenzancounter option:selected").val(), uhenheihouseisuubCounter, $("#uhenheihoubcounter").val() );
                   uhenCounter = adjustRoute(uhenCounter);
                }
                uhen = adjustShiki(uhen, uhenCounter);
             }

             // 計算処理
             if(sahen.kubun == "shiki" || uhen.kubun == "shiki"){
                var keisankekka = shikiKeisan(sahen, uhen, $("#enzan option:selected").val());
             }else{
                var keisankekka = keisanUtil(sahen, uhen, $("#enzan option:selected").val());
             }

             $("#result").empty();
             $("#result").append("&nbsp;&nbsp;&nbsp;&nbsp;" + keisankekka);

      }else{
          alert("\u6570\u5b57\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044");
      }

   });


  $("#reference").click(function(){
    // 使用方法が非表示の場合
    if( $("#manual").css("display") == "none" ){
       $("#manual").css({ "display" :"" });
    }else{
       $("#manual").css({ "display" :"none" });
    }

  });

});


