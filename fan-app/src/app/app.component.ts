import { Component } from '@angular/core';
import { WebService } from './model-controller-angular2/model/webservice/WebService';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ WebService ]
})
export class AppComponent {
  title = 'app works!';

  constructor(private webService: WebService) {
    /** Busca o token de seguranca da aplicacao.*/
    /**Realiza o login da aplicacao afim de pegar o token de
    e guardar no storage do angular, para poder realizar as operecoes
    que sao somente de aplicacao.*/
    webService.loginAplicacao();
  }
}
