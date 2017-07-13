import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';
import { Routes } from '@angular/router';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';

import {AccordionModule} from 'primeng/components/accordion/accordion';
import {MenuItem} from 'primeng/components/common/api';
import {SelectButtonModule} from 'primeng/primeng';
import {ToggleButtonModule} from 'primeng/primeng';
import {CheckboxModule} from 'primeng/primeng';
import { ChartsModule } from 'ng2-charts';
import { HomeModule } from './home/home.module';
import { AppComponent } from './app.component';
import { HomeRoutes } from './home/index';
/** ROTA PRINCIPAL.*/
import { rotaPrincipal } from './app.routes';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CarouselModule.forRoot(),
    BsDropdownModule.forRoot(),
    CollapseModule.forRoot(),
    TypeaheadModule.forRoot(),
    AccordionModule,
    SelectButtonModule,
    ToggleButtonModule,
    CheckboxModule,
    ChartsModule,
    HomeModule,
    RouterModule.forRoot(rotaPrincipal)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


