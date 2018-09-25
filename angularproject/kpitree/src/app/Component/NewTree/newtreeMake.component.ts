import { Component , OnInit, EventEmitter, Output} from '@angular/core';
import { NewTreeService }  from '../Common/Service/newtree.service';
import * as shape from 'd3-shape';
import { KpiObject } from '../Common/kpiobject';
import { MatDialog} from '@angular/material';
import { AlertDialogComponent } from '../Common/alert-dialog.component';
import { CalculateDialogComponent } from '../Common/calculate-dialog.component';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';

@Component({
  selector: 'newTreeMake',
  providers:[
     {provide:NewTreeService ,useClass:NewTreeService}
  ],
  template: `<br/><br/>
    <span class="title">■【新規ツリー作成】ツリー作成</span><br/>
  <table><tr valign ="top">
            <td width="150">タイトル：{{input.title}}</td>
            <td width="50"> <input type ="button" (click)="onTitleEdit($event)" value ="タイトル編集へ戻る" /></td>
            <td width="50"> <input type ="button" (click)="commitTree($event)" value ="KPIツリー保存" /></td>
         </tr>
         <tr>
            <td colspan = "3">※保存時ご利用のブラウザに保存されます。<br/>
               {{storeMessage}}
           
            </td>
         </tr>
  </table>
  <br/>
  <form #treeForm="ngForm" (ngSubmit)="treeRegister()">
    <table>
     <tr>
       <td  width="100">
          KPI名
       </td>
       <td  width="250" align="center">
          <input type="text" size="25" id="kpinameinput" name="kpinameinput"  (ngModelChange)="onKpiNameChange($event)"  [(ngModel)]="input.kpiname" #kpinameinput=ngModel required/>
       </td>

     </tr>
     <tr>
       <td colspan="2" align="center">
          <span *ngIf="kpinameinput.errors?.required" class="error">KPI名を入力してください</span>
       </td>
     </tr>
  </table>
  <span *ngIf="showUnit">
    <table>
     <tr>
       <td width="150">
          単位（円、個、回数など）
       </td>
       <td  width="250" align="center">
          <input type="text" size="25" id="kpiunitinput" name="kpiunitinput"  (ngModelChange)="onKpiUnitChange($event)" [(ngModel)]="input.kpiunit" #kpiunitinput=ngModel required/>
       </td>

     </tr>
     <tr>
       <td colspan="2" align="center">
          <span *ngIf="kpiunitinput.errors?.required" class="error">KPI単位を入力してください</span>
       </td>
     </tr>
  </table>
  </span>
  
  <span *ngIf="showParentLink">
    <table>
     <tr>
       <td width="150">
          親KPIの選択
       </td>
       <td  width="250" align="center">
          <select name="parentKpiList" [(ngModel)]="parentselected" (change)="onParentComboChange()">
             <option *ngFor="let item of parentComboList" [value]="item.value" [disabled]="item.disabled" [selected]="item.value === selected">
                 {{item.label}}
             </option>
          </select>
       </td>

     </tr>
     <tr>
       <td width="150">
          親との関連内容
       </td>
       <td  width="250" align="center">
          <input type="text" size="25" id="kpiparentlinktextInput" name="kpiparentlinktextInput"  [(ngModel)]="input.kpiparentlinktext"/>
       </td>
     </tr>
    </table>
  </span>
  <span *ngIf="showSort">
     <table>
       <tr>
       <td width="150">
          順番の指定
       </td>
       <td  width="80" align="center">
          <select name="sortKpiList" [(ngModel)]="sortselected" (change)="onSortListBoxChange()">
             <option *ngFor="let item of sortSettingList" [value]="item.value" [disabled]="item.disabled" [selected]="item.value === selected">
                 {{item.label}}
             </option>
          </select>
       </td>
       <td  width="80" align="center">
          <select name="sorBfAfComboList" [(ngModel)]="selectedSortBfAf" >
             <option *ngFor="let item of sorBfAfComboList" [value]="item.value" [disabled]="item.disabled" [selected]="item.value === selected">
                 {{item.label}}
             </option>
          </select>
       </td>
     </tr>
    </table>
  </span>
  <span *ngIf="showRegister">
     <table>
       <tr>
         <td width="100">
           詳細
         </td>
         <td  width="250" align="center">
           <textarea rows="3" cols="20" id="kpidetailinput" name="kpidetailinput" [(ngModel)]="input.kpidetail"></textarea>
         </td>
       </tr>
     </table>
     <span *ngIf="calculateDisplay">
         <table cellspacing="15">
             <tr>
                 <td colspan="2">現在の計算式設定：{{calculateSetting}}</td>
             </tr>
             <tr>
                 <td><input type ="button" (click)="onCalculateSet($event)" value ="計算式を設定する" /></td>
                 <td><input type ="button" (click)="onCalculateClear($event)" value ="計算式をクリア" /></td>
             </tr>
         </table>
     </span>
     <span *ngIf="registerNew">
         <input type="submit" value="KPI新規追加" [disabled]="treeForm.invalid"/>
     </span>
     <span *ngIf="registerNew == false">
         <table cellspacing="15">
           <tr>
              <td>
                 <input type="submit" value="KPI変更" [disabled]="treeForm.invalid"/>
              </td>
              <td>
                 <input type ="button" (click)="onDeleteKpi($event)" value ="KPI削除" />
              </td>
              <td>
                 <input type ="button" (click)="onChangeNewRegister($event)" value ="新規追加に切替" />
              </td>
           </tr>
           <tr>
              <td colspan="3">{{opereationMessage}}</td>
           </tr>
         </table>
     </span>
  </span>
  </form>
  <span *ngIf="showTree">
   ※KPIをクリックすると内容が修正できます。
     <hierarchialGraph (nodeClick) = "onNodeClick($event)" [hierarchialGraph] = "hierarchialGraph"></hierarchialGraph>
  </span>
  `,
  styleUrls: ['./CSS/newtree.component.css']
  
})


