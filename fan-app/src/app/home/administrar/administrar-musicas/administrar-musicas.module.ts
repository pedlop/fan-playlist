import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdministrarMusicasComponent } from './administrar-musicas.component';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TypeaheadModule
    ],
    declarations: [AdministrarMusicasComponent],
    exports: [AdministrarMusicasComponent]
})
export class AdministrarMusicasModule {

}
