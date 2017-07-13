package br.com.inf.ufg.fanplaylist.dto;

import org.junit.Test;

import br.com.inf.ufg.fanplaylist.dominio.UsuarioTeste;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Teste de usuario dto teste.
 */
public class UsuarioDTOTeste {
    
    @Test
    public void converteDominioDTO() throws Exception {
        UsuarioDTO dto = UsuarioDTO.converteDominioDto(UsuarioTeste.getUsuario());
        DtoTeste.serializaDesserializaObjetos(dto);
        assert(true);
    }
}
