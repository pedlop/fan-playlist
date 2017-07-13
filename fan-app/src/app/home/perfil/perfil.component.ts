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
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  public controller: ControllerLogin;
  /* Dados de login.*/
  public usuarioDTO: UsuarioDTO = new UsuarioDTO();

  public senhaCorfim : string = '';

  constructor(private webService: WebService, public router: Router) {
    this.controller = new ControllerLogin();
    this.controller.setFormCdsClienteComponent(this, this.webService);
  }


  ngOnInit() {
    this.webService.requesicaoGetObs(true, Endereco.FIND_BY_EMAIL, localStorage.getItem('emaiUser'))
                  .subscribe(resp => {
       if (!resp.text() != null){
         this.usuarioDTO = JSON.parse(resp.text());
       } else {
         alert('Por gentileza realize o login');
       }
    });
  }

  public atualizar(): void {
    console.log(this.usuarioDTO);
    if (this.senhaCorfim.localeCompare(this.usuarioDTO.senha) === 0) {
      alert('Por favor digite as senhas que confirmem');
    }
    if (this.controller.tokenSincronizacaoOK) {
      this.webService.pegarTokenSincronizacao(true).subscribe((rest) => {
        this.controller.tokenSincronizacaoOK = false;
        this.controller.setFormCdsClienteComponent(this, this.webService);
        this.webService.requisicaoPost(true,
            Endereco.ATUALIZAR_USUARIO, rest.text(),
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
  private component: PerfilComponent;
  private webservice : WebService;

  setFormCdsClienteComponent(f: PerfilComponent,webservice : WebService) {
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
    alert('Senha atualizada com sucesso!');
  }
}
