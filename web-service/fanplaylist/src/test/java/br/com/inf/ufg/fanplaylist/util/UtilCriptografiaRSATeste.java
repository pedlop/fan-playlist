package br.com.inf.ufg.fanplaylist.util;

import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.junit.Test;

public class UtilCriptografiaRSATeste {
    
	@Test
	public void testaCriptografiaMenor117() throws IllegalBlockSizeException, BadPaddingException {
		String textoMenor117 = "AAAAAAAAAAAAAAAAAAAA";
		byte[] arrayCriptografado = UtilCriptografiaRSA.criptografaString(textoMenor117);
		assert(textoMenor117.equals(UtilCriptografiaRSA.decriptografaString(arrayCriptografado)));
	}
	
	@Test
	public void testaCriptogrfiaBloco117() throws IllegalBlockSizeException, BadPaddingException {
       String texto117Caracteres = "";
       for(int i = 0; i < 117; i++) {
    	   texto117Caracteres += "A";
       }
       byte[] arrayCriptografado = UtilCriptografiaRSA.criptografaString(texto117Caracteres);
       assert(texto117Caracteres.equals(UtilCriptografiaRSA.decriptografaString(arrayCriptografado)));
   }
   
   @Test
   public void testaCriptografiaTextoMairoes() throws IllegalBlockSizeException, BadPaddingException {
	   String texto = UUID.randomUUID().toString();
	   for(int i = 0; i < 1000; i++ ){
		   texto = UUID.randomUUID().toString();
	   }
	   byte[] arrayCriptografado = UtilCriptografiaRSA.criptografaString(texto);
	   assert(texto.equals(UtilCriptografiaRSA.decriptografaString(arrayCriptografado)));
   }
}