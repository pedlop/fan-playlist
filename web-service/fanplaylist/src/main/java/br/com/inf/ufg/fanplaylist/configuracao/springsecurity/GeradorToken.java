package br.com.inf.ufg.fanplaylist.configuracao.springsecurity;

/**@author gabriel
 * @version 1.0.0*/

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.inf.ufg.fanplaylist.dominio.Usuario;
import br.com.inf.ufg.fanplaylist.repositorio.IUsuarioRepositorio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**Servico para a manipulacao de usuarios com tokens,
 * geracao de tokens e autenticacao a partir de token
 * para login.*/
@Component
@Scope("singleton")
public class GeradorToken {

    /**Chave ou senha usada como base para alimentar algoritmo
     * de critografia.*/
     private final String chave;

    /**Servico de acesso a base de dados, para conferir existencia
    * de determinado login.*/
    private IUsuarioRepositorio iUsuarioRepositorio;

    /**@param iUsuarioRepositorioP - Repositorio da entidade Login.*/
    @Autowired
    public GeradorToken(final IUsuarioRepositorio iUsuarioRepositorioP) {
         iUsuarioRepositorio = iUsuarioRepositorioP;
         chave = UUID.randomUUID().toString();
    }

   /**@param token - Token registrado e gerado outrora pela
   * aplicacao.
   * @return Sendo que login implementa a interface */
   public Optional<Usuario> converteTokenParaLogin(final String token) {
      String login = Jwts.parser()
                         .setSigningKey(chave)
                         .parseClaimsJws(token)
                         .getBody()
                         .getSubject();
      return iUsuarioRepositorio.findByEmail(login);
   }

   /**@param login - Login autenticado, que servira para a cricao de token.
    * @return token gerado a partir do login autenticado, cada token
    * gerado ficara valido somente para o prazo de um dia.*/
   public String criaTokenLogin(final UserDetails login) {
      final ZonedDateTime validoPorUmDia
                                    = ZonedDateTime.now().plusDays(1);
      return Jwts.builder()
                 .setSubject(login.getUsername())
                 .signWith(SignatureAlgorithm.HS256, chave)
                 .setExpiration(Date.from(validoPorUmDia.toInstant()))
                 .compact();
   }
}
