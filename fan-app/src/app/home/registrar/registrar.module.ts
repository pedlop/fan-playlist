import { RegistrarComponent } from './registrar.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [RegistrarComponent],
    exports: [RegistrarComponent]
})
export class RegistrarModule {

}
