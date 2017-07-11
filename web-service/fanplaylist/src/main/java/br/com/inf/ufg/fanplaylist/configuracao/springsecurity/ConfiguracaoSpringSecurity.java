package br.com.inf.ufg.fanplaylist.configuracao.springsecurity;
/**@author gabriel
 *@version 1.0.0*/

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.inf.ufg.fanplaylist.dominio.Usuario;
import br.com.inf.ufg.fanplaylist.repositorio.IUsuarioRepositorio;

/**
 * Classe que configura o acesso aos servicos rest do sistema. Sendo que este
 * controle de acesso aos servicos nao tem relacacao com o controle de acesso da
 * aplicacao, o controle que este componente configura se da pela autenticacao
 * do usuario com o token sendo que este deve passar o login e senha
 * {@link Login} e pegar ser token caso a senha e o login passado forem validos.
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class ConfiguracaoSpringSecurity extends WebSecurityConfigurerAdapter
                implements UserDetailsService {

        /** Servico de acesso ao repositorio. */
        @Autowired
        private IUsuarioRepositorio iUsuarioRepositorio;

        /** Servico de autenticacao de tokens. */
        @Autowired
        private ServicoAtenticacaoToken servicoAtenticacaoToken;

        /**Construtor padrao do cotainer que implementa
         * as regras de seguranca e intercepta as requisicoes
         * enviada ao servidor.*/
        public ConfiguracaoSpringSecurity() {
                super(true);
        }

        /**
         * Implementacao das interceptacoes das url(s).
         *
         * @param http
         *            - Requisicao HTTP.
         * @throws Exception
         *             - Excecao no processo de interceptacao da request http.
         */
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
           http.csrf().disable();

           http.cors()
               .and().exceptionHandling()
               .and().anonymous()
               .and().servletApi()
               .and().headers()
               .cacheControl();

           http.addFilterBefore(
                  new FiltroStatelessLogin("/api/login",
                                    servicoAtenticacaoToken,
                                    iUsuarioRepositorio,
                                    authenticationManager()),
                            UsernamePasswordAuthenticationFilter.class);

            http.addFilterBefore(
                 new FiltroStatelessAutenticacao(
                      servicoAtenticacaoToken),
                 UsernamePasswordAuthenticationFilter.class);
        }

        /** Container do gerenciador de autenticacoes do SpringSecurity.
         * @throws Exception - Excecao qualquer no autenticador de usuarios.*/
        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean()
                                                    throws Exception {
                return super.authenticationManagerBean();
        }

        /**
         * Configura o provedor padrao de criptografia para comparar as senhas
         * passadas pelo servico de autenticacao com a salva no banco se a ambas
         * depois de criptogradas forem identicas o framework libera o acesso.
         */
        @Override
        protected void configure(final AuthenticationManagerBuilder auth)
                        throws Exception {
                auth.userDetailsService(this)
                                .passwordEncoder(new BCryptPasswordEncoder());
        }

        /**
         * @return Classe que implementa a interface padrao de busca na base de
         *         dados de algum usuario que deseja se autenticar no sistema.
         */
        @Override
        protected UserDetailsService userDetailsService() {
                return this;
        }

        /**
         * @return Busca usuario de acordo com o nome de usuario passado.
         * @throws UsernameNotFoundException
         *             - Excecao padrao para quando o framework nao encontrar na
         *             base de dados algum usuario com o nome de login passado.
         */
        @Override
        public UserDetails loadUserByUsername(final String arg0)
                        throws UsernameNotFoundException {
                Optional<Usuario> login = iUsuarioRepositorio.findByEmail(arg0);
                final AccountStatusUserDetailsChecker detailsChecker
                                        = new AccountStatusUserDetailsChecker();
                login.ifPresent(detailsChecker::check);
                return login.orElseThrow(
                                () -> new UsernameNotFoundException(
                                          "Usuario nao encontrado"));
        }
}
