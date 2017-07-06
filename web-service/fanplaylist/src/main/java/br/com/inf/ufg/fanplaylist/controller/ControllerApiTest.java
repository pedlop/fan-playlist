package br.com.inf.ufg.fanplaylist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gabriel
 * @version 1.0.0
 * 
 *          Serviço rest para testar funcionamento do WebServer.
 */
@RestController
public class ControllerApiTest {

    /**
     * @return String 'ok' indicando que o web service esta funcionando
     *         corretamente.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ControllerApiTest/ok")
    public String getOk() {
        return "ok";
    }
}
