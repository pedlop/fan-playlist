package br.com.inf.ufg.fanplaylist.dto;

import br.com.inf.ufg.fanplaylist.dominio.TipoUsuario;
import br.com.inf.ufg.fanplaylist.dominio.Usuario;
import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Classe para a serializacao da entidade {@link Usuario}.
 */
public class UsuarioDTO extends ExcecaoNegocio implements PadraoRetorno<Usuario> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Campos do usuario.
     */
    
    private String email;
    
    private String senha;

    private TipoUsuario tipoUsuario;
    
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }


    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioDTO() {
        super();
    }


    public UsuarioDTO(ExcecaoNegocio e, String controllerP) {
        super(e, controllerP);
    }


    public UsuarioDTO(int codigoErroP, String nomeClasseP,
            String tituloMensagemP, String descricaoMensagemP,
            String... concatena) {
        super(codigoErroP, nomeClasseP, tituloMensagemP, descricaoMensagemP, concatena);
        // TODO Auto-generated constructor stub
    }


    /**
     * @param dominio
     *            - Objeto da camada de dominio que devera ser convertido em seu
     *            respectivo DTO.
     *
     * @return - DTO a partir do dominio.
     */
    static public UsuarioDTO converteDominioDto(Usuario dominio) {
        UsuarioDTO u = new UsuarioDTO();
            u.email = dominio.getEmail();
            u.tipoUsuario = dominio.getTipoUsuario();
        return u;
    }

    
    @Override
    public PadraoRetorno<Usuario> criaDTO() {
        return new UsuarioDTO();
    }

    @Override
    public PadraoRetorno<Usuario> converteExcecaoNegocio(ExcecaoNegocio excecao,
            String controllerP) {
        return new UsuarioDTO(excecao, controllerP);
    }
}
