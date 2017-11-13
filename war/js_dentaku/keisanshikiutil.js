function objectShikiCreate(kubun, shiki){
    var obj = {
        kubun :kubun,
        shiki :shiki,
    }
    return obj;
}

function adjustShiki(obj, objCounter){

      // ともに実数の場合
      if( (obj.kubun == "seisuu" || obj.kubun == "syousuu") &&
          (objCounter.kubun == "seisuu"  || objCounter.kubun == "syousuu") ){
         var sahen = kigouAdjust(obj);
         var uhen = kigouAdjust(objCounter);
         var jisuu;
         if(sahen.kubun == "seisuu" && uhen.kubun == "seisuu"){
            jisuu = sahen.jisuu + uhen.jisuu;
         }else{
            jisuu = marumeKekka(sahen.jisuu + uhen.jisuu);
         }

         if(jisuu < 0){
            return objectCreate("seisuu", "sa", (jisuu * (-1)), null );
         }else{
            return objectCreate("seisuu", "wa", jisuu, null );
         }
      // ともに平方根の場合
      }else if(obj.kubun == "route" && objCounter.kubun == "route"){

         // 平方部が同じ場合
         if(obj.heihoukon == objCounter.heihoukon){
            var sahen = kigouAdjust(obj);
            var uhen = kigouAdjust(objCounter);
            sahen.jisuu = sahen.jisuu + uhen.jisuu;
            // 実数部が0の場合
            if(sahen.jisuu == 0){
              return objectCreate("seisuu", "wa", 0, null );
            // 負の場合
            }else if(sahen.jisuu < 0){
              return objectCreate("route", "sa", (sahen.jisuu * (-1)), sahen.heihoukon);
            }else{
              return objectCreate("route", "wa", sahen.jisuu, sahen.heihoukon);
            }
         }else{
            var array = new Array(obj,objCounter);
            return objectShikiCreate("shiki", array);
         }
      }else{
         var array = new Array(obj,objCounter);
         return objectShikiCreate("shiki", array);
      }
}

function shikiEdit(keisanShiki){
     var i = 0;
     var loop = keisanShiki.shiki.length - 1;
     var adjustFlg = false;
     var afterAdjustShiki;
     var adjustResult;
     // 式数-1分ループ
     for(i ;i < loop  ;i++){
         var k = i + 1;
         // iの配列以降にループをかける
         for(k; k < keisanShiki.shiki.length ;k++){
            adjustResult = adjustShiki(keisanShiki.shiki[i], keisanShiki.shiki[k]);
            // 計算結果が式でない場合
            if(adjustResult.kubun != "shiki"){
               // 元々の計算結果が2つの場合
               if(keisanShiki.shiki.length == 2){
                  keisanShiki = adjustResult;
                  var adjustFlg = true;
                  break;
               }else{
                  var adjustFlg = true;
                  var l = 0;
                  var array = new Array();
                  // 式数分ループ
                  for(l ;l < keisanShiki.shiki.length  ;l++){
                     // iの位置に編集結果を代入
                     if(l == i){
                        array.push(adjustResult);
                     }else if(l == k){
                        // kの場合は何もしない
                     }else{
                        array.push(keisanShiki.shiki[l]);
                     }
                  }
                  afterAdjustShiki = objectShikiCreate("shiki", array);
                  break;
               }

            }
         }
         if(adjustFlg == true){
            break;
         }
     }
     // 編集結果が式の場合はメソッド再呼び出し
     if(adjustFlg == true && keisanShiki.kubun == "shiki"){
        return shikiEdit(afterAdjustShiki);
     }else{
        return keisanShiki;
     }

}

