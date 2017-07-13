
import { Routes } from '@angular/router';

import { HomeRoutes } from './home/index';

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
    }
];