package br.com.inf.ufg.fanplaylist.configuracao.springsecurity;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;
import io.jsonwebtoken.JwtException;

/**
 * @author  gabriel
 * @version 1.0.0
 * 
 * Filtro de autenticacao que intercepta requisicoes ao servidor afim de
 * conferir se o token e valido e se o usuario tem acesso ao servico.
 */
public class FiltroStatelessAutenticacao extends GenericFilterBean {

     /** Servico de Autenticacao Token.*/
     private final ServicoAtenticacaoToken servicoAtenticacaoToken;

     /**@param servicoAtenticacaoTokenP - Servico com implementacao
      * da validacao de tokens.*/
     public FiltroStatelessAutenticacao(
               final ServicoAtenticacaoToken servicoAtenticacaoTokenP) {
          servicoAtenticacaoToken = servicoAtenticacaoTokenP;
     }

     /**@param requisicao - Requisicao interceptada pelo filtro.
      * @param resposta - Resposta interceptada pelo filtro.
      * @param chain - Implementacao do filtro.*/
     @Override
      public void doFilter(final ServletRequest requisicao,
                  final ServletResponse resposta, final FilterChain chain)
                  throws IOException, ServletException {
            try {
                  Authentication autenticacao = servicoAtenticacaoToken
                          .geraAutenticacaoDaRequest(
                              (HttpServletRequest) requisicao);
                  SecurityContextHolder
                             .getContext()
                             .setAuthentication(autenticacao);
                  chain.doFilter(requisicao, resposta);
            } catch (ExcecaoNegocio e) {
                   ((HttpServletResponse) resposta).sendError(
                           HttpServletResponse.SC_EXPECTATION_FAILED,
                           e.getTituloMensagem()
                           .concat(": ")
                           .concat(e.getDescricaoMensagem()));
            } catch (AuthenticationException | JwtException e) {
                  SecurityContextHolder.clearContext();
                  ((HttpServletResponse) resposta)
                              .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
      }

}
