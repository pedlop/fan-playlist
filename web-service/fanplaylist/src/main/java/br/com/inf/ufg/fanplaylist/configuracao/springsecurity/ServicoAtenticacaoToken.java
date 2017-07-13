package br.com.inf.ufg.fanplaylist.configuracao.springsecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;
import br.com.inf.ufg.fanplaylist.excecao.NumeroErro;

/**@author  gabriel
 * @version 1.0.0*/

/**Servico de geracao de tokens com JWT.*/
@Service
public class ServicoAtenticacaoToken {

    /**Nome do header a ser passado na request
     * onde esta o token. Nas requests o token
     * devera ser passado como "auth-token-pontal" : "conteudo token"*/
    public static final String NOME_HEADER_AUTH = "auth-token-fanplaylist";

    /**Token reposavel pela sincronizacao dos envios de
     * formularios.*/
    public static final String SINCRONIZACAO_HEADER = "sincronizacao-header";

    /**Gerador de token e autenticador. {@link GeradorToken}*/
    private final GeradorToken geradorToken;

    /**Gerenciador de sincronizacao de request(s).*/
    private final GerenciadorTokenSincronizacao gerenciadorTokenSinconizacao;

    /**@param geradorTokenP      - Injecao da dependecia do gerador de tokens.
     * @param tokenSinconizacaoP - Gerenciador de sincronizacao token
     * {@link GerenciadorTokenSincronizacao}.*/
    @Autowired
    public ServicoAtenticacaoToken(final GeradorToken geradorTokenP,
                     final GerenciadorTokenSincronizacao tokenSinconizacaoP) {
        geradorToken = geradorTokenP;
        gerenciadorTokenSinconizacao = tokenSinconizacaoP;
    }

    /**Filtro de adicao de token(s) aos Header(s) de requests.
     * @param response - Resposta do servidor a uma request.
     * @param autenticacaoLogin - Servico de autenticacao.*/
    public void adicionaTokenHeader(final HttpServletResponse response,
         final AutenticacaoLogin autenticacaoLogin) {

        final UserDetails login = autenticacaoLogin.getDetails();
        response.addHeader(NOME_HEADER_AUTH,
                           geradorToken.criaTokenLogin(login));
    }

    /**@param request - Requisicao qualquer que tenha um toke passado no header.
     * @return - Autenticacao obtida a partir do token passado no header
     * @throws ExcecaoNegocio */
    public Authentication geraAutenticacaoDaRequest(
                             final HttpServletRequest request)
                                       throws ExcecaoNegocio {

       final String token = request.getHeader(NOME_HEADER_AUTH);

     if (request.getMethod().equals(RequestMethod.POST.toString())
         && !(request.getRequestURI().equals("/ControllerLogin/login"))) {
           final String tokenSincronizacao
                                    = request.getHeader(SINCRONIZACAO_HEADER);
           if (tokenSincronizacao == null) {
              throw new ExcecaoNegocio(NumeroErro.ERRO_10,
               "ServicoAutenticacaoToken",
               "ServicoAtenticacaoToken10.titulo",
               "ServicoAutenticacaoToken10.mensagem");
           }
           if (!(gerenciadorTokenSinconizacao
                    .confereTokenSincronizacao(tokenSincronizacao))) {
               throw new ExcecaoNegocio(NumeroErro.ERRO_20,
                "ServicoAutenticacaoToken",
                "ServicoAtenticacaoToken20.titulo",
                "ServicoAutenticacaoToken20.mensagem");
           }
       }

       if (token == null || token.isEmpty()) {
              return null;
       }
       return geradorToken
              .converteTokenParaLogin(token)
              .map(AutenticacaoLogin::new)
              .orElse(null);
    }
}
