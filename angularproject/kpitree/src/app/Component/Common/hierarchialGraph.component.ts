import { Component, Input , OnInit, EventEmitter, Output} from '@angular/core';
import * as shape from 'd3-shape';

@Component({
  selector:'hierarchialGraph',
  template: `
   <!-- グラフ表示 -->
     <ngx-graph
        #graph
        class="chart-container"
        [view]="view"
        [enableZoom]="enableZoom"
        [autoZoom]="autoZoom"
        [panOnZoom]="panOnZoom"
        [panOffsetX]="panOffsetX"
        [panOffsetY]="panOffsetY"
        [zoomLevel]="zoomLevel"
        [legend]="showLegend"
        [links]="hierarchialGraph.links"
        [nodes]="hierarchialGraph.nodes"
        [scheme]="colorScheme"
        [orientation]="orientation"
        [curve]="curve"
        (select)="selectKpi($event)">
        
           <!-- Linkの矢印の定義 -->
           <ng-template #defsTemplate>
              <svg:marker id="arrow" viewBox="0 -5 10 10" refX="8" refY="0" markerWidth="4" markerHeight="4" orient="auto">
                  <svg:path d="M0,-5L10,0L0,5" class="arrow-head" />
              </svg:marker>
           </ng-template>
           
           <!-- nodeの定義 -->
           <ng-template #nodeTemplate let-node>
              <svg:g class="node"
               ngx-tooltip
               [tooltipPlacement]="'top'"
               [tooltipType]="'tooltip'"
               [tooltipTitle]="node.detail">
                  <svg:rect [attr.width]="node.width" [attr.height]="node.height" [attr.fill]="node.options.color" />
                  <svg:text alignment-baseline="central" [attr.x]="10" [attr.y]="node.height / 2">{{node.label}}</svg:text>
               </svg:g>
           </ng-template>
           
           <!-- Linkの定義 -->
           <ng-template #linkTemplate let-link>
              <svg:g class="edge">
                 <svg:path
                      class="line"
                      stroke-width="2"
                      marker-end="url(#arrow)" >
                 </svg:path>
                 <svg:text class="edge-label" text-anchor="middle">
                    <textPath
                      class="text-path"
                      [attr.href]="'#' + link.id"
                      [style.dominant-baseline]="link.dominantBaseline"
                      startOffset="50%">
                     {{link.label}}
                    </textPath>
                  </svg:text>
              </svg:g>
           </ng-template>
           
     </ngx-graph>
  `,
  styleUrls: ['./CSS/common.component.css']
})
export class HierarchialGraphComponent  { 
  // グラフの設定
  // グラフのデータ
  @Input() hierarchialGraph: { links: any[], nodes: any[] };
  // viewの配列（横と縦をセット）
  view: any[];
  // viewの横幅
  width: number = 600;
  // viewの縦幅
  height: number = 500;
  // ズーム可能か
  enableZoom: boolean = true;  
  // 自動ズーム
  autoZoom: boolean = false;
  // ズーム状態にするか
  panOnZoom: boolean = false;
  // x座標の位置
  panOffsetX: number = 100;
  // y座標の位置
  panOffsetY: number = 10;
  // zoomをどれだけにするか
  zoom: number = 0.7;
  // 凡例表示
  showLegend: boolean = false;
  // 色合い
  colorScheme: any;
  // グラフの向き
  orientation: string = 'BT';
  // 線のタイプ
  curve: any = shape.curveLinear;
  
  // node選択用
  @Output() nodeClick = new EventEmitter<any>();
  
  ngOnInit(){
     // グラフのviewをセット
     this.view = [this.width, this.height];
     // グラフの色合いのセット（picnic）
     this.colorScheme = {
        name: 'picnic',
        selectable: false,
        group: 'Ordinal',
        domain: [
           '#FAC51D', '#66BD6D', '#FAA026', '#29BB9C', '#E96B56', '#55ACD2', '#B7332F', '#2C83C9', '#9166B8', '#92E7E8'
        ]
     };
   }
   
  // グラフのノードがクリックされたとき
  selectKpi(node:any) {
      // 呼び出し元画面へクリックされたnodeを渡す
      this.nodeClick.emit(node);
      this.hierarchialGraph;
  }

}