function shikiKeisan(sahen, uhen, enzan){
    var shikiObj;
    if(enzan == "wa"){
       shikiObj = shikiKeisanForKagen(sahen, uhen);
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
    }else if(enzan == "sa"){
       // 右辺の演算記号を逆にする
       var i = 0;
       // 右辺が計算式の場合
       if(uhen.kubun == "shiki"){
          for(i; i < uhen.shiki.length ;i++){
             if(uhen.shiki[i].kigou == "wa"){
                uhen.shiki[i].kigou = "sa";
             }else{
                uhen.shiki[i].kigou = "wa";
             }
          }
       }else{
          if(uhen.kigou == "wa"){
             uhen.kigou = "sa";
          }else{
             uhen.kigou = "wa";
          }
       }

       // 以下は足し算と同じ
       shikiObj = shikiKeisanForKagen(sahen, uhen);
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
    }else if(enzan == "seki"){

       shikiObj = shikiKeisanForSeki(sahen, uhen);
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
    // 割り算の場合
    }else{
        // 分母の整数化
        var array = bunboAdjust(sahen,uhen);
        sahen = array[0];
        uhen = array[1];
        // 左辺の区分が式の場合
        if(sahen.kubun == "shiki"){
           // 公約数の取得
           var kouyakusuu = getShikiKouyakusuu(sahen,uhen);
           // 公約数が存在した場合
           if(kouyakusuu != 0){
              // 左辺と右辺を公約数で割る
              uhen.jisuu = uhen.jisuu / kouyakusuu;
              var i = 0;
              for(i; i < sahen.shiki.length ;i++){
                 sahen.shiki[i].jisuu = sahen.shiki[i].jisuu / kouyakusuu;
              }

           }
           return getShikiBunsuu(sahen, uhen);
        // 左辺の区分が平方根の場合
        }else if(sahen.kubun == "route"){
          var sahen = kigouAdjust(sahen);
          var uhen = kigouAdjust(uhen);
          return routeKeisan(sahen, uhen, "syou");
        // 左辺の区分が小数の場合
        }else if(sahen.kubun == "syousuu"){
          var sahen = kigouAdjust(sahen);
          var uhen = kigouAdjust(uhen);
          return syousuuKeisan(sahen.jisuu, uhen.jisuu, "syou");

        }else{
          var sahen = kigouAdjust(sahen);
          var uhen = kigouAdjust(uhen);
          return seisuuKeisan(sahen.jisuu, uhen.jisuu, "syou");

        }
    }

}

function shikiKeisanForKagen(sahen, uhen){
    // 左辺・右辺ともに計算式の場合
    if(sahen.kubun == "shiki" && uhen.kubun == "shiki"){
        var i = 0;
        // 右辺の式数分ループ
        for(i; i < uhen.shiki.length ;i++){
           sahen.shiki.push(uhen.shiki[i]);
        }
        return shikiEdit(sahen);
    }else if(sahen.kubun == "shiki"){
        sahen.shiki.push(uhen);
        return shikiEdit(sahen);
    }else{
        uhen.shiki.push(sahen);
        return shikiEdit(uhen);
    }
}


function shikiKeisanForSeki(sahen, uhen){
    var i = 0;
    var array = new Array();
    var shiki;

    // 左辺・右辺ともに計算式の場合
    if(sahen.kubun == "shiki" && uhen.kubun == "shiki"){

        // 左辺の式数分ループ
        for(i; i < sahen.shiki.length ;i++){
          var k = 0;
          // 右辺の式数分ループ
          for(k; k < uhen.shiki.length ;k++){
             array.push(sekiKeisan(sahen.shiki[i], uhen.shiki[k]));
          }
        }
        shiki = objectShikiCreate("shiki", array);
        return shikiEdit(shiki);
    // 左辺のみが式の場合
    }else if(sahen.kubun == "shiki"){
        // 左辺の式数分ループ
        for(i; i < sahen.shiki.length ;i++){
            array.push(sekiKeisan(sahen.shiki[i], uhen));
        }
        shiki = objectShikiCreate("shiki", array);
        return shikiEdit(shiki);
    }else{
        // 右辺の式数分ループ
        for(i; i < uhen.shiki.length ;i++){
            array.push(sekiKeisan(uhen.shiki[i], sahen));
        }
        shiki = objectShikiCreate("shiki", array);
        return shikiEdit(shiki);
    }

}

