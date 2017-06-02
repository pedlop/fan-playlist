import { Component, OnInit } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Discografia } from '../../model/discografia';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-musicas',
  templateUrl: './musicas.component.html',
  styleUrls: ['./musicas.component.css']
})
export class MusicasComponent implements OnInit {

  //public listaMusicas : Discografia[];
  public discografiaCantor : string[];
  private url : string = "../../assets/led/hej-discografia.json";

  public listaMusicas: Array<Discografia> = new Array<Discografia>();

  constructor(public http: Http) {
    /*this.http.get(this.url).map(res => res.json())
    .subscribe(data => {
      //let teste : string = JSON.stringify(data);
      this.listaMusicas = data;
      //console.log(teste);
    });*/

    this.listaMusicas.push(
      {
        "titulo" : "O Céu Explica Tudo",
        "ano" : "2017",
        "imagem" : "../../assets/img/Henrique-e-Juliano-O-Céu-Explica-Tudo.jpg",
        "musicas" : [
          "O Céu Explica Tudo",
          "De trás pra frente",
          "Aquela pessoa"
        ]
      },
      {
          "titulo" : "Novas Historias",
          "ano" : "2015",
          "imagem" : "../../assets/img/HeJ-Novas-Historias.jpg",
          "musicas" : [
            "Na Hora da Raiva",
            "Como É Que a Gente Fica",
            "Ele Quer Ser Eu",
            "Parece Piada",
            "Flor e o Beija-Flor (feat. Marília Mendonça)",
            "Deixa Ela Saber",
            "Brigas de Amor",
            "Só Eu pra Te Amar",
            "Nada Nada",
            "Tudo Virou Saudade",
            "Colecionando Bobo",
            "De Vez Outra Vez",
            "Contar pra Quê",
            "Vida"
          ]
        },
        {
          "titulo" : "Ao Vivo em Brasília",
          "ano" : "2014",
          "imagem" : "https://www.henriqueejuliano.com.br/uploads/cache/discografia/fd6e6c407db03d333334c3061d8f1fa1-370x370.jpg",
          "musicas" : [
            "Gordinho Saliente",
            "Até Você Voltar",
            "Céu Particular",
            "Eu Me Enrosquei de Novo",
            "Faz Tempo",
            "Pra Que Juízo",
            "Quem Ama Sempre Entende",
            "Jesus Apaga a Luz",
            "Sereia",
            "Às Vezes",
            "Cuida Bem Dela",
            "Mudando de Assunto",
            "Estrelas Cadentes",
            "É Fake",
            "Não To Valendo Nada",
            "Eu Chamo Você Volta",
            "Recaídas",
            "Calafrio",
            "Não Tem Dó de Mim",
            "Sem Avisar",
            "Não Fala Meu Nome",
            "Não Sou Caloteiro"
          ]
        })
  
}

        



  ngOnInit() {
  }


}
