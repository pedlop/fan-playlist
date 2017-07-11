package br.com.inf.ufg.fanplaylist.util;

/**@author  gabriel
 * @version 1.0.0*/

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Interface que marca todos os atributos que podem ser validados
 * por motivos de negocio e persistencia.
 * Nas classes que sao entidades do sistema esta inteface sera
 * inferida antes das operacoes de persistencia.*/
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidadorString {

  /**@return Tamanho maximo de caracteres permitido no campo.
   * Se (-1) o campo tem os caracteres ilimitados.*/
  int tamanho();

  /**@return
   * - Expressao regular para validar o conteudo da string.*/
  String regex();

  /**@return - Se verdadeiro o objeto pode ser nulo.*/
  boolean aceitaNulo();

  /**@return Parametro opcional que serve para havaliar o resultado
   * dos .matches() da regex, quando a regex nao for vazia.
   * O padrao e false, pois ja esta compativel com a regex que
   * havalia numeros em {@link ConstanteString}.*/
   boolean logicaRegex() default false;

  /**ABAIXO AS CONFIGURACOES DAS EXECOES QUE
   * PODEM SER LANCADAS EM CASO DE ALGUMA DAS
   * VALIDACOES ACIMA NAO CONFERIR.*/

  /**@return - Codigo do erro vide a classe
   * {@link ExcecaoNegocio}.*/
  int codigoErro();

  /**@return - Nome classe de onde esta sendo
   * lancada a excecao {@link ExcecaoNegocio}.*/
  String nomeClasse();

  /**@return - Titulo da mensagem a ser exibida
   * para o usuario{@link ExcecaoNegocio}.*/
  String tituloMensagem();

  /**@return - Descricao da mensagem de erro
   * que esta sendo lanca {@link ExcecaoNegocio}.*/
  String descricaoMensagem();

}
