function checkNumber(){
      var checkFlg = true;
      // class属性にinputが入っているものが対象
      $(".input").each(function(){
         // 表示されている場合チェック
         var number = $(this).val();
         // 表示対象の項目の場合基礎チェック
         if($(this).css("display") != "none"){
            // 未入力だった場合、NG
            if(number.length == 0){
              // フラグをfalseにしてループを抜ける
              checkFlg = false;
              return;
            }

            // 数字の正規表現チェック
            var result = new RegExp("\\d+");
            var match = number.match(result);

            // すべて数字だった場合、チェックOK
            if(match != null && number == match[0]){
               //何もしない
            }else{
               // フラグをfalseにしてループを抜ける
               checkFlg = false;
               return;
            }

         }

      });
      // 平方根の整数部チェック
      $(".inputheihouseisuu").each(function(){
         // 表示されている場合チェック
         var number = $(this).val();
         // 表示対象の項目の場合基礎チェック
         if($(this).css("display") != "none"){
            // 未入力だった場合、OK
            if(number.length != 0){
               // 数字の正規表現チェック
               var result = new RegExp("\\d+");
               var match = number.match(result);

               // すべて数字だった場合、チェックOK
               if(match != null && number == match[0]){
                  //何もしない
               }else{
                  // フラグをfalseにしてループを抜ける
                  checkFlg = false;
                  return;
               }
            }
         }

      });

     // 全てOKだった場合、trueを返却
     return checkFlg;
}

function objectCreate(kubun, kigou, jisuu, heihoukon){
      var obj = {
           kubun :kubun,
           kigou :kigou,
           jisuu :jisuu,
           heihoukon :heihoukon,
      }
      return obj;
}

function syousuuCreate(kubun, kigou, seisuu, syousuu){
      // 文字数を取り出すため一旦文字列化
      var syousuuStr = new String(syousuu);
      // 小数の桁数を取得
      var syousuuKeta = 1;
      var i = 0;
      for(i; i<syousuuStr.length; i++){
         syousuuKeta = syousuuKeta * 10;
      }

      syousuu = syousuu / syousuuKeta;
      // 整数部と小数部を足す
      var jisuu = parseInt(seisuu) + syousuu;
      return objectCreate(kubun, kigou, jisuu, null );
}

function adjustRoute(obj){
      var seisuu = obj.jisuu;
      var heihou = obj.heihoukon;
      // 0が含まれていた場合
      if(seisuu == 0 || heihou == 0){
         // 0の整数を返却
         return objectCreate("seisuu", "wa", 0, null );
      }
      // 平方部が1の場合
      if(heihou ==1){
         // 実数部を返却
         return objectCreate("seisuu", obj.kigou, obj.jisuu, null );
      }
      // 平方部/2をスタートとして、2乗可能な数を探索
      var i = parseInt(heihou/2) +1;
      var jijyouNumber = 0;
      var amari;
      for(i; i>1; i--){
         // iの2乗を平方部で割り、余りが出るか
         amari = heihou % (i*i);
         if(amari == 0){
            jijyouNumber = i;
            break;
         }
      }
      if(jijyouNumber == 0){
         // 元のオブジェクトを返す
         return obj;
      }else{
         seisuu = seisuu * jijyouNumber;
         heihou = heihou / (jijyouNumber*jijyouNumber);
         // 平方部が1の場合
         if(heihou == 1){
            return objectCreate("seisuu", obj.kigou, seisuu, null );
         }else{
            return objectCreate(obj.kubun, obj.kigou, seisuu, heihou );
         }
      }

}


function keisanUtil(sahen, uhen, enzan){
      var sahen = kigouAdjust(sahen);
      var uhen = kigouAdjust(uhen);
      // 左辺・右辺ともに整数の場合
      if(sahen.kubun == "seisuu" && uhen.kubun == "seisuu"){
         return seisuuKeisan(sahen.jisuu, uhen.jisuu, enzan);
      }
      // 左辺または右辺が平方根の場合
      else if(sahen.kubun == "route" || uhen.kubun == "route"){
         return routeKeisan(sahen, uhen, enzan);
      }

      else{
         return syousuuKeisan(sahen.jisuu, uhen.jisuu, enzan);
      }
}

