package br.com.inf.ufg.fanplaylist.configuracao.springsecurity;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.inf.ufg.fanplaylist.dominio.TipoUsuario;
import br.com.inf.ufg.fanplaylist.dominio.Usuario;
import br.com.inf.ufg.fanplaylist.dto.UsuarioDTO;
import br.com.inf.ufg.fanplaylist.repositorio.IUsuarioRepositorio;

/**
 * @author  gabriel
 * @version 1.0.0
 * Classe que tem a implementacao dos filtros de interceptacao de requisicoes
 * http para autenticacao de usuarios no sistema, usando as
 * implementacoes internas do framework SpringSecurity.*/
public class FiltroStatelessLogin
            extends AbstractAuthenticationProcessingFilter {

      /** Servico autenticacao de token. */
      private final ServicoAtenticacaoToken servicoAtenticacaoToken;

      /** Servico de busca na base de dados. */
      private final IUsuarioRepositorio iUsuarioRepositorio;

      /**@param urlFiltro - Url em que o filtro autuara, como este
       * filtro e o filtro da autenticacao a url devera ser a destinada
       * para login na aplicacao.
       * @param servicoAtenticacaoTokenP - Servico que cria e recupera
       * autenticacoes com base no token passado no header das request de login.
       * @param iUsuarioRepositorioP - Servico de acesso a base de dados afim
       * de coferir a autenticidade do nome de usuario passado.
       * @param authenticationManagerP - Gerenciador de autenticacoes do
       * SpringSecurity.*/
      public FiltroStatelessLogin(final String urlFiltro,
                  final ServicoAtenticacaoToken servicoAtenticacaoTokenP,
                  final IUsuarioRepositorio iUsuarioRepositorioP,
                  final AuthenticationManager authenticationManagerP) {
            super(urlFiltro);
            servicoAtenticacaoToken = servicoAtenticacaoTokenP;
            iUsuarioRepositorio = iUsuarioRepositorioP;
            setAuthenticationManager(authenticationManagerP);
      }

      /**@param request - Resquest que sera interceptada pelo filtro.
       * @param response - Response que sera interceptada pelo filtro.
       * @return Resultado do processo de autenticacao que foi analizado
       * pelo gerenciador de autenticacoes do SpringSecurity.*/
      @Override
      public Authentication attemptAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response)
               throws AuthenticationException, IOException, ServletException {
        final UsuarioDTO login = pegaDtoLogin(request);
        final UsernamePasswordAuthenticationToken loginToken = 
                new Usuario(login.getEmail(), login.getSenha())
                .pegaAutenticacaoToken();
        return getAuthenticationManager().authenticate(loginToken);
      }

      /**@param request - Resquest que sera interceptada pelo filtro.
       * @return Conversao em LoginDTO dos bytes da requisicao http.
       * @throws IOException - Possivel erro na desserializacao.*/
      private UsuarioDTO pegaDtoLogin(final HttpServletRequest request) {
            try {
             return new ObjectMapper()
                        .readValue(request.getInputStream(),
                         UsuarioDTO.class);
           } catch (IOException e) {
                System.out.print("\n\n\n\n\n"
              + "LOGIN INPUT VAZIO" + "\n\n\n\\n\n\n");
              UsuarioDTO loginDTO = new UsuarioDTO();
              loginDTO.setEmail("a");
              loginDTO.setSenha("a");
              loginDTO.setTipoUsuario(TipoUsuario.USUARIO);
              return loginDTO;
           }
      }

      /**@param request    - Resquest que sera interceptada pelo filtro.
       * @param response   - Response que sera interceptada pelo filtro.
       * @param chain      - Implementacao de filtro de requisicao.
       * @param authResult - Resultado do processo de autenticacao.
       *
       * @throws IOException - Problema ao acessar o header.
       * @throws ServletException - Problema ao acessar o header.*/
      @Override
      protected void successfulAuthentication(final HttpServletRequest request,
                  final HttpServletResponse response, final FilterChain chain,
                  final Authentication authResult)
                  throws IOException, ServletException {
            final Usuario usuarioAutenticado = iUsuarioRepositorio
                        .findByEmail(authResult.getName()).get();
            final AutenticacaoLogin autenticacaoLogin = new AutenticacaoLogin(
                        usuarioAutenticado);
            servicoAtenticacaoToken.adicionaTokenHeader(response,
                        autenticacaoLogin);
            SecurityContextHolder.getContext()
                                 .setAuthentication(autenticacaoLogin);
      }
}