export class NewTreeMakeComponent  {

  // グラフのオブジェクト
  hierarchialGraph: { links: any[], nodes: any[] };
  
  // KPI単位の表示フラグ
  showUnit = false;
  // KPI単位の登録表示フラグ
  showRegister = false;
  // 親ID欄のリンク入力
  showParentLink = false;
  // ソート順の設定欄
  showSort = false;
  // ツリーの表示フラグ
  showTree = false;
  // 新規登録ボタンの表示フラグ
  registerNew = true;
  // 計算式設定の表示フラグ
  calculateDisplay = false;
  // アラートの押されたボタンの結果格納用
  alertDialogResult = '';
  
  // 計算式の設定内容
  calculateSetting = "なし";
  
  // 操作後のメッセージ
  opereationMessage = "";
  
  // 保存時メッセージ
  storeMessage = "";
  
  // 入力内容を入れるオブジェクト
  input = {
     title: '',
     kpiname: '',
     kpiunit: '',
     kpidetail:'',
     kpiparentlinktext:'',
  };
  
  // 親KPI選択用のコンボ配列
  parentComboList: any[];
  // 選択された親KPIのID
  parentselected = '';
  // ソート設定用のリストボックス配列
  sortSettingList: any[];
  // 選択されたソートのKPIのID
  sortselected = '';
  // ソートの前後指定のコンボ配列
  sorBfAfComboList: any[];
  // 選択されたソートの前後指定
  selectedSortBfAf = '';
  
  // グラフからクリックされたKPIオブジェクト
  clickedKpiId:string = null;
  
  // タイトル入力画面への遷移用
  @Output() newTreeTitleScreen = new EventEmitter<string>();
  
  constructor( private newTreeService: NewTreeService
             , public matDialog : MatDialog){ 
  }

  ngOnInit(){
     // KPIツリーのタイトルをセット
     this.input.title = this.newTreeService.getTitle();
     
     // ソートの前後指定コンボの設定
     this.sorBfAfComboList = [{label: '指定したKPIの前', value: 'BF', disabled:false},
                              {label: '指定したKPIの後', value: 'AF', disabled:false}
                             ];
     // 前を初期選択する
     this.selectedSortBfAf = 'BF';
     
     // 登録済みのKPIがある場合
     if( this.newTreeService.isKpiRegistred() ){
        // treeの表示部分を更新
        this.showTree = true;
        // グラフのセット
        this.hierarchialGraph = this.newTreeService.getHierarchialGraph();
     
     }
     
  }
  
