package br.com.inf.ufg.fanplaylist.repositorio;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import br.com.inf.ufg.fanplaylist.dominio.Musica;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 */
public class IMusicaRepositorioTeste extends RepositorioTeste {
	
	@Autowired
	private IMusicaRepositorio iMusicaRepositorio;
	
	@Test
	public void testaPersistenciaMusica() throws Exception {
		List<Musica> lista = this.criaListaMusicas(
				"Não Passa Vontade",
				"Meu Amor",
				"De Trás Pra Frente",
				"Vidinha de Balada",
				"O Céu Explica Tudo",
				"Aquela pessoa",
				"Modo Sofrimento",
				"Bebida com Saudade",
				"Maquiagem Não Disfarça",
				"Tinta de Amor",
				"Faz do Seu Jeito",
				"Na Boa",
				"Zé Ruela",
				"Vem Pra Minha Vida",
				"5KM",
				"Mais Amor e Menos Drama",
				"Amor Não é Só Love",
				"3 Horas de Motel",
				"Esse Filho é Meu");
		iMusicaRepositorio.save(lista);
	}
	
	private List<Musica> criaListaMusicas(String...musicas) {
		List<Musica> lista = new ArrayList<>();
		for (int i  = 0; i < musicas.length; i++) {
			lista.add(new Musica(musicas[i]));
		}
		return lista;
	}
}
