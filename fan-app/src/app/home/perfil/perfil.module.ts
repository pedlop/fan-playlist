import { PerfilComponent } from './perfil.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [PerfilComponent],
    exports: [PerfilComponent]
})
export class PerfilModule {

}