function sekiKeisan(sahen, uhen){
    var kubun;
    var kigou;
    var jisuu;
    var heihoukon;

    // 右辺・左辺がともに和または差の場合
    if( (sahen.kigou == "wa" && uhen.kigou == "wa") ||
        (sahen.kigou == "sa" && uhen.kigou == "sa") ){
       kigou = "wa";
    }else{
       kigou = "sa";
    }

    // 左辺が平方根の場合
    if(sahen.kubun == "route" ){
        // 右辺が平方根の場合
        if(uhen.kubun == "route" ){
           jisuu = sahen.jisuu * uhen.jisuu;
           heihoukon = sahen.heihoukon * uhen.heihoukon;
           return adjustRoute(objectCreate("route", kigou, jisuu, heihoukon ));
        // 右辺が小数の場合
        }else if(uhen.kubun == "syousuu"){
           jisuu = marumeKekka(sahen.jisuu * uhen.jisuu);
           return adjustRoute(objectCreate("route", kigou, jisuu, sahen.heihoukon ));
        }else{
           jisuu = sahen.jisuu * uhen.jisuu;
           return adjustRoute(objectCreate("route", kigou, jisuu, sahen.heihoukon ));
        }
    // 左辺が小数の場合
    }else if(sahen.kubun == "syousuu" ){
        // 右辺が平方根の場合
        if(uhen.kubun == "route" ){
           jisuu = marumeKekka(sahen.jisuu * uhen.jisuu);
           return adjustRoute(objectCreate("route", kigou, jisuu, uhen.heihoukon ));
        }else{
           jisuu = marumeKekka(sahen.jisuu * uhen.jisuu);
           return objectCreate("syousuu", kigou, jisuu, null );
        }
     }else{
        // 右辺が平方根の場合
        if(uhen.kubun == "route" ){
           jisuu = sahen.jisuu * uhen.jisuu;
           return adjustRoute(objectCreate("route", kigou, jisuu, uhen.heihoukon ));
        // 右辺が小数の場合
        }else if(uhen.kubun == "syousuu"){
           jisuu = marumeKekka(sahen.jisuu * uhen.jisuu);
           return objectCreate("syousuu", kigou, jisuu, null );
        }else{
           jisuu = sahen.jisuu * uhen.jisuu;
           return objectCreate("seisuu", kigou, jisuu, null );
        }

     }

}


function bunboAdjust(sahen,uhen){
    // 右辺が式の場合は分母を整数化
    if(uhen.kubun == "shiki"){
       var bunboCounter = createCounterObject(uhen);
       uhen = shikiKeisanForSeki(uhen, bunboCounter);
       sahen = shikiKeisanForSeki(sahen, bunboCounter);

       // 右辺が計算式または平方根の場合
       if(uhen.kubun == "shiki" || uhen.kubun == "route"){
          bunboAdjust(sahen,uhen);
       }
    // 右辺が平方根の場合
    }else if(uhen.kubun == "route"){
       // 右辺の平方部を抽出
       var bunboCounter = objectCreate("route", uhen.kigou, 1, uhen.heihoukon );

       if(sahen.kubun == "shiki"){
           sahen = shikiKeisanForSeki(sahen, bunboCounter);
       }else if(sahen.kubun == "route"){
           sahen.heihoukon = sahen.heihoukon * uhen.heihoukon;
           sahen = adjustRoute(sahen);
       }else{
           sahen = objectCreate("route", sahen.kigou, sahen.jisuu, uhen.heihoukon );
       }
       // 右辺の平方部を整数化
       uhen.heihoukon = uhen.heihoukon * uhen.heihoukon;
       uhen = adjustRoute(uhen);

    }
    return new Array(sahen, uhen);
}


