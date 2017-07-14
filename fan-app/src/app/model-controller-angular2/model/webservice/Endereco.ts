/**Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo Definir os enderecos de todas as
 * api(s) do sistema.
*/

export class Endereco {

    public static LOGIN_APLICACAO  = '/api/login';

    public static TOKEN_SINCRONIZACAO  = '/api/token/sincronizacao';

    public static CADASTRAR_USUARIO   = '/ControllerUsuario/salvarUsuario';

    public static ATUALIZAR_USUARIO   = '/ControllerUsuario/atualizarUsuario';

    public static DELETAR_USUARIO   = '/ControllerUsuario/deletarUsuario';

    public static FIND_BY_EMAIL = '/ControllerUsuario/findByEmail';

    public static LOGIN_USUARIO  = '/ControllerLogin/login';

}