  // KPI名が変わったとき
  onKpiNameChange(inputvalue:string){
    // KPI単位の表示判定
    if(inputvalue.length > 0){
       this.showUnit = true;
       // KPI単位も入力あり
       if(this.input.kpiunit.length > 0 ){
          // 登録済みのKPIがある場合
          if( this.newTreeService.isKpiRegistred() ){
             // 親KPI選択用のデータをセット
             this.setParentComboAndListBox();
             
          }else{
             // 親ID欄のリンク入力非表示
             this.showParentLink = false;
             // ソート順の設定欄を非表示
             this.showSort = false;
             // 計算式設定を非表示
             this.calculateDisplay = false;
          }
          // 登録欄を表示
          this.showRegister = true;
          
       }
       
    }else{
       this.showUnit = false;
       this.showRegister = false;
       this.showParentLink = false;
       this.showSort = false;
       this.calculateDisplay = false;
    }
  
  }

  // KPI単位が変わったとき
  onKpiUnitChange(inputvalue:string){
    // KPI単位の表示判定
    if(inputvalue.length > 0 && this.input.kpiname.length > 0){
       this.showRegister = true;
       // 登録ずみのKPIがあるか
       if( this.newTreeService.isKpiRegistred() ){
          // 親KPI選択用のデータをセット
          this.setParentComboAndListBox();
       }else{
          // 親ID欄のリンク入力非表示
          this.showParentLink = false;
          // ソート順の設定欄を非表示
          this.showSort = false;
          // 計算式設定を非表示
          this.calculateDisplay = false;
       }
    }else{
       // 登録欄と親ID欄とソート順の設定欄を非表示
       this.showRegister = false;
       this.showParentLink = false;
       this.showSort = false;
       this.calculateDisplay = false;
    }
  
  }
  
  // タイトル編集ボタン
  onTitleEdit(event:string){
      // タイトル編集画面へ
      this.newTreeTitleScreen.emit('newTreeTitle');
  }
  
  // KPI登録が押されたとき
  treeRegister(){
     // 新規追加の場合
     if(this.registerNew){
          // 登録ずみのKPIがあるか
          if( this.newTreeService.isKpiRegistred() ){
             // セットするソート番号
             let settingSort:string;
             // ソート欄が表示
             if(this.showSort){
                 // 既存のソートを再設定してソートを取得
                 settingSort = this.newTreeService.sortResettingKpiArray(this.sortselected , this.selectedSortBfAf);

             }else{
                 // 初期値の1000
                 settingSort = '1000';
             }
             // parentidとsortをいれて追加。
             this.newTreeService.addKpiObject(this.input.kpiname , this.input.kpiunit , this.input.kpidetail , this.parentselected , this.input.kpiparentlinktext , settingSort);
         
          }else{
             // parentidとparentlinktextとsortをいれないで追加。
             this.newTreeService.addKpiObject(this.input.kpiname , this.input.kpiunit , this.input.kpidetail , null , null , null);
         
          }
          // 入力欄をクリア
          this.clearInputArea();

     //変更の場合
     }else{
         // セットするソート番号
         let settingSort:string;
         // ソート欄が表示
         if(this.showSort){
             // 既存のソートを再設定してソートを取得
             settingSort = this.newTreeService.sortResettingKpiArray(this.sortselected , this.selectedSortBfAf);
         }else{
             // 初期値の1000
             settingSort = '1000';
         }
         this.newTreeService.changeKpiObject(this.clickedKpiId , this.input.kpiname , this.input.kpiunit , this.input.kpidetail , this.parentselected , this.input.kpiparentlinktext , settingSort);
         // 操作メッセージ変更完了
         this.opereationMessage = "変更しました。";
         // 親コンボの再セット
         this.setParentComboAndListBox();
     
     }
     //treeの表示部分を更新
     this.showTree = true;
     // グラフの再セット
     this.hierarchialGraph = this.newTreeService.getHierarchialGraph();
     
  }
  
