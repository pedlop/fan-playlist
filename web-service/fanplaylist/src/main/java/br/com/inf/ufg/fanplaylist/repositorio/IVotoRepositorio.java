package br.com.inf.ufg.fanplaylist.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.inf.ufg.fanplaylist.dominio.Voto;

public interface IVotoRepositorio extends CrudRepository<Voto, Long> {

}
