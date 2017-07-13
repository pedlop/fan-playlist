import { Component } from '@angular/core';


@Component({
    selector: 'app-ranking',
    templateUrl: './ranking.component.html',
    styleUrls: ['./ranking.component.css']
})
export class RankingComponent {

    data: any;
    public rankings: Array<Raking> = new Array<Raking>();

    constructor() {

        this.rankings.push(
            {
                colocacao:1,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:2,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:3,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:4,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:5,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:6,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:7,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:8,
                musica:'Vidinha de Balada',
                album:'O Céu Explica Tudo',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            }
        );



        
    }

    public barChartOptions:any = {
    scaleShowVerticalLines: false,
    responsive: true
    };
    public barChartLabels:string[] = ['Tinta de Amor', 'Vidinha de Balada', 'Aquela Pessoa', 'Flor e o Beija-Flor', 'Na Hora da Raiva', 'Contar pra Quê', 'Recaídas', 'É Fake'];
    public barChartType:string = 'bar';
    public barChartLegend:boolean = true;

    public x : number;
    public y : number;
    public x1 : number;
    public y1 : number;
    public x2 : number;
    public y2 : number;
    public x3 : number;
    public y3 : number;
    public x4 : number;
    public y4 : number;
    public x5 : number;
    public y5 : number;
    public x6 : number;
    public y6 : number;
    public x7 : number;
    public y7 : number;
    
    public barChartData:any[] = [
        {data: [this.x = 3785, this.x1 = 1424, this.x2 = 500, this.x3 = 995, this.x4 = 561, this.x5 = 40, this.x6 = 405, this.x7 = 2100], label: 'Votos Femininos'},
        {data: [this.y = 3247, this.y1 = 4100, this.y2 = 420, this.y3 = 1900, this.y4 = 860, this.y5 = 237, this.y6 = 90, this.y7 = 1021], label: 'Votos Masculinos'}
    ];

    /*public lineChartColors:Array<any> = [
        { // grey
        backgroundColor: 'rgba(148,159,177,0.2)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        },
        { // dark grey
        backgroundColor: 'rgba(77,83,96,0.2)',
        borderColor: 'rgba(77,83,96,1)',
        pointBackgroundColor: 'rgba(77,83,96,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(77,83,96,1)'
        },
        { // grey
        backgroundColor: 'rgba(148,159,177,0.2)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        }
    ];*/
    
    // events
    public chartClicked(e:any):void {
        console.log(e);
    }
    
    public chartHovered(e:any):void {
        console.log(e);
    }
    
    public randomize():void {
        // Only Change 3 values
        let data = [
        Math.round(Math.random() * 100),
        59,
        80,
        (Math.random() * 100),
        56,
        (Math.random() * 100),
        40];
        let clone = JSON.parse(JSON.stringify(this.barChartData));
        clone[0].data = data;
        this.barChartData = clone;
        /**
         * (My guess), for Angular to recognize the change in the dataset
         * it has to change the dataset variable directly,
         * so one way around it, is to clone the data, change it and then
         * assign it;
         */
        
    }
    public pieChartLabels:string[] = this.barChartLabels;
    public pieChartData:number[] = [this.x + this.y, this.x1 + this.y1, this.x2 + this.y2, this.x3 + this.y3, this.x4 + this.y4, this.x5 + this.y5, this.x6 + this.y6, this.x7 + this.y7];
    public pieChartType:string = 'pie';

    
}

class Raking {
    colocacao: number;
    musica: string;
    album: string;
    artista: string;
    fotoArtista: string;
    fotoAlbum: string;
}
