package br.com.inf.ufg.fanplaylist.util;

import java.io.ByteArrayOutputStream;

/**@author gabriel
 *@version 1.0.0*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**Classe do pacote Util que define metodos padraoes para
 * criptografia e decriptografia de String(s) e aquivos
 * binarios. Ultilizaremos o Algoritmos de Criptografia RSA.*/
public final class UtilCriptografiaRSA {

    /**Construtor privado que nao pode ser acessado
     * por nenhuma classe.*/
    private UtilCriptografiaRSA() { }

    /**Nome do algoritmo de criptografia que esta sendo usado.*/
    private static final String ALGORITMO   = "RSA";

    /**Numero maximo de caracteres que podem ser criptografados
     * de uma so vez com o algoritmo.*/
    private static final int MAX_CARACTERES_BYTE = 117;

    /**Tamanho do array gerado pelo algoritmo de criptografia.*/
    private static final int TAM_ARRAY_BYTE = 128;

    /**Chave privada do RSA.*/
    private static PrivateKey chavePrivada;

    /**Chave publica do RSA.*/
    private static PublicKey chavePublica;

    /**@return Busca Cifrador privado para descriptografar
     * informacoes.*/
    public static Cipher buscaCifradorChavePrivada() {
      Cipher cifrador;
      try {
        if (chavePrivada == null) {
          UtilCriptografiaRSA.carregaChaves();
        }
        cifrador = Cipher.getInstance(ALGORITMO);
        cifrador.init(Cipher.DECRYPT_MODE, chavePrivada);
      } catch (NoSuchAlgorithmException | NoSuchPaddingException
              | InvalidKeyException e) {
               e.printStackTrace();
               return null;
      }
     return cifrador;
    }

    /**@return Busca Cifrador publico para criptografar
     * informacoes.*/
    public static Cipher buscaCifradorChavePublica() {
      Cipher cifrador;
      try {
        if (chavePublica == null) {
           UtilCriptografiaRSA.carregaChaves();
        }
        cifrador = Cipher.getInstance(ALGORITMO);
        cifrador.init(Cipher.ENCRYPT_MODE, chavePublica);
      } catch (NoSuchAlgorithmException | NoSuchPaddingException
        | InvalidKeyException e) {
           e.printStackTrace();
           return null;
      }
      return cifrador;
    }


    /**Verifica se o par de chaves Pública e Privada já foram geradas.*/
    public static void carregaChaves() {
      try {
            /** Carrega CHAVE PUBLICA. */
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(
                    new FileInputStream(
                            ClassLoader
                            .getSystemResource("chavePublica.key")
                            .getPath()
                    )
            );
            chavePublica = (PublicKey) inputStream.readObject();
            inputStream.close();

            /** Carrega CHAVE PRIVADA. */
            inputStream = new ObjectInputStream(
                    new FileInputStream(
                           ClassLoader
                           .getSystemResource("chavePrivada.key")
                           .getPath()
                     )
            );
            chavePrivada = (PrivateKey) inputStream.readObject();
            inputStream.close();
      } catch (IOException | ClassNotFoundException e) {
       e.printStackTrace();
      }
    }

