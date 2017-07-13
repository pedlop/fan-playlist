package br.com.inf.ufg.fanplaylist.repositorio;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.inf.ufg.fanplaylist.dominio.UsuarioTeste;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Classe de teste do repositorio de usuario.
 */
public class UsuarioRepositorioTeste extends RepositorioTeste {
    
    @Autowired
    private IUsuarioRepositorio iUsuarioRepositorio;
    
    @Test
    public void testePersistenciaUsuario() throws Exception {
        iUsuarioRepositorio.save(UsuarioTeste.getUsuario());
        assert(true);
    }
    
    @Test
    public void testFindByEmail() throws Exception {
        testEntityManager.persistAndFlush(UsuarioTeste.getUsuario());
        String email = UsuarioTeste.getUsuario().getEmail();
        assert(iUsuarioRepositorio.findByEmail(email).isPresent());
    }
}
