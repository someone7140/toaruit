import { NgModule }      from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule }  from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgxGraphModule } from '@swimlane/ngx-graph';

import { MatDialogModule } from '@angular/material';

import { NewTreeService }  from '../Common/Service/newtree.service';
import { ChangeTreeService }  from '../Common/Service/changetree.service';

import { AlertDialogComponent }  from '.././Common/alert-dialog.component';
import { CalculateDialogComponent } from '../Common/calculate-dialog.component';

import { ChangeTreeComponent }  from './changetree.component';
import { ChangeTreeKpiListComponent }  from './changetreekpilist.component';



@NgModule({
  imports:      [ CommonModule ,FormsModule , BrowserAnimationsModule , NgxChartsModule, NgxGraphModule, MatDialogModule, ReactiveFormsModule],
  declarations: [ ChangeTreeComponent, ChangeTreeKpiListComponent],
  providers:    [ NewTreeService ,ChangeTreeService],
  entryComponents: [ AlertDialogComponent , CalculateDialogComponent],
  exports:      [ ChangeTreeComponent, ChangeTreeKpiListComponent ]
})

export class ChangeTreeModule { }
