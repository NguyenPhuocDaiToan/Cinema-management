import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'security',
    loadChildren: () => import('./security/security.module').then(module => module.SecurityModule)
  },
  {
    path: 'account',
    loadChildren: () => import('./account-module/account-module.module').then(module => module.AccountModuleModule)
  },
  {
    path: 'booking',
    loadChildren: () => import('./booking-module/booking-module.module').then(module => module.BookingModuleModule)
  },
  {
    path: 'film',
    loadChildren: () => import('./film-module/film-module.module').then(module => module.FilmModuleModule)
  },
  {
    path: 'history',
    loadChildren: () => import('./history-module/history-module.module').then(module => module.HistoryModuleModule)
  },
  {
    path: 'room',
    loadChildren: () => import('./room-module/room-module.module').then(module => module.RoomModuleModule)
  },
  {
    path: 'seat',
    loadChildren: () => import('./seat-module/seat-module.module').then(module => module.SeatModuleModule)
  },
  {
    path: 'ticker',
    loadChildren: () => import('./ticket-module/ticket-module.module').then(module => module.TicketModuleModule)
  },
  {
    path: 'statistic',
    loadChildren: () => import('./statistic-module/statistic-module.module').then(module => module.StatisticModuleModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
