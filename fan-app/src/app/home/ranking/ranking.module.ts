import { RankingComponent } from './ranking.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ChartsModule } from 'ng2-charts';


@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        FormsModule,
        ChartsModule
    ],
    declarations: [RankingComponent],
    exports: [RankingComponent]
})
export class RankingModule {

}
