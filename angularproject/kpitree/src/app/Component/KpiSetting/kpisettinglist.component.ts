import { Component  , OnInit, EventEmitter, Output} from '@angular/core';
import { ChangeTreeService }  from '../Common/Service/changetree.service';
import { NewTreeService }  from '../Common/Service/newtree.service';

@Component({
  selector: 'kpiSettingList',
  template: `<br/><br/>
    <span class="title">■【KPI値設定・進捗確認】登録ツリー一覧</span><br/><br/>
    <span *ngIf="nonTreeFlg">
        ツリーの登録がありません。
    </span>
    <span *ngIf="displayTreeFlg">
        <table border = "1">
            <tr bgcolor ="aqua">
                <td>
                     KPIツリー名
                </td>
                <td>
                     状況
                </td>
                <td>
                     進捗更新日
                </td>
                <td>
                     詳細確認・設定
                </td>
            </tr>
            <tr *ngFor="let item of treeList">
                <td>
                     {{item.title}}
                </td>
                <td>
                     状況
                </td>
                <td>
                     進捗更新日
                </td>
                
                <td>
                     <input type="button" value="詳細確認・設定" (click)="onclickLink(item)">
                </td>
        </table>
    </span>
  `,
  styleUrls: ['./CSS/kpisetting.component.css']
})
export class KpiSettingListComponent  {
   // ツリー一覧用の配列
   treeList: any[];
   // 一覧無し表示
   nonTreeFlg = false;
   // 一覧表示
   displayTreeFlg = false;
   
   // ツリーの詳細設定画面への遷移用
   @Output() kpiSettingScreen = new EventEmitter<string>();
   
   // ChangeTreeServiceをinject
   constructor( private changeTreeService: ChangeTreeService , private newTreeService: NewTreeService){}

   ngOnInit(){
      // セッションを削除
      this.newTreeService.clearKpiTreeFromSession();
      // ツリーの一覧を表示
      this.showTreeList();
   }
   
   // KPIツリーのリンククリック
   onclickLink(item:any){
     // ローカルストレージのツリーをセッションにうつす。
     copyKpiTreeLocalStrageToSession(item.id);
     // 次画面（値設定詳細画面）のパラメータ引き渡し
     this.kpiSettingScreen.emit(item.id);
   
   }
   
   // ツリーの一覧を表示
   showTreeList(){
      // ローカルストレージからツリーの一覧を取得
      this.treeList = this.changeTreeService.getKpiTreeList();
      // 一個もない場合
      if(this.treeList.length == 0){
         this.nonTreeFlg = true;
         this.displayTreeFlg = false;
      }else{
         this.nonTreeFlg = false;
         this.displayTreeFlg = true;
      }
   }
   
   
}
