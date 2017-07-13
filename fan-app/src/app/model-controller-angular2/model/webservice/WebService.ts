/**Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo: Classe para definir os
 * padroes de requisicoes GET/POST
*/

import { Dto } from '../dto/Dto';
import { UsuarioDTO } from '../dto/UsuarioDTO';
import { Controller } from '../../controller/Controller';
import { DnsWebService } from './DnsWebService';
import { Endereco } from './Endereco';
import { Injectable } from '@angular/core';
import { Http, Headers,  Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class WebService {

    //Injeta dependecia http
    //Do angular.
    constructor(private http: Http) {
    }

    //Realiza a requisicao do tipo GET no servidor.
    requesicaoGet(aplicacao: boolean,
        endereco: string,
        controller: Controller,
        ...parametros: string[]): void {
        this.http.get(DnsWebService.dns +
            endereco + (parametros.length > 0 ? this.trataParametrosGet(parametros) : ''),
            { headers: this.getHeaderGet(aplicacao) })
            .subscribe(controller);
    }

   /**Requisicao a ser usada quando os parametos
    * do Get se econtram em um vetor de string.
   */
   requesicaoGetVetorString(aplicacao: boolean,
        endereco: string,
        controller: Controller,
        parametros: string[]): void {
        this.http.get(DnsWebService.dns +
            endereco + (parametros.length > 0 ? this.trataParametrosGet(parametros) : ''),
            { headers: this.getHeaderGet(aplicacao) })
            .subscribe(controller);
    }

    //Realiza a requisicao do tipo get e devolve observer
    requesicaoGetObs(aplicacao: boolean,
        endereco: string,
         ...parametros: string[]): Observable<Response> {
        return this.http.get(DnsWebService.dns +
            endereco + (parametros.length > 0 ? this.trataParametrosGet(parametros) : ''),
            { headers: this.getHeaderGet(aplicacao) });
    }

    //Realiza a requisicao do tipo POST no servidor.
    requisicaoPost(aplicacao: boolean,
        endereco: string,
        tokenSincronizacao: string,
        dtoParametro: Dto,
        controller: Controller): void {
        this.http.post(
            DnsWebService.dns + endereco,
            JSON.stringify(dtoParametro),
            { headers: this.getHeaderPost(aplicacao, tokenSincronizacao) }
        )
            .subscribe(controller);
    }

    //Realiza a requisicao do tipo POST no servidor.
    //Onde o parametro nao e necessarimente um DTO.
    requisicaoPostNaoDto(aplicacao: boolean,
        endereco: string,
        tokenSincronizacao: string,
        parametro: string,
        controller: Controller): void {
        this.http.post(
            DnsWebService.dns + endereco,
            parametro,
            { headers: this.getHeaderPost(aplicacao, tokenSincronizacao) }
        )
            .subscribe(controller);
    }

    loginAplicacao(): void {
        this.http.post(
            DnsWebService.dns + '/ControllerLogin/login',
            '{"email":"fanplaylist.com","senha":"CE&atutu2a-r"}',
            { headers: this.getHeaderLogin() })
            .subscribe((resp) => {
                localStorage.setItem(DnsWebService.storageTokenAplicacao,
                resp.text());
                console.log(JSON.stringify(resp));
            });
    }

    loginUsuario(login: UsuarioDTO, controller : Controller): void {
        this.http.post(
            DnsWebService.dns + '/ControllerLogin/login',
            JSON.stringify(login),
            { headers: this.getHeaderLogin() })
            .subscribe(controller);
    }

    pegarTokenSincronizacao(aplicacao :boolean ) : Observable<Response> {
        console.log(JSON.stringify(this.http.get(DnsWebService.dns + Endereco.TOKEN_SINCRONIZACAO)));
        return this.http.get(DnsWebService.dns + Endereco.TOKEN_SINCRONIZACAO , { headers: this.getHeaderGet(aplicacao)});
    }

    //MONTA OS Headers DA REQUISICAO DE ACORDO
    //COM A CONFIGURACAO DO LOGIN.
    //E PEGA O TOKEN DE SEGURANCA DE ACORDO COM
    //A INFORMACAO DE SE A REQUEST E DE USO SO SISTEMA OU DE USO DA APLICAO
    private getHeaderGet(aplicacao: boolean): Headers {
        console.log(localStorage.getItem(DnsWebService.storageTokenAplicacao));
        return new Headers({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'auth-token-fanplaylist': localStorage.getItem(aplicacao === true ?
                DnsWebService.storageTokenAplicacao :
                DnsWebService.storageTokenUsuario)
        });
    }

    //MONTA OS Headers DA REQUIDICAO DE ACORDO
    //COM A CONFIGURACAO DO LOGIN.
    private getHeaderPost(aplicacao: boolean, tokenSincronizacao: string): Headers {
        return new Headers({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'auth-token-fanplaylist': localStorage.getItem(aplicacao === true ?
                DnsWebService.storageTokenAplicacao :
                DnsWebService.storageTokenUsuario),
            'sincronizacao-header': tokenSincronizacao
        });
    }

    //MONTA OS Headers DA REQUISICAO DE LOGIN
    private getHeaderLogin(): Headers {
        return new Headers({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
    }

    private trataParametrosGet(paramentros: string[]): string {
        let parametro: string = '';
        paramentros.forEach((a) => parametro += '/' + a);
        return parametro;
    }
}