function kigouAdjust(obj){
      //　記号が「－」だった場合
      if(obj.kigou == "sa"){
         obj.jisuu = (-1) * (obj.jisuu);
         return obj;
      }else{
         obj.jisuu = (1) * (obj.jisuu);
         return obj;
      }
}

function seisuuKeisan(sahen, uhen, enzan){
      //　足し算の場合
      if(enzan == "wa"){
          return sahen + uhen;
      //　引き算の場合
      }else if (enzan == "sa"){
          return sahen - uhen;
      //　かけ算の場合
      }else if (enzan == "seki"){
          return sahen * uhen;
      //　割り算の場合
      }else if (enzan == "syou"){
          var amari = sahen % uhen;
          if(amari == 0){
             return sahen / uhen;
          }else {
             var kouyakusuu = getKouyakusuu(sahen, uhen);
             if(kouyakusuu != 0){
                 sahen = sahen / kouyakusuu;
                 uhen = uhen / kouyakusuu;
             }
             return getBunsuu(sahen, uhen);

          }

      }
}

function syousuuKeisan(sahen, uhen, enzan){
      var kekka;
      //　足し算の場合
      if(enzan == "wa"){
          kekka = sahen + uhen;
          return marumeKekka(kekka);
      //　引き算の場合
      }else if (enzan == "sa"){
          kekka = sahen - uhen;
          return marumeKekka(kekka);
      //　かけ算の場合
      }else if (enzan == "seki"){
          kekka = sahen * uhen;
          return marumeKekka(kekka);
      //　割り算の場合
      }else if (enzan == "syou"){
          var amari = sahen % uhen;
          amari = marumeKekka(amari);
          if(amari == 0){
             kekka = sahen / uhen;
             return marumeKekka(kekka);
          }else {
             var kouyakusuu = getSyousuuKouyakusuu(sahen, uhen);
             if(kouyakusuu != 0){
                 sahen = marumeKekka(sahen / kouyakusuu);
                 uhen = marumeKekka(uhen / kouyakusuu);
             }
             return getBunsuu(sahen, uhen);

          }

      }
}

