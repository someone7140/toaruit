import { Component } from '@angular/core';
import { NewTreeService }  from '../Common/Service/newtree.service';

@Component({
  template: `
     <div [ngSwitch]="displayComponent">
        <span *ngSwitchCase="'kpiSettingList'"><kpiSettingList (kpiSettingScreen)="onkpiSetingScreen($event)"></kpiSettingList></span>
        <span *ngSwitchCase="'kpiSettingDetail'"><kpiSettingDetail (kpiListScreen)="onkpiListScreen($event)"></kpiSettingDetail></span>
        <span *ngSwitchDefault><kpiSettingList  (kpiSettingScreen)="onkpiSetingScreen($event)"></kpiSettingList></span>
     </div>
  `
})
export class KpiSettingComponent  {
   // ��ʂɕ\������R���|�[�l���g
   displayComponent ='';
   
   // �T�[�r�X���C���X�^���X��
   constructor(private newTreeService: NewTreeService){}
   
   // �o�^�c���[�ꗗ��ʂ���̑J��
   onkpiSetingScreen(selectKpiId : string){
      // KPI�c���[�EID���Z�b�V������
      this.newTreeService.copyKpiTreeLocalStrageToSession(selectKpiId);
      // �R���|�[�l���g�̕\����؂�ւ�
      this.displayComponent = 'kpiSettingDetail';
   }
   
   // KPI�l�ڍ׉�ʂ���ꗗ�ւ̑J��
   onkpiListScreen(nextscreen : string){
      // �R���|�[�l���g�̕\����؂�ւ�
      this.displayComponent = nextscreen;
   }
}
