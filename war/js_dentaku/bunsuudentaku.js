$(function(){
  // 整数以外は初期で非表示
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

  $("#sahenlinejisuu").css({ "display" :"" });
  $("#sahenlineroute").css({ "display" :"none" });
  $("#sahenlineshiki").css({ "display" :"none" });

  $("#uhenseisuu").css({ "display" :"" });
  $("#uhenheihouseisuub").css({ "display" :"none" });
  $("#uhenroute").css({ "display" :"none" });
  $("#uhenheihoub").css({ "display" :"none" });
  $(".uhenshiki").css({ "display" :"none" });
  $("#uhenseisuucounter").css({ "display" :"none" });
  $("#uhenheihouseisuubcounter").css({ "display" :"none" });
  $("#uhenroutecounter").css({ "display" :"none" });
  $("#uhenheihoubcounter").css({ "display" :"none" });

  $("#uhenseisuubunbo").css({ "display" :"" });
  $("#uhenheihouseisuubbunbo").css({ "display" :"none" });
  $("#uhenroutebunbo").css({ "display" :"none" });
  $("#uhenheihoubbunbo").css({ "display" :"none" });
  $(".uhenshikibunbo").css({ "display" :"none" });
  $("#uhenseisuucounterbunbo").css({ "display" :"none" });
  $("#uhenheihouseisuubcounterbunbo").css({ "display" :"none" });
  $("#uhenroutecounterbunbo").css({ "display" :"none" });
  $("#uhenheihoubcounterbunbo").css({ "display" :"none" });

  $("#uhenlinejisuu").css({ "display" :"" });
  $("#uhenlineroute").css({ "display" :"none" });
  $("#uhenlineshiki").css({ "display" :"none" });

  // プルダウンは整数を初期表示
  $("#kubunsahen").val("seisuu");
  $("#kubunuhen").val("seisuu");
  $("#kubunsahenbunbo").val("seisuu");
  $("#kubunuhenbunbo").val("seisuu");

  $("#manual").css({ "display" :"none" });
  $("#result").append("&nbsp;&nbsp;&nbsp;&nbsp;");

  $("#kubunsahen").change(function(){
      layoutChange();
      var kubunsahen = $("#kubunsahen option:selected").val();
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


  $("#kubunuhen").change(function(){
      layoutChange();
      var kubunuhen = $("#kubunuhen option:selected").val();

      // 整数の場合
      if( kubunuhen == "seisuu" ){
           $("#uhenseisuu").css({ "display" :"" });
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


  $("#kubunuhenbunbo").change(function(){
      layoutChange();
      var kubunuhenbunbo = $("#kubunuhenbunbo option:selected").val();

      // 整数の場合
      if( kubunuhenbunbo == "seisuu" ){
           $("#uhenseisuubunbo").css({ "display" :"" });
           $("#uhenheihouseisuubbunbo").css({ "display" :"none" });
           $("#uhenroutebunbo").css({ "display" :"none" });
           $("#uhenheihoubbunbo").css({ "display" :"none" });
           $(".uhenshikibunbo").css({ "display" :"none" });
           $("#uhenseisuucounterbunbo").css({ "display" :"none" });
           $("#uhenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#uhenroutecounterbunbo").css({ "display" :"none" });
           $("#uhenheihoubcounterbunbo").css({ "display" :"none" });

      // 平方根の場合
      }else if( kubunuhenbunbo == "route"){
           $("#uhenseisuubunbo").css({ "display" :"none" });
           $("#uhenheihouseisuubbunbo").css({ "display" :"" });
           $("#uhenroutebunbo").css({ "display" :"" });
           $("#uhenheihoubbunbo").css({ "display" :"" });
           $(".uhenshikibunbo").css({ "display" :"none" });
           $("#uhenseisuucounterbunbo").css({ "display" :"none" });
           $("#uhenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#uhenroutecounterbunbo").css({ "display" :"none" });
           $("#uhenheihoubcounterbunbo").css({ "display" :"none" });

      }else{
           $("#uhenseisuubunbo").css({ "display" :"" });
           $("#uhenheihouseisuubbunbo").css({ "display" :"none" });
           $("#uhenroutebunbo").css({ "display" :"none" });
           $("#uhenheihoubbunbo").css({ "display" :"none" });
           $(".uhenshikibunbo").css({ "display" :"" });
           $("#uhenseisuucounterbunbo").css({ "display" :"" });
           $("#uhenheihouseisuubcounterbunbo").css({ "display" :"none" });
           $("#uhenroutecounterbunbo").css({ "display" :"none" });
           $("#uhenheihoubcounterbunbo").css({ "display" :"none" });

           // プルダウンは整数を初期表示
           $("#uhenshikikubunbunbo").val("seisuu");
           $("#uhenshikikubuncounterbunbo").val("seisuu");

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
      if( kubunsahenbunbo == "seisuu" ){
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



  $("#uhenshikikubun").change(function(){
      layoutChange();
      var kubunuhen = $("#uhenshikikubun option:selected").val();
      // 整数の場合
      if( kubunuhen == "seisuu" ){
           $("#uhenseisuu").css({ "display" :"" });
           $("#uhenheihouseisuub").css({ "display" :"none" });
           $("#uhenroute").css({ "display" :"none" });
           $("#uhenheihoub").css({ "display" :"none" });

      }else{
           $("#uhenseisuu").css({ "display" :"none" });
           $("#uhenheihouseisuub").css({ "display" :"" });
           $("#uhenroute").css({ "display" :"" });
           $("#uhenheihoub").css({ "display" :"" });
      }

   });


  $("#uhenshikikubunbunbo").change(function(){
      layoutChange();
      var kubunuhenbunbo = $("#uhenshikikubunbunbo option:selected").val();
      // 整数の場合
      if( kubunuhenbunbo == "seisuu" ){
           $("#uhenseisuubunbo").css({ "display" :"" });
           $("#uhenheihouseisuubbunbo").css({ "display" :"none" });
           $("#uhenroutebunbo").css({ "display" :"none" });
           $("#uhenheihoubbunbo").css({ "display" :"none" });

      }else{
           $("#uhenseisuubunbo").css({ "display" :"none" });
           $("#uhenheihouseisuubbunbo").css({ "display" :"" });
           $("#uhenroutebunbo").css({ "display" :"" });
           $("#uhenheihoubbunbo").css({ "display" :"" });
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


  $("#uhenshikikubuncounterbunbo").change(function(){
      var kubunuhenbunbo = $("#uhenshikikubuncounterbunbo option:selected").val();
      // 整数の場合
      if( kubunuhenbunbo == "seisuu" ){
        $("#uhenseisuucounterbunbo").css({ "display" :"" });
        $("#uhenheihouseisuubcounterbunbo").css({ "display" :"none" });
        $("#uhenroutecounterbunbo").css({ "display" :"none" });
        $("#uhenheihoubcounterbunbo").css({ "display" :"none" });

      }else{
        $("#uhenseisuucounterbunbo").css({ "display" :"none" });
        $("#uhenheihouseisuubcounterbunbo").css({ "display" :"" });
        $("#uhenroutecounterbunbo").css({ "display" :"" });
        $("#uhenheihoubcounterbunbo").css({ "display" :"" });
      }

   });


  $("#calculate").click(function(){
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


             // 右辺のオブジェクト化
             var uhenKubun = $("#kubunuhen option:selected").val();
             if(uhenKubun == "seisuu"){
                var uhen = objectCreate($("#kubunuhen option:selected").val(), $("#uhenenzan option:selected").val(), $("#uhenseisuu").val(), null );
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


             // 右辺分母のオブジェクト化
             var uhenKubunbunbo = $("#kubunuhenbunbo option:selected").val();
             if(uhenKubunbunbo == "seisuu"){
                var uhenbunbo = objectCreate($("#kubunuhenbunbo option:selected").val(), "wa", $("#uhenseisuubunbo").val(), null );
             }
             // 平方根の場合
             else if (uhenKubunbunbo == "route"){
                var uhenheihouseisuubbunbo = $("#uhenheihouseisuubbunbo").val();
                // 整数部がnullの場合、1を代入
                if(uhenheihouseisuubbunbo == ""){
                   uhenheihouseisuubbunbo = 1;
                }
                var uhenbunbo = objectCreate(uhenKubunbunbo, "wa", uhenheihouseisuubbunbo, $("#uhenheihoubbunbo").val() );
                uhenbunbo = adjustRoute(uhenbunbo);
             }else{
                var uhenbunbo;
                var uhenCounterbunbo;
                var uhenShikiKubunbunbo = $("#uhenshikikubunbunbo option:selected").val();
                // 整数の場合
                if(uhenShikiKubunbunbo == "seisuu"){
                   uhenbunbo = objectCreate(uhenShikiKubunbunbo, "wa", $("#uhenseisuubunbo").val(), null );
                // 平方根の場合
                }else{
                   var uhenheihouseisuubbunbo = $("#uhenheihouseisuubbunbo").val();
                   // 整数部がnullの場合、1を代入
                   if(uhenheihouseisuubbunbo == ""){
                      uhenheihouseisuubbunbo = 1;
                   }
                   uhenbunbo = objectCreate(uhenShikiKubunbunbo, "wa", uhenheihouseisuubbunbo, $("#uhenheihoubbunbo").val() );
                   uhenbunbo = adjustRoute(uhenbunbo);
                }
                var uhenShikiKubunCounterbunbo = $("#uhenshikikubuncounterbunbo option:selected").val();
                // 整数の場合
                if(uhenShikiKubunCounterbunbo == "seisuu"){
                   uhenCounterbunbo = objectCreate(uhenShikiKubunCounterbunbo, $("#uhenenzancounterbunbo option:selected").val(), $("#uhenseisuucounterbunbo").val(), null );
                // 平方根の場合
                }else{
                   var uhenheihouseisuubCounterbunbo = $("#uhenheihouseisuubcounterbunbo").val();
                   // 整数部がnullの場合、1を代入
                   if(uhenheihouseisuubCounterbunbo == ""){
                      uhenheihouseisuubCounterbunbo = 1;
                   }
                   uhenCounterbunbo = objectCreate(uhenShikiKubunCounterbunbo, $("#uhenenzancounterbunbo option:selected").val(), uhenheihouseisuubCounterbunbo, $("#uhenheihoubcounterbunbo").val() );
                   uhenCounterbunbo = adjustRoute(uhenCounterbunbo);
                }
                uhenbunbo = adjustShiki(uhenbunbo, uhenCounterbunbo);
             }


             // 計算処理
             var keisankekka = bunsuuKeisanUtil(sahen, uhen, sahenbunbo, uhenbunbo, $("#enzan option:selected").val());


             $("#result").empty();
             $("#result").append("&nbsp;&nbsp;&nbsp;&nbsp;" + keisankekka);

      }else if(numberCheck){
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
      var kubunuhen = $("#kubunuhen option:selected").val();
      var kubunsahenBunbo = $("#kubunsahenbunbo option:selected").val();
      var kubunuhenBunbo = $("#kubunuhenbunbo option:selected").val();

      // 右辺か左辺が計算式の場合は、横幅を1700へ広げる
      if(kubunsahen == "shiki" || kubunuhen == "shiki" || kubunsahenBunbo == "shiki" || kubunuhenBunbo == "shiki"){
          $("#size").attr("width","1700");
      }else{
          $("#size").attr("width","1200");
      }

      // 左辺が式の場合
      if(kubunsahen == "shiki" || kubunsahenBunbo == "shiki"){
           $("#sahenlinejisuu").css({ "display" :"none" });
           $("#sahenlineroute").css({ "display" :"none" });
           $("#sahenlineshiki").css({ "display" :"" });
      // 左辺が平方根の場合
      }else if(kubunsahen == "route" || kubunsahenBunbo == "route"){
           $("#sahenlinejisuu").css({ "display" :"none" });
           $("#sahenlineroute").css({ "display" :"" });
           $("#sahenlineshiki").css({ "display" :"none" });
      }else{
           $("#sahenlinejisuu").css({ "display" :"" });
           $("#sahenlineroute").css({ "display" :"none" });
           $("#sahenlineshiki").css({ "display" :"none" });
      }

      // 右辺が式の場合
      if(kubunuhen == "shiki" || kubunuhenBunbo == "shiki"){
           $("#uhenlinejisuu").css({ "display" :"none" });
           $("#uhenlineroute").css({ "display" :"none" });
           $("#uhenlineshiki").css({ "display" :"" });
      // 左辺が平方根の場合
      }else if(kubunuhen == "route" || kubunuhenBunbo == "route"){
           $("#uhenlinejisuu").css({ "display" :"none" });
           $("#uhenlineroute").css({ "display" :"" });
           $("#uhenlineshiki").css({ "display" :"none" });
      }else{
           $("#uhenlinejisuu").css({ "display" :"" });
           $("#uhenlineroute").css({ "display" :"none" });
           $("#uhenlineshiki").css({ "display" :"none" });
      }

}