function routeKeisan(sahen, uhen, enzan){
      var kekka = "";
      // 左辺・右辺ともに平方根の場合
      if(sahen.kubun == "route" && uhen.kubun == "route"){
         //　足し算の場合
         if(enzan == "wa"){
           // 左辺と右辺の平方部が同じ場合
           if(sahen.heihoukon == uhen.heihoukon){
              sahen.jisuu = sahen.jisuu + uhen.jisuu;
              // 実数部が0の場合
              if(sahen.jisuu == 0){
                 return 0;
              }
              // 実数部が1の場合
              else if(sahen.jisuu == 1){
                 kekka = ("\u221a") + sahen.heihoukon;
                 return kekka;
              }
              else{
                 kekka = sahen.jisuu + ("\u221a") + sahen.heihoukon;
                 return kekka;
              }

           }else{
              // 実数部が1の場合
              if(sahen.jisuu == 1){
                  kekka = ("\u221a") + sahen.heihoukon;
              }else if(sahen.jisuu == -1){
                  kekka = "-" + ("\u221a") + sahen.heihoukon;
              }else{
                  kekka = sahen.jisuu + ("\u221a") + sahen.heihoukon;
              }

              // 右辺が負の場合
              if(uhen.jisuu < 0){
                 // 実数部が1の場合
                 if(uhen.jisuu == -1){
                    kekka = kekka + "&nbsp;&nbsp;-&nbsp;&nbsp;" + ("\u221a") + uhen.heihoukon;
                 }else{
                    kekka = kekka + "&nbsp;&nbsp;-&nbsp;&nbsp;" + uhen.jisuu + ("\u221a") + uhen.heihoukon;
                 }
                 return kekka;
              }else{
                 // 実数部が1の場合
                 if(uhen.jisuu == 1){
                    kekka = kekka + "&nbsp;&nbsp;+&nbsp;&nbsp;" + ("\u221a") + uhen.heihoukon;
                 }else{
                    kekka = kekka + "&nbsp;&nbsp;+&nbsp;&nbsp;" + uhen.jisuu + ("\u221a") + uhen.heihoukon;
                 }
                 return kekka;
              }
           }
         }
         //　引き算の場合
         if(enzan == "sa"){
           // 左辺と右辺の平方部が同じ場合
           if(sahen.heihoukon == uhen.heihoukon){
              sahen.jisuu = sahen.jisuu - uhen.jisuu;
              // 実数部が0の場合
              if(sahen.jisuu == 0){
                 return 0;
              }
              // 実数部が1の場合
              else if(sahen.jisuu == 1){
                 kekka = ("\u221a") + sahen.heihoukon;
                 return kekka;
              }
              else{
                 kekka = sahen.jisuu + ("\u221a") + sahen.heihoukon;
                 return kekka;
              }

           }else{
              // 実数部が1の場合
              if(sahen.jisuu == 1){
                  kekka = ("\u221a") + sahen.heihoukon;
              }else if(sahen.jisuu == -1){
                  kekka = "-" + ("\u221a") + sahen.heihoukon;
              }else{
                  kekka = sahen.jisuu + ("\u221a") + sahen.heihoukon;
              }
              // 右辺が負の場合
              if(uhen.jisuu < 0){
                 // 実数部が1の場合
                 if(uhen.jisuu == -1){
                    kekka = kekka + "&nbsp;&nbsp;+&nbsp;&nbsp;" + ("\u221a") + uhen.heihoukon;
                 }else{
                    kekka = kekka + "&nbsp;&nbsp;+&nbsp;&nbsp;" + uhen.jisuu + ("\u221a") + uhen.heihoukon;
                 }
                 return kekka;
              }else{
                 // 実数部が1の場合
                 if(uhen.jisuu == 1){
                    kekka = kekka + "&nbsp;&nbsp;-&nbsp;&nbsp;" + ("\u221a") + uhen.heihoukon;
                 }else{
                    kekka = kekka + "&nbsp;&nbsp;-&nbsp;&nbsp;" + uhen.jisuu + ("\u221a") + uhen.heihoukon;
                 }
                 return kekka;
              }
           }
         }

         //　かけ算の場合
         if(enzan == "seki"){
           sahen.jisuu = sahen.jisuu * uhen.jisuu;
           sahen.heihoukon = sahen.heihoukon * uhen.heihoukon;
           sahen = adjustRoute(sahen);
           // 区分が平方根でない場合
           if(sahen.kubun != "route"){
              return sahen.jisuu;
           }else{
              // 実数部が0の場合
              if(sahen.jisuu == 0){
                return 0;
              }
              // 実数部が1の場合
              else if(sahen.jisuu == 1){
                 kekka = ("\u221a") + sahen.heihoukon;
                 return kekka;
              }
              else{
                 kekka = sahen.jisuu + ("\u221a") + sahen.heihoukon;
                 return kekka;
              }
           }

         //　割り算の場合
         }else if (enzan == "syou"){
           // 右辺の平方部を両方にかける
           sahen.heihoukon = sahen.heihoukon * uhen.heihoukon;
           sahen = adjustRoute(sahen);
           uhen.heihoukon = uhen.heihoukon * uhen.heihoukon;
           uhen = adjustRoute(uhen);

           // 左辺の実数を右辺の実数で割った余り
           var amari = sahen.jisuu % uhen.jisuu;
           // 余りがない場合
           if(amari == 0){
              // 左辺の区分が平方根でない場合
              if(sahen.kubun != "route"){
                 return (sahen.jisuu/uhen.jisuu);
              }else{
                 kekka = (sahen.jisuu/uhen.jisuu) + ("\u221a") + sahen.heihoukon;
                 return kekka;
              }

           }
           else{
              var kouyakusuu = getKouyakusuu(sahen.jisuu, uhen.jisuu);
              if(kouyakusuu != 0){
                 sahen.jisuu = sahen.jisuu / kouyakusuu;
                 uhen.jisuu = uhen.jisuu / kouyakusuu;
              }
              // 左辺の区分が平方根でない場合
              if(sahen.kubun != "route"){
                 return getBunsuu(sahen.jisuu, uhen.jisuu);
              }else{
                 return getRouteBunsuu(sahen, uhen);
              }

           }

         }
      // 左辺または右辺が平方根でない場合
      }else{
         var heihou;
         var jisuu;
         // 左辺が平方根の場合
         if(sahen.kubun =="route"){
            heihou = sahen;
            jisuu = uhen;
         // 右辺が平方根の場合
         }else{
            heihou = uhen;
            jisuu = sahen;
         }

         //　足し算の場合
         if(enzan == "wa"){
            // 整数部が0の場合
            if(jisuu.jisuu ==0){
                kekka = heihou.jisuu + ("\u221a") + heihou.heihoukon;
                return kekka;

            // 整数部が負の場合
            }else if(jisuu.jisuu < 0){
                if(heihou.jisuu == 1){
                   kekka = ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;-&nbsp;&nbsp;" + (jisuu.jisuu * (-1));
                   return kekka;
                }else if(heihou.jisuu == -1){
                   kekka = "-" + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp-&nbsp;&nbsp;" + (jisuu.jisuu * (-1));
                   return kekka;

                }else{
                   kekka = heihou.jisuu + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;-&nbsp;&nbsp;" + (jisuu.jisuu * (-1));
                   return kekka;
                }

            }else{
                if(heihou.jisuu == 1){
                   kekka = ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;+&nbsp;&nbsp;" + jisuu.jisuu;
                   return kekka;
                }else if(heihou.jisuu == -1){
                   kekka = "-" + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;+&nbsp;&nbsp;" + jisuu.jisuu;
                   return kekka;

                }else{
                   kekka = heihou.jisuu + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;+&nbsp;&nbsp;" + jisuu.jisuu;
                   return kekka;
                }
            }
         //　引き算の場合
         }else if(enzan == "sa"){
            // 整数部が0の場合
            if(jisuu.jisuu ==0){
                kekka = heihou.jisuu + ("\u221a") + heihou.heihoukon;
                return kekka;

            // 整数部が負の場合
            }else if(jisuu.jisuu < 0){
                if(heihou.jisuu == 1){
                   kekka = ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;+&nbsp;&nbsp;" + (jisuu.jisuu * (-1));
                   return kekka;
                }else if(heihou.jisuu == -1){
                   kekka = "-" + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;+&nbsp;&nbsp;" + (jisuu.jisuu * (-1));
                   return kekka;

                }else{
                   kekka = heihou.jisuu + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;+&nbsp;&nbsp;" + (jisuu.jisuu * (-1));
                   return kekka;
                }
            }else{
                if(heihou.jisuu == 1){
                   kekka = ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;-&nbsp;&nbsp;" + jisuu.jisuu;
                   return kekka;
                }else if(heihou.jisuu == -1){
                   kekka = "-" + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;-&nbsp;&nbsp;" + jisuu.jisuu;
                   return kekka;

                }else{
                   kekka = heihou.jisuu + ("\u221a") + heihou.heihoukon +"&nbsp;&nbsp;-&nbsp;&nbsp;" + jisuu.jisuu;
                   return kekka;
                }
            }
         //　かけ算の場合
         }else if(enzan == "seki"){
            // 整数部が0の場合
            if(jisuu.jisuu ==0){
                return 0;

            }else{
                heihou.jisuu = heihou.jisuu * jisuu.jisuu;
                kekka = heihou.jisuu  + ("\u221a") + heihou.heihoukon
                return kekka;
            }
         //　割り算の場合
         }else{
            // 左辺が平方根の場合
            if(sahen.kubun =="route"){
               // 右辺が小数の場合
               if(uhen.kubun == "syousuu"){
                  var amari = heihou.jisuu % jisuu.jisuu;
                  amari = marumeKekka(amari);
                  if(amari == 0){
                     var jisuuNumber = marumeKekka(heihou.jisuu / jisuu.jisuu);
                     kekka = jisuuNumber + ("\u221a") + heihou.heihoukon;
                     return kekka;
                  }else {
                     var kouyakusuu = getSyousuuKouyakusuu(heihou.jisuu, jisuu.jisuu);
                     if(kouyakusuu != 0){
                        heihou.jisuu = marumeKekka(heihou.jisuu / kouyakusuu);
                        jisuu.jisuu = marumeKekka(jisuu.jisuu / kouyakusuu);
                     }
                  }
                  return getRouteBunsuu(heihou, jisuu);
               // 右辺が整数の場合
               }else{
                  var amari = heihou.jisuu % jisuu.jisuu;
                  if(amari == 0){
                     var jisuuNumber = heihou.jisuu / jisuu.jisuu;
                     kekka = jisuuNumber + ("\u221a") + heihou.heihoukon;
                     return kekka;
                  }else {
                     var kouyakusuu = getKouyakusuu(heihou.jisuu, jisuu.jisuu);
                     if(kouyakusuu != 0){
                        heihou.jisuu = heihou.jisuu / kouyakusuu;
                        jisuu.jisuu = jisuu.jisuu / kouyakusuu;
                     }
                  }
                  return getRouteBunsuu(heihou, jisuu);
               }
            // 右辺が平方根の場合
            }else{
               // 実数部が0の場合
               if(jisuu.jisuu == 0){
                   return 0;
               }
               var bunbo;
               var bunshi;
               // 右辺の平方部を両方にかける
               bunshi = objectCreate("route", jisuu.kigou, jisuu.jisuu, heihou.heihoukon );
               heihou.heihoukon = heihou.heihoukon * heihou.heihoukon;
               bunbo = adjustRoute(heihou);
               // 左辺が小数の場合
               if(sahen.kubun == "syousuu"){
                  var amari = bunshi.jisuu % bunbo.jisuu;
                  amari = marumeKekka(amari);
                  if(amari == 0){
                     var jisuuNumber = marumeKekka(bunshi.jisuu / bunbo.jisuu);
                     if(jisuuNumber == 1){
                        kekka = ("\u221a") + bunshi.heihoukon;
                     }else if(jisuuNumber == -1){
                        kekka = "-" + ("\u221a") + bunshi.heihoukon;
                     }else{
                        kekka = jisuuNumber + ("\u221a") + bunshi.heihoukon;
                     }

                     return kekka;
                  }else {
                     var kouyakusuu = getSyousuuKouyakusuu(bunshi.jisuu, bunbo.jisuu);
                     if(kouyakusuu != 0){
                        heihou.jisuu = marumeKekka(bunshi.jisuu / kouyakusuu);
                        jisuu.jisuu = marumeKekka(bunbo.jisuu / kouyakusuu);
                     }
                  }
                  return getRouteBunsuu(heihou, jisuu);
               // 右辺が整数の場合
               }else{
                  var amari = bunshi.jisuu % bunbo.jisuu;
                  if(amari == 0){
                     var jisuuNumber = bunshi.jisuu / bunbo.jisuu;
                     if(jisuuNumber == 1){
                        kekka = ("\u221a") + bunshi.heihoukon;
                     }else if(jisuuNumber == -1){
                        kekka = "-" + ("\u221a") + bunshi.heihoukon;
                     }else{
                        kekka = jisuuNumber + ("\u221a") + bunshi.heihoukon;
                     }
                     return kekka;
                  }else {
                     var kouyakusuu = getKouyakusuu(bunshi.jisuu, bunbo.jisuu);
                     if(kouyakusuu != 0){
                        bunshi.jisuu = bunshi.jisuu / kouyakusuu;
                        bunbo.jisuu = bunbo.jisuu / kouyakusuu;
                     }
                  }
                  return getRouteBunsuu(bunshi, bunbo);
               }
            }
         }
      }
}

