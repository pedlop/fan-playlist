import { RankingComponent } from './ranking.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        CommonModule,
        RouterModule
    ],
    declarations: [RankingComponent],
    exports: [RankingComponent]
})
export class RankingModule {

}
