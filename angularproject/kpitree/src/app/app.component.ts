import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
     <div id="menuBox">
        <span id="title">KPI-Tree</span>
        <br/><br/><br/>
        <ul class="link">
           <li><a routerLink="/" routerLinkActive="current">Top</a></li>
           <li><a routerLink="/newtree" routerLinkActive="current">新規作成</a></li>
           <li><a routerLink="/changetree" routerLinkActive="current">確認・変更</a></li>
           <li><a routerLink="/kpisetting" routerLinkActive="current">KPI値設定・進捗確認</a></li>
        </ul>
     </div>
     <div id="contentsBox">
        <router-outlet></router-outlet>
     </div>
  
  
  `,
  styleUrls: ['./CSS/app.component.css']
})
export class AppComponent  { name = 'Angular'; }