  // 新規登録の切り替えボタン
  onChangeNewRegister(event:string){
      // 入力欄をクリアと選択したKPI・IDをNULLに。
      this.clearInputArea();
  }
  
  // 計算式設定ボタンが押されたとき
  onCalculateSet(event:string){
      // this.caluculateSetDisplay = true;
      // ダイアログの表示
      let dialog = this.matDialog.open(CalculateDialogComponent, {
                  'data' : {'title': '計算式設定' , 'targetKpiId':this.clickedKpiId},
                  'height' : '100%',
                  'width' : '500px',
                  'disableClose' : false
      });
      
      // ボタンの結果を取得
      dialog.afterClosed().subscribe( (result:any) => {
            // 登録ボタンが押された場合のOKチェック
            if(result == "OK"){
                 // 計算式設定を再表示
                 this.displayCalculateSetting();
                 
            }
      });
      
  }
  
  // 計算式クリアボタンが押されたとき
  onCalculateClear(event:string){
      // ダイアログの表示
      let dialog = this.matDialog.open(AlertDialogComponent, {
                  'data' : {'title': 'クリア確認' , 'message' : '計算式の設定をクリアしても大丈夫ですか？'},
                  'height' : '300px',
                  'width' : '500px',
                  'disableClose' : false
      });
      
      // ボタンの結果を取得
      dialog.afterClosed().subscribe( (result:any) => {
            // 結果をセット
            this.alertDialogResult = result;
            // OKボタンが押された場合
            if(this.alertDialogResult == 'OK'){
                 // 計算式設定をクリア
                 this.newTreeService.clearCalculateList(this.clickedKpiId);
                 // 計算式設定を再表示
                 this.displayCalculateSetting();
            }
      });
  }
  
  
  // 削除ボタンが押されたとき
  onDeleteKpi(event:string){
      // ダイアログの表示
      let dialog = this.matDialog.open(AlertDialogComponent, {
                  'data' : {'title': '削除確認' , 'message' : 'KPIを削除しますか？該当のKPIの配下にあるKPIも全て削除されます。'},
                  //  'data' : {'title': '削除確認' , 'message' : '削除しても大丈夫ですか？'},
                  'height' : '300px',
                  'width' : '500px',
                  'disableClose' : false
      });
      
      // ボタンの結果を取得
      dialog.afterClosed().subscribe( (result:any) => {
            // 結果をセット
            this.alertDialogResult = result;
            // OKボタンが押された場合
            if(this.alertDialogResult == 'OK'){
                // KPIの配列から選択されたものを削除
                this.newTreeService.deleteKpi(this.clickedKpiId , true);
                // グラフの再セット
                this.hierarchialGraph = this.newTreeService.getHierarchialGraph();
                // 入力欄をクリアと選択したKPI・IDをNULLに。
                this.clearInputArea();
                // ダイアログログ結果をクリア
                this.alertDialogResult = '';
           }
      });
      
  }
  
  // 親選択コンボの値が変わったとき
  onParentComboChange(){
     // ソートのリストボックスをセット
     this.setSortListBox();
     
  }
  
  // ソート選択リストボックスの選択が変わったとき
  onSortListBoxChange(){
     // とりあえず何もしない
  }
  
  // グラフのノードがクリックされたとき
  onNodeClick(node:any) {
     // 入力欄をクリア
     this.clearInputArea();
     // 選択したIDを格納
     this.clickedKpiId = node.id;
     // クリックされたnodeのIDでKPIのオブジェクトを取得
     let selecedKpiObject:KpiObject = this.newTreeService.getKpiObjectFromId(node.id);
     // 各種入力欄を表示
     this.showUnit = true;
     this.showRegister = true;
     this.setParentComboAndListBox();
     
     // 各入力欄の値をセット
     this.input.kpiname = selecedKpiObject.kpiname;
     this.input.kpiunit = selecedKpiObject.kpiunit;
     this.input.kpidetail = selecedKpiObject.kpidetail;
     this.input.kpiparentlinktext = selecedKpiObject.parentlinktext;
     
     // 親のコンボがある場合
     if(this.parentComboList != null && this.parentComboList.length != 0){
         // 現状設定されている親IDが空の場合
         if(selecedKpiObject.parentid == null || selecedKpiObject.parentid == ''){
             // コンボの0番目を初期選択する
             this.parentselected = this.parentComboList[0].value;
         }else{
             // KPIに設定されている親IDをセット
             this.parentselected = selecedKpiObject.parentid;
         }
         // 再度ソート入力欄を設定
         this.setSortListBox();
     }

     // 新規登録フラグをfalse
     this.registerNew = false;
     
     // テスト用のラベル表示
     // this.selectedNodeTestLabel = node.label;
  }
  
