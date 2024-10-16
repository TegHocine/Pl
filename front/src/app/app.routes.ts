import { HomeDetailComponent } from '@/pages/home-detail/home-detail.component';
import { HomePageComponent } from '@/pages/home-page/home-page.component';
import { PositionDetailComponent } from '@/pages/position-detail/position-detail.component';
import { PositionPageComponent } from '@/pages/position-page/position-page.component';
import { TeamDetailComponent } from '@/pages/team-detail/team-detail.component';
import { TeamPageComponent } from '@/pages/team-page/team-page.component';
import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'nation/:nation', component: HomeDetailComponent },
  {
    path: 'team',
    children: [
      { path: '', component: TeamPageComponent },
      { path: ':team', component: TeamDetailComponent },
    ],
  },
  {
    path: 'position',
    children: [
      { path: '', component: PositionPageComponent },
      { path: ':position', component: PositionDetailComponent },
    ],
  },
  // {path:'allPlayer', component: PositionPageComponent} // Uncomment if needed
];
