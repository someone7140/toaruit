import { Injectable } from '@angular/core';
import { KpiTree } from '../kpitree';
import { KpiObject } from '../kpiobject';
import { CaluculateSettingObject } from '../calculateobject';
import { EnzanConstList } from '../calculateobject';

@Injectable()
export class NewTreeService{
   // KPIツリーを格納する変数
   tree: KpiTree =
   {
      id    : "",
      title : "",
      kpiArray:[],
   };
   // 削除対象のKPIを格納する変数
   deleteKpiArray: any[] = [];
   
   constructor(){
      // null・空文字判定
      if(sessionStorage.getItem("newkpiTree") != null && sessionStorage.getItem("newkpiTree") != ''){
         // セッションストレージからKPIツリーの情報を取得
         this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));
      }else{
         // IDを現在時刻で生成
         var now = new Date();
         var nowString = now.toLocaleString();
         // スラッシュとコロンと空白を置換
         nowString = nowString.split('/').join('');
         nowString = nowString.split(':').join('');
         nowString = nowString.split(' ').join('');
         this.tree.id = nowString + "_kpitree"
      
      }
   }
   
   // ツリーのIDを取得
   public getId():string{
      return this.tree.id;
   }

   // ツリーのタイトルを取得
   public getTitle():string{
      return this.tree.title;
   }

  // ツリーのタイトルをセット
   public setTitle(inputTitle : string):void{
      this.tree.title = inputTitle;
      // セッションストレージにKPIツリーの情報をセット
      sessionStorage.setItem( "newkpiTree" , JSON.stringify(this.tree) );
   }
   
   // KPIのオブジェクトを追加
   public addKpiObject(kpinameInput:string, kpiunitInput:string, kpidetailInput:string, parentidInput:string, parentlinktextInput:string, sortInput:string):void{
      // 現状の配列の長さを取得
      let arrayLength:number = this.tree.kpiArray.length;
      // IDを設定
      let kpiid:string  = (arrayLength + 1).toString();
      // layerを設定
      let kpilayer:string = "";
      // sortを設定
      let kpisort:string = "";
      // 既存で設定されているKPIがない場合
      if(arrayLength <= 0){
          // 0の場合はlayerを1000
          kpilayer = "1000";
          // sortを1000
          kpisort = "1000";
      // KPIがある場合
      }else{
          // 指定されたKpiの親オブジェクトを取得
          let parentKpiObject:KpiObject = this.tree.kpiArray.find( (kpiObject) => {return (kpiObject.id === parentidInput)});
          // 親のlayerを取得
          let templayer:number = parseInt(parentKpiObject.layer);
          // 親のlayerから1プラスして設定
          kpilayer = (templayer + 1).toString();
        //  // KPIの配列
        //  for (var i = 0; i < arrayLength; i++) {
        //      // parentidのKPIを探す
        //      if(this.tree.kpiArray[i].id == parentidInput){
        //          // 親のlayerを取得
        //          let templayer:number = parseInt(this.tree.kpiArray[i].layer);
        //          // 親のlayerから1プラスして設定
        //          kpilayer = (templayer + 1).toString();
        //          break;
        //      }
        //  }
          // 入力された内容でsortを設定
          kpisort = sortInput;
          // parentidの入力がない場合
          //if(parentidInput == null || parentidInput == ""){
          //    // 
          //    for (var i = 0; i < arrayLength; i++) {
          //       // 自分のIDを親に設定しているKPI
          //       if(this.tree.kpiArray[i].parentid == kpiid){
          //           let templayer:number = parseInt(this.tree.kpiArray[i].layer);
          //           // 1マイナスして設定
          //           kpilayer = (templayer - 1).toString();
          //           break;
          //       }
          //    }
          // parentidの入力がある場合
          //}else{
          //}
      }
      
      // 引数からオブジェクトを作成
      let kpiobject:KpiObject;
      kpiobject= {
         id:kpiid,
         kpiname:kpinameInput,
         kpiunit:kpiunitInput,
         kpidetail:kpidetailInput,
         parentid:parentidInput,
         layer:kpilayer,
         parentlinktext:parentlinktextInput,
         sort: kpisort,
         calculateList: [],
         kpiTargetList: [],
         kpiProgressList: []
      };
      
      // 配列にオブジェクトを追加
      this.tree.kpiArray.push(kpiobject);
      // ソート
      this.tree.kpiArray.sort(this.kpiArraySort);
      // セッションストレージにKPIツリーの情報をセット
      sessionStorage.setItem( "newkpiTree" , JSON.stringify(this.tree) );
   }
   
   // KPIのオブジェクトを変更
   public changeKpiObject(kpiId: string , kpinameInput:string, kpiunitInput:string, kpidetailInput:string, parentidInput:string, parentlinktextInput:string, sortInput:string):void{
       // 該当のKPIの配列項番を取得
       let kpiIndex = this.tree.kpiArray.findIndex( (kpiObject) => {return (kpiObject.id === kpiId)});
       // 名前の変更
       this.tree.kpiArray[kpiIndex].kpiname = kpinameInput;
       // 単位の変更
       this.tree.kpiArray[kpiIndex].kpiunit = kpiunitInput;
       // 詳細の変更
       this.tree.kpiArray[kpiIndex].kpidetail = kpidetailInput;
       // 親にするKPI
       let parentkpiIndex = this.tree.kpiArray.findIndex( (kpiObject) => {return (kpiObject.id === parentidInput)});
       // 親にするKPIの親が自分
       if(parentkpiIndex >= 0 && this.tree.kpiArray[parentkpiIndex].parentid == kpiId){
          // 親にするKPIの親とソートを自分のに設定
          this.tree.kpiArray[parentkpiIndex].parentid = this.tree.kpiArray[kpiIndex].parentid;
          this.tree.kpiArray[parentkpiIndex].sort = this.tree.kpiArray[kpiIndex].sort;
          // 自分の計算式設定のクリア判定
          let calculateKpiMyObject:CaluculateSettingObject = this.tree.kpiArray[kpiIndex].calculateList.find( (calculateObj) => {return (calculateObj.kpiId === parentidInput)});
          // 見つかった場合
          if(calculateKpiMyObject != null && calculateKpiMyObject.kpiId === parentidInput){
              // 自分の計算式をクリア
              this.tree.kpiArray[kpiIndex].calculateList = [];
          }
          // 親IDの変更
          this.tree.kpiArray[kpiIndex].parentid = parentidInput;
       }else{
           let parentidInputTemp = "";
           if(parentidInput != null){
                parentidInputTemp = parentidInput;
           }
           let registeredParentId = "";
           if(this.tree.kpiArray[kpiIndex].parentid != null){
                registeredParentId = this.tree.kpiArray[kpiIndex].parentid;
           }
           // 親IDの変更判定
           if(parentidInputTemp != registeredParentId){
               // 親の計算式設定のクリア判定
               let calculateKpiobject:KpiObject = this.tree.kpiArray[parentkpiIndex].calculateList.find( (calculateObj) => {return (calculateObj.kpiId === kpiId)});
               // 見つかった場合
               if(calculateKpiobject != null && calculateKpiobject.id === kpiId){
                    // 親の計算式をクリア
                    this.tree.kpiArray[parentkpiIndex].calculateList = [];
               }
               // 親IDの変更
               this.tree.kpiArray[kpiIndex].parentid = parentidInput;
           }

       }
       //親テキストの設定
       this.tree.kpiArray[kpiIndex].parentlinktext = parentlinktextInput;
       // ソートの設定
       this.tree.kpiArray[kpiIndex].sort = sortInput;
       // 計算式の取得
       let tempkpiCalculate:KpiObject = JSON.parse(sessionStorage.getItem("tempkpiCalculate"));
       // ID同一判定
       if(tempkpiCalculate!= null && tempkpiCalculate.id == kpiId && tempkpiCalculate.calculateList != null && tempkpiCalculate.calculateList.length >=1){
           // 計算式の設定
           this.tree.kpiArray[kpiIndex].calculateList = tempkpiCalculate.calculateList;
       }
       // 計算式のtempセッションクリア
       this.clearCalculateList(kpiId);
       // ソート
      this.tree.kpiArray.sort(this.kpiArraySort);
      // セッションストレージにKPIツリーの情報をセット
      sessionStorage.setItem( "newkpiTree" , JSON.stringify(this.tree) );
   }
   
   // KPIツリーにすでにKPIが設定されているか
   public isKpiRegistred():boolean{
      // 配列の長さが0
      if(this.tree.kpiArray.length <= 0){
         return false;
      }else{
         return true;
      }
   }
   
   // 表示用のグラフデータ取得
   public getHierarchialGraph():any{
       // セッションストレージからKPIツリーの情報を取得
       this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));

       // ノードのオブジェクト配列
       var nodesSetArray:any[] =[];
       // リンクのオブジェクト配列
       var linksSetArray:any[] = [];
       
       // nodeのセット
       for (var i = 0; i < this.tree.kpiArray.length; i++) {
           // nodeにIDとラベルと詳細を設定
           let node:any;
           node = {id: this.tree.kpiArray[i].id, label: this.tree.kpiArray[i].kpiname, detail:this.tree.kpiArray[i].kpidetail};
           // nodeの配列に追加
           nodesSetArray.push(node);
       }
       
       // linkのセット
       // ツリーの配列数が2以上
       if(this.tree.kpiArray.length >= 2){
           // linkのセット
           for (var i = 0; i < this.tree.kpiArray.length; i++) {
               // 親IDがある。
               if(this.tree.kpiArray[i].parentid != null && this.tree.kpiArray[i].parentid != ''){
                   // linkに自ID・親ID・リンクテキストをセット
                   let link:any;
                   link = {source: this.tree.kpiArray[i].id, target: this.tree.kpiArray[i].parentid, label:this.tree.kpiArray[i].parentlinktext};
                   // linkの配列に追加
                   linksSetArray.push(link);
               
               }
           }
           
       }
       // グラフオブジェクトの格納用
       let hierarchialGraph: { links: any[], nodes: any[] };
       // グラフオブジェクトにノードとリンクをセット
       hierarchialGraph = {links:linksSetArray, nodes:nodesSetArray};
       // グラフオブジェクトを返す
       return hierarchialGraph;
   
   }
   
   // 親KPI用のコンボ配列取得
   public getKpiListForCombo(clickedKpiId:string):any[]{
      // コンボ用のオブジェクト配列
      var comboSetArray:any[] = [];
      
      // クリックされたKPIオブジェクトがある、かつツリー配列が2以上
      if(clickedKpiId != null && clickedKpiId != '' && this.tree.kpiArray.length >= 2){
          // クリックされたKPIオブジェクトを取得
          let clickedKpiObject = this.getKpiObjectFromId(clickedKpiId);
          // クリックされたKPIオブジェクトに親がない
          if(clickedKpiObject.parentid == null || clickedKpiObject.parentid ==''){
              // コンボボックスのオブジェクトに未選択用の項目を追加
              let comboObj:any;
              comboObj = {label: "未選択", value: '', disabled:false};
              // コンボ用のオブジェクト配列に追加
              comboSetArray.push(comboObj);
          }
      }

      // KPIの配列でループ
      for (var i = 0; i < this.tree.kpiArray.length; i++) {
          // idがクリックされたKPIでない
          if(this.tree.kpiArray[i].id != clickedKpiId){
             // KPI配列からコンボボックスのオブジェクトをセット
             let comboObj:any;
             comboObj = {label: this.tree.kpiArray[i].kpiname, value: this.tree.kpiArray[i].id, disabled:false};
             // コンボ用のオブジェクト配列に追加
             comboSetArray.push(comboObj);
          }
      }
      return comboSetArray;
   }
   
   // ソート設定用のリストボックス配列取得
   public getSortSettingList(myKpiId:string , parentselectedId:string):any[]{
      // リストボックス用のオブジェクト配列
      var listBoxSetArray:any[] = [];
      // KPIの配列でループ
      for (var i = 0; i < this.tree.kpiArray.length; i++) {
          // idが選択されたKPIでない、かつ選択された親IDの配下
          if(this.tree.kpiArray[i].id != myKpiId && this.tree.kpiArray[i].parentid == parentselectedId){
             // KPI配列からコンボボックスのオブジェクトをセット
             let listBoxObj:any;
             listBoxObj = {label: this.tree.kpiArray[i].kpiname, value: this.tree.kpiArray[i].id, disabled:false};
             // コンボ用のオブジェクト配列に追加
             listBoxSetArray.push(listBoxObj);
          }
      }
      return listBoxSetArray;
   }
   
   // 指定したIDより後のKPIのソートをずらしセットするソート番号を返す
   public sortResettingKpiArray(selectedKpiId:String , selectedSortBfAf:String):string{
      // 指定されたKpiのオブジェクトを取得
      let selectKpiobject:KpiObject = this.tree.kpiArray.find( (kpiObject) => {return (kpiObject.id === selectedKpiId)});
      // 返却するソート番号
      let returnSort:string = selectKpiobject.sort;
      // KPIの配列
      for (var i = 0; i < this.tree.kpiArray.length; i++) {
          // layerが同じものを探す
          if(this.tree.kpiArray[i].layer == selectKpiobject.layer){
              // 前に追加
              if(selectedSortBfAf == 'BF'){
                   // ソート番号を比較して選択されたものより小さい
                   if( parseInt(this.tree.kpiArray[i].sort) <  parseInt(selectKpiobject.sort) ){
                       // 1減らす
                       this.tree.kpiArray[i].sort = ( parseInt(this.tree.kpiArray[i].sort) - 1 ).toString();
                   // それ以外は1ずつ増やす
                   }else{
                       this.tree.kpiArray[i].sort = ( parseInt(this.tree.kpiArray[i].sort) + 1 ).toString();
                   }
              // 後に追加
              }else{
                   // ソート番号を比較して選択されたものより大きい
                   if( parseInt(this.tree.kpiArray[i].sort) >  parseInt(selectKpiobject.sort) ){
                       // 1増やす
                       this.tree.kpiArray[i].sort = ( parseInt(this.tree.kpiArray[i].sort) + 1 ).toString();
                   // それ以外は1ずつ減らす
                   }else{
                       this.tree.kpiArray[i].sort = ( parseInt(this.tree.kpiArray[i].sort) - 1 ).toString();
                   }
              
              }
          }
      }
      // セッションストレージに更新後のKPIツリーの情報をセット
      sessionStorage.setItem( "newkpiTree" , JSON.stringify(this.tree) );
      
      // ソート番号を返す
      return returnSort;
   
   }
   
   // 指定したKPIのID配下のKPIオブジェクトを削除する。
   public deleteKpi(deleteKpiId:string , firstFlg:boolean):void{
       // KPIの配列でループ
       for (var i = 0; i < this.tree.kpiArray.length; i++) {            
            // 自分を親に設定している場合
            if(this.tree.kpiArray[i].parentid == deleteKpiId){
                // 再度該当のIDで削除用のメソッドを呼ぶ
                this.deleteKpi(this.tree.kpiArray[i].id , false);
            }
            // 自分のIDと一致
            if(this.tree.kpiArray[i].id == deleteKpiId){
                // delete対象の配列に登録
                this.deleteKpiArray.push(this.tree.kpiArray[i].id);
            }
       }
       // 最初の呼び出しだけ
       if(firstFlg){
           // もう一度KPIの配列でループ
           for (var k = 0; k < this.tree.kpiArray.length; k++) {
                // 削除対象配列でループ
                for (var l = 0; l < this.deleteKpiArray.length; l++) {
                     // 自分のIDと一致
                     if(this.tree.kpiArray[k].id == this.deleteKpiArray[l]){
                         // 配列から該当のものを削除
                         this.tree.kpiArray.splice(k , 1);
                     }
                }
            }
            // セッションストレージに更新後のKPIツリーの情報をセット
            sessionStorage.setItem( "newkpiTree" , JSON.stringify(this.tree) );
            // 削除対象配列のクリア
            this.deleteKpiArray = [];
       
       }
      
   }
   
   // KPIの配列ソート
   public kpiArraySort(a:any ,b:any):any{
      // layerの比較
      if( parseInt(a.layer) < parseInt(b.layer) ){
          // 昇順でソート
          return -1;
      // layerが同一
      }else if (  parseInt(a.layer) == parseInt(b.layer) ){
          // ソートの比較
          if( parseInt(a.sort) < parseInt(b.sort) ){
             // 昇順でソート
             return -1;
          }else{
             return 1;
          }
      }else{
          // 昇順でソート
          return 1;
      }
   }
   
   // IDを指定してKPIのオブジェクトを取得する
   public getKpiObjectFromId(kpiId:string):KpiObject{
       // セッションストレージからKPIツリーの情報を取得
       this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));
       // IDを指定してオブジェクトを取得
       return this.tree.kpiArray.find( (kpiObject) => {return (kpiObject.id === kpiId)});
   
   }
   
   // IDを指定して子のKPIのリストを取得する
   public getChildKpiList(parentKpiIdInput:string):KpiObject[]{
       // セッションストレージからKPIツリーの情報を取得
       this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));
       // 入力KPIのNULL判定
       if(parentKpiIdInput != null && parentKpiIdInput != ""){
           // 親IDが該当のもの
           return this.tree.kpiArray.filter( (kpiObject) => {return (kpiObject.parentid === parentKpiIdInput)});
       }else{
           // nullを返す
           return null;
       }
   }
   
   // 指定したKPIオブジェクトに計算式のリストを設定する。
   public setCalculateList(calculateList:any[] , kpiId:string):void{
       // セッションストレージからKPIツリーの情報を取得
       this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));
       // 該当のKPIの配列項番を取得
       let kpiIndex = this.tree.kpiArray.findIndex( (kpiObject) => {return (kpiObject.id === kpiId)});
       let tempKpiObj = this.tree.kpiArray[kpiIndex];
       tempKpiObj.calculateList = calculateList;
       // セッションストレージに計算式設定後のKPIツリーの情報をセット
       sessionStorage.setItem( "tempkpiCalculate" , JSON.stringify(tempKpiObj) );
       
   
   }
   
   // 指定したKPIオブジェクトの計算式設定をクリアする。
   public clearCalculateList(kpiId:string):void{
       // セッションストレージからKPIの情報を取得
       let tempKpiObj = JSON.parse(sessionStorage.getItem("tempkpiCalculate"));
       // 該当のKPIの配列項番を取得
       // let kpiIndex = this.tree.kpiArray.findIndex( (kpiObject) => {return (kpiObject.id === kpiId)});
       // null判定
       if(tempKpiObj != null){
            tempKpiObj.calculateList = [];
            // セッションストレージにKPIの情報をセット
            sessionStorage.setItem( "tempkpiCalculate" , JSON.stringify(tempKpiObj) );
       }

   }
   
   // 計算式のtempからオブジェクト取得
   public getTempCalculateObj(kpiId:string):KpiObject{
       let tempKpiObj = JSON.parse(sessionStorage.getItem("tempkpiCalculate"));
       // IDが同一
       if(tempKpiObj != null && tempKpiObj.id == kpiId){
           return tempKpiObj;
       }else{
           return null;
       }

   }

   // 指定したKPIオブジェクトの計算式内容を文字列で取得する
   public getCalculateString(kpiId:string):string{
       // セッションストレージからtempのKPIツリーの情報を取得
       let tempKpiObj =  JSON.parse(sessionStorage.getItem("tempkpiCalculate"));
       // セッションストレージに保存しているKPIつりーからKPIオブジェクトを取得
       this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));
       let kpiobjectFromTree = this.tree.kpiArray.find( (kpiObject) => {return (kpiObject.id === kpiId)});
       // tempのnull判定
       if(tempKpiObj != null && tempKpiObj.calculateList != null && tempKpiObj.calculateList.length >=1 ){
           let calculateList:any[];
           // 選択したKPIIDと違う
           if(kpiId != tempKpiObj.id){
               this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));
               // IDを指定してオブジェクトを取得
               let kpiobject = this.tree.kpiArray.find( (kpiObject) => {return (kpiObject.id === kpiId)});
               calculateList = kpiobject.calculateList;
               // セッションストレージの計算式KPIの情報をnull
               sessionStorage.setItem( "tempkpiCalculate" , null );
           }else{
               calculateList = tempKpiObj.calculateList;
           }
           
           return this.makeCalculateString(calculateList);
       // ツリーから取得した内容の判定
       }else if(kpiobjectFromTree != null && kpiobjectFromTree.calculateList != null && kpiobjectFromTree.calculateList.length >=1){
           return this.makeCalculateString( kpiobjectFromTree.calculateList);
       }else{
           // そもそも設定がない場合は空を返す
           return "";
       
       }
   }
   
   // 計算式のリストオブジェクトから文字列作成
   public makeCalculateString(calculateList:any[]):string{
         // 計算式内容の文字列
         let calculateString:string = "";
         // 計算式のリストでループ
         for(let calculateObj of calculateList){
              // 演算が空でない
              if(calculateObj.enzan != null && calculateObj.enzan != ""){
                    // 演算の定数よりオブジェクトを取得
                    let enzanObj = EnzanConstList.find( (enzanObj) => {return (enzanObj.value === calculateObj.enzan )});
                    // 文字列に演算を結合
                    calculateString = calculateString + enzanObj.label;
              }
              // 入力方法がコンボの場合
              if(calculateObj.inputMethod == "combo"){
                    // 選択されたKPI・IDからオブジェクトを取得
                    let kpiobjectSelected = this.tree.kpiArray.find( (kpiObject) => {return (kpiObject.id === calculateObj.kpiId)});
                    // KPIの名称で文字列結合
                    calculateString = calculateString + kpiobjectSelected.kpiname;
              // それ以外
              }else{
                    // テキストをそのまま文字列結合
                    calculateString = calculateString + calculateObj.inputText;
              }
         }
         return calculateString;
   
   }
   
   // KPIツリーをwebストレージへ保存
   public storeLocalStrageKpiTree(){
       // セッションストレージからKPIツリーの情報を取得
       this.tree = JSON.parse(sessionStorage.getItem("newkpiTree"));
       // ツリーのIDでローカルストレージへ保存
       localStorage.setItem( this.tree.id , JSON.stringify(this.tree) );
   }
   
   // KPIツリーをローカルからセッションへ
   public copyKpiTreeLocalStrageToSession(kpiTreeId:string){
       // ローカルストレージからツリー情報を取得
       this.tree = JSON.parse(localStorage.getItem(kpiTreeId));
       // セッションへ格納
       sessionStorage.setItem( "newkpiTree" , JSON.stringify(this.tree) );
   
   }
   
   // KPIのセッション情報をクリア
   public clearKpiTreeFromSession(){
       // ツリー情報を初期化
       this.tree = {
          id    : "",
          title : "",
          kpiArray:[],
       };
       // セッションを削除
       sessionStorage.clear();
   
   }
   
   
}
