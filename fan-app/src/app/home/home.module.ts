import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule  } from '@angular/forms';
import { HomeComponent } from './home.component';
import { CollapseModule , ModalModule , BsDropdownModule, CarouselModule } from 'ngx-bootstrap';
import { LoginModule } from './login/login.module';
import { RegistrarModule } from './registrar/registrar.module';
import { RankingModule } from './ranking/ranking.module';
import { MusicasModule } from './musicas/musicas.module';
import { PaginaInicialModule } from './pagina-inicial/pagina-inicial.module';
import { PerfilModule } from './perfil/perfil.module';

@NgModule({
    imports: [
        FormsModule,
        CollapseModule.forRoot(),
        ModalModule.forRoot(),
        BsDropdownModule.forRoot(),
        CarouselModule.forRoot(),
        CommonModule,
        RouterModule,
        LoginModule,
        RegistrarModule,
        RankingModule,
        MusicasModule,
        PaginaInicialModule,
        PerfilModule
        ],
    declarations: [HomeComponent],
    exports: [HomeComponent]
})
export class HomeModule {

}
