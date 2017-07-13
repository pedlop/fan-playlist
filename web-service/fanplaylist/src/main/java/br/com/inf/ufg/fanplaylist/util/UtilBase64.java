package br.com.inf.ufg.fanplaylist.util;
/**@author  gabriel
 * @version 1.0.0*/

/***Converte bytes e array de bytes em base 64,
 * afim de garantir compatibilidade
 * com o outras maquinas bem com diversos
 * ambientes em que os bytes poderao ser enviados.*/
public final class UtilBase64 {

 /**@param vetorByte - Vetor de bytes a ser convertido
  * em base 64.
  * @return vetorByte convertido em base 64.*/
  public static String codificaBase64(final byte[] vetorByte) {
   return org.apache.commons.codec.binary.Base64.encodeBase64String(vetorByte);
  }

  /**@param base64 - String em base 64 que se deseja converter
   * para byte[].
  * @return vetorByte[] A pasrtir da base 64.*/
  public static byte[] decodificaBase64(final String base64) {
   return org.apache.commons.codec.binary.Base64.decodeBase64(base64);
  }

  /**Classe ultilitaria nao pode ser
   instanciada.*/
  private UtilBase64() { }
}
