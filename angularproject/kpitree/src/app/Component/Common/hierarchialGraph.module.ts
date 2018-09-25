import { NgModule }      from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule }  from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgxGraphModule } from '@swimlane/ngx-graph';

import { HierarchialGraphComponent } from '../Common/hierarchialGraph.component';

@NgModule({
  imports:      [ CommonModule ,FormsModule , BrowserAnimationsModule , NgxChartsModule, NgxGraphModule],
  declarations: [ HierarchialGraphComponent],
  exports:      [ HierarchialGraphComponent]
})
export class HierarchialGraphModule { }
