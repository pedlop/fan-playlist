import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';

import { CarouselModule } from 'ngx-bootstrap/carousel';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';

import {AccordionModule} from 'primeng/components/accordion/accordion';
import {MenuItem} from 'primeng/components/common/api';
import {SelectButtonModule} from 'primeng/primeng';
import {ToggleButtonModule} from 'primeng/primeng';
import {CheckboxModule} from 'primeng/primeng';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { RankingComponent } from './ranking/ranking.component';
import { MusicasComponent } from './musicas/musicas.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegistrarComponent,
    RankingComponent,
    MusicasComponent
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
    RouterModule.forRoot([
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'registrar',
        component: RegistrarComponent
      },
      {
        path: 'ranking',
        component: RankingComponent
      },
      {
        path: 'musicas',
        component: MusicasComponent
      }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
