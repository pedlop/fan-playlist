import { MusicasComponent } from './musicas.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CheckboxModule } from 'primeng/primeng';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        CheckboxModule,
        TypeaheadModule
    ],
    declarations: [MusicasComponent],
    exports: [MusicasComponent]
})
export class MusicasModule {

}
