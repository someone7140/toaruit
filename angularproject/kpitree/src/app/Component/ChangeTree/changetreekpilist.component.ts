import { Component , OnInit, EventEmitter, Output} from '@angular/core';
import { ChangeTreeService }  from '../Common/Service/changetree.service';
import { NewTreeService }  from '../Common/Service/newtree.service';
import { MatDialog} from '@angular/material';
import { AlertDialogComponent } from '../Common/alert-dialog.component';

@Component({
  selector: 'chgKpiTreeList',
  providers:[
     {provide:NewTreeService ,useClass:NewTreeService},
     {provide:ChangeTreeService ,useClass:ChangeTreeService},
  ],
  template: `<br/><br/>
    <span class="title">■【ツリー確認・変更】登録ツリー一覧</span><br/><br/>
    <span *ngIf="nonTreeFlg">
        ツリーの登録がありません。
    </span>
    <span *ngIf="displayTreeFlg">
        <table border = "1">
            <tr *ngFor="let item of treeList">
                <td>
                     <a href = "newtree/{{item.id}}" routerLinkActive ="current">{{item.title}}</a>
                </td>
                <td>
                     <input type ="button" (click)="onDeleteKpiTree(item.id)" value ="KPIツリー削除" />
                </td>
            </tr>
        </table>
    </span>
  `,
  styleUrls: ['./CSS/changetree.component.css']
})
export class ChangeTreeKpiListComponent  {
  // ツリー一覧用の配列
  treeList: any[];
  // 一覧無し表示
  nonTreeFlg = false;
  // 一覧表示
  displayTreeFlg = false;
  // アラートの押されたボタンの結果格納用
  alertDialogResult = '';
  
  // ChangeTreeServiceをinject
  constructor( private changeTreeService: ChangeTreeService , public matDialog : MatDialog, private newTreeService: NewTreeService){}

  ngOnInit(){
     // セッションを削除
     this.newTreeService.clearKpiTreeFromSession();
     // ツリーの一覧を表示
     this.showTreeList();
  }
  
  // KPIツリー削除ボタン押下
  onDeleteKpiTree(treeId:string){
      // ダイアログの表示
      let dialog = this.matDialog.open(AlertDialogComponent, {
                  'data' : {'title': '削除確認' , 'message' : 'KPIツリーを削除しますか？'},
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
                 // ローカルストレージからツリーを削除
                 this.changeTreeService.deleteKpiTree(treeId);
                 // ツリーの再表示
                 this.showTreeList();
           }
      });

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
