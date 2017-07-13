import { MusicasComponent } from './musicas.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';


@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [MusicasComponent],
    exports: [MusicasComponent]
})
export class MusicasModule {

}