function createCounterObject(obj){
    var array = new Array();
    var i = 0;
    var length = obj.shiki.length - 1;
    // 最後の数まではそのまま代入
    for(i; i < length ;i++){
       array.push(obj.shiki[i]);
    }
    // 最後だけ和と差を逆にする
    if(obj.shiki[length].kigou == "wa"){
        if(obj.shiki[length].kubun == "route"){
            array.push(objectCreate("route", "sa", obj.shiki[length].jisuu, obj.shiki[length].heihoukon ));
        }else{
            array.push(objectCreate(obj.shiki[length].kubun, "sa", obj.shiki[length].jisuu, null ));
        }
    }else{
        if(obj.shiki[length].kubun == "route"){
            array.push(objectCreate("route", "wa", obj.shiki[length].jisuu, obj.shiki[length].heihoukon ));
        }else{
            array.push(objectCreate(obj.shiki[i].kubun, "wa", obj.shiki[length].jisuu, null ));
        }

    }
    return objectShikiCreate("shiki", array);

}


function getShikiKouyakusuu(sahen,uhen){
     // 公約数に右辺の値を代入
     var kouyakusuu;
     if(uhen.jisuu < 0){
        kouyakusuu = (-1) * uhen.jisuu;
     }else{
        kouyakusuu = uhen.jisuu;
     }

     var i = 0;
     var tempKouyakusuu;
     // 分母と分子の比較
     for(i; i < sahen.shiki.length ;i++){
        tempKouyakusuu = getKouyakusuu(sahen.shiki[i].jisuu, uhen.jisuu);
        // 公約数がない場合
        if(tempKouyakusuu == 0){
           return 0;
        // 該当の値が暫定の公約数よりも小さい場合
        }else if(tempKouyakusuu < kouyakusuu){
           kouyakusuu = tempKouyakusuu;
        }
     }
     var bunshiKouyakusuu = sahen.shiki[0].jisuu;
     var i = 1;
     // 分子どうしの比較
     for(i; i < sahen.shiki.length ;i++){
        tempKouyakusuu = getKouyakusuu(sahen.shiki[i-1].jisuu, sahen.shiki[i].jisuu);
        // 公約数がない場合
        if(tempKouyakusuu == 0){
           return 0;
        // 該当の値が暫定の公約数よりも小さい場合
        }else if(tempKouyakusuu < kouyakusuu){
           bunshiKouyakusuu = tempKouyakusuu;
        }
     }
     return getKouyakusuu(kouyakusuu, bunshiKouyakusuu);
}

function shikiDisplay(shiki){

    var kekka = "";
    // 区分が平方根の場合
    if(shiki.shiki[0].kubun == "route" ){
       if(shiki.shiki[0].kigou == "sa"){
          kekka = kekka + "-";
       }
       // 平方部の実数が1の場合、整数部を非出力
       if(shiki.shiki[0].jisuu == 1){
          kekka = kekka + ("\u221a") + shiki.shiki[0].heihoukon;
       }else{
          kekka = kekka + shiki.shiki[0].jisuu + ("\u221a") + shiki.shiki[0].heihoukon;
       }
    }else{
       if(shiki.shiki[0].kigou == "sa"){
          kekka = kekka + "-";
       }
       if(shiki.shiki[0].jisuu != 0){
         kekka = kekka + shiki.shiki[0].jisuu;
       }

    }

    var i = 1;
    var shikiObj;
    // 式数分ループ
    for(i; i < shiki.shiki.length ;i++){
       shikiObj = shiki.shiki[i];
       // 区分が平方根の場合
       if(shikiObj.kubun == "route" ){
          if(shikiObj.kigou == "sa"){
             kekka = kekka + "&nbsp;&nbsp;-&nbsp;&nbsp;";
          }else{
             kekka = kekka + "&nbsp;&nbsp;+&nbsp;&nbsp;";
          }
          // 平方部の実数が1の場合、整数部を非出力
          if(shikiObj.jisuu == 1){
             kekka = kekka + ("\u221a") + shikiObj.heihoukon;
          }else{
             kekka = kekka + shikiObj.jisuu + ("\u221a") + shikiObj.heihoukon;
          }

       }else{
         if(shikiObj.jisuu != 0){
           if(shikiObj.kigou == "sa"){
              kekka = kekka + "&nbsp;&nbsp;-&nbsp;&nbsp;";
           }else{
              kekka = kekka + "&nbsp;&nbsp;+&nbsp;&nbsp;";
           }

           kekka = kekka + shikiObj.jisuu;
         }
       }

    }
    // 結果が空文字の場合
    if(kekka ==""){
        return 0;
    }else{
        return kekka;
    }

}


