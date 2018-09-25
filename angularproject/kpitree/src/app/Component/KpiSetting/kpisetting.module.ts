import { NgModule }      from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule }  from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatDialogModule } from '@angular/material';

import { NewTreeService }  from '../Common/Service/newtree.service';
import { ChangeTreeService }  from '../Common/Service/changetree.service';
import { KpiSettingService }  from '../Common/Service/kpisetting.service';

import { AlertDialogComponent }  from '.././Common/alert-dialog.component';

import { KpiSettingComponent }  from './kpisetting.component';
import { KpiSettingListComponent }  from './kpisettinglist.component';
import { KpiSettingDetailComponent }  from './kpisettingdetail.component';

import { HierarchialGraphModule } from '../Common/hierarchialGraph.module';

@NgModule({
  imports:      [ CommonModule ,FormsModule , BrowserAnimationsModule , MatDialogModule, ReactiveFormsModule , HierarchialGraphModule],
  declarations: [ KpiSettingComponent , KpiSettingListComponent , KpiSettingDetailComponent],
  providers:    [ NewTreeService ,ChangeTreeService ,KpiSettingService],
  entryComponents: [ AlertDialogComponent ],
  exports:      [ KpiSettingComponent , KpiSettingListComponent , KpiSettingDetailComponent]
})

export class KpiSettingModule { }
