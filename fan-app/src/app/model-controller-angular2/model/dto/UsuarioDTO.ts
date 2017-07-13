/**Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo: Classe que correponde ao UsuarioDTO
*/
import {Dto} from './Dto';

export class UsuarioDTO extends Dto {

    public  id: number;
    
    public email: string;
    
    public senha: string;

    public tipoUsuario: string;
}
