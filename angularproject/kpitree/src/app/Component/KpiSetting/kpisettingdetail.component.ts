import { Component , OnInit, EventEmitter, Output} from '@angular/core';
import { NewTreeService }  from '../Common/Service/newtree.service';
import * as shape from 'd3-shape';
import { KpiObject } from '../Common/kpiobject';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { MatDialog} from '@angular/material';
import { KpiDialogComponent } from '../Common/kpi-dialog.component';

@Component({
  selector: 'kpiSettingDetail',
  providers:[
     {provide:NewTreeService ,useClass:NewTreeService}
  ],
  template: `<br/><br/>
    <span class="title">■【KPI値設定・進捗確認】KPI詳細画面</span><br/>
    <table><tr valign ="top">
            <td width="150">タイトル：{{title}}</td>
            <td width="50"> <input type ="button" (click)="onkpiListScreen($event)" value ="一覧へ戻る" /></td>
            <td width="50"> <input type ="button" (click)="storeLocalStrage($event)" value ="設定内容を保存する" /></td>
            </tr>
            <tr>
            <td colspan = "3">※保存時ご利用のブラウザに保存されます。<br/>
               {{storeMessage}}
           
            </td>
         </tr>
    </table>
    <table>
    <tr>
        <td></td>
        <td>
            ※KPIをクリックすると目標・期限・進捗が設定できます。
            <hierarchialGraph (nodeClick) = "onNodeClick($event)" [hierarchialGraph] = "hierarchialGraph"></hierarchialGraph>
         </td>
     </tr>
     </table>
  `,
  styleUrls: ['./CSS/kpisetting.component.css']
  
})


export class KpiSettingDetailComponent  {

  // グラフのオブジェクト
  hierarchialGraph: { links: any[], nodes: any[] };
  // KPIのタイトル
  title = "";
  
  // 保存時メッセージ
  storeMessage = "";
  
  // 一覧画面への遷移用
  @Output() kpiListScreen = new EventEmitter<string>();
  
  constructor( private newTreeService: NewTreeService , public matDialog : MatDialog){ 
  }

  ngOnInit(){
     // KPIツリーのタイトルをセット
     this.title = this.newTreeService.getTitle();
     // グラフのセット
     this.hierarchialGraph = this.newTreeService.getHierarchialGraph();
     // 状況の表示オブジェクトを設定
     this.displayKpiSetting();
  }
  
  // KPI一覧画面への遷移
  onkpiListScreen(event:string){
      // タイトル編集画面へ
      this.kpiListScreen.emit('kpiSettingList');
  }
  
  // グラフのノードがクリックされたとき
  onNodeClick(node:any) {
     // IDからKPIオブジェクトを取得
     let selecedKpiObject:KpiObject = this.newTreeService.getKpiObjectFromId(node.id);
     // ダイアログの表示
     let dialog = this.matDialog.open(KpiDialogComponent, {
                  'data' : {'title': selecedKpiObject.kpiname + 'KPI設定' , 'targetKpiId':node.id , 'returnMessage':'設定を一時保存し元画面へ戻る' , 'kpiUnit':selecedKpiObject.kpiunit},
                  'height' : '100%',
                  'width' : '500px',
                  'disableClose' : false
     });
     
     // ボタンの結果を取得
     dialog.afterClosed().subscribe( (result:any) => {
            // 登録ボタンが押された場合のOKチェック
            if(result == "OK"){
                 // 状況の再表示
                 this.displayKpiSetting();
                 // 保存メッセージを空
                 this.storeMessage = "";
            }
      });
  }
  
  // KPIの状況を表示
  displayKpiSetting(){
  
  }
 
  // ローカルストレージに設定内容を保存。
  storeLocalStrage(event:string){
      // KPIをローカルストレージへ保存
      this.newTreeService.storeLocalStrageKpiTree();
      // 保存メッセージをセット
      this.storeMessage = "KPIツリーををお使いのブラウザへ保存しました。";
  }

}
