import { Component } from '@angular/core';

@Component({
  template: `
   <div class="outer">
     <div class="inner">
         KPI-Treeを作成・管理するためのアプリです。
         <ul class ="detail">
            <li>新規作成：KPI-Treeを新規作成します。</li>
            <li>確認・変更：作成したKPI-Treeの確認と変更を行います。</li>
            <li>KPI値設定・進捗確認：作成したKPI-Treeに値を設定し進捗状況の確認を行います。</li>
            <li>ファイル出力・取込：作成したKPI-Treeのファイル出力と他の人が作成したファイルの取り込みを行います。</li>
         </ul>
     </div>
    </div>
     
  `,
  styleUrls: ['./CSS/top.component.css']
})
export class TopComponent  { name = 'Angular'; }
