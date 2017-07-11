package br.com.inf.ufg.fanplaylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf.ufg.fanplaylist.configuracao.springsecurity.FiltroStatelessAutenticacao;
import br.com.inf.ufg.fanplaylist.configuracao.springsecurity.GerenciadorTokenSincronizacao;
import br.com.inf.ufg.fanplaylist.configuracao.springsecurity.ServicoAtenticacaoToken;
import br.com.inf.ufg.fanplaylist.dominio.TipoUsuario;

/**
 * @author  gabriel
 * @version 1.0.0
 * Controller de token de sincronizacao.
 * Aplicacao cliente devera pegar o token de
 * sincronizacao para efetuar operacoes de POST
 * no servidor. Vide {@link GerenciadorTokenSincronizacao},
 * {@link FiltroStatelessAutenticacao} e {@link ServicoAtenticacaoToken}.*/
@Secured({
    TipoUsuario.USUARIO_S,
    TipoUsuario.ADMIN_S,
    TipoUsuario.APP_S})
@RestController
@RequestMapping("/api/token/sincronizacao")
public class ControllerTokenSincronizacao {

    /**Gerenciador de tokens de sincronizacao.
     * Objeto singleton que controla a emissao de tokens
     * bem como as operacoes de deletar e confirir tokens.
     * Ver {@link GerenciadorTokenSincronizacao}.*/
     private GerenciadorTokenSincronizacao tokenSinconizacao;

    /**@param tokenSinconizacaoP - Injeta instancia de
     * {@link GerenciadorTokenSincronizacao}.*/
    @Autowired
    public ControllerTokenSincronizacao(final
             GerenciadorTokenSincronizacao tokenSinconizacaoP) {
       tokenSinconizacao = tokenSinconizacaoP;
    }

    /**@return - Retorna para cliente o token de sincronizacao gerado.
     * a partir do {@link GerenciadorTokenSincronizacao}*/
    @RequestMapping(method = RequestMethod.GET)
    public String getTokenSincronizacao() {
       return tokenSinconizacao.geraTokenSincronizacao();
    }
}
