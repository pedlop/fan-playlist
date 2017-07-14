package br.com.inf.ufg.fanplaylist.repositorio;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.inf.ufg.fanplaylist.dominio.Musica;

public interface IMusicaRepositorio extends CrudRepository<Musica, Long> {
	
	Optional<Musica> findById(Long id);
	
	Collection<Musica> findAll();
}
