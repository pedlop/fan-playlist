import { Component, OnInit } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Discografia } from '../../../model/discografia';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { TypeaheadMatch } from 'ngx-bootstrap/typeahead';
import {SelectItem} from 'primeng/primeng';
import {SelectButtonModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';

@Component({
  selector: 'app-musicas',
  templateUrl: './musicas.component.html',
  styleUrls: ['./musicas.component.css']
})
export class MusicasComponent implements OnInit {

  cities: SelectItem[];

  selectedCity: string;

  selectedCities: string[];

  cars: SelectItem[];
  selectedCar: string;


  selectedCategories: string[] = ['Technology', 'Sports'];

  checked: boolean = false;
  checked2: boolean = false;

  //public listaMusicas : Discografia[];
  public discografiaCantor : string[];
  private url : string = "../../assets/led/hej-discografia.json";

  musics: SelectItem[];
  selectedMusic: string;
  public listaMusicas: Array<Discografia> = new Array<Discografia>();

  public asyncSelected: string;
  public typeaheadLoading: boolean;
  public typeaheadNoResults: boolean;
  public dataSource: Observable<any>;
  public statesComplex: any[] = [
    {id: 1, artista: 'Marilia Mendonça', show: 'Pecuária de Goiânia'},
    {id: 2, artista: 'Gusttavo Lima', show: 'Pecuária de Goiânia'},
    {id: 3, artista: 'Zé Neto e Cristiano', show: 'Pecuária de Goiânia'},
    {id: 4, artista: 'Maiara e Maraisa', show: 'Pecuária de Goiânia'},
    {id: 5, artista: 'Henrique e Juliano', show: 'Pecuária de Goiânia'}   
    ];

  constructor(public http: Http) {
    /*this.http.get(this.url).map(res => res.json())
    .subscribe(data => {
      //let teste : string = JSON.stringify(data);
      this.listaMusicas = data;
      //console.log(teste);
    });*/
    this.dataSource = Observable
      .create((observer: any) => {
        // Runs on every search
        observer.next(this.asyncSelected);
      })
      .mergeMap((token: string) => this.getStatesAsObservable(token));

      this.cities = [];
        this.cities.push({label:'New York', value:'New York'});
        this.cities.push({label:'Rome', value:'Rome'});
        this.cities.push({label:'London', value:'London'});
        this.cities.push({label:'Istanbul', value:'Istanbul'});
        this.cities.push({label:'Paris', value:'Paris'});

      this.cars = [];
        this.cars.push({label: 'Audi', value: 'Audi'});
        this.cars.push({label: 'BMW', value: 'BMW'});
        this.cars.push({label: 'Fiat', value: 'Fiat'});
        this.cars.push({label: 'Ford', value: 'Ford'});
        this.cars.push({label: 'Honda', value: 'Honda'});
        this.cars.push({label: 'Jaguar', value: 'Jaguar'});
        this.cars.push({label: 'Mercedes', value: 'Mercedes'});
        this.cars.push({label: 'Renault', value: 'Renault'});
        this.cars.push({label: 'VW', value: 'VW'});
        this.cars.push({label: 'Volvo', value: 'Volvo'});

      this.musics = [];
      this.musics.push({label: 'Não Passa Vontade', value: 'Não Passa Vontade'});
      this.musics.push({label: 'Meu Amor', value: 'Meu Amor'});
      this.musics.push({label: 'De Trás Pra Frente', value: 'De Trás Pra Frente'});
      this.musics.push({label: 'Vidinha de Balada', value: 'Vidinha de Balada'});
      this.musics.push({label: 'O Céu Explica Tudo', value: 'O Céu Explica Tudo'});
      this.musics.push({label: 'Aquela pessoa', value: 'Aquela pessoa'});
      this.musics.push({label: 'Modo Sofrimento', value: 'Modo Sofrimento'});
      this.musics.push({label: 'Bebida com Saudade', value: 'Bebida com Saudade'});

    this.listaMusicas.push(
      { 
        "titulo" : "O Céu Explica Tudo",
        "ano" : "2017",
        "imagem" : "../../assets/img/Henrique-e-Juliano-O-Céu-Explica-Tudo.jpg",
        "musicas" : [
          "Não Passa Vontade",
          "Meu Amor",
          "De Trás Pra Frente",
          "Vidinha de Balada",
          "O Céu Explica Tudo",
          "Aquela pessoa",
          "Modo Sofrimento",
          "Bebida com Saudade",
          "Maquiagem Não Disfarça",
          "Tinta de Amor",
          "Faz do Seu Jeito",
          "Na Boa",
          "Zé Ruela",
          "Vem Pra Minha Vida",
          "5KM",
          "Mais Amor e Menos Drama",
          "Amor Não é Só Love",
          "3 Horas de Motel",
          "Esse Filho é Meu"
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
            "Flor e o Beija-Flor",
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

  public getStatesAsObservable(token: string): Observable<any> {
      let query = new RegExp(token, 'ig');
  
      return Observable.of(
        this.statesComplex.filter((state: any) => {
          return query.test(state.artista);
        })
      );
    }

  public changeTypeaheadLoading(e: boolean): void {
      this.typeaheadLoading = e;
    }
  
  public changeTypeaheadNoResults(e: boolean): void {
    this.typeaheadNoResults = e;
  }

  public typeaheadOnSelect(e: TypeaheadMatch): void {
    console.log('Selected value: ', e.value);
    //alterar a discografia conforme artista selecionado
    /*if(e.value){
          this.listaMusicas = this.listaMusicas.filter(a => a. === selectedValue);
          this.cidades = this.listaCidades[0].cidades;
          //console.log(this.cidades);
          //this.navCtrl.push(CidadesDetalhePage);
        }*/
  }

 
  public votos():number {
      return Math.floor((Math.random() * 100) + 1);
  }

  handleChange(e) {
        var isChecked = e.checked;
    }

  ngOnInit() {
  }

}
