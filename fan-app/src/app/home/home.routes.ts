import { Route } from '@angular/router';

import { HomeComponent } from './index';

import { LoginRoutes } from './login/index';
import { MusicasRoutes } from './musicas/index';
import { RankingRoutes } from './ranking/index';
import { RegistrarRoutes } from './registrar/index';
import { PaginaInicialRoutes } from './pagina-inicial/index';
import { PerfiRoutes } from './perfil/index';
import { AdministrarRoutes } from './administrar/index';
import { AdministrarShowsRoutes } from "./administrar/administrar-shows/index";
import { AdministrarArtistasRoutes } from './administrar/administrar-artistas/index';
import { AdministrarMusicasRoutes } from './administrar/administrar-musicas/index';



export const HomeRoutes: Route[] = [
  {
    path: 'homeComponent',
    component: HomeComponent,
    children: [
        /**IMPORTACOES DAS PAGINAS QUE REALMENTE
        FAZEM PARTE DO SISTEMA.*/
        ...LoginRoutes,
        ...MusicasRoutes,
        ...RankingRoutes,
        ...RegistrarRoutes,
        ...PaginaInicialRoutes,
        ...PerfiRoutes,
        ...AdministrarRoutes,
        ...AdministrarShowsRoutes,
        ...AdministrarArtistasRoutes,
        ...AdministrarMusicasRoutes

    ]
  }
];
