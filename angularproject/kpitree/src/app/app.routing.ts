import { ModuleWithProviders }      from '@angular/core';
import { Routes,RouterModule } from '@angular/router';

import { TopComponent }  from './Component/top.component';
import { NewTreeComponent }  from './Component/NewTree/newtree.component';
import { ChangeTreeComponent }  from './Component/ChangeTree/changetree.component';
import { KpiSettingComponent }  from './Component/KpiSetting/kpisetting.component';

// ルーティングテーブルの準備
const myRoutes = [
 {path: '', component: TopComponent},
 {path: 'newtree', component: NewTreeComponent},
 {path: 'newtree/:id', component: NewTreeComponent},
 {path: 'changetree', component: ChangeTreeComponent},
 {path: 'kpisetting', component: KpiSettingComponent},
];

export const MY_ROUTES: ModuleWithProviders = RouterModule.forRoot(myRoutes);
