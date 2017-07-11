package br.com.inf.ufg.fanplaylist.excecao;
/**@author gabriel
 * @version 1.0.0*/

/**Interface que define o padrao que outras excecoes deveram
 * seguir, essas podem nao ser herdadas ou mesmo serem uma
 * {@link ExcecaoNegocio}, visto que pode ser necessario a criacao
 * de outro tipos de excecao.
 *
 * @param <T>*/
public interface IPadraoRetornoExcecao<T extends Exception> {

  /**@return Cofigo Unico da excecao
   * dentro da classe vide {@link ExcecaoNegocio}.*/
  int getCodigoErro();

  /**@return Nome da classe de onde a excecao esta
   * sendo disparada.{@link ExcecaoNegocio}.*/
  String getNomeClasse();

  /**@return Titulo da mensagem a ser apresentada
   * ao usuario. {@link ExcecaoNegocio}*/
  String getTituloMensagem();

  /**@return Descricao da messagem a ser apresentada
   * ao usuario. {@link ExcecaoNegocio}*/
  String getDescricaoMensagem();
}
