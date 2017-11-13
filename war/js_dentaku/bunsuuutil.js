function checkNumberZero(){
      var checkFlg = true;
      // class属性に bunboが入っているものが対象
      $(".bunbo").each(function(){
         // 表示されている場合チェック
         var number = $(this).val();
         // 表示対象の項目の場合基礎チェック
         if($(this).css("display") != "none"){
            // 0だった場合、NG
            if(number == "0"){
              // フラグをfalseにしてループを抜ける
              checkFlg = false;
              return;
            }

         }

      });
      return checkFlg;

}


function bunsuuKeisanUtil(sahen, uhen, sahenbunbo, uhenbunbo, enzan){
     var bunshi;
     var bunbo;
     // 左辺の分母が式の場合
     if(sahenbunbo.kubun == "shiki"){
        // 分母の整数化
        var array = bunboAdjust(sahen,sahenbunbo);
        sahen = array[0];
        sahenbunbo = array[1];
        // 和と差の反転
        if(sahenbunbo.kigou = "sa"){
           sahenbunbo.kigou = "wa";
           if(sahen.kubun == "shiki" ){
              var i =0;
              for(i; i< sahen.shiki.length; i++){
                 if(sahen.shiki[i].kigou == "wa"){
                     sahen.shiki[i].kigou = "sa";
                 }else{
                     sahen.shiki[i].kigou = "wa";
                 }
              }
           }else{
              if(sahen.kigou == "wa"){
                 sahen.kigou = "sa";
              }else{
                 sahen.kigou = "wa";
              }
           }
        }
     }

     // 右辺の分母が式の場合
     if(uhenbunbo.kubun == "shiki"){
        // 分母の整数化
        var array = bunboAdjust(uhen,uhenbunbo);
        uhen = array[0];
        uhenbunbo = array[1];
        // 和と差の反転
        if(uhenbunbo.kigou = "sa"){
           uhenbunbo.kigou = "wa";
           if(uhen.kubun == "shiki" ){
              var i =0;
              for(i; i< uhen.shiki.length; i++){
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
        }
     }

     // 左辺と右辺の統合
     // 和または差の場合
     if(enzan == "wa" || enzan == "sa"){
        // 差の場合は記号を反転
        if(enzan == "sa"){
           // 式の場合
           if(uhen.kubun == "shiki"){
              var i = 0;
              for(i; i < uhen.shiki.length ;i++){
                if(uhen.shiki[i].kigou == "wa"){
                   uhen.shiki[i].kigou = "sa";
                }else{
                   uhen.shiki[i].kigou = "wa";
                }
              }
           // 式以外の場合
           }else{
              if(uhen.kigou == "wa"){
                 uhen.kigou = "sa";
              }else{
                 uhen.kigou = "wa";
              }

           }

        }

        // 分母同士の掛け合わせ
        // 式が含まれている場合
        if(sahenbunbo.kubun == "shiki" || uhenbunbo.kubun == "shiki"){
           bunbo = shikiKeisanForSeki(sahenbunbo, uhenbunbo);
        // 式が含まれていない場合
        }else{
           bunbo = sekiKeisan(sahenbunbo, uhenbunbo);
        }

        // 左辺分母と右辺分子の掛け合わせ
        var bunshiRight;
        if(sahenbunbo.kubun == "shiki" || uhen.kubun == "shiki"){
           bunshiRight = shikiKeisanForSeki(sahenbunbo, uhen);
        // 式が含まれていない場合
        }else{
           bunshiRight = sekiKeisan(sahenbunbo, uhen);
        }

        // 右辺分母と左辺分子の掛け合わせ
        var bunshiLeft;
        if(sahen.kubun == "shiki" || uhenbunbo.kubun == "shiki"){
           bunshiLeft = shikiKeisanForSeki(sahen, uhenbunbo);
        // 式が含まれていない場合
        }else{
           bunshiLeft = sekiKeisan(sahen, uhenbunbo);
        }

        // 分子を一旦式として纏める
        var array = new Array();
        // 式の場合
        if(bunshiLeft.kubun == "shiki"){
           var i = 0;
           for(i; i < bunshiLeft.shiki.length ;i++){
              array.push(bunshiLeft.shiki[i]);
           }
        }else{
           array.push(bunshiLeft);
        }

        // 式の場合
        if(bunshiRight.kubun == "shiki"){
           var i = 0;
           for(i; i < bunshiRight.shiki.length ;i++){
              array.push(bunshiRight.shiki[i]);
           }
        }else{
           array.push(bunshiRight);
        }

        bunshi = shikiEdit(objectShikiCreate("shiki", array));
     // 積の場合
     }else if(enzan == "seki"){
        // 分母同士の掛け合わせ
        // 式が含まれている場合
        if(sahenbunbo.kubun == "shiki" || uhenbunbo.kubun == "shiki"){
           bunbo = shikiKeisanForSeki(sahenbunbo, uhenbunbo);
        // 式が含まれていない場合
        }else{
           bunbo = sekiKeisan(sahenbunbo, uhenbunbo);
        }

        // 分子同士の掛け合わせ
        // 式が含まれている場合
        if(sahen.kubun == "shiki" || uhen.kubun == "shiki"){
           bunshi = shikiKeisanForSeki(sahen, uhen);
        // 式が含まれていない場合
        }else{
           bunshi = sekiKeisan(sahen, uhen);
        }

     }else{
        // 左辺分母・右辺分子の掛け合わせ
        // 式が含まれている場合
        if(sahenbunbo.kubun == "shiki" || uhen.kubun == "shiki"){
           bunbo = shikiKeisanForSeki(sahenbunbo, uhen);
        // 式が含まれていない場合
        }else{
           bunbo = sekiKeisan(sahenbunbo, uhen);
        }

        // 右辺分母・左辺分子の掛け合わせ
        // 式が含まれている場合
        if(sahen.kubun == "shiki" || uhenbunbo.kubun == "shiki"){
           bunshi = shikiKeisanForSeki(sahen, uhenbunbo);
        // 式が含まれていない場合
        }else{
           bunshi = sekiKeisan(sahen, uhenbunbo);
        }

     }
     return shikiKeisan(bunshi, bunbo, "syou");
}