    /**
     * @param texto Texto a ser criptografado.
     * Criptografa o texto puro usando chave pública.
     * @return Array de bytes criptografados.
     * O algoritmo RSA com a chave de 1024 bits usada por
     * nossa aplicacao por motivos de performace so consegue
     * criptografar 117 caracteres por vez, sendo assim o algoritmo
     * abaixo separ o texto que supera 117 caracteres em bloco
     * criptografa cada bloco de texto e em seguida concatena os array(s)
     * de bytes criptografados.
     */
    public static byte[] criptografaString(final String texto) {
      try {

       byte[] textoCriptografado = null;
       final Cipher cifrador = UtilCriptografiaRSA.buscaCifradorChavePublica();

       if (texto.length() <= MAX_CARACTERES_BYTE) {
          textoCriptografado = cifrador.doFinal(texto.getBytes());
       } else {

           int qtdBlocos = 0;

           if (texto.length() % MAX_CARACTERES_BYTE == 0) {
             qtdBlocos = texto.length() / MAX_CARACTERES_BYTE;
           } else {
              qtdBlocos = (texto.length() / MAX_CARACTERES_BYTE) + 1;
           }
          /**Comeco e termino do primeiro bloco de caracteres
           * a ser criptografado.*/
           int inicio = 0, fim = MAX_CARACTERES_BYTE;

           textoCriptografado = cifrador.doFinal(texto.substring(
           inicio, fim).getBytes());

           /**PRIMEIRO BLOCO JA FOI CRIPTOGRAFADO*/
           qtdBlocos--;
           for (int i = 0; i < qtdBlocos; i++) {

              /**INICIO <- FIM
              * FIM += QTD DE CARACTERES DE UM BLOCO.*/
              inicio =  fim;
              fim    += MAX_CARACTERES_BYTE;
              byte[] bloco = null;

              /**Ultimo bloco incompleto de caracteres.*/
              if (fim > texto.length()) {
                bloco = cifrador.doFinal(texto.substring(inicio,
                texto.length()).getBytes());
             } else {
               bloco = cifrador.doFinal(texto.substring(inicio,
                fim).getBytes());
             }

            /**Concatena o que ja fora criptografado
            * antes pelo algoritmo com o bloco atual.*/
            textoCriptografado = UtilCriptografiaRSA
              .concatenaArrayByte(textoCriptografado,
                   bloco);
         } //FIM DO FOR.
       } //FIM DO ELSE.
        return textoCriptografado;
     } catch (IllegalBlockSizeException | BadPaddingException e) {
        e.printStackTrace();
        return null;
     }
  }

    /**
     * @param texto - Array criptografado com o algoritmo
     * acima de criptografia.
     * Caso o array de bytes supere os 128 byte TAM_ARRAY_BYTE
     * o algoritmo divide o array em blocos de 128 e decriptografa
     * bloco a bloco e concatena com os anteriores assim monta
     * o array de bytes decriptografado.
     * Decriptografa o texto puro usando chave privada.
     * @throws BadPaddingException - Preechimento da String e
     * invalido para o funcionamento do algoritmo.
     * @throws IllegalBlockSizeException - A quantidade de caracteres
     * passadas ultrapassa a quantidade maxima de bytes suportados.
     * @return Texto decriptografado.
     */
    public static String decriptografaString(final byte[] texto) {
    try {

       byte[] textoDecriptografado = null;
       final Cipher cifrador = UtilCriptografiaRSA.buscaCifradorChavePrivada();

      if (texto.length == TAM_ARRAY_BYTE) {
         textoDecriptografado  = cifrador.doFinal(texto);
      } else {
       /**Bloco de caracteres que guarda
        * o segumento do array texto decriptografado.*/
        byte[] bloco = new byte[TAM_ARRAY_BYTE];

       /**Divide o texto em blocos de 128 bytes
        * copia conteudo criptografado em bloco
        * decriptografa o bloco e em seguida concatena com
        * o que antes fora criptografado.*/
        for (int indice = 0; indice < texto.length;
                       indice += TAM_ARRAY_BYTE) {
            for (int i = 0; i < TAM_ARRAY_BYTE; i++) {
              bloco[i] = texto[indice + i];
            }
            bloco = cifrador.doFinal(bloco);
            textoDecriptografado = concatenaArrayByte(textoDecriptografado,
            bloco);
            bloco = new byte[TAM_ARRAY_BYTE];
        }
       }
      return new String(textoDecriptografado);
     } catch (IllegalBlockSizeException | BadPaddingException e) {
        e.printStackTrace();
         return null;
     }
 }
  /**@param primeiroBloco - Primeiro bloco escrito do buffer.
   * @param segundoBloco  - Segundo bloco que escrito logo apos
   * o termino do primeiro bloco.
   * Caso o primeiro blobo seja nulo retorna-se o
   * segundo bloco, caso ambos nulos retorna-se null.
   * @return Array de byte[] com a concatenacao.*/
  public static byte[] concatenaArrayByte(final byte[] primeiroBloco,
      final byte[] segundoBloco) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
          if (primeiroBloco != null && primeiroBloco.length != 0) {
            outputStream.write(primeiroBloco);
          }
          if (segundoBloco != null && segundoBloco.length != 0) {
            outputStream.write(segundoBloco);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        return outputStream.toByteArray();
  }
}
