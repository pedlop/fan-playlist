/**Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo: Classe que correponde ao UsuarioDTO
*/
import {Dto} from './Dto';

export class UsuarioDTO extends Dto {

    private id: number;
    
    private email: string;
    
    private senha: string;

    public tipoUsuario: string;
}
