import { Injectable } from '@angular/core';
import { KpiTarget }  from './kpitarget';
import { KpiProgress }  from './kpiprogress';
import { NewTreeService }  from './Service/newtree.service';

@Injectable()
export class KpiSettingService{
   
   constructor(private newTreeService: NewTreeService) { }
   
   // 現在のKpiTargetを取得
   public getPresentKpiTarget(kpiId : string):int{
      // セッションストレージからKPIのオブジェクトを取得
      var kpiObject = newTreeService.getKpiObjectFromId(kpiId);
      // 計算式がある。
      if(kpiObject.calculateList.length > 0){
          var tempEnzan = "";
          var tempValue = 0;
          var caluculateValue = 0;
          // 計算式のリストでループ
          for(let calculateObj of kpiObject.calculateList){
              
              // 直接入力
              if(calculateObj.inputMethod = "text"){
                  tempValue = parseInt(calculateObj.inputText);
              }else{
                  // 計算式のもととなるKPIの取得
                  var tempKpiObject = newTreeService.getKpiObjectFromId(calculateObj.kpiId);
                  // 該当KPIの目標オブジェクト
                  var tempKpiTarget= this.getPresentKpiTarget(kpiObject);
                  tempValue = parseInt(tempKpiTarget.target);
              }
              // 演算による計算
              caluculateValue = this.getEnzanResult(tempEnzan , caluculateValue , tempValue);
              // 演算更新
              tempEnzan = calculateObj.enzan;
          }
          reutrn caluculateValue;
      }else{
          // 目標リストから最新の日付のものを取得する
          var tempKpiTarget= this.getRecentTarget(kpiObject);
          return tempKpiTarget.target;
      }
   }
   
   // 現在のKpiTargetの期限を取得
   public getPresentKpiLimitDate(kpiId : string):string{
      // セッションストレージからKPIのオブジェクトを取得
      var kpiObject = newTreeService.getKpiObjectFromId(kpiId);
      // 目標リストから最新の日付の期限を取得する
      var tempKpiTarget= this.getRecentTarget(kpiObject);
      if(tempKpiTarget == null){
          // 期限が未設定の文言を返す
          return "期限が未設定です";
      }else{
          // 日付を文字列にして返す
          return getDisplayStringDate(tempKpiTarget.limitdate);
      }

   }
   // 日付を表示用に加工
   public getDisplayStringDate(inputDate:Date):string{
       var y = inputDate.getFullYear();
       var m = ("00" + (inputDate.getMonth()+1)).slice(-2);
       var d = ("00" + inputDate.getDate()).slice(-2);
       var result = y + "/" + m + "/" + d;
       return result;
   }
   
   // 該当のKPIに計算式があるか
   public isHavingCaluculate(kpiId : string):boolean{
      // セッションストレージからKPIのオブジェクトを取得
      var kpiObject = newTreeService.getKpiObjectFromId(kpiId);
      // 計算式がある。
      if(kpiObject.calculateList.length > 0){
          return true;
      }else{
          return false;
      }
   
   }
   
   // 進捗リストから最新の日付のものを取得する
   public getRecentProgress(KpiObject:kpiObject):KpiProgress{
         // 進捗のLISTを取得
         progressList = kpiObject.kpiProgressList;
         // nullまたは長さが0
         if(progressList == null || progressList.length == 0){
              return null;
      
         }else{
              // 昇順にソート
              var sorted_list = progressList.map(function(item) {
                                             return new Date(item.settingdate).getTime();
                                          }).sort(); 
              // 最新のオブジェクトを取得
              var latestProgress= sorted_list[sorted_list.length-1];
              return latestProgress;
          }
   }
   
   // 目標リストから最新の日付のものを取得する
   public getRecentTarget(KpiObject:kpiObject):KpiTarget{
         // 目標のLISTを取得
         targetList = kpiObject.kpiTargetList;
         // nullまたは長さが0
         if(targetList == null || targetList.length == 0){
              return null;
      
         }else{
              // 昇順にソート
              var sorted_list = targetList.map(function(item) {
                                             return new Date(item.settingdate).getTime();
                                          }).sort(); 
              // 最新のオブジェクトを取得
              var latestTarget = sorted_list[sorted_list.length-1];
              return latestTarget;
          }
   }
   // 演算した結果を返す
   public getEnzanResult(enzan:string , caluculateValue:int , inputValue:int):int{
       // 演算判定
       if(enzan != null && enzan != ""){
           if(enzan == "plus"){
               caluculateValue = caluculateValue + inputValue;
           }else if (enzan == "minus"){
               caluculateValue = caluculateValue - inputValue;
           }else if (enzan == "multiply"){
               caluculateValue = caluculateValue * inputValue;
           }else{
               caluculateValue = caluculateValue / inputValue;
           }
           
       }
       return caluculateValue;
   }
   
   // 現在のKpiProgressを取得
   public getPresentKpiProgress(kpiId : string):int{
      // セッションストレージからKPIのオブジェクトを取得
      var kpiObject = newTreeService.getKpiObjectFromId(kpiId);
      // 計算式がある。
      if(kpiObject.calculateList.length > 0){
          var tempEnzan = "";
          var tempValue = 0;
          var caluculateValue = 0;
          // 計算式のリストでループ
          for(let calculateObj of kpiObject.calculateList){
              
              // 直接入力
              if(calculateObj.inputMethod = "text"){
                  tempValue = parseInt(calculateObj.inputText);
              }else{
                  // 計算式のもととなるKPIの取得
                  var tempKpiObject = newTreeService.getKpiObjectFromId(calculateObj.kpiId);
                  // 該当KPIの目標オブジェクト
                  var tempKpiProgress= this.getPresentKpiProgress(kpiObject);
                  tempValue = parseInt(tempKpiProgress.progress);
              }
              // 演算による計算
              caluculateValue = this.getEnzanResult(tempEnzan , caluculateValue , tempValue);
              // 演算更新
              tempEnzan = calculateObj.enzan;
          }
          reutrn caluculateValue;
      }else{
          // 進捗リストから最新の日付のものを取得する
          var tempKpiProgress= this.getRecentProgress(kpiObject);
          return tempKpiProgress.progress;
      }
   }
   
   //設定の更新内容をセッションに保存
   public updateSettingtoSession(kpiId : string , kpitarget: int , kpitargetdate: Date, kpiprogress: int){
       // KPI目標の更新
       let kpiobject:KpiObject;
   
       // KPI進捗の更新
   
   }
   
}