  // 親KPI設定用のコンボとソートのリストボックスデータをセット
  setParentComboAndListBox(){
     // 親KPIの選択対象となるKPI配列の取得
     this.parentComboList =  this.newTreeService.getKpiListForCombo(this.clickedKpiId);
     // 配列のlengthが0
     if(this.parentComboList.length == 0){
          // 親ID入力欄を非表示
          this.showParentLink = false;
          // 計算式設定を非表示
          this.calculateDisplay = false;
     }else{
          // 親KPIの配列のうち0番目を初期選択する
          this.parentselected = this.parentComboList[0].value;
          // 親ID欄のリンク入力表示
          this.showParentLink = true;
          // 計算式設定の表示
          this.displayCalculateSetting();
          // ソートのリストボックスをセット
          this.setSortListBox();
     }
  }
  
  // ソートのリストボックスのデータをセット
  setSortListBox(){
     // 指定した親の直下の子供でソート欄のリストボックスを設定する。
     this.sortSettingList = this.newTreeService.getSortSettingList(this.clickedKpiId , this.parentselected);
     // 配列のlengthが0でない
     if(this.sortSettingList.length != 0){
         // リストボックス配列のうち0番目を初期選択する
         this.sortselected = this.sortSettingList[0].value;
         // ソート順の設定欄を表示
         this.showSort = true;
     }else{
         // ソート順の設定欄を非表示
         this.showSort = false;
     }
  }
  
  // 入力欄をクリア
  clearInputArea(){
      // 選んだKPIのIDをnull
      this.clickedKpiId = null;
      // 入力データをクリア
      this.input.kpiname = '';
      this.input.kpiunit = '';
      this.input.kpidetail = '';
      this.input.kpiparentlinktext = '';
      // KPI名入力欄以外は非表示
      this.showUnit = false;
      this.showRegister = false;
      this.showParentLink = false;
      this.showSort = false;
      this.calculateDisplay = false;
      // 新規登録ボタンを表示
      this.registerNew = true;
      // 操作メッセージを空
      this.opereationMessage = "";
      // 計算式設定をクリア
      this.newTreeService.clearCalculateList(this.clickedKpiId);
      // 保存メッセージを空
      this.storeMessage = "";
  
  }
  
  // 計算式設定の表示
  displayCalculateSetting(){
      // 計算式設定の表示判定
      let childKpiList = this.newTreeService.getChildKpiList(this.clickedKpiId);
      // 子のKPIがいる場合
      if(childKpiList != null && childKpiList.length != 0){
           // セッションから計算式内容を取得
           let calculateSettingFromSession = this.newTreeService.getCalculateString(this.clickedKpiId);
           // nullまたは空文字
           if(calculateSettingFromSession == null || calculateSettingFromSession == ""){
               // なしを設定
               this.calculateSetting = "なし";
           }else{
               this.calculateSetting = calculateSettingFromSession;
           }
           // 計算式を表示
           this.calculateDisplay = true;
           
      }else{
           // 登録されている計算式をクリア
           this.newTreeService.clearCalculateList(this.clickedKpiId);
           // 計算式を非表示
           this.calculateDisplay = false;
      }

  }
  
  // KPI保存のボタンを押下
  commitTree(event:string){
     // KPIをローカルストレージへ保存
     this.newTreeService.storeLocalStrageKpiTree();
     // 保存メッセージ保存完了
     this.storeMessage = "KPIツリーををお使いのブラウザへ保存しました。";
  }

}
