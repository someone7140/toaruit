import { NgModule }      from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule }  from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatDialogModule } from '@angular/material';

import { NewTreeService }  from '../Common/Service/newtree.service';
import { AlertDialogComponent }  from '.././Common/alert-dialog.component';
import { CalculateDialogComponent } from '../Common/calculate-dialog.component';

import { NewTreeComponent }  from './newtree.component';
import { NewTreeTitleComponent }  from './newtreeTitle.component';
import { NewTreeMakeComponent }  from './newtreeMake.component';

import { HierarchialGraphModule } from '../Common/hierarchialGraph.module';

@NgModule({
  imports:      [ CommonModule ,FormsModule , BrowserAnimationsModule , MatDialogModule, ReactiveFormsModule ,HierarchialGraphModule],
  declarations: [ NewTreeComponent, NewTreeTitleComponent, NewTreeMakeComponent , AlertDialogComponent,  CalculateDialogComponent],
  providers:    [ NewTreeService ],
  entryComponents: [ AlertDialogComponent , CalculateDialogComponent],
  exports:      [ NewTreeComponent, NewTreeTitleComponent, NewTreeMakeComponent]
})
export class NewTreeModule { }
