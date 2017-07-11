package br.com.inf.ufg.fanplaylist.dominio;

/**
 * 
 * @author gabriel
 * @version 1.0.0
 * 
 * Enum para classificar logins de acordo com o nível
 * de acesso e assim poder serparar administadores do
 * sistema de usúario comuns a aplicação.
 */
public enum TipoUsuario {
    
    /**
     * Usúario final da aplicação.
     */
    USUARIO("1"),
    
    /**
     * Usúario com nível de acesso de administrador.
     */
    ADMIN("2");
    
    /**Guarda valor ordinal das constantes.*/
    private String tipoUsuario;

    /**@param valor - Cria nova Constante.*/
    TipoUsuario(final String valor) {
      tipoUsuario = valor;
    }

    /**@return Retorna valor ordinal das constantes.*/
    public String getTipoUsuario() {
      return tipoUsuario;
    }
    
    /**
     * Constantes de uso do Framework seguindo notação
     * @Secured.
     */
    public static final String USUARIO_S = "ROLE_1";
    
    public static final String ADMIN_S = "ROLE_2";
}
