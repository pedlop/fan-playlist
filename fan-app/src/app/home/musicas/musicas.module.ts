import { MusicasComponent } from './musicas.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MultiSelectModule} from 'primeng/primeng';
import { ListboxModule } from 'primeng/primeng';

@NgModule({
    imports: [
        CommonModule,
        ListboxModule,
        FormsModule,
        MultiSelectModule
    ],
    declarations: [MusicasComponent],
    exports: [MusicasComponent]
})
export class MusicasModule {

}