function getShikiBunsuu(bunshi, bunbo){
    var kekka = "<table><tr><td></td><td align = \"center\">"

    // 分子の一つ目と分母の記号が違う場合
    if( (bunshi.shiki[0].kigou == "wa" && bunbo.kigou == "sa") ||
        (bunshi.shiki[0].kigou == "sa" && bunbo.kigou == "wa") ){
       // 0でない場合マイナスを付与
       if(bunshi.shiki[0].jisuu != 0){
          kekka = kekka + "-";
       }

    }
    // 分子の一つ目が平方根の場合
    if(bunshi.shiki[0].kubun == "route"){
        // 実数部が0の場合
        if(bunshi.shiki[0].jisuu == 0){
            // 何も出力しない
        }
        // 実数部が1の場合
        else if(bunshi.shiki[0].jisuu == 1){
           kekka = kekka + ("\u221a") + bunshi.shiki[0].heihoukon;
        }
        else{
           kekka = kekka + bunshi.shiki[0].jisuu + ("\u221a") + bunshi.shiki[0].heihoukon;
        }
    }else{
       // 実数部が0でない場合
       if(bunshi.shiki[0].jisuu != 0){
          kekka = kekka + bunshi.shiki[0].jisuu;
       }
    }

    var i = 1;
    // 式数分ループ
    for(i; i < bunshi.shiki.length ;i++){
       // 分子と分母の記号が違う場合
       if( (bunshi.shiki[i].kigou == "wa" && bunbo.kigou == "sa") ||
           (bunshi.shiki[i].kigou == "sa" && bunbo.kigou == "wa") ){
          // 0でない場合マイナスを付与
          if(bunshi.shiki[i].jisuu != 0){
             kekka = kekka + "&nbsp;&nbsp;-&nbsp;&nbsp;";
          }

       }else{
          // 0でない場合プラスを付与
          if(bunshi.shiki[i].jisuu != 0){
             kekka = kekka + "&nbsp;&nbsp;+&nbsp;&nbsp;";
          }
       }

       // 分子のが平方根の場合
       if(bunshi.shiki[i].kubun == "route"){
           // 実数部が0の場合
           if(bunshi.shiki[i].jisuu == 0){
               // 何も出力しない
           }
           // 実数部が1の場合
           else if(bunshi.shiki[i].jisuu == 1){
              kekka = kekka + ("\u221a") + bunshi.shiki[i].heihoukon;
           }
           else{
              kekka = kekka + bunshi.shiki[i].jisuu + ("\u221a") + bunshi.shiki[i].heihoukon;
           }
       }else{
           // 実数部が0でない場合
           if(bunshi.shiki[i].jisuu != 0){
              kekka = kekka + bunshi.shiki[i].jisuu;
           }
       }
    }

    // 分母が1以外の場合、分数形式で表示。
    if(bunbo.jisuu != 1){
        // 式の長さが3未満の場合
        if(bunshi.shiki.length < 3){
           kekka = kekka + "<tr><td></td><td>――――――――</td></tr>"
        }else{
           kekka = kekka + "<tr><td></td><td>――――――――――――――――</td></tr>"
        }
        kekka = kekka + "<tr><td></td><td align = \"center\">" + bunbo.jisuu + "</td></tr></table>";
    }else{
        kekka = kekka + "</td></tr></table>";
    }

    return kekka;

}
