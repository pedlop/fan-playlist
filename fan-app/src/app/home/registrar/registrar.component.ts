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
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {

  public controller: ControllerLogin;
  /* Dados de login.*/
  public usuarioDTO: UsuarioDTO = new UsuarioDTO();

  constructor(private webService: WebService, public router: Router) {
    this.controller = new ControllerLogin();
    this.controller.setFormCdsClienteComponent(this, this.webService,this.router);
  }


  ngOnInit() {
  }

  public cadastrar(): void {
    console.log(this.usuarioDTO);
    if (this.controller.tokenSincronizacaoOK) {
      this.webService.pegarTokenSincronizacao(true).subscribe((rest) => {
        this.controller.tokenSincronizacaoOK = false;
        this.controller.setFormCdsClienteComponent(this, this.webService,this.router);
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
  private component: RegistrarComponent;
  private webservice : WebService;
  public router : Router;

  setFormCdsClienteComponent(f: RegistrarComponent,webservice : WebService,router : Router) {
    this.component = f;
    this.webservice = webservice;
    this.router = router;
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
    localStorage.setItem('emaiUser', this.component.usuarioDTO.email);
    this.router.navigate([''])
  }
}
