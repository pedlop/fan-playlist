package br.com.inf.ufg.fanplaylist.dominio;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.inf.ufg.fanplaylist.excecao.NumeroErro;
import br.com.inf.ufg.fanplaylist.util.UtilCriptografa;
import br.com.inf.ufg.fanplaylist.util.ValidadorString;



/**
 * 
 * @author gabriel
 * @see Entidade, {@link UserDetails}
 * @version 1.0.0
 * 
 *          Classe com objetivo de implementar padrão de login para usúario bem
 *          como estratégia de autenticação via token, visto que usamos
 *          SpringSecurity para auxílio na parte de segurança.
 */
@Entity
public class Usuario extends Entidade implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Email do usúrio que poderá vir de outra rede social
     * que o mesmo já seja vinculado.
     */
    @Column(length = ConstanteString.EMAIL)
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_10,
            tituloMensagem = "Usuario10.titulo",
            descricaoMensagem = "Usuario10.mensagem",
            nomeClasse = "Usuario",
            logicaRegex = true,
            regex = ConstanteString.EMAIL_REGEX,
            tamanho = ConstanteString.EMAIL)
    private String email;

    /**
     * Senha para acesso da conta.
     */
    @Column(columnDefinition = "text")
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_20,
            tituloMensagem = "Usuario20.titulo",
            descricaoMensagem = "Usuario20.mensagem",
            nomeClasse = "Usuario",
            regex = ConstanteString.SEM_REGEX,
            tamanho = ConstanteString.TEXTO30)
    private String senha;
    
    /** Tipo de usuario. */
    @Enumerated(EnumType.STRING)
    @Column
    private TipoUsuario tipoUsuario;
    
    /**
     * Default constructor entity.
     */
    public Usuario(){}


    /**
     * 
     * @param email - Email do usúario.
     * @param senha - Senha do usúario.
     */
    public Usuario(String email, String senha) {
        super();
        this.email = email;
        //CRIPTGRAFA A SENHA DO USUARIO
        this.senha = UtilCriptografa.criptografa(senha);
        this.tipoUsuario = TipoUsuario.USUARIO;
    }
    
    /**
     * 
     * @param email - Email do usúario admin.
     * @param senha - Senha do usúario admin.
     * @return Login de usúario do tipo ADMIN.
     */
    public static Usuario criaAdmin(String email, String senha) {
        Usuario u = new Usuario(email, senha);
        u.tipoUsuario = TipoUsuario.ADMIN;
        return u;
    }

    /**
     * 
     * @param email - Email do usúario admin.
     * @param senha - Senha do usúario admin.
     * @return Login de usúario do tipo ADMIN.
     */
    public static Usuario criaApp(String email, String senha) {
        Usuario u = new Usuario(email, senha);
        u.tipoUsuario = TipoUsuario.APP;
        return u;
    }
    
    public Usuario autualizarUsuario(Usuario u, String email, String senha) {
        Usuario novoUsuario = new Usuario(email, senha);
        novoUsuario.tipoUsuario = u.tipoUsuario;
        novoUsuario.id = u.id;
        return novoUsuario;
    }
    /**
     * 
     * @return Email associada ao login.
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @return Senha associado ao login.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * 
     * @return Nível de acesso.
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Implementação da interface UserDetails.
     */
    /**
     * @return - Retorna a lista de credenciais disponiveis para o usuario de
     *         acordo com o nivel de acesso do usuario. Visto que o tipo de
     *         usuario que definira quao o nivel de acesso do mesmo. Retorna o
     *         valor ordinal da constantes com o prefixo padrao do
     *         SpringSecurity, sendo que para o framework funcionar devera ser
     *         passado o nome de usuario com o prefixo padrao ROLE_.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final String roleUser = tipoUsuario.getTipoUsuario();
        return Collections.singleton(() -> "ROLE_" + roleUser);
    }

    /** @return - Retorna a senha usada para autenticar o usuario. */
    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder()
                .encode(UtilCriptografa.decriptografa(senha));
    }

    /** @return - Retorna o login do usuario. */
    @Override
    public String getUsername() {
        return email;
    }

    /** @return - Retorna se a conta do usuario expirou. */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /** @return - Retorna se a conta do usuario esta bloqueada. */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /** @return - Retorna se a senha do usuario esta expirada. */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** @return - Retorna se o usuario esta habilitado ou nao. */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    /**
     * @return - Gera objeto de autenticacao que sera avaliado pelo gerenciador
     *         de autenticacoes no filtro de autenticacao.
     */
    public UsernamePasswordAuthenticationToken pegaAutenticacaoToken() {
        return new UsernamePasswordAuthenticationToken(email,
                UtilCriptografa.decriptografa(senha), getAuthorities());

    }
}
