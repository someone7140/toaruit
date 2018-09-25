import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { MY_ROUTES }  from './app.routing';

import { AppComponent }  from './app.component';

import { TopComponent }  from './Component/top.component';
import { NewTreeModule }  from './Component/NewTree/newtree.module';
import { ChangeTreeModule }  from './Component/ChangeTree/changetree.module';
import { KpiSettingModule }  from './Component/KpiSetting/kpisetting.module';

@NgModule({
  imports:      [ BrowserModule ,MY_ROUTES, NewTreeModule , ChangeTreeModule , KpiSettingModule],
  declarations: [ AppComponent , TopComponent],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
