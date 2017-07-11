package br.com.inf.ufg.fanplaylist.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.inf.ufg.fanplaylist.dominio.Usuario;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 */
public interface IUsuarioRepositorio extends CrudRepository<Usuario, Long> {
    
    /**
     * 
     */
    Optional<Usuario> findByEmail(String email);
}
