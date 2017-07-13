/**
 * Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo classe que correponde as excecoes associadas ao negocio
 * da aplicacao que podem ser lancadas pelo servidor de aplicao, durante
 * as operacoes de insercao, consulta e qualquer outras operacoes com o servidor.
 */
import { PadraoRetorno } from '../retorno/PadraoRetorno';

export class ExcecaoNegocio extends PadraoRetorno {

  /**Codigo do erro ver classe equilibrio ExecaoNegocio. */
  public codigoErro: number;

  /**Nome da classe dentro do sistema onde surgiu o erro ou seja
   * classe que lancou a excecao, ver classe equilibrio ExecaoNegocio. */
  public nomeClasse: string;

  /**Ttulo da mensagem que devera ser apresentado ao usuario final
   * da aplicacao, classe equilibrio ExecaoNegocio. */
  public tituloMensagem: string;

  /**Descricao do erro que devera ser apresentado ao usuario final
   * no corpo da mensagem, ver classe equilibrio ExecaoNegocio. */
  public descricaoMensagem: string;

  /**Controller dentro do web service de onde surgiu a excecao,
   *ou seja o controller correpondente a servico chamado que originou a execao,
    ver classe equilibrio ExecaoNegocio. */
  public controller: string;
}
