import { Injectable } from '@angular/core';
import { KpiTree } from '../kpitree';
import { KpiObject } from '../kpiobject';
import { CaluculateSettingObject } from '../calculateobject';
import { EnzanConstList } from '../calculateobject';

@Injectable()
export class ChangeTreeService{
   
   constructor(){}
   
   // ツリーの一覧を取得
   public getKpiTreeList():KpiTree[]{
      var kpiTreeList: KpiTree[] = [];
      for (let key in localStorage) {
         if (localStorage.hasOwnProperty(key)) {
             let tempTree = JSON.parse(localStorage.getItem(key));
             // idとタイトルだけ取得
             let pushTree: KpiTree =
             {
                id    : tempTree.id,
                title : tempTree.title,
                kpiArray:[],
             };
             kpiTreeList.push(pushTree);
         }
      }
      return kpiTreeList;
   }

  
   // KPIツリーをローカルストレージから削除
   public deleteKpiTree( deleteTreeId:string ){
       // ローカルストレージから該当KPIツリーの情報を削除
       localStorage.removeItem(deleteTreeId);
   }
   
}
