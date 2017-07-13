import { PaginaInicial } from './pagina-inicial.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CarouselModule , CollapseModule} from 'ngx-bootstrap';


@NgModule({
    imports: [
        CommonModule,
        CarouselModule,
        CollapseModule
    ],
    declarations: [PaginaInicial],
    exports: [PaginaInicial]
})
export class PaginaInicialModule {

}