function getKouyakusuu(sahen, uhen){
     var i;
     var sahenValue;
     var uhenValue;
     // 左辺または右辺が負の場合、正に値を戻す。
     if(sahen < 0){
        sahenValue = (-1) * sahen;
     }else{
        sahenValue = sahen;
     }

     if(uhen < 0){
        uhenValue = (-1) * uhen;
     }else{
        uhenValue = uhen;
     }

     var amariSahen;
     var amariUhen;

     for(i=uhenValue; i > 1; i--){
         amariSahen = sahenValue % i;
         amariUhen = uhenValue % i;
         // 余りがない場合
         if( (amariSahen == 0) && (amariUhen ==0) ){
            return i;
         }
    }
    // 公約数がない場合
    return 0;
}

function getSyousuuKouyakusuu(sahen, uhen){
     var i;
     var sahenValue;
     var uhenValue;
     // 左辺または右辺が負の場合、正に値を戻す。
     if(sahen < 0){
        sahenValue = (-1) * sahen;
     }else{
        sahenValue = sahen;
     }

     if(uhen < 0){
        uhenValue = (-1) * uhen;
     }else{
        uhenValue = uhen;
     }

     var amariSahen;
     var amariUhen;
     // 左辺の小数桁を取得
     var sahenStr = new String(sahenValue);
     var sahenKeta;
     if(sahenStr.indexOf(".") != -1){
        sahenKeta = sahenStr.length - (sahenStr.indexOf(".") + 1);
     }else{
        sahenKeta = -1
     }

     // 右辺の小数桁を取得
     var uhenStr = new String(uhenValue);
     if(uhenStr.indexOf(".") != -1){
        uhenKeta = uhenStr.length - (uhenStr.indexOf(".") + 1);
     }else{
        uhenKeta = -1
     }

     var marumeKeta = 1;
     var i=0;
     // 小数桁の大きいほうをベースに丸める
     if(sahenKeta < uhenKeta){
         if(uhenKeta != -1){
            for(i; i<uhenKeta; i++){
               marumeKeta = marumeKeta * 10;
            }
         }

     }else{
         if(sahenKeta != -1){
            for(i; i<sahenKeta; i++){
               marumeKeta = marumeKeta * 10;
            }
         }

     }

     sahenValue = marumeKeta * sahenValue;
     uhenValue = marumeKeta * uhenValue;
     for(i=uhenValue; i > 1; i--){
         amariSahen = marumeKekka(sahenValue % i);
         amariUhen = marumeKekka(uhenValue % i);
         // 余りがない場合
         if( (amariSahen == 0) && (amariUhen ==0) ){
            var kouyakusuu = i/marumeKeta;
            return kouyakusuu;
         }
    }
    // 公約数がない場合
    return 0;
}

