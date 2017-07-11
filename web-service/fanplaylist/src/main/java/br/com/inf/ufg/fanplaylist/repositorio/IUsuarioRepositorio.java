package br.com.inf.ufg.fanplaylist.repositorio;

import java.util.Optional;
import br.com.inf.ufg.fanplaylist.dominio.Usuario;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 */
public interface IUsuarioRepositorio extends RepositorioBase<Usuario> {
    
    /**
     * Optional de usúario.
     */
    Optional<Usuario> findByEmail(String email);
}
