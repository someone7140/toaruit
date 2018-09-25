import { Component , OnInit, EventEmitter, Output} from '@angular/core';
import { NewTreeService }  from '../Common/Service/newtree.service';

@Component({
  selector: 'newTitle',
  providers:[
     {provide:NewTreeService ,useClass:NewTreeService}
  ],
  template: `<br/><br/>
    <span class="title">■【新規ツリー作成】タイトル登録</span><br/>
  <form #titleForm="ngForm" (ngSubmit)="next()">
  <table>
     <tr>
       <td  width="250" align="center">
          <input type="text" size="25" id="titleinput" name="titleinput" [(ngModel)]="input.title" #titleinput=ngModel required/>
       </td>
       <td>
          <input type="submit" value="次へ" [disabled]="titleForm.invalid"/>
       </td>
     </tr>
     <tr>
       <td colspan="2" align="center">
          <span *ngIf="titleinput.errors?.required" class="error">タイトルを入力してください</span>
       </td>
     </tr>
  </table>
  </form>
  `,
  styleUrls: ['./CSS/newtree.component.css']
  
})


export class NewTreeTitleComponent  {
  
  input = {
     title: ''
  };
  
  @Output() newTreeNextScreen = new EventEmitter<string>();
  
  //NewTreeServiceをinject
  constructor( private newTreeService: NewTreeService){}

  ngOnInit(){
     this.input.title = this.newTreeService.getTitle();
  }
  
  next(){
     // 画面の入力内容をサービスにセット
     this.newTreeService.setTitle(this.input.title);
     // 次画面（ツリー作成画面）のパラメータ引き渡し
     this.newTreeNextScreen.emit('newTreeMake');
  }
}
