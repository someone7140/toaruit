export class KpiObject{
   // ID
   id:string;
   // KPI名
   kpiname:string;
   // KPI単位
   kpiunit:string;
   // 詳細
   kpidetail:string;
   // 親KPIのID
   parentid:string;
   // 階層
   layer:string;
   // 親へのリンクテキスト
   parentlinktext:string;
   // ソート
   sort:string;
   // 計算式のリスト
   calculateList:any[];
   // KPI目標のリスト
   kpiTargetList:any[];
   // KPI進捗のリスト
   kpiProgressList:any[];
}