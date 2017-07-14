import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdministrarComponent } from './administrar.component';
import { AdministrarArtistasModule } from './administrar-artistas/administrar-artistas.module';
import { AdministrarMusicasModule } from './administrar-musicas/administrar-musicas.module';
import { AdministrarShowsModule } from './administrar-shows/administrar-shows.module';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        AdministrarArtistasModule,
        AdministrarMusicasModule,
        AdministrarShowsModule
    ],
    declarations: [AdministrarComponent],
    exports: [AdministrarComponent]
})
export class AdministrarModule {

}
