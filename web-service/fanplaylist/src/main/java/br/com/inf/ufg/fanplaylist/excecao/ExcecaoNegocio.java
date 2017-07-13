package br.com.inf.ufg.fanplaylist.excecao;
/**@author  gabriel
 * @version 1.0.0*/

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.inf.ufg.fanplaylist.configuracao.Internacionalizacao;



/**Classe que mapeia os padroes de excecao de negocio disparada pelas
 * classes de dominio e de servico.
 * JsonIngonerProperties - Ignora no processo de serializacao as informacoes de
 * {@link Exception} e de {@link Throwable}.
 * As informacoes: "cause", "stackTrace",
 * "suppressed", "localizedMessage", "message",
 * sa da classe mae {@link Exception}.*/
@JsonIgnoreProperties({ "cause", "stackTrace",
"suppressed", "localizedMessage", "message" })
public class ExcecaoNegocio extends Exception
                            implements IPadraoRetornoExcecao<ExcecaoNegocio> {

  /**
   * Serial default.
   */
   private static final long serialVersionUID = 1L;

  /**Codigo de erro da Mensagem.
   * Cada classe que dispara uma excecao nao
   * pode ter outra excecao com o mesmo codigo
   * erro na mesma classe.*/
   private int codigoErro;

   /**Nome da classe onde se disparando
    * a excecao, cada excecao de ser identificada
    * pelo codigoErro e pelo nome da classe.*/
   private String nomeClasse;

   /**Titulo da Mensagem para o usuario,
    * final.*/
   private String tituloMensagem;

   /**Descricao da Mensagem a ser apresentada
    * para o usuario final.*/
   private String descricaoMensagem;

   /**Campo opcional a ser usado pelos controller(s)
    * da aplicacao para indicar de qual controller partir
    * a sequencia de execucao que acarretou a excecao.*/
   private String controller;

   /**Construtor de serializacao.*/
   public ExcecaoNegocio() { }

   /**@param e - Excecao a ser convertida.
    * @param controllerP que esta lancando
    * a excecao.
    * Construtor padrao para as subclasses
    * que herdam de ExcecaoNegocio, classes
    * do pacote DTO. Visto que os servicos rest
    * devolvem sempre um objeto DTO, em caso de excecao
    * o servidor retorna a execao disparada de qualquer parte
    * do sistema que a mesma pode ser convertida em ExcecaoNegocio
    *                                                   ^
    *                                                   |
    *                                                  DTO(s).*/
   protected ExcecaoNegocio(final ExcecaoNegocio e,
                            final String controllerP) {
       codigoErro = e.getCodigoErro();
       nomeClasse = e.getNomeClasse();
       tituloMensagem = e.getTituloMensagem();
       descricaoMensagem = e.getDescricaoMensagem();
       controller = controllerP;
   }

   /**Construtor padrao para classe que desejam disparar
    * alguma excecao no sistema.
    *
    * @param codigoErroP - Codigo unico dentro da classe.
    * @param nomeClasseP - Nome da classe em que a excecao se encontra.
    * @param tituloMensagemP - Titulo da Messsagem pro usuario.
    * @param descricaoMensagemP - Descricao da Mensagem pro usuario.
    * @param concatena - Texto que sera concatenado junto a mensagem
    * que sera exibida ao usuario.*/
   @JsonCreator
   public ExcecaoNegocio(@JsonProperty("codigoErro") final int codigoErroP,
      @JsonProperty("nomeClasse") final String nomeClasseP,
      @JsonProperty("tituloMensagem") final String tituloMensagemP,
      @JsonProperty("descricaoMensagem") final String descricaoMensagemP,
      final String... concatena) {

       /**Constroi a Exception com as informacoes passadas.*/
        super(codigoErroP + "\n"
         + nomeClasseP + "\n"
         + tituloMensagemP + "\n"
         + descricaoMensagemP);

        codigoErro = codigoErroP;
        nomeClasse = nomeClasseP;
        /**Busca o locale do singleton para
         * traduzir as mensagens de acordo com a configuracao.*/
       try {
        tituloMensagem = Internacionalizacao
        .getBuscadorExcecao()
        .getString(tituloMensagemP);
         descricaoMensagem = Internacionalizacao
        .getBuscadorExcecao()
        .getString(descricaoMensagemP);
        if (concatena != null && concatena.length > 0) {
           for (int i = 0; i < concatena.length; i++) {
              descricaoMensagem = descricaoMensagem.concat(concatena[i]);
           }
        }
       } catch (java.util.MissingResourceException e) {
          tituloMensagem = tituloMensagemP;
          descricaoMensagem = descricaoMensagemP;
       }

  }

  /**@return codigoErro*/
  @JsonGetter("codigoErro")
  public int getCodigoErro() {
    return codigoErro;
  }

  /**@return nomeClasse*/
  @JsonGetter("nomeClasse")
  public String getNomeClasse() {
    return nomeClasse;
  }

  /**@return tituloMensagem*/
  @JsonGetter("tituloMensagem")
  public String getTituloMensagem() {
    return tituloMensagem;
  }

  /**@return descricaoMensagem*/
  @JsonGetter("descricaoMensagem")
  public String getDescricaoMensagem() {
    return descricaoMensagem;
  }

  /**@return controller*/
  @JsonGetter("controller")
  public String getController() {
     return controller;
  }
}
