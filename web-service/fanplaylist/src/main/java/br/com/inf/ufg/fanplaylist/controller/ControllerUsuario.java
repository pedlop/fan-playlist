package br.com.inf.ufg.fanplaylist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf.ufg.fanplaylist.dominio.TipoUsuario;
import br.com.inf.ufg.fanplaylist.dominio.Usuario;
import br.com.inf.ufg.fanplaylist.dto.UsuarioDTO;
import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;
import br.com.inf.ufg.fanplaylist.repositorio.IUsuarioRepositorio;
import br.com.inf.ufg.fanplaylist.util.UtilValidador;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Controller para as operações que envolvem os usúarios
 * da aplicação administradores ou não.
 */
@RestController
public class ControllerUsuario {
    
    private IUsuarioRepositorio iUsuarioRepositorio;
    
    @Autowired
    public ControllerUsuario(IUsuarioRepositorio iUsuarioRepositorio) {
        
        /**
         * Cria usuario padrao de uso da aplicação.
         */
        if (!iUsuarioRepositorio.findByEmail("fanplaylist.com").isPresent()) {
            Usuario usuario = Usuario.criaApp("fanplaylist.com", "CE&atutu2a-r");
            iUsuarioRepositorio.save(usuario);
        }
        
    }
    
    @Secured({
        TipoUsuario.ADMIN_S,
        TipoUsuario.APP_S
    })
    @RequestMapping(method = RequestMethod.POST,
    value = "/ControllerUsuario/salvarUsuario")
    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = new Usuario(usuarioDTO.getEmail(),
                    usuarioDTO.getSenha());
            iUsuarioRepositorio.save(usuario);
            UtilValidador.validaObjetos(usuario);
            return usuarioDTO;
        } catch (ExcecaoNegocio e) {
            return new UsuarioDTO(e, "ControllerUsuario");
        }
    }
    
    @Secured({
        TipoUsuario.ADMIN_S,
        TipoUsuario.APP_S
    })
    @RequestMapping(method = RequestMethod.POST,
    value = "/ControllerUsuario/atualizarUsuario")
    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO) {
        try {
            Optional<Usuario> opUsuario = iUsuarioRepositorio.findById(usuarioDTO.getId());
            //CASO NAO ENCONTRE USUARIO NAO LANCA EXCECAO
            //POR QUESTOES DE SEGURANÇA.
            if (opUsuario.isPresent()) {
                Usuario novoUsuario = opUsuario.get().autualizarUsuario(opUsuario.get(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
                UtilValidador.validaObjetos(novoUsuario);
                iUsuarioRepositorio.save(novoUsuario);
            }
            return usuarioDTO;
        } catch (ExcecaoNegocio e) {
            return new UsuarioDTO(e, "ControllerUsuario");
        }
    }
    
    @Secured({ TipoUsuario.ADMIN_S, TipoUsuario.APP_S })
    @RequestMapping(method = RequestMethod.POST,
            value = "/ControllerUsuario/deletarUsuario")
    public UsuarioDTO detetarUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> opUsuario = iUsuarioRepositorio
                .findById(usuarioDTO.getId());
        // CASO NAO ENCONTRE USUARIO NAO LANCA EXCECAO
        // POR QUESTOES DE SEGURANÇA.
        if (opUsuario.isPresent()) {
            iUsuarioRepositorio.delete(opUsuario.get());
        }
        return usuarioDTO;
    }
 
}
