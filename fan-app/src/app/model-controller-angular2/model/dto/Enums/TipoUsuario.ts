/**Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo: Classe que correponde ao TipoUsuario
*/

import { IEnum } from '../IEnums/IEnum';

export class TipoUsuario implements IEnum {

    getEnums(): string[] {
      return [ 'USUARIO', 'ADMIN', 'APP'];
    }

}