function getBunsuu(sahen, uhen){
     var kekka;
     var seki = sahen * uhen;
     // 左辺または右辺が負の場合、正に値を戻す。
     if(sahen < 0){
        sahen = (-1) * sahen;
     }else{
        sahen = sahen;
     }

     if(uhen < 0){
        uhen = (-1) * uhen;
     }else{
        uhen = uhen;
     }
     kekka = "<table><tr><td></td><td align = \"center\">" + sahen + "</td></tr>";

     if(seki < 0){
        kekka = kekka + "<tr><td>－</td><td>――――</td></tr>"
     }else{
        kekka = kekka + "<tr><td></td><td>――――</td></tr>"
     }

     kekka = kekka + "<tr><td></td><td align = \"center\">" + uhen + "</td></tr></table>";

     return kekka;

}

function getRouteBunsuu(bunshi, bunbo){
     var kekka;
     var seki = bunshi.jisuu * bunbo.jisuu;
     // 左辺または右辺が負の場合、正に値を戻す。
     if(bunshi.jisuu < 0){
        bunshi.jisuu = (-1) * bunshi.jisuu;
     }

     if(bunbo.jisuu < 0){
        bunbo.jisuu = (-1) * bunbo.jisuu;
     }
     // 分子の整数部が1の場合、1を出力しない
     if(bunshi.jisuu == 1){
        kekka = "<table><tr><td></td><td align = \"center\">" + ("\u221a") + bunshi.heihoukon + "</td></tr>";
     }else{
        kekka = "<table><tr><td></td><td align = \"center\">" + bunshi.jisuu + ("\u221a") + bunshi.heihoukon + "</td></tr>";
     }

     if(seki < 0){
        kekka = kekka + "<tr><td>－</td><td>――――</td></tr>"
     }else{
        kekka = kekka + "<tr><td></td><td>――――</td></tr>"
     }

     kekka = kekka + "<tr><td></td><td align = \"center\">" + bunbo.jisuu + "</td></tr></table>";

     return kekka;

}

function marumeKekka(value){
     var kekka = new String(value);
     // 小数点以下の桁数を取得
     if(kekka.indexOf(".") != -1){
        var syousuuKeta = kekka.length - (kekka.indexOf(".") + 1);
        // 小数点以下が10桁より大きい場合、四捨五入
        if(syousuuKeta > 10){
           value = value * 10000000000;
           value = Math.round(value);
           value = value / 10000000000;
        }
     }
     return value;

}