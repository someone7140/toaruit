import { Component, OnInit, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { NewTreeService }  from './Service/newtree.service';
import { KpiObject } from './kpiobject';
import { EnzanConstList } from './calculateobject';
import { CaluculateSettingObject } from './calculateobject';

@Component({
  selector:'app-calculate-dialog',
  providers:[
     {provide:NewTreeService ,useClass:NewTreeService}
  ],
  template: `
   <h2 mat-dialog-title>{{data.title}}</h2>
        <mat-dialog-content>
            <form [formGroup]="calculateFormGroup">
                  <div formArrayName="calculatelist">
                        <div *ngFor="let calculate of calculatelist.controls; let i=index" [formGroupName]="i">
                             <table>
                               <span *ngIf="calculate.get('enzan').value != ''">
                                   <tr><td>
                                        演算選択:
                                        <select formControlName="enzan">
                                            <option *ngFor="let enzan of enzanList" [value]="enzan.value">{{enzan.label}}</option>
                                        </select>
                                   </td></tr>
                               </span>
                             
                               <tr>
                                  <td>
                                     <span *ngIf="calculate.get(calculate.get('radioName').value).value == 'combo'">
                                        対象KPI選択:
                                        <select formControlName="kpiSelect">
                                            <option *ngFor="let kpiObject of comboList" [value]="kpiObject.id">{{kpiObject.kpiname}}</option>
                                        </select>
                                     </span>
                                     <span *ngIf="calculate.get(calculate.get('radioName').value).value == 'text'">
                                        直接数値入力:<input type="number" formControlName="inputText" />
                                     </span>

                                  </td>
                                     <input type="radio" name="{{calculate.get('radioName').value}}" value="combo" formControlName="{{calculate.get('radioName').value}}" /> 対象KPI選択<br/>
                                     <input type="radio" name="{{calculate.get('radioName').value}}" value="text" formControlName="{{calculate.get('radioName').value}}" /> 直接数値入力
                               </tr>

                            </table>
                        </div>
                  </div>
                  <table cellspacing="15">
                       <tr>
                         <td>
                             <input type ="button"  (click)="addCalculate()" value="計算式を追加" />
                         </td>
                         <td>
                             <span *ngIf="deleteActive">
                                  <input type ="button"  (click)="deleteCalculate()" value="計算式を削除" />
                             </span>
                             <span *ngIf="deleteActive == false">
                                  <input type ="button"  (click)="deleteCalculate()" value="計算式を削除"  disabled/>
                             </span>
                         </td>
                       </tr>
                  </table>
             </form>
        </mat-dialog-content>
    <mat-dialog-actions>
        <table cellspacing="15">
           <tr>
             <td>
                 <button mat-raised-button (click)="onClickOkButton()">OK</button>
             </td>
             <td>
                 <button mat-raised-button mat-dialog-close="cancel">キャンセル</button>
             </td>
           </tr>
           <tr>
             <td colspan="2">
                 <span *ngIf="checkNgFlg"  class="error">
                     入力項目に空欄があります。
                 </span>
             </td>
           </tr>
        </table>

    </mat-dialog-actions>
  `,
  styleUrls: ['./CSS/common.component.css']
})
export class CalculateDialogComponent  { 
    // 計算式のフォームグループ
    calculateFormGroup: FormGroup;
    // 計算式で選択するコンボボックスのリスト
    comboList: any[];
    // 演算で使用するコンボボックスのリスト
    enzanList: any[];
    // OKボタン押下時のチェックNG
    checkNgFlg: boolean = false;
    // 削除ボタンの有効か
    deleteActive: boolean = false;
    constructor(
        private newTreeService: NewTreeService,
        @Inject(MAT_DIALOG_DATA) public data : any,
        public matDialogRef : MatDialogRef<CalculateDialogComponent>,
        private fb: FormBuilder) {
             // 計算式のフォームグループ初期化
             this.calculateFormGroup = this.fb.group({
                 calculatelist: this.fb.array([]),
             });
             // serviceから子の一覧を取得
             this.comboList = this.newTreeService.getChildKpiList(data.targetKpiId);
             // 演算のコンボ設定
             this.enzanList = EnzanConstList;
             // tempからKPIオブジェクトを取得
             let tempKpiObj = this.newTreeService.getTempCalculateObj(data.targetKpiId);
             // KPIのIDを指定して計算式のリストを取得
             let calculatelistFromSession = this.newTreeService.getKpiObjectFromId(data.targetKpiId).calculateList;
             
             // tempKPIのnull判定
             if(tempKpiObj != null && tempKpiObj.calculateList != null &&  tempKpiObj.calculateList.length >=1){
                 this.setDisplayCalculate( tempKpiObj.calculateList);
             }
             // セッションのKPIがnullでないかつlengthが0でない
             else if(calculatelistFromSession != null && calculatelistFromSession.length != 0){
                 this.setDisplayCalculate(calculatelistFromSession);
                 
             }else{
                 //初期の入力欄を追加（1個目）
                 let radioKey1 = 'radio' + this.calculatelist.length;
                 this.calculatelist.push(this.fb.group({radioName:radioKey1 , [radioKey1]: 'combo' ,inputText: '' , kpiSelect: this.comboList[0].id , enzan:'' }));
                 //初期の入力欄を追加（2個目）
                 let radioKey2 = 'radio' + this.calculatelist.length;
                 this.calculatelist.push(this.fb.group({radioName:radioKey2 , [radioKey2]: 'combo' ,inputText: '' , kpiSelect: this.comboList[0].id , enzan:'plus'}));
             }

    }
    
    // 計算式のリストから表示をセット
    setDisplayCalculate(calculateListInput:any[]){
           // リストでループ
           for(let calculateObj of calculateListInput){
                // ラジオボタンのキーを作成
                let radioKey = 'radio' + this.calculatelist.length;
                // formのリストに追加
                this.calculatelist.push(this.fb.group({radioName:radioKey , [radioKey]: calculateObj.inputMethod ,inputText: calculateObj.inputText , 
                                                       kpiSelect: calculateObj.kpiId , enzan:calculateObj.enzan }));
           }
    
    }
    

    // 計算式追加ボタンが押されたとき
    addCalculate() {
       // 入力欄追加
       let radioKey = 'radio' + this.calculatelist.length;
       this.calculatelist.push(this.fb.group({radioName:radioKey, [radioKey]: 'combo' , inputText: '',kpiSelect: this.comboList[0].id, enzan:'plus'}));
       // 削除ボタンの有効化判定
       this.setDeleteActive();
    }
  
    // 計算式削除ボタンが押されたとき
    deleteCalculate() {
       // 配列の長さ確認
       if(this.calculatelist.length > 2){
            // 末尾を削除
            this.calculatelist.removeAt(this.calculatelist.length - 1);
       }
       // 削除ボタンの有効化判定
       this.setDeleteActive();
    }
    
    // 計算式リストの取得用
    get calculatelist(): FormArray {
        return this.calculateFormGroup.get('calculatelist') as FormArray;
    };
    
    // 削除ボタンの有効化設定
    setDeleteActive():void{
       // 配列の長さ確認
       if(this.calculatelist.length > 2){
           // 有効化
           this.deleteActive = true;
       }else{
           // 無効化
           this.deleteActive = false;
       }
    
    }
    onClickOkButton():void {
        // 計算式のオブジェクトリスト
        let calculateObjList: any[] = [];
        // チェックOKか
        let checkOkFlg = true;
        // 入力内容チェックしてリスト格納
        for(let calculateObj of  this.calculatelist.controls){
           // 計算式オブジェクト
           let caluculateSettingObject:CaluculateSettingObject =
              {
                inputMethod    : "",
                inputText : "",
                kpiId:"",
                enzan:"",
              };
           // 入力方法を取得
           let inputMethod = calculateObj.get(calculateObj.get('radioName').value).value;
           caluculateSettingObject.inputMethod = inputMethod;
           // コンボ選択
           if(inputMethod == 'combo'){
               // コンボ選択があるか
               if(calculateObj.get('kpiSelect').value != null && calculateObj.get('kpiSelect').value != ""){
                   caluculateSettingObject.kpiId = calculateObj.get('kpiSelect').value;
               }else{
                   // それ以外はチェックNG
                   checkOkFlg = false;
                   break;
               }
           // テキスト選択
           }else if(inputMethod == 'text'){
               // テキスト入力があるか
               if(calculateObj.get('inputText').value != null && calculateObj.get('inputText').value != ""){
                   caluculateSettingObject.inputText = calculateObj.get('inputText').value;
               }else{
                   // それ以外はチェックNG
                   checkOkFlg = false;
                   break;
               }
           // それ以外はチェックNG
           }else{
               checkOkFlg = false;
               break;
           }
           
           // 演算チェック
           if(calculateObj.get('enzan').value != null && calculateObj.get('enzan').value != ""){
               caluculateSettingObject.enzan = calculateObj.get('enzan').value;
           // 最初のブロック
           }else if(calculateObj.get('radioName').value == "radio0"){
               // 演算は空白
               caluculateSettingObject.enzan = "";
           // それ以外はチェックNG
           }else{
               checkOkFlg = false;
               break;
           }
           
           // リストにオブジェクトを追加
           calculateObjList.push(caluculateSettingObject);

        }
        // チェックOK
        if(checkOkFlg){
            // サービスのセッションに計算式を保存
            this.newTreeService.setCalculateList(calculateObjList , this.data.targetKpiId);
            // OKを呼び出し元に渡す。
            this.matDialogRef.close('OK');
        }else{
            // エラーメッセージを表示
            this.checkNgFlg = true;
        }

    }

}
