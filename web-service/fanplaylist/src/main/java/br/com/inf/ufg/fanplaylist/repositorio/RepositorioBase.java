package br.com.inf.ufg.fanplaylist.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.inf.ufg.fanplaylist.dominio.Entidade;

/** @author  gabriel
 *  @version 1.0.0
 */
public interface RepositorioBase<T extends Entidade> extends CrudRepository<T, Long>{
    
    Optional<T> findById(Long id);
}
