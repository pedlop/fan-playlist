package br.com.inf.ufg.fanplaylist.util;


import java.util.UUID;
import org.junit.Test;

public class UtilCriptografiaTeste {

	@Test
	public void criptografaDecriptografa() {
		String conteudo = UUID.randomUUID().toString();
		String criptografado = UtilCriptografa.criptografa(conteudo);
		assert(conteudo.equals(UtilCriptografa.decriptografa(criptografado)));
	}

}
