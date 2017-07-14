
import { Routes } from '@angular/router';

import { HomeRoutes } from './home/index';
import { AdministrarRoutes } from './home/administrar/index';

export const rotaPrincipal: Routes = [
    ...HomeRoutes,
    {
      path: '',
      redirectTo: '/homeComponent/paginaInicial',
      pathMatch: 'full'
    },
    {
      path: 'login',
      redirectTo: '/homeComponent/login',
      pathMatch: 'full'
    },
    {
      path: 'registrar',
      redirectTo: '/homeComponent/registrar',
      pathMatch: 'full'
    },
    {
      path: 'ranking',
      redirectTo: '/homeComponent/ranking',
      pathMatch: 'full'
    },
    {
      path: 'musicas',
      redirectTo: '/homeComponent/musicas',
      pathMatch: 'full'
    },
    {
      path: 'perfil',
      redirectTo: '/homeComponent/perfil',
      pathMatch: 'full'
    },
    {
      path: 'administrar',
      redirectTo: '/homeComponent/administrar',
      pathMatch: 'full'
    },
    {
      path: 'administrar-shows',
      redirectTo: '/homeComponent/administrar-shows',
      pathMatch: 'full'
    },
    {
      path: 'administrar-artistas',
      redirectTo: '/homeComponent/administrar-artistas',
      pathMatch: 'full'
    },
    {
      path: 'administrar-musicas',
      redirectTo: '/homeComponent/administrar-musicas',
      pathMatch: 'full'
    }
];

/*export const rotaAdmin: Routes = [
  ...AdministrarRoutes,
  {
    path: 'administrar',
    redirectTo: '/administracao',
    pathMatch: 'full'
  },
  {
    path: 'administrar-shows',
    redirectTo: '/administracao/shows',
    pathMatch: 'full'
  },
  {
    path: 'administrar-artistas',
    redirectTo: 'administracao/artistas',
    pathMatch: 'full'
  },
  {
    path: 'administar-musicas',
    redirectTo: 'administracao/musicas',
    pathMatch: 'full'
  }

];*/