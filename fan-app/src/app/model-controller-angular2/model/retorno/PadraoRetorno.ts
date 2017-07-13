/**
 * Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo classe generecia que define todas as
 * propriedades poderao eventualmente serem retornadas
 * pelo servidor.
 * Sendo que esta classe e a classe mae das excecoes visto
 * que a mesmas tambem podem ou nao ter um campo que esta no padrao
 * retorno.
 */

export abstract class PadraoRetorno {
    /**Esse atributos sao padrao do Spring em caso
     * de algum problema durante a autenticacao ou mesmo
     * para erros internos no servidor.
    */
    /**Time*/
    public timestamp : string;

    /**Status http (500 erro interno de aplicacao), 
     *             (417 sem token de sincronizacao),
     *             (200 ok transacao com sucesso),
     *             (404 servico ou recurso nao encontrado).*/
    public status : number;

    /***Titulo do erro http.*/
    public error : string;

    /*** Descricao da menssagem http.*/
    public message : string;

    /** Caminho do servico ou recurso que foi requisitado*/
    public path : string;
}
