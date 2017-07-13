import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Controller } from '../../model-controller-angular2/controller/Controller';
import { WebService } from '../../model-controller-angular2/model/webservice/WebService';
import { ExcecaoNegocio } from '../../model-controller-angular2/model/excecao/ExcecaoNegocio';
import { Dto } from '../../model-controller-angular2/model/dto/Dto';
import { UsuarioDTO } from '../../model-controller-angular2/model/dto/UsuarioDTO';
import { DnsWebService } from '../../model-controller-angular2/model/webservice/DnsWebService';
import { Endereco } from '../../model-controller-angular2/model/webservice/Endereco';

@Component({
  selector: 'app-login',
  providers: [WebService],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public controller: ControllerLogin;
  /* Dados de login.*/
  public usuarioDTO: UsuarioDTO = new UsuarioDTO();

  constructor(private webService: WebService, public router: Router) {
    this.controller = new ControllerLogin();
    this.controller.setFormCdsClienteComponent(this, this.webService);
  }

  ngOnInit() {
  }

  public logar(): void {
    console.log(this.usuarioDTO);
    if (this.controller.tokenSincronizacaoOK) {
      this.webService.pegarTokenSincronizacao(true).subscribe((rest) => {
        this.controller.tokenSincronizacaoOK = false;
        this.controller.setFormCdsClienteComponent(this, this.webService);
        this.webService.requisicaoPost(true,
            Endereco.CADASTRAR_USUARIO, rest.text(),
            this.usuarioDTO, this.controller);
      },
        (erro: any) => {
          this.controller.tokenSincronizacaoOK = true;
        });
    }
  }

}

class ControllerLogin extends Controller {
  public tokenSincronizacaoOK = true;
  private component: LoginComponent;
  private webservice : WebService;

  setFormCdsClienteComponent(f: LoginComponent,webservice : WebService) {
    this.component = f;
    this.webservice = webservice;
  }

  casoErroRede(): void {
    this.tokenSincronizacaoOK = true;
  }

  casoErroNegocio(): void {
    const erro: ExcecaoNegocio = JSON.parse(this.response.text());
    this.tokenSincronizacaoOK = true;
    console.log('ERRO DE NEGOCIO. ' + erro.codigoErro + '/' + erro.nomeClasse + '/' + erro.controller);
  }

  casoComunicacaoCompleta(): void {
    this.tokenSincronizacaoOK = true;
    /*Guarda o token de seguranca do usuario na memoria.*/
    localStorage.setItem(DnsWebService.storageTokenUsuario,
    this.response.text());
  }
}
