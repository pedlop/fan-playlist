
import { Route } from '@angular/router';

import { AdministrarComponent } from './index';

import { AdministrarMusicasRoutes } from './administrar-musicas/index';
import { AdministrarArtistasRoutes } from './administrar-artistas/index';
import { AdministrarShowsRoutes } from './administrar-shows/index';


export const AdministrarRoutes: Route[] = [
    {
        path: 'administrar',
        component: AdministrarComponent
    }
];

/*export const AdministrarRoutes: Route[] = [
  {
    path: 'administrar',
    component: AdministrarComponent,
    children: [
        ...AdministrarShowsRoutes,
        ...AdministrarArtistasRoutes,
        ...AdministrarMusicasRoutes

    ]
  }
];*/
