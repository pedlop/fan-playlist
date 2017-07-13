package br.com.inf.ufg.fanplaylist.util;
/**@author  gabriel
 * @version 1.0.0
 * */

/**Classe padrao para uso em todo sistema
 * criptografia usando algoritmo RSA, sendo
 * que os metodos aqui previstos servem para a
 * criptografia dos dados sigiliosos e que eventualmente
 * sejam enviados a rede.
 *
 * A classe criptografa sequencia qualquer de caracteres
 * e devolve o byte[] em base64 para fins de persistencia
 * e envio na rede.
 *
 * Recebe sequencias em base64 e converte a mesma para byte[],
 * em seguida aplica o codigo de decriptografia da da sequencia
 * de bytes.*/
public final class UtilCriptografa {

  /**@param  conteudo - Texto ou informacao a ser encriptada.
   * @return byte[]   - vetorByte codificado em base64.*/
  public static String criptografa(final String conteudo) {
     byte[] vetorByte = UtilCriptografiaRSA.criptografaString(conteudo);
     return UtilBase64.codificaBase64(vetorByte);
  }

  /**@param  base64Criptografada - vetorByte criptografado.
   * @return Conteudo decriptografado.*/
  public static String decriptografa(final String base64Criptografada) {
    byte[] vetorByte = UtilBase64.decodificaBase64(base64Criptografada);
    return UtilCriptografiaRSA.decriptografaString(vetorByte);
  }

  /**Classe ultilitaria.*/
  private UtilCriptografa() { }
}
