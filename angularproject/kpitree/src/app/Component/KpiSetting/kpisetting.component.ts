import { Component } from '@angular/core';
import { NewTreeService }  from '../Common/Service/newtree.service';

@Component({
  template: `
     <div [ngSwitch]="displayComponent">
        <span *ngSwitchCase="'kpiSettingList'"><kpiSettingList (kpiSettingScreen)="onkpiSetingScreen($event)"></kpiSettingList></span>
        <span *ngSwitchCase="'kpiSettingDetail'"><kpiSettingDetail (kpiListScreen)="onkpiListScreen($event)"></kpiSettingDetail></span>
        <span *ngSwitchDefault><kpiSettingList  (kpiSettingScreen)="onkpiSetingScreen($event)"></kpiSettingList></span>
     </div>
  `
})
export class KpiSettingComponent  {
   // 画面に表示するコンポーネント
   displayComponent ='';
   
   // サービスをインスタンス化
   constructor(private newTreeService: NewTreeService){}
   
   // 登録ツリー一覧画面からの遷移
   onkpiSetingScreen(selectKpiId : string){
      // KPIツリー・IDをセッションへ
      this.newTreeService.copyKpiTreeLocalStrageToSession(selectKpiId);
      // コンポーネントの表示を切り替え
      this.displayComponent = 'kpiSettingDetail';
   }
   
   // KPI値詳細画面から一覧への遷移
   onkpiListScreen(nextscreen : string){
      // コンポーネントの表示を切り替え
      this.displayComponent = nextscreen;
   }
}
