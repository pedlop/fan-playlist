/**Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo: Classe onde configura-se
 * o endereco de dns serem usadas
 * nas requisicoes.
*/

export class DnsWebService {

    //Endereco de onde se encontra o servidor na Web
    public static  dns : string = 'http://localhost:8080';

    //Constante para identificar tokens de segura da aplicacao
    //E dos usuarios.
    public static storageTokenUsuario : string  = 'storageTokenUsuario';

    //Constante para identifica tokens de seguranca da aplicacao.
    public static storageTokenAplicacao : string = 'storageTokenAplicacao';

}
