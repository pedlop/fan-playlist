package br.com.inf.ufg.fanplaylist.configuracao.springsecurity;

import java.util.HashMap;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author gabriel
 * @author lucas
 * 
 * @version 1.0.0
 * 
 * A classe tem por finalidade pegar o token gerado pelo
 * web service e verificar se ele existe na hash em que é
 * armazenado todos os tokens gerados pelo mesmo. Caso exista,
 * é executado a operação e posteriormente removido o token
 * da hash. Caso não exista, é retornado uma mensagem infomando
 * que a operação já foi realizada.
 * */
@Component
@Scope(value = "singleton")
public class GerenciadorTokenSincronizacao {

    /**
     * Grupos de tokens.
     */
     private String[] grupoToken = {"GRUPO0", "GRUPO1", "GRUPO2" };

      /**
       * Foi criado uma hash dentro de outra hash.
       * Sendo que a chave mais externa é um dos
       * grupos listados acima (grupoToken[]), cada
       * grupo possui sua própria hashmap de tokens,
       * assim, quando um token estiver sendo procurado,
       * basta navegar nas hashmaps de todos
       * os grupos. Caso o token seja encontrado,
       * a operação é autorizada e logo após apaga-se
       * o token a fim de não repetir a mesma request.
       * Padão utilizado: Token Sincronizador.
       */
      private HashMap<String, HashMap<String, String>> tabelaToken;

      /**
       * Variável de controle da lista circular (grupoToken[]).
       */
      private static final int  QTD_GRUPO = 3;

      /** Variáveis de controle da lista circular.
       * Posicacao procurada a interacao.*/
      private int posicaoProcurada = 0;

      /**
       * Grupos de tokens.
       */
      private boolean threadNaoStartada = true;

      /**Thread de limpesa de tokens antigos.
       * Interacao na lista circular.*/
      private Runnable threadLimpaGruposToken = () -> {
            this.mudaGrupoToken();
            System.out
            .println("\n\n\nTHREAD LIMPA TOKEN SINCRONIZACAO OK.\n\n\n");
      };

      /**Construtor padrao que inicializa a tabela HashMap.*/
      public GerenciadorTokenSincronizacao() {
            tabelaToken = new HashMap<>();
      }

      /**Gera token de sincronizacao para aplicacao cliente,
       * confere se a Thread foi inicializada senao inicializa
       * a thread.
       * @return Token de sincronizacao.*/
      public synchronized String geraTokenSincronizacao() {
            String token  = proximoToken();
            HashMap<String, String> tokenHash = new HashMap<>();
            tokenHash.put(token, token);
            tabelaToken.put(grupoToken[posicaoProcurada % QTD_GRUPO],
                        tokenHash);
            if (threadNaoStartada) {
                  new Thread(this.threadLimpaGruposToken).run();
            }
            threadNaoStartada = false;
            return token;
      }

      /**@param tokenSincronizacao - Token cliente.
       * @return - Confere se o token passado pela aplicacao
       * cliente esta ou nao autoriazado(true), ou se ja foi
      * feita alguma operacao com o token false.*/
      public synchronized boolean confereTokenSincronizacao(
         final String tokenSincronizacao) {
            for (int i = 0; i < grupoToken.length; i++) {
               if (tabelaToken.containsKey(grupoToken[i])) {
                 if (tabelaToken.get(grupoToken[i])
                             .containsKey(tokenSincronizacao))  {
                      tabelaToken.get(grupoToken[i]).remove(tokenSincronizacao);
                      return true;
                  }
               }
            }
        return false;
      }

      /**@return Gera token unico com base o clock de cpu
       * combinado com um numero aleatorio.*/
      private  String proximoToken() {
          long millisegundosAtual = System.currentTimeMillis();
          Random aleatorio = new Random();
          aleatorio.setSeed(millisegundosAtual);
          return "equilibrio"   + Long.toString(millisegundosAtual)
                                + Long.toString(Math.abs(aleatorio.nextLong()));
      }

      /**Realiza limpeza da HashMap de grupos ou seja
       * intera uma posicao da lista circular e limpa
       * a posicao mais antiga, o grupo de tokens mais antigos.
       * Vide a threadLimpaGruposToken.*/
      private synchronized void mudaGrupoToken() {
            posicaoProcurada++;
        if (posicaoProcurada == QTD_GRUPO) {
            posicaoProcurada = 0;
        }
        tabelaToken.remove(grupoToken[posicaoProcurada % QTD_GRUPO]);
      }
}