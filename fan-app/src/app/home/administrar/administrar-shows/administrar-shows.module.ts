import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdministrarShowsComponent } from './administrar-shows.component';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TypeaheadModule
    ],
    declarations: [AdministrarShowsComponent],
    exports: [AdministrarShowsComponent]
})
export class AdministrarShowsModule {

}