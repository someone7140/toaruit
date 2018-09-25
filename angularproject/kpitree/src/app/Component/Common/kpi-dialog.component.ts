import { Component, OnInit, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { KpiSettingService }  from './Service/kpisetting.service';
import { KpiTarget }  from './kpitarget';
import { KpiProgress }  from './kpiprogress';

@Component({
  selector:'app-kpi-dialog',
    providers:[
     {provide:KpiSettingService ,useClass:KpiSettingService},
     {provide:NewTreeService ,useClass:NewTreeService}
  ],
  template: `
   <h2 mat-dialog-title>{{data.title}}</h2>
   
   <mat-dialog-actions>
        <button mat-raised-button (click)="onClickReturnButton()">{{data.returnMessage}}</button>
    </mat-dialog-actions>
   <mat-dialog-content>
       <span class="title">■現在の状況</span><br/>
           <table><tr>
               <td>
                   【目標】<br/>
                    <span *ngIf="presentKpiTargetDisplay">
                        ・目標値：{{presentKpiTarget.target}}{{data.kpiUnit}}
                        ・期限：{{presentKpiTargetDate}}
                        <span *ngIf="isHavingCaluculate">
                             ・計算式：{{caluculateString}}
                        </span>
                    </span>
                    <span *ngIf="presentKpiTargetDisplay == false">
                        目標の設定がありません。
                    </span>
               </td>
               <td>
                   【進捗】<br/>
                    <span *ngIf="presentKpiProgressDisplay">
                        ・現在値：{{presentKpiProgress.target}}{{data.kpiUnit}}
                         <span *ngIf="isHavingCaluculate">
                             ・計算式：{{caluculateString}}
                        </span>
                    </span>
                    <span *ngIf="presentKpiProgressDisplay == false">
                        進捗の設定がありません。
                    </span>
               </td>
           </tr></table><br/>
       <span class="title">■設定の更新</span><br/>
           <table><tr>
                  <td>
                   【目標】<br/>
                    <span *ngIf="isHavingCaluculate == false">
                      ・目標値：<input type="number" size="25" id="kpitarget" name="kpitarget"  [(ngModel)]="input.kpitarget"/>{{data.kpiUnit}}
                    </span>
                    . ・期限：<input type="date" size="25" id="kpitargetdate" name="kpitargetdate"  [(ngModel)]="input.kpitargetdate"/>
                  </td>
               <td>
                   <span *ngIf="isHavingCaluculate == false">
                   【進捗】<br/ >
                   ・現在値：<input type="number" size="25" id="kpiprogress" name="kpiprogress"  [(ngModel)]="input.kpiprogress"/>{{data.kpiUnit}}
                    </span>
               </td>
           </tr></table><br/>
       
       <span class="title">■過去の設定更新履歴</span><br/>
        <table border ="1">
           <tr bgcolor="aqua">
              <td>目標値</td><td>期限</td><td>進捗</td><td>設定日</td>
           </tr>
           <tr *ngFor="let updateitem of updatelist">
              
              
           </tr>
        </table>
   </mat-dialog-content>
  `,
  styleUrls: ['./CSS/common.component.css']
})
export class KpiDialogComponent  {
    // 選択されたKPIID
    selectedKpiId = '';

    // 現在時点での目標設定
    presentKpiTarget: KpiTarget;
    // 現在時点での期限
    presentKpiTargetDate = '';
    // 現在時点での目標設定表示
    presentKpiTargetDisplay: boolean = false;
    // 現在時点での進捗設定
    presentKpiProgress: KpiProgress;
    // 現在時点での進捗設定表示
    presentKpiProgressDisplay: boolean = false;
    // 計算式の有無
    isHavingCaluculate: boolean = false;
    // 計算式の文字列
    caluculateString = '';
    // 過去の設定更新リスト
    updatelist: any[];
    // 入力内容を入れるオブジェクト
    input = {
      kpitarget: 0,
      kpitargetdate: new Date(),
      kpiprogress: 0
    };
    
    constructor(
        @Inject(MAT_DIALOG_DATA) public data : any,
        private kpiSettingService: KpiSettingService,
        private newTreeService: NewTreeService,
        public matDialogRef : MatDialogRef<KpiDialogComponent>) { 
        // 選択されたKPIのID
        this.selectedKpiId = data.targetKpiId;
        // 現在時点の状況表示
        this.presentDisplay(this.selectedKpiId);
        // 過去の履歴表示
        this.pastSetingDisplay(this.selectedKpiId);
    
    // 元の画面に戻るボタン
    onClickReturnButton():void {
        // セッションに更新内容を保存する
        kpiSettingService.updateSettingtoSession(this.selectedKpiId , this.input.kpitarget , this.input.kpitargetdate , this.input.kpiprogress);
        // 「OK」を呼び出し元に渡す。
        this.matDialogRef.close('OK');
    }
    
    // 現在の状況の表示
    presentDisplay(kpiId:string):void{
        // 計算式の有無
        this.isHavingCaluculate = kpiSettingService.isHavingCaluculate(kpiId);
        // 計算式がある場合は文字列をセット
        if(this.isHavingCaluculate){
            // KPIのオブジェクトを取得
            var kpiObj = newTreeService.getKpiObjectFromId(kpiId);
            this.caluculateString = newTreeService.makeCalculateString(kpiObj.calculateList);
        }
        // 現在時点での目標設定取得
        this.presentKpiTarget.target = kpiSettingService.getPresentKpiTarget(kpiId);
        // null判定
        if(presentKpiTarget.target != null){
            // 表示フラグをtrue
            this.presentKpiTargetDisplay = true;
            // 期限を取得
            this.presentKpiTargetDate = kpiSettingService.getPresentKpiLimitDate(kpiId);
        }
        
        // 現在時点での進捗設定取得
        this.presentKpiProgress.target = kpiSettingService.getPresentKpiProgress(kpiId);

    }

}
