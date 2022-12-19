import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmstatisticComponent } from './filmstatistic/filmstatistic.component';
import { AccountstatisticComponent } from './accountstatistic/accountstatistic.component';



@NgModule({
  declarations: [FilmstatisticComponent, AccountstatisticComponent],
  imports: [
    CommonModule
  ]
})
export class StatisticModuleModule { }
