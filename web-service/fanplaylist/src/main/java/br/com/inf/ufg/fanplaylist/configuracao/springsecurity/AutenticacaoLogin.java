package br.com.inf.ufg.fanplaylist.configuracao.springsecurity;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.inf.ufg.fanplaylist.dominio.TipoUsuario;


/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Classe que implmenta autentication a ser usada
 * pelas classe de configuracação do framework
 * spring security.
 */
public class AutenticacaoLogin implements Authentication {

    /**Implementa {@link Serializable}.*/
    private static final long serialVersionUID = 1L;

    /** Login passado no parametro do construtor. */
    private final UserDetails login;

    /**
     * Varivel logica que indica se o login passado esta autenticado ou nao.
     */
    private boolean autenticado = true;

    /**
     * @param loginP
   * - Recebe uma classe do tipo login, que por sua vez
   * implementa a interface {@link UserDetails}
     */
    public AutenticacaoLogin(final UserDetails loginP) {
          login = loginP;
    }

    /** @return - Login do usuario passado no construtor. */
    @Override
    public String getName() {
          return login.getUsername();
    }

    /**
     * @return - Colecao de autorizacoes que o usuario
     *  passado tem acesso. Sendo que na mesma e
     *  setada a informa {@link TipoUsuario},
     *  visto que com base nessa informacao sera
     *  restrigido o acesso de determinadas urls de
     *  servicos rest do sistema.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
          return login.getAuthorities();
    }

    /** @return Senha de acesso a aplicacao do usuario. */
    @Override
    public Object getCredentials() {
          return login.getPassword();
    }

    /**
     * @return - Objeto do login que implementa
     * a interface {@link UserDetails}.
     */
    @Override
    public UserDetails getDetails() {
          return login;
    }

    /** @return - Nome de usuario da conta de login. */
    @Override
    public Object getPrincipal() {
          return login.getUsername();
    }

    /** @return - Se usuario encontra-se ou nao autenticado. */
    @Override
    public boolean isAuthenticated() {
          return autenticado;
    }

    /**
     * @param autenticadoP
     * - Caso usuario esteja autoriza, metodo seta
     * variavel interna do servico para indicar
     * que o usuario passado no construtor da
     * classe encontra-se autenticado no sistema.
     */
    @Override
    public void setAuthenticated(final boolean autenticadoP)
                throws IllegalArgumentException {
          autenticado = autenticadoP;
    }
}
