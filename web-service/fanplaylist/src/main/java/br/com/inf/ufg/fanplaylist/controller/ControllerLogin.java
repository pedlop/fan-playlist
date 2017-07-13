package br.com.inf.ufg.fanplaylist.controller;

/**@author lucas
 * @version 1.0.0*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf.ufg.fanplaylist.configuracao.springsecurity.GeradorToken;
import br.com.inf.ufg.fanplaylist.dominio.Usuario;
import br.com.inf.ufg.fanplaylist.dto.UsuarioDTO;
import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;

/**
 * Classe com o objetivo de buscas as empresas no qual o o login de usuario esta
 * associado.
 */
@RestController
public class ControllerLogin {

    /**
     * Servico para geracao de tokens de seguranca.
     */
    @Autowired(required = true)
    private GeradorToken geradorToken;
	

    /**
     * @param pessoaServicoP
     *            - Injecao das dependencias do controller.
     */
    @Autowired
    public ControllerLogin() {
    }


    /**
     * @param loginDTO
     *            - Classe de serializacao do login. Metodo alternativativo para
     *            buscar o token de seguranca auth-token-equilibrio.
     * @throws ExcecaoNegocio
     *             - Excecoes de negocio que podem ocorrer durante o processo de
     *             busca do login desejado.
     * @return Token de seguranca valido po 24h.
     */
    @RequestMapping(method = RequestMethod.POST,
            value = "/ControllerLogin/login")
    public String login(@RequestBody final UsuarioDTO usuarioDTO)
            throws ExcecaoNegocio {
    	Usuario usuario = new Usuario(usuarioDTO.getEmail(), usuarioDTO.getSenha());
        return geradorToken.criaTokenLogin(usuario);
    }
}
