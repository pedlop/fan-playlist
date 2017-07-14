import { PaginaInicial } from './pagina-inicial.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CarouselModule , CollapseModule} from 'ngx-bootstrap';




@NgModule({
    imports: [
        CommonModule,
        CarouselModule,
        CollapseModule,
        FormsModule
    ],
    declarations: [PaginaInicial],
    exports: [PaginaInicial]
})
export class PaginaInicialModule {

}
