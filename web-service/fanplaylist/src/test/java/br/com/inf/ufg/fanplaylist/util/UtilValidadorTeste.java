package br.com.inf.ufg.fanplaylist.util;

import org.junit.Test;

public class UtilValidadorTeste {

	@Test
	public void testeNulo() {
		Object o = null;
	    assert(UtilValidador.eNulo(o));
	}
	
	@Test
	public void casoNuloDevolveStringVazia() {
		String textoNulo = null;
		assert("".equals(UtilValidador.casoNuloRetornaStringVazia(textoNulo)));
	}


}
