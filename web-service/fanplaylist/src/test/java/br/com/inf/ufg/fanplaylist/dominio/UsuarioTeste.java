package br.com.inf.ufg.fanplaylist.dominio;

import org.junit.Test;

import br.com.inf.ufg.fanplaylist.util.UtilCriptografa;

public class UsuarioTeste {
    
    
    public static Usuario getUsuario() {
        return new Usuario("fanplaylist@com.br", "123456");
    }
    
    @Test
    public void CriarUsuarioTeste() throws Exception {
        Usuario usuario = UsuarioTeste.getUsuario();
        assert(usuario.getTipoUsuario().equals(TipoUsuario.USUARIO));
        assert(UtilCriptografa.decriptografa(usuario.getSenha()).equals("123456"));
    }
}
