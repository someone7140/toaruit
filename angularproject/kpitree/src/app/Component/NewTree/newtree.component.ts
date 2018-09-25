import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { NewTreeService }  from '../Common/Service/newtree.service';

@Component({
  providers:[
     {provide:NewTreeService ,useClass:NewTreeService}
  ],
  template: `
     <div [ngSwitch]="displayComponent">
        <span *ngSwitchCase="'newTreeTitle'"><newTitle (newTreeNextScreen)="onnewTreeNextScreen($event)"></newTitle></span>
        <span *ngSwitchCase="'newTreeMake'"><newTreeMake  (newTreeTitleScreen)="onnewTreeTitleScreen($event)"></newTreeMake></span>
        <span *ngSwitchDefault><newTitle  (newTreeNextScreen)="onnewTreeNextScreen($event)"></newTitle></span>
     </div>
  `
})
export class NewTreeComponent implements OnInit {
   // 画面に表示するコンポーネント
   displayComponent ='';
   // パラメータで渡されるKPIツリーのID
   paramKpiTreeId = '';
   
   // サービスをインスタンス化
   constructor(private route:ActivatedRoute , private newTreeService: NewTreeService){}
   
   ngOnInit(){
      // KPIツリーのidパラメータを取得
      this.route.params.subscribe(
           params => this.paramKpiTreeId = params["id"]);
      // NULLでない
      if(this.paramKpiTreeId != null && this.paramKpiTreeId != ""){
           // ローカルストレージからセッションにツリーを移す
           this.newTreeService.copyKpiTreeLocalStrageToSession(this.paramKpiTreeId);
      
      }else{
           // セッションを削除
           this.newTreeService.clearKpiTreeFromSession();
      }
   }
   
   // 新規タイトル登録画面からの遷移
   onnewTreeNextScreen(nextscreen : string){
      // コンポーネントの表示を切り替え
      this.displayComponent = nextscreen;
   }
   
   // ツリー作成画面からのタイトル画面遷移
   onnewTreeTitleScreen(nextscreen : string){
      // コンポーネントの表示を切り替え
      this.displayComponent = nextscreen;
   }

}
