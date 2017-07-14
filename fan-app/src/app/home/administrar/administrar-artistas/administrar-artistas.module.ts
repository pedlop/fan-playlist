import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdministrarArtistasComponent } from './administrar-artistas.component';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TypeaheadModule
    ],
    declarations: [AdministrarArtistasComponent],
    exports: [AdministrarArtistasComponent]
})
export class AdministrarArtistasModule {

}
