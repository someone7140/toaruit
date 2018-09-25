export class CaluculateSettingObject{
   // 計算式の形式（text：直接入力、combo：KPI選択）
   inputMethod:string;
   // 直接入力の内容
   inputText:string;
   // KPI選択
   kpiId:string;
   // 前の入力との演算
   enzan:string;

}


export const EnzanConstList = [ {value:'plus', label:'＋'} ,
                                {value:'minus', label:'－'} ,
                                {value:'multiply', label:'×'} ,
                                {value:'divide', label:'÷'}
];