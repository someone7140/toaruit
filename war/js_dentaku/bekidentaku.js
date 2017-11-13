$(function(){
  // 整数以外は初期で非表示
  $(".hibunsuu").css({ "display" :"" });
  $("#sahenseisuu").css({ "display" :"" });
  $("#sahenheihouseisuub").css({ "display" :"none" });
  $("#sahenroute").css({ "display" :"none" });
  $("#sahenheihoub").css({ "display" :"none" });
  $(".sahenshiki").css({ "display" :"none" });
  $("#sahenseisuucounter").css({ "display" :"none" });
  $("#sahenheihouseisuubcounter").css({ "display" :"none" });
  $("#sahenroutecounter").css({ "display" :"none" });
  $("#sahenheihoubcounter").css({ "display" :"none" });

  $(".bunsuu").css({ "display" :"none" });
  $("#sahenseisuubunbo").css({ "display" :"none" });
  $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
  $("#sahenroutebunbo").css({ "display" :"none" });
  $("#sahenheihoubbunbo").css({ "display" :"none" });
  $(".sahenshikibunbo").css({ "display" :"none" });
  $("#sahenseisuucounterbunbo").css({ "display" :"none" });
  $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
  $("#sahenroutecounterbunbo").css({ "display" :"none" });
  $("#sahenheihoubcounterbunbo").css({ "display" :"none" });

  $("#sahenlinejisuu").css({ "display" :"none" });
  $("#sahenlineroute").css({ "display" :"none" });
  $("#sahenlineshiki").css({ "display" :"none" });

  // プルダウンは整数を初期表示
  $("#kubunsahen").val("seisuu");
  $("#kubunsahenbunbo").val("seisuu");
  $("#manual").css({ "display" :"none" });
  $("#result").append("&nbsp;&nbsp;&nbsp;&nbsp");
  $("#resultbunsuu").append("&nbsp;&nbsp;&nbsp;&nbsp");

  $("#kubunsahen").change(function(){
      var kubunsahen = $("#kubunsahen option:selected").val();
      // 整数の場合
      if( kubunsahen == "seisuu" ){
           $(".hibunsuu").css({ "display" :"" });
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });
           $(".bunsuu").css({ "display" :"none" });
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });

      // 平方根の場合
      }else if( kubunsahen == "route"){
           $(".hibunsuu").css({ "display" :"" });
           $("#sahenseisuu").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"" });
           $("#sahenroute").css({ "display" :"" });
           $("#sahenheihoub").css({ "display" :"" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });
           $(".bunsuu").css({ "display" :"none" });
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });
      // 計算式の場合
      }else if( kubunsahen == "shiki"){
           $(".hibunsuu").css({ "display" :"" });
           $("#sahenseisuu").css({ "display" :"" });
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
           $(".bunsuu").css({ "display" :"none" });
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });
      // 分数の場合
      }else{
           $(".hibunsuu").css({ "display" :"none" });
           $(".bunsuu").css({ "display" :"" });
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });

           $("#sahenseisuubunbo").css({ "display" :"" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });
           $("#kubunsahenbunsuu").val("bunsuu");
           $("#kubunsahenbunshi").val("seisuu");
           $("#kubunsahenbunbo").val("seisuu");

      }
      layoutChange();
   });

  $("#kubunsahenbunsuu").change(function(){
      var kubunsahen = $("#kubunsahenbunsuu option:selected").val();
      // 整数の場合
      if( kubunsahen == "seisuu" ){
           $(".hibunsuu").css({ "display" :"" });
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });
           $(".bunsuu").css({ "display" :"none" });
           $("#kubunsahen").val("seisuu");
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });

      // 平方根の場合
      }else if( kubunsahen == "route"){
           $(".hibunsuu").css({ "display" :"" });
           $("#sahenseisuu").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"" });
           $("#sahenroute").css({ "display" :"" });
           $("#sahenheihoub").css({ "display" :"" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });
           $(".bunsuu").css({ "display" :"none" });
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });
           $("#kubunsahen").val("route");
      // 計算式の場合
      }else if( kubunsahen == "shiki"){
           $(".hibunsuu").css({ "display" :"" });
           $("#sahenseisuu").css({ "display" :"" });
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
           $(".bunsuu").css({ "display" :"none" });
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });
           $("#kubunsahen").val("shiki");
      // 分数の場合
      }else{
           $(".hibunsuu").css({ "display" :"none" });
           $(".bunsuu").css({ "display" :"" });
           $("#sahenseisuubunbo").css({ "display" :"" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });

      }
      layoutChange();

   });


  $("#kubunsahenbunshi").change(function(){
      var kubunsahen = $("#kubunsahenbunshi option:selected").val();
      // 整数の場合
      if( kubunsahen == "seisuu" ){
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });
           layoutChange();

      // 平方根の場合
      }else if( kubunsahen == "route"){
           $("#sahenseisuu").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"" });
           $("#sahenroute").css({ "display" :"" });
           $("#sahenheihoub").css({ "display" :"" });
           $(".sahenshiki").css({ "display" :"none" });
           $("#sahenseisuucounter").css({ "display" :"none" });
           $("#sahenheihouseisuubcounter").css({ "display" :"none" });
           $("#sahenroutecounter").css({ "display" :"none" });
           $("#sahenheihoubcounter").css({ "display" :"none" });
           layoutChange();
      // 計算式の場合
      }else{
           $("#sahenseisuu").css({ "display" :"" });
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
           layoutChange();
       }

   });

  $("#kubunsahenbunbo").change(function(){
      layoutChange();
      var kubunsahenbunbo = $("#kubunsahenbunbo option:selected").val();
      // 整数の場合
      if( kubunsahenbunbo == "seisuu" ){
           $("#sahenseisuubunbo").css({ "display" :"" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });

      // 平方根の場合
      }else if( kubunsahenbunbo == "route"){
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"" });
           $("#sahenroutebunbo").css({ "display" :"" });
           $("#sahenheihoubbunbo").css({ "display" :"" });
           $(".sahenshikibunbo").css({ "display" :"none" });
           $("#sahenseisuucounterbunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });
      }else{
           $("#sahenseisuubunbo").css({ "display" :"" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });
           $(".sahenshikibunbo").css({ "display" :"" });
           $("#sahenseisuucounterbunbo").css({ "display" :"" });
           $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#sahenroutecounterbunbo").css({ "display" :"none" });
           $("#sahenheihoubcounterbunbo").css({ "display" :"none" });

           // プルダウンは整数を初期表示
           $("#sahenshikikubunbunbo").val("seisuu");
           $("#sahenshikikubuncounterbunbo").val("seisuu");

      }

   });


  $("#sahenshikikubun").change(function(){
      layoutChange();
      var kubunsahen = $("#sahenshikikubun option:selected").val();
      // 整数の場合
      if( kubunsahen == "seisuu" ){
           $("#sahenseisuu").css({ "display" :"" });
           $("#sahenheihouseisuub").css({ "display" :"none" });
           $("#sahenroute").css({ "display" :"none" });
           $("#sahenheihoub").css({ "display" :"none" });

      }else{
           $("#sahenseisuu").css({ "display" :"none" });
           $("#sahenheihouseisuub").css({ "display" :"" });
           $("#sahenroute").css({ "display" :"" });
           $("#sahenheihoub").css({ "display" :"" });
      }

   });


  $("#sahenshikikubunbunbo").change(function(){
      layoutChange();
      var kubunsahenbunbo = $("#sahenshikikubunbunbo option:selected").val();
      // 整数の場合
      if( kubunsahenbunbo == "seisuu" ){
           $("#sahenseisuubunbo").css({ "display" :"" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"none" });
           $("#sahenroutebunbo").css({ "display" :"none" });
           $("#sahenheihoubbunbo").css({ "display" :"none" });

      }else{
           $("#sahenseisuubunbo").css({ "display" :"none" });
           $("#sahenheihouseisuubbunbo").css({ "display" :"" });
           $("#sahenroutebunbo").css({ "display" :"" });
           $("#sahenheihoubbunbo").css({ "display" :"" });
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



  $("#sahenshikikubuncounterbunbo").change(function(){
      var kubunsahen = $("#sahenshikikubuncounterbunbo option:selected").val();
      // 整数の場合
      if( kubunsahen == "seisuu" ){
        $("#sahenseisuucounterbunbo").css({ "display" :"" });
        $("#sahenheihouseisuubcounterbunbo").css({ "display" :"none" });
        $("#sahenroutecounterbunbo").css({ "display" :"none" });
        $("#sahenheihoubcounterbunbo").css({ "display" :"none" });

      }else{
        $("#sahenseisuucounterbunbo").css({ "display" :"none" });
        $("#sahenheihouseisuubcounterbunbo").css({ "display" :"" });
        $("#sahenroutecounterbunbo").css({ "display" :"" });
        $("#sahenheihoubcounterbunbo").css({ "display" :"" });
      }

   });


  $(".calculate").click(function(){
      // テキストボックスの数字チェック
      var numberCheck = checkNumber();
      var zeroCheck = checkNumberZero();
      if( numberCheck && zeroCheck){
             // 左辺のオブジェクト化
             var sahenKubun = $("#kubunsahen option:selected").val();
             if(sahenKubun == "seisuu"){
                var sahen = objectCreate(sahenKubun, $("#sahenenzan option:selected").val(), $("#sahenseisuu").val(), null );
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
             // 計算式の場合
             else if (sahenKubun == "shiki"){
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
             // 分数の場合
             }else{
                // 左辺分子のオブジェクト化
                var sahenKubunBunshi = $("#kubunsahenbunshi option:selected").val();
                if(sahenKubunBunshi == "seisuu"){
                   var sahen = objectCreate(sahenKubunBunshi, $("#sahenenzan option:selected").val(), $("#sahenseisuu").val(), null );
                }
                // 平方根の場合
                else if (sahenKubunBunshi == "route"){
                   var sahenheihouseisuub = $("#sahenheihouseisuub").val();
                   // 整数部がnullの場合、1を代入
                   if(sahenheihouseisuub == ""){
                      sahenheihouseisuub = 1;
                   }
                   var sahen = objectCreate(sahenKubunBunshi, $("#sahenenzan option:selected").val(), sahenheihouseisuub, $("#sahenheihoub").val() );
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

                // 左辺分母のオブジェクト化
                var sahenKubunbunbo = $("#kubunsahenbunbo option:selected").val();
                if(sahenKubunbunbo == "seisuu"){
                   var sahenbunbo = objectCreate(sahenKubunbunbo, "wa", $("#sahenseisuubunbo").val(), null );
                }
                // 平方根の場合
                else if (sahenKubunbunbo == "route"){
                   var sahenheihouseisuubbunbo = $("#sahenheihouseisuubbunbo").val();
                   // 整数部がnullの場合、1を代入
                   if(sahenheihouseisuubbunbo == ""){
                      sahenheihouseisuubbunbo = 1;
                   }
                   var sahenbunbo = objectCreate(sahenKubunbunbo, "wa", sahenheihouseisuubbunbo, $("#sahenheihoubbunbo").val() );
                   sahenbunbo = adjustRoute(sahenbunbo);
                }
                else{
                  var sahenbunbo;
                  var sahenCounterbunbo;
                  var sahenShikiKubunbunbo = $("#sahenshikikubunbunbo option:selected").val();
                  // 整数の場合
                  if(sahenShikiKubunbunbo == "seisuu"){
                    sahenbunbo = objectCreate(sahenShikiKubunbunbo, "wa", $("#sahenseisuubunbo").val(), null );
                  // 平方根の場合
                  }else{
                    var sahenheihouseisuubbunbo = $("#sahenheihouseisuubbunbo").val();
                    // 整数部がnullの場合、1を代入
                    if(sahenheihouseisuubbunbo == ""){
                      sahenheihouseisuubbunbo = 1;
                    }
                    sahenbunbo = objectCreate(sahenShikiKubunbunbo, "wa", sahenheihouseisuubbunbo, $("#sahenheihoubbunbo").val() );
                    sahenbunbo = adjustRoute(sahenbunbo);
                    }
                    var sahenShikiKubunCounterbunbo = $("#sahenshikikubuncounterbunbo option:selected").val();
                    // 整数の場合
                    if(sahenShikiKubunCounterbunbo == "seisuu"){
                      sahenCounterbunbo = objectCreate(sahenShikiKubunCounterbunbo, $("#sahenenzancounterbunbo option:selected").val(), $("#sahenseisuucounterbunbo").val(), null );
                    // 平方根の場合
                    }else{
                      var sahenheihouseisuubCounterbunbo = $("#sahenheihouseisuubcounterbunbo").val();
                      // 整数部がnullの場合、1を代入
                      if(sahenheihouseisuubCounterbunbo == ""){
                         sahenheihouseisuubCounterbunbo = 1;
                      }
                      sahenCounterbunbo = objectCreate(sahenShikiKubunCounterbunbo, $("#sahenenzancounterbunbo option:selected").val(), sahenheihouseisuubCounterbunbo, $("#sahenheihoubcounterbunbo").val() );
                      sahenCounterbunbo = adjustRoute(sahenCounterbunbo);
                    }
                    sahenbunbo = adjustShiki(sahenbunbo, sahenCounterbunbo);
                  }
             }

             var keisankekka;
             // 計算処理
             if(sahenKubun == "bunsuu"){
                keisankekka = bekiBunsuuUtil(sahen, sahenbunbo, $("#beki").val());

                $("#resultbunsuu").empty();
                $("#result").empty();
                $("#resultbunsuu").append("&nbsp;&nbsp;&nbsp;&nbsp;" + keisankekka);
             }else{
                keisankekka = bekiKeisanUtil(sahen, $("#beki").val());

                $("#result").empty();
                $("#resultbunsuu").empty();
                $("#result").append("&nbsp;&nbsp;&nbsp;&nbsp;" + keisankekka);
             }


      }else if (numberCheck){
          alert("\u5206\u6bcd\u306b\u306f0\u4ee5\u5916\u306e\u6570\u5b57\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044");
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


function layoutChange(){
      var kubunsahen = $("#kubunsahen option:selected").val();
      var kubunsahenBunshi = $("#kubunsahenbunshi option:selected").val();
      var kubunsahenBunbo = $("#kubunsahenbunbo option:selected").val();

      if(kubunsahen == "bunsuu"){
         // 左辺が式の場合
         if(kubunsahenBunshi == "shiki" || kubunsahenBunbo == "shiki"){
            $("#sahenlinejisuu").css({ "display" :"none" });
            $("#sahenlineroute").css({ "display" :"none" });
            $("#sahenlineshiki").css({ "display" :"" });
         // 左辺が平方根の場合
          }else if(kubunsahenBunshi == "route" || kubunsahenBunbo == "route"){
            $("#sahenlinejisuu").css({ "display" :"none" });
            $("#sahenlineroute").css({ "display" :"" });
            $("#sahenlineshiki").css({ "display" :"none" });
          }else{
            $("#sahenlinejisuu").css({ "display" :"" });
            $("#sahenlineroute").css({ "display" :"none" });
            $("#sahenlineshiki").css({ "display" :"none" });
          }
          // 分数用に括弧を表示
          $(".kakkotable").attr("rowspan","3");
          $(".kakkosize").attr("size","20");
      }else{
          $(".kakkotable").attr("rowspan","1");
          $(".kakkosize").attr("size","6");
      }

}
