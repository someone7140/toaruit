function bekiKeisanUtil(sahen, bekijou){
   // べき乗が0の場合
   if(bekijou == 0){
      // 1を返す
      return 1;
   }

   // 整数の場合
   if(sahen.kubun == "seisuu"){
      var i = 1;
      var kekka = kigouAdjust(sahen);

      var bekiTarget = kekka.jisuu;
      // べき乗分掛け算を繰り返す
      for(i;i < bekijou;i++){
         kekka.jisuu = seisuuKeisan(kekka.jisuu, bekiTarget, "seki");
      }
      return kekka.jisuu;
   }
   // 平方根の場合
   else if(sahen.kubun == "route"){
      var i = 1;
      var result = sahen;
      // べき乗分掛け算を繰り返す
      for(i;i < bekijou;i++){
         result = sekiKeisan(result, sahen);
      }

      var kekka = "";
      // 演算が差の場合
      if(result.kigou == "sa"){
         kekka = kekka + "-";
      }
      if(result.kubun == "route"){
         // 平方部の実数が1の場合、整数部を非出力
         if(result.jisuu == 1){
            kekka = kekka + ("\u221a") + result.heihoukon;
         }else{
            kekka = kekka + result.jisuu + ("\u221a") + result.heihoukon;
         }
         return kekka;
      }else{
         kekka = kekka + result.jisuu;
         return kekka;
      }
   // 計算式の場合
   }else{
      var i = 1;
      var shikiObj = sahen;
      // べき乗分掛け算を繰り返す
      for(i;i < bekijou;i++){
         shikiObj = shikiKeisanForSeki(shikiObj, sahen);
      }

      // 計算式の場合
      if(shikiObj.kubun == "shiki" ){
         return shikiDisplay(shikiObj);
      }else if(shikiObj.kubun == "route" ){
         var kekka = "";
         // 演算が差の場合
         if(shikiObj.kigou == "sa"){
            kekka = kekka + "-";
         }
          // 平方部の実数が1の場合、整数部を非出力
          if(shikiObj.jisuu == 1){
             kekka = kekka + ("\u221a") + shikiObj.heihoukon;
          }else{
             kekka = kekka + shikiObj.jisuu + ("\u221a") + shikiObj.heihoukon;
          }
          return kekka;
       }else{
          var kekka = "";
          // 演算が差の場合
          if(shikiObj.kigou == "sa"){
             kekka = kekka + "-";
          }
          kekka = kekka + shikiObj.jisuu;
          return kekka;
       }
   }

}


function bekiBunsuuUtil(bunshi, bunbo, bekijou){
   // べき乗が0の場合
   if(bekijou == 0){
      // 1を返す
      return 1;
   }

   // 分子のべき乗
   bunshi = bunsuuBekiKeisan(bunshi, bekijou);
   // 分母のべき乗
   bunbo = bunsuuBekiKeisan(bunbo, bekijou);
   // 割り算を行う
   return shikiKeisan(bunshi, bunbo, "syou");
}


function bunsuuBekiKeisan(sahen, bekijou){

   // 整数の場合
   if(sahen.kubun == "seisuu"){
      var i = 1;
      var kekka = kigouAdjust(sahen);

      var bekiTarget = kekka.jisuu;
      // べき乗分掛け算を繰り返す
      for(i;i < bekijou;i++){
         kekka.jisuu = seisuuKeisan(kekka.jisuu, bekiTarget, "seki");
      }
      // 負の場合
      if(kekka.jisuu < 0 ){
         return objectCreate(kekka.kubun, "sa", (kekka.jisuu * (-1)), null );
      }else{
         return objectCreate(kekka.kubun, "wa", kekka.jisuu, null );
      }

   }
   // 平方根の場合
   else if(sahen.kubun == "route"){
      var i = 1;
      var result = sahen;
      // べき乗分掛け算を繰り返す
      for(i;i < bekijou;i++){
         result = sekiKeisan(result, sahen);
      }

      return result;

   // 計算式の場合
   }else{
      var i = 1;
      var shikiObj = sahen;
      // べき乗分掛け算を繰り返す
      for(i;i < bekijou;i++){
         shikiObj = shikiKeisanForSeki(shikiObj, sahen);
      }

      return shikiObj;
   }
}